package com.tinykkkaaa.bootstrap.homepage.dao.impl;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.tinykkkaaa.bootstrap.homepage.dao.BPHomePageDao;
import com.tinykkkaaa.bootstrap.homepage.vo.NavVO;

@Repository("bpHomePageDao")
public class BPHomePageDaoImpl implements BPHomePageDao{
	
	@Resource
	private JdbcTemplate jdbcTemplate;

	@Override
	public JSONArray getNavItem(String itemParam) throws Exception {
		String sql = "SELECT NAV_ID, NAV_NAME, NAV_URL, PARENT_ID FROM T_BP_NAV WHERE PARENT_ID IS NULL";
		List<NavVO> list = this.jdbcTemplate.query(sql, new RowMapper<NavVO>(){
			@Override
			public NavVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				NavVO vo = new NavVO();
				vo.setId(rs.getInt("NAV_ID"));
				vo.setName(rs.getString("NAV_NAME"));
				vo.setUrl(rs.getString("NAV_URL"));
				vo.setParentid(rs.getInt("PARENT_ID"));
				return vo;
			}
		});
		
		return JSONArray.fromObject(list);
	}
}
