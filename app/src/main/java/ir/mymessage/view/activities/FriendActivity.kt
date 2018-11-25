package ir.mymessage.view.activities

import android.content.Context
import android.mymessage.R
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import ir.mymessage.adapter.FriendsAdapter
import ir.mymessage.model.remote.Friend
import ir.mymessage.model.remote.User
import ir.mymessage.model.response.FriendsResponse
import ir.mymessage.presenter.FriendsPresenter
import ir.mymessage.view.base.BaseActivity
import ir.mymessage.view.interfaces.FriendsInterface
import kotlinx.android.synthetic.main.activity_friend.*
import java.util.*

class FriendActivity : BaseActivity(), FriendsInterface {
    internal var presenter: FriendsPresenter? = null

    override fun getContentViewRes(): Int {
        return R.layout.activity_friend
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = FriendsPresenter(this)
        setupMessagesActivity()
    }

    override fun setupMessagesActivity() {
        rv_friends.layoutManager = LinearLayoutManager(this)
        presenter?.getFriends()

        btn_search.setOnClickListener { clickOnSearch() }
    }

    override fun clickOnAddFriend(userId: String, friendUserId: String) {

    }

    override fun clickOnDeleteFriend(userId: String, friendId: String) {
        val friend = Friend()
        friend.userId = userId
        friend.friendId = friendId
        presenter?.deleteFriend(friend)
    }

    override fun showDialogAddFriend(user: User) {
        val dialog = AlertDialog.Builder(this)
        dialog.setMessage("Are you sure add ${user.nickname} to your friends?")
        dialog.setPositiveButton("yes") { dialog, which -> presenter?.addFriend(user) }
        dialog.setNegativeButton("Cancel") { dialog, which -> finish() }
        dialog.show()
    }

    override fun showProgress() {
        rtl_progress.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        rtl_progress.visibility = View.GONE
    }

    override fun displayAllFriends(friendArrayList: ArrayList<FriendsResponse>) {
        val adapter = FriendsAdapter(this, friendArrayList, R.layout.item_friend)
        rv_friends.adapter = adapter
    }

    override fun clickOnSearch() {
        presenter?.searchFriend(getTextUsername())
    }

    override fun getTextUsername(): String {
        return et_search.text.trim().toString()
    }

    override fun getContext(): Context {
        return this
    }
}
