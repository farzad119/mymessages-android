package ir.mymessage.service;

import java.util.ArrayList;

import ir.mymessage.model.remote.Friend;
import ir.mymessage.model.remote.Message;
import ir.mymessage.model.response.DialogsResponse;
import ir.mymessage.model.response.FriendsResponse;
import ir.mymessage.model.response.LoginResponse;
import ir.mymessage.model.remote.User;
import ir.mymessage.model.response.MessagesResponse;
import ir.mymessage.model.response.SendMessageResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    /*@POST("dialogs")
    Call<ArrayList<DialogsResponse>> dialogs(@Query("user_id") String str);*/

    @POST("login")
    Call<LoginResponse> login(@Body User user);
    @POST("signup")
    Call<LoginResponse> signup(@Body User user);

    @POST("messages")
    Call<ArrayList<MessagesResponse>> messages(@Query("friend_id") String str);

    @POST("message")
    Call<SendMessageResponse> sendMessage(@Body Message message);

    @POST("push-notification")
    Call<String> pushMessage(@Body Message message);

    @POST("allfriends")
    Call<ArrayList<DialogsResponse>> dialogs(@Query("user_id") String str);
    @POST("all-friends")
    Call<ArrayList<FriendsResponse>> allFriends(@Query("user_id") String str);
    @POST("add-friend")
    Call<String> addFriend(@Body Friend friend);
    @POST("delete-friend")
    Call<String> deleteFriend(@Body Friend friend);
    @POST("search-friend")
    Call<User> searchFriend(@Query("username") String str);

    @POST("updatefcmtoken")
    Call<String> updateFcmToken(@Query("user_id") String str, @Query("fcm_token") String str2);
}
