package com.zhimin115200.test.SkyFavor.service.impl;

import com.zhimin115200.test.SkyFavor.common.Constant;
import com.zhimin115200.test.SkyFavor.persistent.MailDao;
import com.zhimin115200.test.SkyFavor.persistent.domain.SF_Mail;
import com.zhimin115200.test.SkyFavor.service.MailService;
import com.zhimin115200.test.SkyFavor.service.worker.WorkerManager;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

@Service
@Transactional
public class MailServiceImpl implements MailService {

	private static Log logger = LogFactory.getLog(MailServiceImpl.class);

	@Resource
	private MailDao mailDao;
	@Resource
	private WorkerManager workerManager;

	@Override
	public boolean sendVerifyCode(String email) {
		String verifyCode =String.valueOf((int)((Math.random()*9+1)*100000));//随机6位数
		String content = String.format(Constant.Mail_Verify_Template, verifyCode);
		SF_Mail mail = new SF_Mail();
		mail.setEmailId(UUID.randomUUID().toString().replace("-", ""));
		mail.setEmail(email);
		mail.setContent(content);
		mail.setMailType(1);
		sendMail(email,content);
		logger.info(content);
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
