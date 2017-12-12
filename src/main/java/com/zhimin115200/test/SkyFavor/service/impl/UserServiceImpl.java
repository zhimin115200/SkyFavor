package com.zhimin115200.test.SkyFavor.service.impl;

import com.zhimin115200.test.SkyFavor.common.Constant;
import com.zhimin115200.test.SkyFavor.common.exception.UserException;
import com.zhimin115200.test.SkyFavor.common.response.ResponseConstant;
import com.zhimin115200.test.SkyFavor.common.util.Convert;
import com.zhimin115200.test.SkyFavor.model.UserDto;
import com.zhimin115200.test.SkyFavor.persistent.UserDao;
import com.zhimin115200.test.SkyFavor.persistent.domain.SF_User;
import com.zhimin115200.test.SkyFavor.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Resource
	private UserDao userDao;

	@Override
	@Transactional(readOnly = true)
	public boolean validateAccount(String email, String password) {
		SF_User user = userDao.get(email);
		if(user!=null
				&&StringUtils.equals(user.getPassword(),password)){
			return true;
		}
		return false;
	}

	@Override
	@Transactional(readOnly = true)
	public boolean isExist(String email) {
		SF_User user = userDao.get(email);
		if(user!=null){
			return true;
		}
		return false;
	}

	@Override
	public void createAccount(String email, String password) throws UserException{
		if(!isExist(email)){
			SF_User user = new SF_User();
			user.setEmail(email);
			user.setPassword(password);
			userDao.add(user);
		}else{
			throw new UserException(Constant.ACCOUNT_IS_EXIST);
		}
	}

	@Override
	public UserDto getUser(String email) {
		SF_User user = userDao.get(email);
		if(user!=null){
			return Convert.toDto(user);
		}
		return null;
	}
}
