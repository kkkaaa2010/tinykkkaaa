package com.tinykkkaaa.spring.service;

import net.sf.json.JSONObject;

public interface AccountFxService {
	public JSONObject getExpensesfx(String dateq, String datez) throws Exception;
	public JSONObject getIncomefx(String dateq, String datez) throws Exception;
	public JSONObject getZttjfx(String dateq, String datez) throws Exception;
	public JSONObject getRecentlyfx(String dateq, String datez) throws Exception;
}
