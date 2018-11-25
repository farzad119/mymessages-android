package ir.mymessage.view.interfaces

import android.content.Context
import ir.mymessage.model.remote.User
import ir.mymessage.model.response.FriendsResponse

import java.util.ArrayList

interface FriendsInterface {
    fun setupMessagesActivity()

    fun displayAllFriends(friendArrayList: ArrayList<FriendsResponse>)

    fun clickOnSearch()

    fun clickOnAddFriend(userId: String, friendUserId: String)

    fun clickOnDeleteFriend(userId: String, friendId: String)

    fun showDialogAddFriend(user: User)

    fun showProgress()

    fun hideProgress()

    fun getTextUsername(): String

    fun getContext(): Context
}
