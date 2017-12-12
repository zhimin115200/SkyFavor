package com.zhimin115200.test.SkyFavor.service.impl;

import com.zhimin115200.test.SkyFavor.model.UserDto;
import com.zhimin115200.test.SkyFavor.service.UserService;
import com.zhimin115200.test.SkyFavor.persistent.DemoDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Resource
	private DemoDao dao;

	@Override
	@Transactional(readOnly = true)
	public String validateAccount(String email, String password) {
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public String isExist(String email) {
		return null;
	}

	@Override
	public String createAccount(String email, String password) {
		return null;
	}

	@Override
	public UserDto getUser(String email) {
		return null;
	}
}
