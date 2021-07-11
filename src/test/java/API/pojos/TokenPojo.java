package API.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TokenPojo{

	@JsonProperty("token")
	private String token;

	public void setToken(String token){
		this.token = token;
	}

	public String getToken(){
		return token;
	}
}