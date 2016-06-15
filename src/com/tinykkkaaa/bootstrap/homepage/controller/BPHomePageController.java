package com.tinykkkaaa.bootstrap.homepage.controller;

import javax.annotation.Resource;
import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tinykkkaaa.bootstrap.homepage.service.BPHomePageService;

@Controller
@RequestMapping("/bp/homepage")
public class BPHomePageController {
	
	@Resource
	private BPHomePageService bpHomePageService;
	
	@RequestMapping("/getNavItem")
	@ResponseBody
	public JSONArray getNavItem(String itemParam) throws Exception{
		return this.bpHomePageService.getNavItem(itemParam);
	}

	@RequestMapping(value="/{useraccount}", method=RequestMethod.GET)
	public String showIndex(@PathVariable String useraccount, Model model){
		return "index";
	}
}
