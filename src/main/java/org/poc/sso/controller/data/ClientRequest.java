package org.poc.sso.controller.data;

import java.io.Serializable;

public class ClientRequest implements Serializable{

	 
	private static final long serialVersionUID = -2088635930249835955L;
	
	private String clientURL;
	
	private String token;
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public ClientRequest(){
		// DO Nothing
	}
	
	public ClientRequest(String clientURL) {
		super();
		this.clientURL = clientURL;
	}

	public String getClientURL() {
		return clientURL;
	}

	public void setClientURL(String clientURL) {
		this.clientURL = clientURL;
	}

	
	
	

}
