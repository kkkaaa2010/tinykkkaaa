package com.tinykkkaaa.spring.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tinykkkaaa.comm.util.JsonUtil;
import com.tinykkkaaa.spring.service.AccountService;
import com.tinykkkaaa.spring.vo.DetailAccountVO;
import com.tinykkkaaa.spring.vo.DetailXmVO;

@Controller
@RequestMapping("/sh/account")
public class AccountController {
	
	@Resource
	private AccountService accountService;
	
	@RequestMapping("/addAccount")
	public String addAccount() throws Exception{
		String forward = "account/addAccount";
		return forward;
	}
	
	@RequestMapping("/getAccountList")
	@ResponseBody
	public JSONObject getAccountList(String accounttype_dm) throws Exception{
		return accountService.getAccountList(accounttype_dm);
	}
	
	@RequestMapping("/getDetailType")
	@ResponseBody
	public JSONArray getDetailType() throws Exception{
		return accountService.getDetailType();
	}
	
	@RequestMapping("/getDetailXm")
	@ResponseBody
	public JSONArray getDetailXm(HttpServletRequest request) throws Exception{
		return accountService.getDetailXm(request.getParameter("dm"),request.getParameter("accounttype_dm"));
	}
	
	/**
	 * update at 2016-03-28
	 * 今日账单、今日收入保存操作
	 * @param accounts
	 * @throws Exception
	 */
	@RequestMapping("/saveAccount")
	@ResponseBody
	public String saveAccount(@RequestParam String accounts, String accounttype_dm) throws Exception{
		List<DetailAccountVO> list = JsonUtil.convertToList(accounts, DetailAccountVO[].class);
		String result = accountService.saveAccount(list, accounttype_dm);
		return result;
	}
	
	/**
	 * add at 2016-03-28
	 * 今日收入
	 * @throws Exception
	 */
	@RequestMapping("/addIncome")
	public String addIncome() throws Exception{
		String forward = "account/addIncome";
		return forward;
	}
	
	@RequestMapping("/bill")
	public String bill() throws Exception{
		String forward = "account/bill";
		return forward;
	}
	
	@RequestMapping("/getAccountxmList")
	@ResponseBody
	public JSONObject getAccountxmList(String id) throws Exception{
		return accountService.getAccountxmList(id);
	}
	
	@RequestMapping("/getBillTotal")
	@ResponseBody
	public JSONObject getBillTotal() throws Exception{
		return accountService.getBillTotal();
	}
	
	@RequestMapping("/billList")
	public String billList() throws Exception{
		String forward = "account/billList";
		return forward;
	}
	
	@RequestMapping("/getBillList")
	@ResponseBody
	public JSONObject getBillList(@RequestParam String params) throws Exception{
		Map paramsMap = JsonUtil.convertToObject(params, Map.class);
		//System.out.println(paramsMap);
		return accountService.getBillList(paramsMap);
	}
	
	@RequestMapping("/detailXmList")
	public String detailXmList() throws Exception{
		String forward = "account/detailXmList";
		return forward;
	}
	
	@RequestMapping("/getDetailXmList")
	@ResponseBody
	public JSONObject getDetailXmList() throws Exception{
		return accountService.getDetailXmList();
	}
	
	@RequestMapping("/delDetailXm")
	@ResponseBody
	public String delDetailXm(@RequestParam String dms) throws Exception{
		String result = accountService.delDetailXm(dms);
		return result;
	}
	
	@RequestMapping("/toAddDetailXm")
	public String toAddDetailXm() throws Exception{
		String forward = "account/addDetailXm";
		return forward;
	}
	
	@RequestMapping("/addDetailXm")
	@ResponseBody
	public JSONObject addDetailXm(String zdlx, String xflx, String zcsrxm) throws Exception{
		JSONObject jsonMessage = new JSONObject();
		DetailXmVO vo = new DetailXmVO();
		vo.setAccounttype_dm(zdlx);
		vo.setDetailtype_dm(xflx);
		vo.setXmmc(zcsrxm);
		jsonMessage.put("message", accountService.addDetailXm(vo));
		return jsonMessage;
	}
	
	@RequestMapping("/toUpdateDetailXm")
	public String toUpdateDetailXm(HttpServletRequest request, Model model) throws Exception{
		String dm = request.getParameter("dm");
		DetailXmVO vo = accountService.queryDetailXm(dm);
		model.addAttribute(vo);
		String forward = "account/updateDetailXm";
		return forward;
	}
	
	@RequestMapping("/updateDetailXm")
	@ResponseBody
	public JSONObject updateDetailXm(String xmdm, String zdlx, String xflx, String zcsrxm) throws Exception{
		JSONObject jsonMessage = new JSONObject();
		DetailXmVO vo = new DetailXmVO();
		vo.setXmdm(xmdm);
		vo.setAccounttype_dm(zdlx);
		vo.setDetailtype_dm(xflx);
		vo.setXmmc(zcsrxm);
		jsonMessage.put("message", accountService.updateDetailXm(vo));
		return jsonMessage;
	}
	
	@RequestMapping("/toUpdateBill")
	public String toUpdateExpenses(HttpServletRequest request, Model model) throws Exception{
		String accountid = request.getParameter("accountid");
		String detailid = request.getParameter("detailid");
		String accounttype_dm = request.getParameter("accounttype_dm");
		String rowNumIndex = request.getParameter("rowNumIndex");
		Map<String, String> map = new HashMap<String, String>();
		map.put("accountid", accountid);
		map.put("detailid", detailid);
		map.put("accounttype_dm", accounttype_dm);
		DetailAccountVO vo = accountService.queryDetailAccount(map);
		model.addAttribute(vo);
		model.addAttribute("rowNumIndex", rowNumIndex);
		String forward = "account/updateIncome";
		if("02".equals(accounttype_dm)){
			forward = "account/updateExpenses";
		}
		return forward;
	}
	
	@RequestMapping("/updateBill")
	@ResponseBody
	public JSONObject updateExpenses(String accountid, String detailid, String accounttype_dm, String srtype,
			String srxm, String srsm, String srsj, String srje) throws Exception{
		JSONObject jsonMessage = new JSONObject();
		DetailAccountVO vo = new DetailAccountVO();
		vo.setDetailid(detailid);
		vo.setId(accountid);
		vo.setAccounttype_dm(accounttype_dm);
		vo.setDetailtype_dm(srtype);
		vo.setDetailxm_dm(srxm);
		vo.setDetail_content(srsm);
		vo.setDetail_time(srsj);
		vo.setDetail_je(Double.parseDouble(srje));
		jsonMessage.put("message", accountService.updateDetailAccount(vo));
		return jsonMessage;
	}
	
	@RequestMapping("/getDetailAccountById")
	@ResponseBody
	public JSONObject getDetailAccountById(@RequestParam String params) throws Exception{
		Map paramsMap = JsonUtil.convertToObject(params, Map.class);
		return accountService.getDetailAccountById(paramsMap);
	}
	
	@RequestMapping("/delDetailAccountById")
	@ResponseBody
	public String delDetailAccountById(@RequestParam String params) throws Exception{
		Map paramsMap = JsonUtil.convertToObject(params, Map.class);
		return accountService.delDetailAccountById(paramsMap);
	}
	
	/**
	 * add at 2016-03-21
	 * 账单分析模块
	 */
	@RequestMapping("/billPortal")
	public String billPortal() throws Exception{
		String forward = "account/billPortal";
		return forward;
	}
}
