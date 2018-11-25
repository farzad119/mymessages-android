package ir.mymessage.presenter

import android.app.NotificationManager
import android.content.Context
import android.mymessage.R
import android.os.Handler
import android.util.Log

import com.vanniktech.emoji.EmojiPopup

import java.util.ArrayList

import ir.mymessage.model.local.MessageLocal
import ir.mymessage.model.local.UserLocal
import ir.mymessage.model.remote.Message
import ir.mymessage.model.response.MessagesResponse
import ir.mymessage.model.response.SendMessageResponse
import ir.mymessage.utils.GeneralUtils
import ir.mymessage.utils.MySharedPrefrences
import ir.mymessage.view.base.BasePresenter
import ir.mymessage.view.interfaces.DialogsInterface
import ir.mymessage.view.interfaces.MessagesInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MessagesPresenter(private val messagesInterface: MessagesInterface) : BasePresenter() {

    private val generalUtils = GeneralUtils(messagesInterface.getContext())

    fun getMessages(friendId: String) {
        messagesInterface.showProgress()
        apiService.messages(friendId).enqueue(object : Callback<ArrayList<MessagesResponse>> {
            override fun onResponse(call: Call<ArrayList<MessagesResponse>>, response: Response<ArrayList<MessagesResponse>>) {
                messagesInterface.hideProgress()
                if (response.isSuccessful && response.body()?.size ?:0 > 0) {
                    val messageArrayList = ArrayList<MessageLocal>()
                    for (messagesResponse in response.body().orEmpty()) {
                        val user = UserLocal(messagesResponse.userId,
                                "my name",
                                null,
                                false,
                                "")
                        val message = MessageLocal(messagesResponse.id,
                                user,
                                messagesResponse.content,
                                generalUtils.dateParser(messagesResponse.createdAt))

                        messageArrayList.add(message)
                    }
                    messagesInterface.displayMessages(messageArrayList)
                }
            }

            override fun onFailure(call: Call<ArrayList<MessagesResponse>>, t: Throwable) {
                Log.wtf("MessagePresenter", "onFailure")
            }
        })
    }

    fun sendMessage(content: String, fcmToken: String, friendId: String, toUserId: String) {
        val message = Message()
        message.userId = MySharedPrefrences(messagesInterface.getContext()).userInfo.userId
        message.toUserId = toUserId
        message.friendId = friendId
        message.nickname = MySharedPrefrences(messagesInterface.getContext()).userInfo.nickname
        message.content = content

        apiService.sendMessage(message).enqueue(object : Callback<SendMessageResponse> {
            override fun onResponse(call: Call<SendMessageResponse>, response: Response<SendMessageResponse>) {
                if (response.isSuccessful) {
                    messagesInterface.hideSendingStatus()
                    pushMessage(content, fcmToken, friendId, toUserId)
                }
            }

            override fun onFailure(call: Call<SendMessageResponse>, t: Throwable) {
                Log.w("MessagePresenter", "onFailure : $t")
            }
        })
    }

    fun pushMessage(content: String, fcmToken: String, friendId: String, toUserId: String) {
        val message = Message()
        message.userId = MySharedPrefrences(this.messagesInterface.getContext()).userInfo.userId
        message.toUserId = toUserId
        message.friendId = friendId
        message.nickname = MySharedPrefrences(this.messagesInterface.getContext()).userInfo.nickname
        message.content = content
        message.fcmToken = fcmToken

        this.apiService.pushMessage(message).enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                Log.w("MessagePresenter", "onResponse: ${response.body()}")
            }

            override fun onFailure(call: Call<String>, t: Throwable) {

            }
        })
    }

    fun clearNotification(friendId: String) {
        val notificationManager = messagesInterface.getContext().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.cancel(Integer.valueOf(friendId))
    }
}
