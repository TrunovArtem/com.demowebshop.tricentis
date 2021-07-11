package API.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthCreateTokenPojo{

	@JsonProperty("password")
	private String password;

	@JsonProperty("username")
	private String username;

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	public void setUserName(String username){
		this.username = username;
	}

	public String getUserName(){
		return username;
	}
}