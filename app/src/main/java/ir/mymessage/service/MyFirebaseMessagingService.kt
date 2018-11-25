/**
 * Copyright 2016 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ir.mymessage.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.mymessage.R
import android.net.Uri
import android.os.Build
import android.support.v4.app.NotificationCompat
import android.support.v4.content.LocalBroadcastManager
import android.util.Log

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

import org.json.JSONException
import org.json.JSONObject

import ir.mymessage.model.remote.PushMessage
import ir.mymessage.model.remote.UpdateFcmToken
import ir.mymessage.utils.MySharedPrefrences
import ir.mymessage.view.activities.DialogsActivity
import ir.mymessage.view.activities.LoginActivity
import ir.mymessage.view.activities.MessagesActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyFirebaseMessagingService : FirebaseMessagingService() {

    private val apiService = ApiClient.getRetrofit()!!.create(ApiInterface::class.java)
    private var broadcastManager: LocalBroadcastManager? = null

    override fun onCreate() {
        super.onCreate()
        broadcastManager = LocalBroadcastManager.getInstance(this)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        val notiData = JSONObject(remoteMessage?.data)
        val pushMessage = PushMessage()
        try {
            pushMessage.friendId = notiData.getString("friendId")
            pushMessage.title = notiData.getString("title")
            pushMessage.message = notiData.getString("message")
            pushMessage.userId = notiData.getString("userId")
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        val intent = Intent("messages")
        intent.putExtra("pushMessage", pushMessage.message)
        intent.putExtra("userId", pushMessage.userId)

        if (MessagesActivity.isMessageActivityRunning) {
            broadcastManager?.sendBroadcast(intent)
        } else {
            sendNotification(pushMessage)
        }

        Log.w(TAG, "onMessageReceived: ${remoteMessage?.data}")
    }

    private fun sendRegistrationToServer(token: String) {
        Log.w(TAG, "sendRegistrationToServer: user_id:" + MySharedPrefrences(this).userInfo.userId!!)
        apiService.updateFcmToken("11", token).enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    Log.w(TAG, "onResponse: " + response.body()!!)
                } else {
                    Log.w(TAG, "onResponse not success: " + response.raw())

                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.w(TAG, "onFailure: " + t.toString())

            }
        })
    }

    private fun sendNotification(pushMessage: PushMessage) {
        val intent = Intent(this, DialogsActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(this,
                0,
                intent,
                PendingIntent.FLAG_ONE_SHOT)

        val channelId = pushMessage.friendId
        val notificationBuilder = NotificationCompat.Builder(
                this, channelId!!)
                .setContentTitle(pushMessage.title)
                .setContentText(pushMessage.message)
                .setAutoCancel(true)
                .setSound(RingtoneManager.getDefaultUri(2))
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.shape_incoming_message)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= 26) {
            notificationManager.createNotificationChannel(NotificationChannel(channelId, "Channel human readable title", NotificationManager.IMPORTANCE_DEFAULT))
        }
        notificationManager.notify(Integer.valueOf(pushMessage.friendId), notificationBuilder.build())

        Log.wtf(TAG, "sendNotification: ${pushMessage.message}")

    }

    companion object {
        private val TAG = "MyFirebaseMsgService"
    }
}
