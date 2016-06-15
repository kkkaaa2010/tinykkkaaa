package com.tinykkkaaa.bootstrap.homepage.service;

import com.tinykkkaaa.bootstrap.homepage.vo.UserVO;

public interface UserService {
	public String saveUser(UserVO user) throws Exception;
	public boolean existUser(UserVO user) throws Exception;
}
