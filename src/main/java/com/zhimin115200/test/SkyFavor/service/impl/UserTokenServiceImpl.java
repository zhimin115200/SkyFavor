package com.zhimin115200.test.SkyFavor.service.impl;

import com.zhimin115200.test.SkyFavor.common.util.Convert;
import com.zhimin115200.test.SkyFavor.persistent.UserTokenDao;
import com.zhimin115200.test.SkyFavor.persistent.domain.SF_User_Token;
import com.zhimin115200.test.SkyFavor.service.UserTokenService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.UUID;

@Service
@Transactional
public class UserTokenServiceImpl implements UserTokenService {

	@Resource
	private UserTokenDao tokenDao;

	@Override
	@Transactional(readOnly = true)
	public String get(String email) {
		SF_User_Token token = tokenDao.get(email);
		if(token!=null){
			return token.getToken();
		}
		return "";
	}

	@Override
	public String add(String email){
		SF_User_Token token = new SF_User_Token();
		token.setEmail(email);
		String tk = UUID.randomUUID().toString().replace("-", "");
		token.setToken(tk);
		if(tokenDao.add(token)){
			return tk;
		}
		return "";
	}
}
