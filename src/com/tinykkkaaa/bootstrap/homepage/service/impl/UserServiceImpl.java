package com.tinykkkaaa.bootstrap.homepage.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.tinykkkaaa.bootstrap.homepage.dao.UserDao;
import com.tinykkkaaa.bootstrap.homepage.service.UserService;
import com.tinykkkaaa.bootstrap.homepage.vo.UserVO;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Resource
	private UserDao userDao;

	@Override
	public String saveUser(UserVO user) throws Exception {
		return userDao.saveUser(user);
	}

	@Override
	public boolean existUser(UserVO user) throws Exception {
		return userDao.existUser(user);
	}
}
