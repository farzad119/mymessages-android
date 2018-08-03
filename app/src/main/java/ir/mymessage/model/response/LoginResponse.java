package ir.mymessage.model.response;

import ir.mymessage.model.remote.User;

public class LoginResponse{
	private User user;
	private Boolean status;

	public void setUser(User user){
		this.user = user;
	}

	public User getUser(){
		return user;
	}

	public void setStatus(Boolean status){
		this.status = status;
	}

	public Boolean getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"LoginResponse{" + 
			"user = '" + user + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}
