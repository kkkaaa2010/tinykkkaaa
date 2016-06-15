package com.tinykkkaaa.bootstrap.homepage.dao.impl;
import javax.annotation.Resource;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.tinykkkaaa.bootstrap.homepage.dao.UserDao;
import com.tinykkkaaa.bootstrap.homepage.vo.UserVO;
import com.tinykkkaaa.platform.core.console.util.DBAccessHelper;
import com.tinykkkaaa.platform.framework.dao.JdbcTemplateCallBack;

@Repository("userDao")
public class UserDaoImpl implements UserDao{
	
	@Resource
	private JdbcTemplate jdbcTemplate;

	@Override
	public String saveUser(UserVO user) throws Exception {
		String sql = "INSERT INTO T_PT_USER (USER_ID, USERACCOUNT, USERNAME, PASSWORD) VALUES (?, ?, ?, ?)";
		Object[] objs = new Object[4];
		objs[0] = DBAccessHelper.getSequence("T_PT_USER", 10);
		objs[1] = user.getUseraccount();
		objs[2] = user.getUsername();
		objs[3] = user.getPassword();
		
		int sign = this.jdbcTemplate.update(sql, objs);
		String message = "0";
		if(sign > 0){
			message = "1";
		}
		return message;
	}

	@Override
	public boolean existUser(UserVO user) throws Exception {
		boolean message = false;
		
		final String sql = "SELECT 1 FROM T_PT_USER WHERE USERACCOUNT = ? AND PASSWORD = ?";
		final Object[] objs = new Object[2];
		objs[0] = user.getUseraccount();
		objs[1] = user.getPassword();
		
		Integer sign = this.queryNullAble(new JdbcTemplateCallBack<Integer>() {
			@Override
			public Integer queryForObject(JdbcTemplate jdbcTemplate) {
				return jdbcTemplate.queryForObject(sql, objs, Integer.class);
			}
		});
		
		if(sign != null){
			message = true;
		}
		return message;
	}
	
	//jdbcTemplate.queryForObject为空时不抛出异常，直接返回null
	public <T> T queryNullAble(JdbcTemplateCallBack<T> jdbcTemplateCallBack) throws Exception{
		try {
			return jdbcTemplateCallBack.queryForObject(this.jdbcTemplate);
		} catch (Exception e) {
			if ((e instanceof IncorrectResultSizeDataAccessException)
					&& ((IncorrectResultSizeDataAccessException) e).getActualSize()==0)
				return null;
			// 其他的异常正常抛出
			throw new Exception(e);
		}
	}
}
