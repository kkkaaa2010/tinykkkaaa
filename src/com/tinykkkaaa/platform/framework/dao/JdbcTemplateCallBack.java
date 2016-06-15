package com.tinykkkaaa.platform.framework.dao;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * spring jdbcTemplate.queryForObject接口
 * @author Administrator
 *
 * @param <T>
 */
public interface JdbcTemplateCallBack<T> {
	public T queryForObject(JdbcTemplate jdbcTemplate);
}