package com.tinykkkaaa.spring.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.tinykkkaaa.comm.constant.Const;
import com.tinykkkaaa.spring.dao.UIDao;
import com.tinykkkaaa.spring.vo.TreeVO;

@Repository("uiDao")
public class UIDaoImpl implements UIDao {
	
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public JSONArray getNavTree(String ptreeid) throws Exception {
		JSONArray ja = new JSONArray();
		String orderStr = " ORDER BY TREE_ID";
		String sql = "SELECT TREE_ID, PARENT_TREE_ID, TREE_NAME, TREE_URL" +
				" FROM T_PT_NAVTREE WHERE PARENT_TREE_ID = ?" + orderStr;
		
		List<TreeVO> treeList = this.jdbcTemplate.query(sql, new Object[]{ptreeid}, new RowMapper<TreeVO>(){
			@Override
			public TreeVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				TreeVO vo = new TreeVO();
				vo.setTreeid(rs.getInt("TREE_ID"));
				vo.setPtreeid(rs.getInt("PARENT_TREE_ID"));
				vo.setTreename(rs.getString("TREE_NAME"));
				vo.setUrl(rs.getString("TREE_URL"));
				return vo;
			}
		});
		
		for(TreeVO vo : treeList){
			JSONObject jo = new JSONObject();
			Map<String, String> map = new HashMap<String, String>();
			String url = vo.getUrl();
			
			jo.put("id", vo.getTreeid());
			jo.put("text", vo.getTreename());
			if(url==null || url.trim().equals("")){
				jo.put("state", Const.STATE_CLOSED);
				map.put("url", "");
				jo.put("attributes", map);
			}else{
				jo.put("state", Const.STATE_OPEN);
				map.put("url", url);
				jo.put("attributes", map);
			}
			
			//递归获取下级tree(初始化时全部加载,现在改为点击异步加载)
//			JSONArray childJarray = this.getNavTree(vo.getTreeid()+"");
//			if(childJarray.size() > 0){
//				jo.put("children", childJarray);
//			}
			
			ja.add(jo);
		}
		return ja;
	}

}
