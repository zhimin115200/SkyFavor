package com.zhimin115200.test.SkyFavor.service.impl;

import com.zhimin115200.test.SkyFavor.common.Constant;
import com.zhimin115200.test.SkyFavor.common.exception.UserException;
import com.zhimin115200.test.SkyFavor.common.response.ResponseConstant;
import com.zhimin115200.test.SkyFavor.common.util.Convert;
import com.zhimin115200.test.SkyFavor.model.UserDto;
import com.zhimin115200.test.SkyFavor.persistent.MailDao;
import com.zhimin115200.test.SkyFavor.persistent.UserDao;
import com.zhimin115200.test.SkyFavor.persistent.domain.SF_Mail;
import com.zhimin115200.test.SkyFavor.persistent.domain.SF_User;
import com.zhimin115200.test.SkyFavor.service.MailService;
import com.zhimin115200.test.SkyFavor.service.UserService;
import com.zhimin115200.test.SkyFavor.service.worker.MailWorker;
import com.zhimin115200.test.SkyFavor.service.worker.WorkerManager;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.UUID;

@Service
@Transactional
public class MailServiceImpl implements MailService {

	@Resource
	private MailDao mailDao;
	@Resource
	private WorkerManager workerManager;

	@Override
	public boolean sendVerifyCode(String email) {
		String verifyCode = "";//随机6位数
		String content = String.format(Constant.Mail_Verify_Template, verifyCode);
		SF_Mail mail = new SF_Mail();
		mail.setEmailId(UUID.randomUUID().toString().replace("-", ""));
		mail.setEmail(email);
		mail.setContent(content);
		mail.setType(1);
		sendMail(email,content);
		return mailDao.add(mail);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean validateVerifyCode(String email, String verifyCode) {
		SF_Mail mail = mailDao.getLatest(email,1);
		if(mail!=null
				&&StringUtils.contains(mail.getContent(),verifyCode)){
			return true;
		}
		return false;
	}

	@Override
	public boolean sendMail(String email, String contents) {
//		workerManager.process(new MailWorker(email,Constant.Mail_Subject,contents));
		return false;
	}
}
