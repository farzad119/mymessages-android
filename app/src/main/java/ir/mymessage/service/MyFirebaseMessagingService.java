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

package ir.mymessage.service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.mymessage.R;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

import ir.mymessage.model.remote.PushMessage;
import ir.mymessage.model.remote.UpdateFcmToken;
import ir.mymessage.utils.MySharedPrefrences;
import ir.mymessage.view.activities.DialogsActivity;
import ir.mymessage.view.activities.LoginActivity;
import ir.mymessage.view.activities.MessagesActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    ApiInterface apiService = ApiClient.getRetrofit().create(ApiInterface.class);
    private LocalBroadcastManager broadcastManager;
    Intent intent;
    private static final String TAG = "MyFirebaseMsgService";

    public void onCreate() {
        super.onCreate();
        this.broadcastManager = LocalBroadcastManager.getInstance(this);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        JSONObject object = new JSONObject(remoteMessage.getData());
        PushMessage pushMessage = new PushMessage();
        try {
            pushMessage.setFriendId(object.getString("friendId"));
            pushMessage.setTitle(object.getString("title"));
            pushMessage.setMessage(object.getString("message"));
            pushMessage.setUserId(object.getString("userId"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        this.intent = new Intent("messages");
        this.intent.putExtra("pushMessage", pushMessage.getMessage());
        this.intent.putExtra("userId", pushMessage.getUserId());
        if (MessagesActivity.isMessageActivityRunning) {
            this.broadcastManager.sendBroadcast(this.intent);
        } else {
            sendNotification(pushMessage);
        }

        Log.wtf(TAG, "onMessageReceived: "+ remoteMessage.getData());
    }

    private void sendRegistrationToServer(String token) {
        Log.wtf(TAG, "sendRegistrationToServer: user_id:"+new MySharedPrefrences(this).getUserInfo().getUserId());
        apiService.updateFcmToken("11",token).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()){
                    Log.wtf(TAG, "onResponse: "+response.body());
                }else {
                    Log.wtf(TAG, "onResponse not success: "+response.raw());

                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.wtf(TAG, "onFailure: "+t.toString());

            }
        });
    }

    private void sendNotification(PushMessage pushMessage) {
        Intent intent = new Intent(this, DialogsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        String channelId = pushMessage.getFriendId();
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(
                this, channelId)
                .setContentTitle(pushMessage.getTitle())
                .setContentText(pushMessage.getMessage())
                .setAutoCancel(true)
                .setSound(RingtoneManager.getDefaultUri(2))
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.shape_incoming_message);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= 26) {
            notificationManager.createNotificationChannel(new NotificationChannel(channelId, "Channel human readable title", NotificationManager.IMPORTANCE_DEFAULT));
        }
        notificationManager.notify(Integer.valueOf(pushMessage.getFriendId()), notificationBuilder.build());

        Log.wtf(TAG, "sendNotification: "+ pushMessage.getMessage());

    }
}
