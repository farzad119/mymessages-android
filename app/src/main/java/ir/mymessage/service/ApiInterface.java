package ir.mymessage.service;

import java.util.ArrayList;

import ir.mymessage.model.response.FriendsResponse;
import ir.mymessage.model.response.LoginResponse;
import ir.mymessage.model.remote.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {
    @POST("login")
    Call<LoginResponse> login(@Body User user);

    @POST("allfriends")
    Call<ArrayList<FriendsResponse>> dialogs(@Query("user_id") String userId);
}
