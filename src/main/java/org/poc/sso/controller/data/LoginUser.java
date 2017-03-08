package org.poc.sso.controller.data;

import java.io.Serializable;

public class LoginUser implements Serializable{

	private static final long serialVersionUID = 3172495161743784319L;
	
	private String username;
	private String password;
	
	
 
	
	public LoginUser(){
		super();
	}




	public String getUsername() {
		return username;
	}




	public void setUsername(String username) {
		this.username = username;
	}




	public String getPassword() {
		return password;
	}




	public LoginUser(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}




	public void setPassword(String password) {
		this.password = password;
	}
	
	 
	

}
