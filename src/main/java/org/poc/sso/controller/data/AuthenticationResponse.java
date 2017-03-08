package org.poc.sso.controller.data;

import java.io.Serializable;

public class AuthenticationResponse implements Serializable {
	
	private String clientURL;

	 
	public String getClientURL() {
		return clientURL;
	}

	public void setClientURL(String clientURL) {
		this.clientURL = clientURL;
	}

	private static final long serialVersionUID = -8209130678452168595L;
	
	public AuthenticationResponse(String token,String clientURL) {
		super();
		this.token = token;
		this.clientURL = clientURL;
	}
	
	public AuthenticationResponse(){
		super();
	}

	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	

}
