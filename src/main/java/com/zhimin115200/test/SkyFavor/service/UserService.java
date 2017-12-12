package com.zhimin115200.test.SkyFavor.service;

import com.zhimin115200.test.SkyFavor.model.UserDto;

public interface UserService {
	boolean validateAccount(String email,String password);
	boolean isExist(String email);
	String createAccount(String email,String password);
	UserDto getUser(String email);
}
