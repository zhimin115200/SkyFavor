package com.zhimin115200.test.SkyFavor.persistent;

import com.zhimin115200.test.SkyFavor.persistent.domain.SF_User_Token;

public interface UserTokenDao {
	SF_User_Token get(String tk);
	boolean add(SF_User_Token token);
}
