package com.zhimin115200.test.SkyFavor.service;

import com.zhimin115200.test.SkyFavor.common.exception.UserException;
import com.zhimin115200.test.SkyFavor.model.UserDto;

public interface UserService {
	boolean validateAccount(String email,String password);
	boolean isExist(String email);
	void createAccount(String email,String password) throws UserException;
	UserDto getUser(String email);
}
