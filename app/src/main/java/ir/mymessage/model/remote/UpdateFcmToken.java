package ir.mymessage.model.remote;

import com.google.gson.annotations.SerializedName;

public class UpdateFcmToken {
    @SerializedName("user_id")
    private String userId;
    @SerializedName("user_id")
    private String token;

    public UpdateFcmToken() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
