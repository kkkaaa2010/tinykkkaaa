package com.tinykkkaaa.bootstrap.homepage.vo;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserVO {
	private int userid;
	
	@Pattern(regexp="[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}", message="不是有效的邮件地址")
	private String useraccount;
	
	@Size(min=3, max=50, message="用户名长度必须为3-50位")
	@Pattern(regexp="^[a-zA-Z0-9]+$", message="用户名必须是数字与字母组成,并且不能包含空格")
	private String username;
	
	@Size(min=6, max=20, message="密码长度必须为6-20位")
	private String password;
	private String confirmpassword;
	
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getUseraccount() {
		return useraccount;
	}
	public void setUseraccount(String useraccount) {
		this.useraccount = useraccount;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmpassword() {
		return confirmpassword;
	}
	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}
}
