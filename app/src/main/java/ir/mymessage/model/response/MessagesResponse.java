package ir.mymessage.model.response;

import com.google.gson.annotations.SerializedName;

public class MessagesResponse {
	@SerializedName("id")
	private String id;
	@SerializedName("user_id")
	private String userId;
	@SerializedName("nickname")
	private String nickname;
	@SerializedName("to_friend_user_id")
	private String toUserId;
	@SerializedName("content")
	private String content;
	@SerializedName("sent_at")
	private String sentAt;
	@SerializedName("updated_at")
	private String updatedAt;
	@SerializedName("created_at")
	private String createdAt;

	public MessagesResponse() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getToUserId() {
		return toUserId;
	}

	public void setToUserId(String toUserId) {
		this.toUserId = toUserId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSentAt() {
		return sentAt;
	}

	public void setSentAt(String sentAt) {
		this.sentAt = sentAt;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
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
			"MessagesResponse{" +
			"sent_at = '" + sentAt + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",user_id = '" + userId + '\'' + 
			",to_user_id = '" + toUserId + '\'' + 
			",nickname = '" + nickname + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id = '" + id + '\'' + 
			",content = '" + content + '\'' + 
			"}";
		}
}
