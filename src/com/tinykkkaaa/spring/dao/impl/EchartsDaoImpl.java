package com.tinykkkaaa.spring.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.tinykkkaaa.spring.dao.EchartsDao;

@Repository("echartsDao")
public class EchartsDaoImpl implements EchartsDao {
	
	@Resource
	private JdbcTemplate jdbcTemplate;

	@Override
	public Object[] queryBarData() throws Exception {
		
		Object[] objs = new Object[2];
		
		String sql = "SELECT NVL(XZ.SWJGDS_DM,'0'),NVL(XZ.SWJGDS_MC_J,'空缺') AS DSMC,COUNT(DISTINCT NSR.SWGLM) AS SL" +
				" FROM   DB_DW.ZH_DJ_JGNSR NSR,DB_DW.DIM_SWJGXZ  XZ" +
				" WHERE  NSR.GLJG_DM = XZ.SWJGXZ_DM(+) AND XZ.SWJGXZ_DM NOT LIKE '26103%'" +
				" GROUP  BY XZ.SWJGDS_DM,XZ.SWJGDS_MC_J" +
				" ORDER  BY XZ.SWJGDS_DM";
		
		List<JSONObject> list = jdbcTemplate.query(sql, new RowMapper<JSONObject>(){
			@Override
			public JSONObject mapRow(ResultSet rs, int i) throws SQLException {
				JSONObject jo = new JSONObject();
				jo.put("DSMC", rs.getString("DSMC"));
				jo.put("SL", rs.getInt("SL"));
				return jo;
			}
		});
		
		ArrayList<Integer> seriesList = new ArrayList<Integer>();
		ArrayList<String> xAxisList = new ArrayList<String>();
		for(JSONObject jo : list){
			xAxisList.add(jo.getString("DSMC"));
			seriesList.add(jo.getInt("SL"));
		}
		objs[0] = xAxisList;
		objs[1] = seriesList;
		return objs;
	}

	@Override
	public JSONArray queryPieData() throws Exception {
		JSONArray jrray = new JSONArray();
		
		String sql = "SELECT NVL(XZ.SWJGDS_DM,'0'),NVL(XZ.SWJGDS_MC_J,'空缺') AS MC,COUNT(DISTINCT NSR.SWGLM) AS SL" +
		" FROM   DB_DW.ZH_DJ_JGNSR NSR,DB_DW.DIM_SWJGXZ  XZ" +
		" WHERE  NSR.GLJG_DM = XZ.SWJGXZ_DM(+) AND XZ.SWJGXZ_DM NOT LIKE '26103%'" +
		" GROUP  BY XZ.SWJGDS_DM,XZ.SWJGDS_MC_J";
		
		List<JSONObject> list = jdbcTemplate.query(sql, new RowMapper<JSONObject>(){

			@Override
			public JSONObject mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				JSONObject jo = new JSONObject();
				jo.put("name", rs.getString("MC"));
				jo.put("value", rs.getInt("SL"));
				return jo;
			}
			
		});
		
		for(JSONObject jo : list){
			jrray.add(jo);
		}
		
		return jrray;
	}

	@Override
	public List<int[]> queryHeatmapData() throws Exception {
		List<int[]> list = new ArrayList<int[]>();
		
		String sql = "SELECT CASE WHEN TO_CHAR(T.LRRQ, 'DAY') = '星期日' THEN 0" +
				" WHEN TO_CHAR(T.LRRQ, 'DAY') = '星期一' THEN 1" +
				" WHEN TO_CHAR(T.LRRQ, 'DAY') = '星期二' THEN 2" +
				" WHEN TO_CHAR(T.LRRQ, 'DAY') = '星期三' THEN 3" +
				" WHEN TO_CHAR(T.LRRQ, 'DAY') = '星期四' THEN 4" +
				" WHEN TO_CHAR(T.LRRQ, 'DAY') = '星期五' THEN 5" +
				" WHEN TO_CHAR(T.LRRQ, 'DAY') = '星期六' THEN 6 ELSE NULL END AS WEEK," +
				" TO_NUMBER(TO_CHAR(T.LRRQ, 'HH24')) AS HOUR, COUNT(T.SWGLM) AS SL" +
				" FROM DB_DW.ZH_DJ_JGNSR T " +
				" WHERE T.LRRQ IS NOT NULL" +
				" GROUP BY TO_CHAR(T.LRRQ,'DAY'), TO_CHAR(T.LRRQ, 'HH24')";
		
		list = jdbcTemplate.query(sql, new RowMapper<int[]>(){

			@Override
			public int[] mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				int[] is = new int[3];
				is[0] = rs.getInt("WEEK");
				is[1] = rs.getInt("HOUR");
				is[2] = rs.getInt("SL");
				return is;
			}
			
		});
		
		return list;
	}

}
