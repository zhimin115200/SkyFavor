package com.zhimin115200.test.SkyFavor.service;

public interface MailService {
	boolean sendVerifyCode(String email);
	boolean validateVerifyCode(String email, String verifyCode);
	boolean sendMail(String email,String contents);
}
