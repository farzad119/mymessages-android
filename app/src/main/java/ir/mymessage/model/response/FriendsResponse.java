package ir.mymessage.model.response;

import com.google.gson.annotations.SerializedName;

public class FriendsResponse {
    @SerializedName("user_id")
    private String userId;
    @SerializedName("nickname")
	private String nickname;
    @SerializedName("friend_id")
	private String friendId;
    @SerializedName("friend_nickname")
	private String friendNickname;
    @SerializedName("updated_at")
    private String updatedAt;
    @SerializedName("friended_at")
    private String friendedAt;
    @SerializedName("created_at")
    private String createdAt;

	public FriendsResponse() {
	}

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

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getFriendedAt() {
		return friendedAt;
	}

	public void setFriendedAt(String friendedAt) {
		this.friendedAt = friendedAt;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	@Override
 	public String toString(){
		return 
			"FriendsResponse{" +
			"friend_id = '" + friendId + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",user_id = '" + userId + '\'' + 
			",nickname = '" + nickname + '\'' + 
			",friended_at = '" + friendedAt + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",friend_nickname = '" + friendNickname + '\'' +
			"}";
		}
}
