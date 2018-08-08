package ir.mymessage.model.response;

import com.google.gson.annotations.SerializedName;

public class FriendsResponse {
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("fcm_token")
    private String fcmToken;
    @SerializedName("friend_id")
    private String friendId;
    @SerializedName("friend_nickname")
    private String friendNickname;
    @SerializedName("friend_user_id")
    private String friendUserId;
    @SerializedName("nickname")
    private String nickname;
    @SerializedName("updated_at")
    private String updatedAt;
    @SerializedName("user_id")
    private String userId;

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getFriendId() {
        return this.friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

    public String getFriendUserId() {
        return this.friendUserId;
    }

    public void setFriendUserId(String friendUserId) {
        this.friendUserId = friendUserId;
    }

    public String getFriendNickname() {
        return this.friendNickname;
    }

    public void setFriendNickname(String friendNickname) {
        this.friendNickname = friendNickname;
    }

    public String getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getFcmToken() {
        return this.fcmToken;
    }

    public void setFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }
}
