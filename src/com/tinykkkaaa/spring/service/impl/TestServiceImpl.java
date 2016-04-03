package com.tinykkkaaa.spring.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tinykkkaaa.spring.dao.TestDao;
import com.tinykkkaaa.spring.service.TestService;

@Service("testService")
public class TestServiceImpl implements TestService {
	
	@Resource
	private TestDao testDao;

	@Override
	public String queryTest() throws Exception {
		return testDao.queryTest();
	}

}
