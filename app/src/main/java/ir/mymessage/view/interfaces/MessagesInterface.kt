package ir.mymessage.view.interfaces

import android.content.Context

import java.util.ArrayList

import ir.mymessage.model.local.MessageLocal

interface MessagesInterface {
    fun getContext(): Context
    fun addMessage(content: String, userId: String)
    fun setupMessagesActivity()
    fun displayMessages(messageArrayList: ArrayList<MessageLocal>)
    fun showSendingStatus()
    fun hideSendingStatus()
    fun showProgress()
    fun hideProgress()
}
