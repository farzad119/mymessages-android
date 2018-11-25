package ir.mymessage.view.activities

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.PorterDuff
import android.mymessage.R
import android.os.Bundle
import android.os.Handler
import android.support.v4.content.ContextCompat
import android.support.v4.content.LocalBroadcastManager
import android.view.View
import com.stfalcon.chatkit.commons.ImageLoader
import com.stfalcon.chatkit.messages.MessagesListAdapter
import com.vanniktech.emoji.EmojiPopup
import ir.mymessage.model.local.MessageLocal
import ir.mymessage.model.local.UserLocal
import ir.mymessage.presenter.MessagesPresenter
import ir.mymessage.utils.MySharedPrefrences
import ir.mymessage.view.base.BaseActivity
import ir.mymessage.view.interfaces.MessagesInterface
import kotlinx.android.synthetic.main.activity_messages.*
import java.util.*

class MessagesActivity : BaseActivity(), MessagesInterface {
    private var emojiPopup: EmojiPopup? = null
    private var presenter: MessagesPresenter? = null
    private var adapter: MessagesListAdapter<MessageLocal>? = null
    private lateinit var broadcastReceiver: BroadcastReceiver
    private var friendFcmToken: String? = null
    private var friendId: String? = null
    private var friendNickname: String? = null
    private var friendUserId: String? = null

    override fun getContentViewRes(): Int {
        return R.layout.activity_messages
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        friendId = intent.getStringExtra("friend_id")
        friendUserId = intent.getStringExtra("friend_user_id")
        friendNickname = intent.getStringExtra("friend_nickname")
        friendFcmToken = intent.getStringExtra("friend_fcm_token")
        presenter = MessagesPresenter(this)
        setupMessagesActivity()
    }

    override fun addMessage(content: String, userId: String) {
        if (this.adapter != null) {
            this.adapter!!.addToStart(MessageLocal("", UserLocal(userId, null, null, false, ""), content), true)
        }
    }

    override fun setupMessagesActivity() {
        adapter = MessagesListAdapter(MySharedPrefrences(this).userInfo.userId, ImageLoader { imageView, url ->
            //Glide.with(MessagesActivity.this).load(url).into(imageView);
        })

        adapter?.setOnMessageLongClickListener { message ->
            main_activity_chat_bottom_message_edittext.setText(
                    message.text + "\n⬇\n" + main_activity_chat_bottom_message_edittext.text.toString().trim()
            )

            main_activity_chat_bottom_message_edittext.setSelection(
                    main_activity_chat_bottom_message_edittext.text.length
            )
        }

        presenter?.getMessages(friendId ?: "")

        broadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                addMessage(intent.getStringExtra("pushMessage"), intent.getStringExtra("userId"))
            }
        }

        presenter?.clearNotification(friendId ?: "")
        tv_title.text = friendNickname

        main_activity_emoji.setColorFilter(ContextCompat.getColor(this, R.color.white60), PorterDuff.Mode.SRC_IN)
        main_activity_send.setColorFilter(ContextCompat.getColor(this, R.color.white60), PorterDuff.Mode.SRC_IN)

        emojiPopup = EmojiPopup.Builder.fromRootView(rtl_progress).build(main_activity_chat_bottom_message_edittext)

        main_activity_emoji.setOnClickListener { emojiPopup?.toggle() }

        main_activity_send.setOnClickListener {
            if (!main_activity_chat_bottom_message_edittext.text.toString().isEmpty()) {
                val message = main_activity_chat_bottom_message_edittext.text.toString()
                main_activity_chat_bottom_message_edittext.setText("")
                this@MessagesActivity.addMessage(message, MySharedPrefrences(this).userInfo.userId?:"")
                //val handler = Handler()
                //val r = Runnable {
                    presenter?.sendMessage(message, friendFcmToken ?: "",
                            friendId ?: "",
                            friendUserId ?: "")
                //}
                //handler.postDelayed(r, 1)
            }
        }
    }

    override fun displayMessages(messageArrayList: ArrayList<MessageLocal>) {
        messagesList.setAdapter(adapter)
        adapter?.addToEnd(messageArrayList, false)
    }

    override fun showSendingStatus() {
        tv_sending_message.visibility = View.VISIBLE
    }

    override fun hideSendingStatus() {
        tv_sending_message.visibility = View.GONE
    }

    override fun showProgress() {
        rtl_progress.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        rtl_progress.visibility = View.GONE
    }

    override fun getContext(): Context {
        return this@MessagesActivity
    }

    public override fun onStart() {
        super.onStart()
        isMessageActivityRunning = true
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver, IntentFilter("messages"))
    }

    public override fun onStop() {
        super.onStop()
        isMessageActivityRunning = false
    }

    companion object {
        var isMessageActivityRunning = false
    }
}
