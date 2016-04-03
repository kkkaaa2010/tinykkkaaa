package com.tinykkkaaa.spring.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tinykkkaaa.spring.service.TestService;

@Controller
@RequestMapping("/TestController")
public class TestController {
	
	@Resource
	private TestService testService;
	
	@RequestMapping("/test")
	public String test(){
		String forward = "test/test";
		System.out.println("hello world!");
		return forward;
	}
	
	@RequestMapping("/testQuery")
	public String testQuery(Model model) throws Exception{
		String forward = "test/testQuery";
		String msg = testService.queryTest();
		System.out.println("msg : " + msg);
		model.addAttribute("message", msg);
		return forward;
	}

}
