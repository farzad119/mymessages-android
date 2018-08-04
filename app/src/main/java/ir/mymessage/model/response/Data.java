package ir.mymessage.model.response;

public class Data{
	private String friendId;
	private String updatedAt;
	private String userId;
	private String toFriendUserId;
	private String nickname;
	private String createdAt;
	private int id;
	private String content;

	public void setFriendId(String friendId){
		this.friendId = friendId;
	}

	public String getFriendId(){
		return friendId;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
	}

	public void setToFriendUserId(String toFriendUserId){
		this.toFriendUserId = toFriendUserId;
	}

	public String getToFriendUserId(){
		return toFriendUserId;
	}

	public void setNickname(String nickname){
		this.nickname = nickname;
	}

	public String getNickname(){
		return nickname;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setContent(String content){
		this.content = content;
	}

	public String getContent(){
		return content;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"friend_id = '" + friendId + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",user_id = '" + userId + '\'' + 
			",to_friend_user_id = '" + toFriendUserId + '\'' + 
			",nickname = '" + nickname + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id = '" + id + '\'' + 
			",content = '" + content + '\'' + 
			"}";
		}
}
