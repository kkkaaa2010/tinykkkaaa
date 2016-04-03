package com.tinykkkaaa.spring.controller;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tinykkkaaa.spring.service.AccountFxService;

@Controller
@RequestMapping("/sh/account/fx")
public class AccountFxController {
	
	@Resource
	private AccountFxService accountFxService;
	
	@RequestMapping("/expensesfx")
	public String pie() throws Exception{
		String forward = "account/bill_expensesfx";
		return forward;
	}
	
	@RequestMapping("/initExpensesfx")
	@ResponseBody
	public JSONObject initExpensesfx(String dateq, String datez) throws Exception{
		return accountFxService.getExpensesfx(dateq, datez);
	}
	
	@RequestMapping("/incomefx")
	public String incomefx() throws Exception{
		String forward = "account/bill_incomefx";
		return forward;
	}
	
	@RequestMapping("/initIncomefx")
	@ResponseBody
	public JSONObject initIncomefx(String dateq, String datez) throws Exception{
		return accountFxService.getIncomefx(dateq, datez);
	}
	
	@RequestMapping("/zttjfx")
	public String zttjfx() throws Exception{
		String forward = "account/bill_zttjfx";
		return forward;
	}
	
	@RequestMapping("/initZttjfx")
	@ResponseBody
	public JSONObject initZttjfx(String dateq, String datez) throws Exception{
		return accountFxService.getZttjfx(dateq, datez);
	}
	
	@RequestMapping("/recentlyAccountfx")
	public String recentlyAccountfx() throws Exception{
		String forward = "account/bill_recentlyAccountfx";
		return forward;
	}
	
	@RequestMapping("/initRecentlyfx")
	@ResponseBody
	public JSONObject initRecentlyfx(String dateq, String datez) throws Exception{
		return accountFxService.getRecentlyfx(dateq, datez);
	}
}
