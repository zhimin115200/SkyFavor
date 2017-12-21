package com.zhimin115200.test.SkyFavor.service;

import com.zhimin115200.test.SkyFavor.common.exception.MailException;

public interface MailService {
	void sendVerifyCode(String email) throws MailException;
	boolean validateVerifyCode(String email, String verifyCode);
	boolean sendMail(String email,String contents);
}
