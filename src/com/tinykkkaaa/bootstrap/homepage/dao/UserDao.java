package com.tinykkkaaa.bootstrap.homepage.dao;

import com.tinykkkaaa.bootstrap.homepage.vo.UserVO;

public interface UserDao {
	public String saveUser(UserVO user) throws Exception;
	public boolean existUser(UserVO user) throws Exception;
}
