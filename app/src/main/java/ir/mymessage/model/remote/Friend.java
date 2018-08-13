package ir.mymessage.model.remote;

import com.google.gson.annotations.SerializedName;

public class Friend {
    @SerializedName("user_id")
    private String userId;
    @SerializedName("nickname")
    private String nickname;
    @SerializedName("friend_id")
    private String friendId;
    @SerializedName("friend_nickname")
    private String friendNickname;
    @SerializedName("friend_user_id")
    private String friendUserId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

    public String getFriendNickname() {
        return friendNickname;
    }

    public void setFriendNickname(String friendNickname) {
        this.friendNickname = friendNickname;
    }

    public String getFriendUserId() {
        return friendUserId;
    }

    public void setFriendUserId(String friendUserId) {
        this.friendUserId = friendUserId;
    }
}
