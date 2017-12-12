package com.zhimin115200.test.SkyFavor.persistent;

import com.zhimin115200.test.SkyFavor.persistent.domain.SF_User;

import java.util.List;

public interface UserDao {
	SF_User get(String email);
	boolean add(SF_User user);
	boolean delete(String email);
	boolean modify(SF_User user);
	List<SF_User> getAll();
}
