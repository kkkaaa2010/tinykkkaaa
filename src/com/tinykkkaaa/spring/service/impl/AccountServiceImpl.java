package com.tinykkkaaa.spring.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.tinykkkaaa.spring.dao.AccountDao;
import com.tinykkkaaa.spring.service.AccountService;
import com.tinykkkaaa.spring.vo.DetailAccountVO;
import com.tinykkkaaa.spring.vo.DetailXmVO;

@Service("accountService")
public class AccountServiceImpl implements AccountService {
	
	@Resource
	private AccountDao accountDao;

	@Override
	public JSONArray getDetailType() throws Exception {
		return accountDao.getDetailType();
	}
	
	@Override
	public JSONArray getDetailXm(String dm, String accounttype_dm) throws Exception {
		return accountDao.getDetailXm(dm, accounttype_dm);
	}

	@Override
	public JSONObject getAccountList(String accounttype_dm) throws Exception {
		return accountDao.getAccountList(accounttype_dm);
	}

	@Override
	public String saveAccount(List<DetailAccountVO> list, String accounttype_dm) throws Exception {
		return accountDao.saveAccount(list, accounttype_dm);
	}

	@Override
	public JSONObject getAccountxmList(String id) throws Exception {
		if(id==null || "".equals(id.trim())){
			id = "01";
		}else if("0".equals(id)){
			id = "01";
		}else if("1".equals(id)){
			id = "02";
		}else if("2".equals(id)){
			id = "03";
		}else if("3".equals(id)){
			id = "04";
		}else if("4".equals(id)){
			id = "05";
		}
		return accountDao.getAccountxmList(id);
	}

	@Override
	public JSONObject getBillTotal() throws Exception {
		return accountDao.getBillTotal();
	}

	@Override
	public JSONObject getBillList(Map<String, String> params) throws Exception {
		return accountDao.getBillList(params);
	}

	@Override
	public JSONObject getDetailXmList() throws Exception {
		return accountDao.getDetailXmList();
	}

	@Override
	public String delDetailXm(String dms) throws Exception {
		return accountDao.delDetailXm(dms);
	}

	@Override
	public String addDetailXm(DetailXmVO vo) throws Exception {
		return accountDao.addDetailXm(vo);
	}

	@Override
	public DetailXmVO queryDetailXm(String xmdm) throws Exception {
		return accountDao.queryDetailXm(xmdm);
	}

	@Override
	public String updateDetailXm(DetailXmVO vo) throws Exception {
		return accountDao.updateDetailXm(vo);
	}

	@Override
	public DetailAccountVO queryDetailAccount(Map<String, String> map)
			throws Exception {
		return accountDao.queryDetailAccount(map);
	}

	@Override
	public String updateDetailAccount(DetailAccountVO vo) throws Exception {
		return accountDao.updateDetailAccount(vo);
	}

	@Override
	public JSONObject getDetailAccountById(Map<String, String> params)
			throws Exception {
		return accountDao.getDetailAccountById(params);
	}

	@Override
	public String delDetailAccountById(Map<String, String> params)
			throws Exception {
		return accountDao.delDetailAccountById(params);
	}

	@Override
	public String addBill(Map<String, String> params) throws Exception {
		return accountDao.addBill(params);
	}
}
