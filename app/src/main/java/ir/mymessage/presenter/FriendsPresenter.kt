package ir.mymessage.presenter

import android.util.Log

import java.util.ArrayList

import ir.mymessage.model.local.DialogLocal
import ir.mymessage.model.local.MessageLocal
import ir.mymessage.model.local.UserLocal
import ir.mymessage.model.remote.Friend
import ir.mymessage.model.remote.User
import ir.mymessage.model.response.DialogsResponse
import ir.mymessage.model.response.FriendsResponse
import ir.mymessage.utils.MySharedPrefrences
import ir.mymessage.view.base.BasePresenter
import ir.mymessage.view.interfaces.DialogsInterface
import ir.mymessage.view.interfaces.FriendsInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FriendsPresenter(private val friendsInterface: FriendsInterface) : BasePresenter() {

    fun getFriends() {
        friendsInterface.showProgress()
        apiService.allFriends(MySharedPrefrences(friendsInterface.getContext()).userInfo.userId?:"")
                .enqueue(object : Callback<ArrayList<FriendsResponse>> {
                    override fun onResponse(call: Call<ArrayList<FriendsResponse>>, response: Response<ArrayList<FriendsResponse>>) {
                        friendsInterface.hideProgress()
                        if (response.isSuccessful) {
                            friendsInterface.displayAllFriends(response.body()!!)
                            Log.w("FriendPresenter", "onResponse: succ")
                        } else {
                            Log.w("FriendPresenter", "onResponse: not succ")
                        }
                    }

                    override fun onFailure(call: Call<ArrayList<FriendsResponse>>, t: Throwable) {
                        friendsInterface.hideProgress()
                        Log.w("FriendPresenter", "onFailure: $t")
                    }
                })
    }

    fun addFriend(user: User) {
        friendsInterface.showProgress()
        val friend = Friend()
        friend.userId = MySharedPrefrences(friendsInterface.getContext()).userId
        friend.nickname = MySharedPrefrences(friendsInterface.getContext()).userInfo.nickname
        friend.friendUserId = user.userId
        friend.friendNickname = user.nickname

        apiService.addFriend(friend).enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                friendsInterface.hideProgress()
                if (response.isSuccessful) {
                    getFriends()
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                friendsInterface.hideProgress()
            }
        })
    }

    fun deleteFriend(friend: Friend) {
        friendsInterface.showProgress()
        apiService.deleteFriend(friend)
                .enqueue(object : Callback<String> {
                    override fun onResponse(call: Call<String>, response: Response<String>) {
                        friendsInterface.hideProgress()
                        if (response.isSuccessful) {
                            getFriends()
                            Log.w("FriendPresenter", "clickOnDeleteFriend onResponse: succ : ${response.body()}")
                        }
                    }

                    override fun onFailure(call: Call<String>, t: Throwable) {
                        friendsInterface.hideProgress()
                        Log.wtf("FriendPresenter", "clickOnDeleteFriend onFailure: $t")
                    }
                })
    }

    fun searchFriend(username: String) {
        friendsInterface.showProgress()
        apiService.searchFriend(username).enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                friendsInterface.hideProgress()
                if (response.isSuccessful) {
                    friendsInterface.showDialogAddFriend(response.body()!!)
                    Log.wtf("FriendPresenter", "onResponse: " + "suc")
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                friendsInterface.hideProgress()
                Log.wtf("FriendPresenter", "onResponse: failure because : $t")
            }
        })
    }
}
