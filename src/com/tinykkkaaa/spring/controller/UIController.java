package com.tinykkkaaa.spring.controller;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tinykkkaaa.spring.service.UIService;

@Controller
@RequestMapping("/homepage/ui")
public class UIController {
	
	@Resource
	private UIService uiService;
	
	@RequestMapping("/index")
	public String bar() throws Exception{
		String forward = "ui/ui";
		return forward;
	}
	
	@RequestMapping("/getNavTree")
	@ResponseBody
	public JSONArray getNavTree(String ptreeid) throws Exception{
		return uiService.getNavTree(ptreeid);
	}
}
