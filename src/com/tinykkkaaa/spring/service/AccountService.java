package com.tinykkkaaa.spring.service;

import java.util.List;
import java.util.Map;

import com.tinykkkaaa.spring.vo.DetailAccountVO;
import com.tinykkkaaa.spring.vo.DetailXmVO;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface AccountService {
	public JSONArray getDetailType() throws Exception;
	public JSONArray getDetailXm(String dm, String accounttype_dm) throws Exception;
	public JSONObject getAccountList(String accounttype_dm) throws Exception;
	public String saveAccount(List<DetailAccountVO> list, String accounttype_dm) throws Exception;
	public JSONObject getAccountxmList(String id) throws Exception;
	public JSONObject getBillTotal() throws Exception;
	
	public JSONObject getBillList(Map<String, String> params) throws Exception;
	
	public JSONObject getDetailXmList() throws Exception;
	public String delDetailXm(String dms) throws Exception;
	public String addDetailXm(DetailXmVO vo) throws Exception;
	public DetailXmVO queryDetailXm(String xmdm) throws Exception;
	public String updateDetailXm(DetailXmVO vo) throws Exception;
	
	public DetailAccountVO queryDetailAccount(Map<String, String> map) throws Exception;
	public String updateDetailAccount(DetailAccountVO vo) throws Exception;
	public JSONObject getDetailAccountById(Map<String, String> params) throws Exception;
	public String delDetailAccountById(Map<String, String> params) throws Exception;
	
	public String addBill(Map<String, String> params) throws Exception;
}
