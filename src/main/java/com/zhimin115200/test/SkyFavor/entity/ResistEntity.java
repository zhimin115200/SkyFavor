package com.zhimin115200.test.SkyFavor.entity;

import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

public class ResistEntity extends UserEntity implements Serializable {

	@NotEmpty(message="密码不能为空")
	private String verifyCode;

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	@Override
	public String toString() {
		return "{\"ResistEntity\":{"
				+ "\"email\":\"" + getEmail() + "\""
				+ ",\"passoword\":\"" + getPassword() + "\""
				+ "\"verifyCode\":\"" + verifyCode + "\""
				+ "}}";
	}
}
