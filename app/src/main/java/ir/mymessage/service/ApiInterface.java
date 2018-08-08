package ir.mymessage.service;

import java.util.ArrayList;

import ir.mymessage.model.remote.Message;
import ir.mymessage.model.remote.UpdateFcmToken;
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
    @POST("allfriends")
    Call<ArrayList<FriendsResponse>> dialogs(@Query("user_id") String str);

    @POST("login")
    Call<LoginResponse> login(@Body User user);

    @POST("messages")
    Call<ArrayList<MessagesResponse>> messages(@Query("friend_id") String str);

    @POST("push-notification")
    Call<String> pushMessage(@Body Message message);

    @POST("message")
    Call<SendMessageResponse> sendMessage(@Body Message message);

    @POST("updatefcmtoken")
    Call<String> updateFcmToken(@Query("user_id") String str, @Query("fcm_token") String str2);
}
