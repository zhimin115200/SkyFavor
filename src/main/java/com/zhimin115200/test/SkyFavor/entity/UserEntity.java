package com.zhimin115200.test.SkyFavor.entity;

import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

public class UserEntity implements Serializable {

	@NotEmpty(message="email不能为空")
	private String email;
	@NotEmpty(message="密码不能为空")
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
		return "{\"ResistEntity\":{"
				+ "\"email\":\"" + email + "\""
				+ ",\"password\":\"" + password + "\""
				+ "}}";
	}
}
