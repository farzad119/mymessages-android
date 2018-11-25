package ir.mymessage.view.activities

import android.content.Context
import android.content.Intent
import android.mymessage.R

import butterknife.BindView
import ir.mymessage.model.local.DialogLocal
import ir.mymessage.presenter.DialogsPresenter
import ir.mymessage.utils.MySharedPrefrences
import ir.mymessage.view.base.BaseActivity
import ir.mymessage.view.interfaces.DialogsInterface

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout

import com.bumptech.glide.Glide
import com.stfalcon.chatkit.commons.ImageLoader
import com.stfalcon.chatkit.dialogs.DialogsList
import com.stfalcon.chatkit.dialogs.DialogsListAdapter
import kotlinx.android.synthetic.main.activity_dialogs.*

import java.util.ArrayList

class DialogsActivity : BaseActivity(), DialogsInterface {

    var presenter: DialogsPresenter? = null
    var dialogsListAdapter: DialogsListAdapter<DialogLocal>? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = DialogsPresenter(this)
        setupDialogsActivity()
    }

    override fun onResume() {
        super.onResume()
        presenter?.getDialogs()
    }

    override fun startFriendsActivity() {
        startActivity(Intent(this@DialogsActivity, FriendActivity::class.java))
    }

    override fun startMessagesActivity(dialogLocal: DialogLocal) {
        val intent = Intent(this, MessagesActivity::class.java)
        intent.putExtra("friend_id", dialogLocal.id)
        intent.putExtra("friend_nickname", dialogLocal.dialogName)
        intent.putExtra("friend_user_id", dialogLocal.users[0].id)
        intent.putExtra("friend_fcm_token", dialogLocal.users[0].fcmToken)
        startActivity(intent)
    }

    override fun displayDialogs(dialogArrayList: ArrayList<DialogLocal>) {
        dialogsListAdapter?.clear()
        dialogsList?.setAdapter(dialogsListAdapter)
        dialogsListAdapter?.addItems(dialogArrayList)
    }

    override fun showProgress() {
        rtl_progress?.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        rtl_progress?.visibility = View.GONE
    }

    override fun getContext(): Context {
        return this@DialogsActivity
    }

    override fun setupDialogsActivity() {
        dialogsListAdapter = DialogsListAdapter(
                ImageLoader {
                    imageView, url -> Glide.with(this@DialogsActivity).load(url).into(imageView)
                }
        )

        if (!MySharedPrefrences(this).isSavedFcmToken) {
            presenter?.updateFcmToken()
        }
        presenter?.getDialogs()
        dialogsListAdapter?.setOnDialogClickListener { dialog -> startMessagesActivity(dialog) }
        btn_addfriend!!.setOnClickListener { startFriendsActivity() }
    }

    override fun getContentViewRes(): Int {
        return R.layout.activity_dialogs
    }
}