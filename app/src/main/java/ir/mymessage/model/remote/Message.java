package ir.mymessage.model.remote;

import com.google.gson.annotations.SerializedName;

public class Message {
    @SerializedName("content")
    private String content;
    @SerializedName("fcm_token")
    private String fcmToken;
    @SerializedName("friend_id")
    private String friendId;
    @SerializedName("nickname")
    private String nickname;
    @SerializedName("to_user_id")
    private String toUserId;
    @SerializedName("user_id")
    private String userId;

    public String getFriendId() {
        return this.friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToUserId() {
        return this.toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFcmToken() {
        return this.fcmToken;
    }

    public void setFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }
}
