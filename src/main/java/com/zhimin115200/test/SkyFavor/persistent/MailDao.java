package com.zhimin115200.test.SkyFavor.persistent;

import com.zhimin115200.test.SkyFavor.persistent.domain.SF_Mail;

import java.util.List;

public interface MailDao {
	SF_Mail get(String mailId);
	boolean add(SF_Mail mail);
	boolean delete(String mailId);
	boolean modify(SF_Mail mail);
	List<SF_Mail> getAll();
	SF_Mail getLatest(String mail,Integer type);
}
