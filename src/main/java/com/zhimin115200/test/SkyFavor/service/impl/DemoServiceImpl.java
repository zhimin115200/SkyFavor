package com.zhimin115200.test.SkyFavor.service.impl;

import javax.annotation.Resource;

import com.zhimin115200.test.SkyFavor.service.DemoService;
import com.zhimin115200.test.SkyFavor.persistent.DemoDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DemoServiceImpl implements DemoService {
	
	@Resource
	private DemoDao dao;

	@Override
	@Transactional(readOnly = true)
	public String test() {
		return dao.test();
	}

}
