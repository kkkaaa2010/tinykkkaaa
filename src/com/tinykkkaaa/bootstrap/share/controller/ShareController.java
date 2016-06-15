package com.tinykkkaaa.bootstrap.share.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tinykkkaaa.bootstrap.homepage.vo.NavVO;
import com.tinykkkaaa.bootstrap.share.common.Consts;
import com.tinykkkaaa.bootstrap.share.service.ShareService;

@Controller
@RequestMapping("/bp/share")
public class ShareController {
	
	private static String path = "bootstrap/pages/";
	
	@Resource
	private ShareService shareService;
	
	@RequestMapping("/layout")
	public String layout(Model model) throws Exception{
		String forward = path+"share_layout";
		List<NavVO> list = shareService.getNavList(Consts.SHARE_NAV_ID);
		model.addAttribute("navList", list);
		model.addAttribute("defaultSetting", shareService.getDefaultSetting(Consts.SHARE_NAV_ID));
		return forward;
	}
	
	@RequestMapping("/share_aqf")
	public String share_1(){
		String forward = path+"share_aqf";
		return forward;
	}
}
