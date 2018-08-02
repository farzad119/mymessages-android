package ir.mymessage.utils;

import android.content.Context;
import android.content.SharedPreferences;
import ir.mymessage.model.User;

public class MySharedPrefrences {

    private SharedPreferences mPreferences;
    private Context context;

    public MySharedPrefrences(Context context) {
        this.context = context;
        this.mPreferences = this.context.getSharedPreferences("app_pref", Context.MODE_PRIVATE);
    }

    public void saveUserInfo(User user) {
        mPreferences.edit()
                .putInt("userId",user.getUserId())
                .putString("username", user.getUsername())
                .putString("nickname", user.getNickname())
                .putString("profileImage", user.getProfile_image())
                .apply();
    }

    public User getUserInfo() {
        User user = new User();
        user.setUserId(mPreferences.getInt("userId",0));
        user.setUsername(mPreferences.getString("username",""));
        user.setNickname(mPreferences.getString("nickname",""));
        user.setProfile_image(mPreferences.getString("profileImage",""));

        return user;
    }

}
