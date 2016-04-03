package com.tinykkkaaa.comm.util;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class QueryUtil {
	private static JdbcTemplate jdbcTemplate;

	@Inject
	@Named("jdbcTemplate")
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		QueryUtil.jdbcTemplate = jdbcTemplate;
	}

	public static String getNextPrimaryKey(String tableName, String filed, int length) throws Exception {
		String sql = "SELECT LPAD(TO_CHAR(MAX(TO_NUMBER("+ filed +"))+1), " + length + ", '0') AS KEY"
					 + " FROM " + tableName;
		String key = jdbcTemplate.queryForObject(sql, String.class);
		return key;
	}
	
//	public static void main(String[] args) {
//		//ApplicationContext context = new ClassPathXmlApplicationContext("file:" + System.getProperty("user.dir")+"/WebRoot/WEB-INF/springMVC-servlet.xml");
//		ApplicationContext context = new ClassPathXmlApplicationContext("springJdbcTemplate.xml");
//		QueryUtil bean = (QueryUtil) context.getBean("queryUtil");
//	}
}
