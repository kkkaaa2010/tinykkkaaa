package com.tinykkkaaa.bootstrap.share.dao.impl;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.tinykkkaaa.bootstrap.homepage.vo.NavVO;
import com.tinykkkaaa.bootstrap.share.dao.ShareDao;

@Repository("shareDao")
public class ShareDaoImpl implements ShareDao{
	
	@Resource
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<NavVO> getNavList(String parentid) throws Exception {
		String sql = "SELECT NAV_ID, NAV_NAME, NAV_URL, PARENT_ID FROM T_BP_NAV WHERE PARENT_ID = ? ORDER BY NAV_ID";
		List<NavVO> list = this.jdbcTemplate.query(sql, new Object[]{parentid}, new RowMapper<NavVO>(){
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
		return list;
	}

	@Override
	public Object[] getDefaultSetting(String parentid) throws Exception {
		Object[] objs = new Object[2];
		
		//默认打开url
		String sql = "SELECT NAV_ID, NAV_NAME, NAV_URL, PARENT_ID FROM T_BP_NAV WHERE PARENT_ID = ? AND ROWNUM = 1 ORDER BY NAV_ID";
		Map<String, String> map = this.jdbcTemplate.queryForObject(sql, new Object[]{parentid}, new RowMapper<Map<String, String>>(){
			@Override
			public Map<String, String> mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				Map<String, String> map = new HashMap<String, String>();
				map.put("navid", rs.getInt("NAV_ID")+"");
				map.put("url", rs.getString("NAV_URL"));
				return map;
			}
		});
		objs[0] = map.get("url");
		
		//面包屑导航条
		sql = "SELECT NAV_ID, NAV_NAME, NAV_URL, PARENT_ID FROM T_BP_NAV T START WITH T.NAV_ID = ?" +
			  " CONNECT BY PRIOR T.PARENT_ID = T.NAV_ID ORDER BY T.NAV_ID";
		List<NavVO> list = this.jdbcTemplate.query(sql, new Object[]{map.get("navid")}, new RowMapper<NavVO>(){
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
		objs[1] = list;
		
		return objs;
	}
}
