package ir.mymessage.utils;

import android.content.Context;
import android.content.SharedPreferences;

import ir.mymessage.model.remote.User;

public class MySharedPrefrences {

    private SharedPreferences mPreferences;
    private Context context;

    public MySharedPrefrences(Context context) {
        this.context = context;
        this.mPreferences = this.context.getSharedPreferences("app_pref", Context.MODE_PRIVATE);
    }

    public void saveUserInfo(User user) {
        mPreferences.edit()
                .putString("userId",user.getUserId())
                .putString("username", user.getUsername())
                .putString("nickname", user.getNickname())
                .putString("profileImage", user.getProfileImage())
                .apply();
    }

    public User getUserInfo() {
        User user = new User();
        user.setUserId(mPreferences.getString("userId",""));
        user.setUsername(mPreferences.getString("username",""));
        user.setNickname(mPreferences.getString("nickname",""));
        user.setProfileImage(mPreferences.getString("profileImage",""));

        return user;
    }

    public void login(){
        mPreferences.edit()
                .putBoolean("isLoggedIn",true)
                .apply();
    }

    public Boolean isLoggedIn(){
        return mPreferences.getBoolean("isLoggedIn",false);
    }

    public String getUserId(){
        return mPreferences.getString("userId","");
    }

    public void saveFcmToken(){
        mPreferences.edit()
                .putBoolean("isSavedFcmToken",true)
                .apply();
    }

    public boolean isSavedFcmToken(){
        return mPreferences.getBoolean("isSavedFcmToken",false);
    }
}
