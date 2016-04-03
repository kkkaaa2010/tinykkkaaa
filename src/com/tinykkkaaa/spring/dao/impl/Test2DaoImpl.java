package com.tinykkkaaa.spring.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.tinykkkaaa.spring.dao.TestDao;

@Repository("test2Dao")
public class Test2DaoImpl implements TestDao {
	
	@Resource
	private JdbcTemplate jdbcTemplate;

	@Override
	public String queryTest() throws Exception {
		
		System.out.println("----------dao2");
		
		String sql = "SELECT TO_CHAR(SYSDATE, 'YYYYMMDD') AS TESTCOL FROM DUAL";
		
		String msg = jdbcTemplate.query(sql, new ResultSetExtractor<String>(){
			@Override
			public String extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				rs.next();
				return rs.getString("TESTCOL");
			}
		});
		return msg;
	}

}
