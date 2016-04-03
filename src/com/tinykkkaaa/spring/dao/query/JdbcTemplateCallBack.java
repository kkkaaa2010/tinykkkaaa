package com.tinykkkaaa.spring.dao.query;

import org.springframework.jdbc.core.JdbcTemplate;

public interface JdbcTemplateCallBack<T> {
	public T queryForObject(JdbcTemplate jdbcTemplate);
}
