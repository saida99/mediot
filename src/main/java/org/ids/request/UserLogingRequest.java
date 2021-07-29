package org.ids.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
public class UserLogingRequest {
	
	private String email;
	private String password;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "UserLogingRequest [email=" + email + ", password=" + password + "]";
	}
	public UserLogingRequest(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	public UserLogingRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
