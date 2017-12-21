package com.zhimin115200.test.SkyFavor.model;

import java.io.Serializable;
import java.util.Date;

public class UserDto implements Serializable {


	private String email;
	private String token;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
