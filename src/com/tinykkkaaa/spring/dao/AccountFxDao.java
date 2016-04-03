package com.tinykkkaaa.spring.dao;

import net.sf.json.JSONObject;

public interface AccountFxDao {
	public JSONObject getExpensesfx(String dateq, String datez) throws Exception;
	public JSONObject getIncomefx(String dateq, String datez) throws Exception;
	public JSONObject getZttjfx(String dateq, String datez) throws Exception;
	public JSONObject getRecentlyfx(String dateq, String datez) throws Exception;
}
