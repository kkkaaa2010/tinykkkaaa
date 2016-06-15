package com.tinykkkaaa.bootstrap.homepage.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tinykkkaaa.bootstrap.homepage.service.UserService;
import com.tinykkkaaa.bootstrap.homepage.vo.UserVO;

@Controller
@RequestMapping("/bp/user")
public class UserController {
	
	private static String path = "bootstrap/pages/";
	
	@Resource
	private UserService userService;
	
	@RequestMapping(value="/login",method={RequestMethod.GET})
	public String login(Model model) throws Exception{
		String forward = "login";
		model.addAttribute("userModel", new UserVO());
		return path+forward;
	}
	
	@RequestMapping(value="/login",method={RequestMethod.POST})
	public String login(Model model, @ModelAttribute("userModel") UserVO user, BindingResult bindingResult) throws Exception{
		String forward = "login";
		if(userService.existUser(user)){
			return "redirect:/rest/bp/homepage/"+user.getUseraccount();
		}else{
			bindingResult.addError(new FieldError("userModel", "useraccount", "用户名或密码错误"));
			model.addAttribute("userModel", user);
			return path+forward;
		}
		
	}
	
	@RequestMapping(value="/register",method={RequestMethod.GET})
	public String register(Model model) throws Exception{
		String forward = "register";
		model.addAttribute("registerUserModel", new UserVO());
		return path+forward;
	}
	
	@RequestMapping(value="/register",method={RequestMethod.POST})
	public String register(Model model, @Valid @ModelAttribute("registerUserModel") UserVO user, BindingResult bindingResult) throws Exception{
		String forward = "register";
		if(!user.getPassword().equals(user.getConfirmpassword())){
			bindingResult.addError(new FieldError("registerUserModel", "confirmpassword", "输入密码与确认密码不一致"));
		}
		if(bindingResult.hasErrors()){
			model.addAttribute("registerUserModel", user);
			return path+forward;
		}
		
		//判断时候该用户名与邮箱已经注册
		
		userService.saveUser(user);
		return "redirect:/rest/bp/homepage/"+user.getUseraccount();
	}

}
