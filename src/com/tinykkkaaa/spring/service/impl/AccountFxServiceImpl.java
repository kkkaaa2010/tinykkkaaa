package com.tinykkkaaa.spring.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.sf.json.JSONObject;

import com.tinykkkaaa.spring.dao.AccountFxDao;
import com.tinykkkaaa.spring.service.AccountFxService;

@Service("accountFxService")
public class AccountFxServiceImpl implements AccountFxService {
	
	@Resource
	private AccountFxDao accountFxDao;

	@Override
	public JSONObject getExpensesfx(String dateq, String datez)
			throws Exception {
		return accountFxDao.getExpensesfx(dateq, datez);
	}

	@Override
	public JSONObject getIncomefx(String dateq, String datez) throws Exception {
		return accountFxDao.getIncomefx(dateq, datez);
	}

	@Override
	public JSONObject getZttjfx(String dateq, String datez) throws Exception {
		return accountFxDao.getZttjfx(dateq, datez);
	}

	@Override
	public JSONObject getRecentlyfx(String dateq, String datez)
			throws Exception {
		return accountFxDao.getRecentlyfx(dateq, datez);
	}

}
