package ir.mymessage.service

import java.util.ArrayList

import ir.mymessage.model.remote.Friend
import ir.mymessage.model.remote.Message
import ir.mymessage.model.response.DialogsResponse
import ir.mymessage.model.response.FriendsResponse
import ir.mymessage.model.response.LoginResponse
import ir.mymessage.model.remote.User
import ir.mymessage.model.response.MessagesResponse
import ir.mymessage.model.response.SendMessageResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiInterface {

    @POST("login")
    fun login(@Body user: User): Call<LoginResponse>

    @POST("signup")
    fun signup(@Body user: User): Call<LoginResponse>

    @POST("messages")
    fun messages(@Query("friend_id") str: String): Call<ArrayList<MessagesResponse>>

    @POST("message")
    fun sendMessage(@Body message: Message): Call<SendMessageResponse>

    @POST("push-notification")
    fun pushMessage(@Body message: Message): Call<String>

    @POST("allfriends")
    fun dialogs(@Query("user_id") str: String): Call<ArrayList<DialogsResponse>>

    @POST("all-friends")
    fun allFriends(@Query("user_id") str: String): Call<ArrayList<FriendsResponse>>

    @POST("add-friend")
    fun addFriend(@Body friend: Friend): Call<String>

    @POST("delete-friend")
    fun deleteFriend(@Body friend: Friend): Call<String>

    @POST("search-friend")
    fun searchFriend(@Query("username") str: String): Call<User>

    @POST("updatefcmtoken")
    fun updateFcmToken(@Query("user_id") str: String, @Query("fcm_token") str2: String): Call<String>
}
