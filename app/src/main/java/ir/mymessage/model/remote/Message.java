package ir.mymessage.model.remote;

import com.google.gson.annotations.SerializedName;

public class Message {

    @SerializedName("user_id")
    private String userId;
	@SerializedName("to_user_id")
	private String toUserId;
    @SerializedName("friend_id")
	private String friendId;
	@SerializedName("nickname")
	private String nickname;
	@SerializedName("content")
	private String content;

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
