package ir.mymessage.model.remote;

import com.google.gson.annotations.SerializedName;

public class UpdateFcmToken {
    @SerializedName("user_id")
    private String token;
    @SerializedName("user_id")
    private String userId;

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}