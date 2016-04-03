package com.tinykkkaaa.spring.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.tinykkkaaa.spring.dao.AccountFxDao;
import com.tinykkkaaa.spring.vo.TypeVO;
import com.tinykkkaaa.spring.vo.ValueVO;

@Repository("accountFxDao")
public class AccountFxDaoImpl implements AccountFxDao {
	
	@Resource
	private JdbcTemplate jdbcTemplate;

	@Override
	public JSONObject getExpensesfx(String dateq, String datez)
			throws Exception {
		JSONObject jo = new JSONObject();
		
		String whereStr = "";
		if(dateq!=null && !"".equals(dateq.trim())){
			whereStr += " AND T.ACCOUNT_ID >= '" + dateq + "' ";
		}
		if(datez!=null && !"".equals(datez.trim())){
			whereStr += " AND T.ACCOUNT_ID <= '" + datez + "' ";
		}
		if(whereStr.length() <= 0){
			whereStr += " AND SUBSTR(T.ACCOUNT_ID,1,6) = TO_CHAR(SYSDATE, 'YYYYMM')";
		}
		String sql = "SELECT TYPE.DETAILTYPE_DM, TYPE.DETAILTYPE_MC AS NAME, SUM(T.JE) AS VALUE" +
				" FROM T_SH_ACCOUNTDETAIL T, T_DM_DETAILTYPE TYPE, T_DM_DETAILXM XM" +
				" WHERE T.DETAILTYPE_DM = TYPE.DETAILTYPE_DM(+) " +
				" AND T.DETAILXM_DM = XM.DETAILXM_DM(+) AND T.ACCOUNTTYPE_DM = '02'" + whereStr +
				" GROUP BY TYPE.DETAILTYPE_DM, TYPE.DETAILTYPE_MC" +
				" ORDER BY TYPE.DETAILTYPE_DM";
		
		List<ValueVO> typeList = this.jdbcTemplate.query(sql, new RowMapper<ValueVO>(){
			@Override
			public ValueVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				ValueVO vo = new ValueVO();
				vo.setName(rs.getString("NAME"));
				vo.setValue(rs.getDouble("VALUE") + "");
				return vo;
			}
		});
		
		sql = "SELECT XM.DETAILXM_DM, XM.DETAILXM_MC AS NAME, TYPE.DETAILTYPE_DM, SUM(T.JE) AS VALUE" +
		" FROM T_SH_ACCOUNTDETAIL T, T_DM_DETAILTYPE TYPE, T_DM_DETAILXM XM" +
		" WHERE T.DETAILTYPE_DM = TYPE.DETAILTYPE_DM(+) " +
		" AND T.DETAILXM_DM = XM.DETAILXM_DM(+) AND T.ACCOUNTTYPE_DM = '02'" + whereStr +
		" GROUP BY XM.DETAILXM_DM, XM.DETAILXM_MC, TYPE.DETAILTYPE_DM" +
		" ORDER BY TYPE.DETAILTYPE_DM, XM.DETAILXM_DM";
		
		List<ValueVO> xmList = this.jdbcTemplate.query(sql, new RowMapper<ValueVO>(){
			@Override
			public ValueVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				ValueVO vo = new ValueVO();
				vo.setName(rs.getString("NAME"));
				vo.setValue(rs.getDouble("VALUE") + "");
				return vo;
			}
		});
		
		String[] legends = new String[typeList.size() + xmList.size()];
		int legendNum = 0;
		
		JSONArray jarray = new JSONArray();
		for(ValueVO vo : typeList){
			JSONObject tempjo = new JSONObject();
			tempjo.put("name", vo.getName());
			tempjo.put("value", vo.getValue());
			legends[legendNum++] = vo.getName();
			jarray.add(tempjo);
		}
		jo.put("inData", jarray);
		
		jarray = new JSONArray();
		for(ValueVO vo : xmList){
			JSONObject tempjo = new JSONObject();
			tempjo.put("name", vo.getName());
			tempjo.put("value", vo.getValue());
			legends[legendNum++] = vo.getName();
			jarray.add(tempjo);
		}
		jo.put("outData", jarray);
		jo.put("legendData", legends);
		
		return jo;
	}

	@Override
	public JSONObject getIncomefx(String dateq, String datez) throws Exception {
		JSONObject jo = new JSONObject();
		String whereStr = "";
		if(dateq!=null && !"".equals(dateq.trim())){
			whereStr += " AND T.ACCOUNT_ID >= '" + dateq + "' ";
		}
		if(datez!=null && !"".equals(datez.trim())){
			whereStr += " AND T.ACCOUNT_ID <= '" + datez + "' ";
		}
		if(whereStr.length() <= 0){
			whereStr += " AND SUBSTR(T.ACCOUNT_ID,1,6) = TO_CHAR(SYSDATE, 'YYYYMM')";
		}
		String sql = "SELECT XM.DETAILXM_DM, XM.DETAILXM_MC AS NAME, SUM(T.JE) AS VALUE" +
				" FROM   T_SH_ACCOUNTDETAIL T, T_DM_DETAILXM XM" +
				" WHERE  T.DETAILXM_DM = XM.DETAILXM_DM(+) AND T.ACCOUNTTYPE_DM = '01'" + whereStr + 
				" GROUP  BY XM.DETAILXM_DM, XM.DETAILXM_MC" +
				" ORDER  BY XM.DETAILXM_DM";
		
		List<ValueVO> list = this.jdbcTemplate.query(sql, new RowMapper<ValueVO>(){
			@Override
			public ValueVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				ValueVO vo = new ValueVO();
				vo.setName(rs.getString("NAME"));
				vo.setValue(rs.getDouble("VALUE") + "");
				return vo;
			}
		});
		
		String[] legends = new String[list.size()];
		int i = 0;
		for(ValueVO vo : list){
			legends[i++] = vo.getName();
		}
		jo.put("legendData", legends);
		
		JSONArray jarray = JSONArray.fromObject(list);
		jo.put("seriesData", jarray);
		
		return jo;
	}

	@Override
	public JSONObject getZttjfx(String dateq, String datez) throws Exception {
		JSONObject jo = new JSONObject();
		String whereStr = "";
		if(dateq!=null && !"".equals(dateq.trim())){
			whereStr += " AND T.ACCOUNT_ID >= '" + dateq + "' ";
		}
		if(datez!=null && !"".equals(datez.trim())){
			whereStr += " AND T.ACCOUNT_ID <= '" + datez + "' ";
		}
		if(whereStr.length() <= 0){
			whereStr += " AND SUBSTR(T.ACCOUNT_ID,1,6) = TO_CHAR(SYSDATE, 'YYYYMM')";
		}
		String sql = "SELECT ATYPE.ACCOUNTTYPE_DM,ATYPE.ACCOUNTTYPE_MC AS NAME,SUM(T.JE) AS VALUE" +
				" FROM   T_SH_ACCOUNTDETAIL T, T_DM_ACCOUNTTYPE ATYPE" +
				" WHERE  T.ACCOUNTTYPE_DM = ATYPE.ACCOUNTTYPE_DM(+)" + whereStr + 
				" GROUP  BY ATYPE.ACCOUNTTYPE_DM, ATYPE.ACCOUNTTYPE_MC" +
				" ORDER  BY ATYPE.ACCOUNTTYPE_DM";
		
		List<ValueVO> list = this.jdbcTemplate.query(sql, new RowMapper<ValueVO>(){
			@Override
			public ValueVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				ValueVO vo = new ValueVO();
				vo.setName(rs.getString("NAME"));
				vo.setValue(rs.getDouble("VALUE") + "");
				return vo;
			}
		});
		
		String[] legends = new String[list.size()];
		int i = 0;
		for(ValueVO vo : list){
			legends[i++] = vo.getName();
		}
		jo.put("legendData", legends);
		
		JSONArray jarray = JSONArray.fromObject(list);
		jo.put("seriesData", jarray);
		
		return jo;
	}

	@Override
	public JSONObject getRecentlyfx(String dateq, String datez)
			throws Exception {
		JSONObject jo = new JSONObject();
		
		String whereStr = "";
		if(dateq!=null && !"".equals(dateq.trim())){
			whereStr += " AND T.ACCOUNT_ID >= '" + dateq + "' ";
		}else{
			whereStr += " AND T.ACCOUNT_ID >= TO_CHAR(SYSDATE-6, 'YYYYMMDD')";
		}
		if(datez!=null && !"".equals(datez.trim())){
			whereStr += " AND T.ACCOUNT_ID <= '" + datez + "' ";
		}else{
			whereStr += " AND T.ACCOUNT_ID <= TO_CHAR(SYSDATE, 'YYYYMMDD')";
		}
		
		String sql = "SELECT DM.DETAILTYPE_DM AS DM, DM.DETAILTYPE_MC AS MC FROM T_DM_DETAILTYPE DM";
		List<TypeVO> dmList = this.jdbcTemplate.query(sql, new RowMapper<TypeVO>(){
			@Override
			public TypeVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				TypeVO vo = new TypeVO();
				vo.setType_dm(rs.getString("DM"));
				vo.setType_mc(rs.getString("MC"));
				return vo;
			}
		});
		
		sql = "SELECT DISTINCT T.ACCOUNT_ID AS RQ FROM T_SH_ACCOUNTDETAIL T WHERE 1=1" + whereStr + " ORDER BY T.ACCOUNT_ID";
		List<String> rqList = this.jdbcTemplate.query(sql, new RowMapper<String>(){
			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString("RQ");
			}
		});
		
		sql = "SELECT T.ACCOUNT_ID AS RQ,DM.DETAILTYPE_DM, DM.DETAILTYPE_MC AS NAME, SUM(T.JE) AS VALUE" +
				" FROM T_SH_ACCOUNTDETAIL T, T_DM_DETAILTYPE DM" +
				" WHERE  T.DETAILTYPE_DM = DM.DETAILTYPE_DM(+) AND T.ACCOUNTTYPE_DM = '02'" + whereStr + 
				" GROUP BY T.ACCOUNT_ID,DM.DETAILTYPE_DM, DM.DETAILTYPE_MC" +
				" ORDER BY T.ACCOUNT_ID,DM.DETAILTYPE_DM";
		
		List<Map<String, Object>> list = this.jdbcTemplate.query(
				sql,
				new RowMapper<Map<String, Object>>() {
					public Map<String, Object> mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("rq", rs.getString("RQ"));
						map.put("name", rs.getString("NAME"));
						map.put("value", rs.getDouble("VALUE"));
						return map;
					}
				}
		);
		
		//逻辑过于复杂，需要优化
		JSONObject rqJson = new JSONObject();
		for(String rq : rqList){
			JSONArray barArray = new JSONArray();
			JSONArray pieArray = new JSONArray();
			
			int nextDmNum = 0;
			for(Map<String, Object> map : list){
				
				if(rq.equals(map.get("rq"))){
					Map<String, String> tempBarMap = new HashMap<String, String>();
					Map<String, String> tempPieMap = new HashMap<String, String>();
					
					int i = nextDmNum;
					
					for(;i<=dmList.size()-1;i++){
						TypeVO vo = dmList.get(i);
						if(vo.getType_mc().equals(map.get("name"))){
							tempBarMap.put("name", (String) map.get("name"));
							tempBarMap.put("value", (Double) map.get("value") + "");
							tempPieMap.put("name", (String) map.get("name"));
							tempPieMap.put("value", (Double) map.get("value") + "");
							barArray.add(tempBarMap);
							nextDmNum++;
							break;
						}else{
							tempBarMap.put("name", vo.getType_mc());
							tempBarMap.put("value", "-");
							nextDmNum++;
							barArray.add(tempBarMap);
						}
					}
					
					pieArray.add(tempPieMap);
				}
			}
			rqJson.put(rq, barArray);
			rqJson.put(rq+"pie", pieArray);
		}
		
		sql = "SELECT MAX(A.VALUE) AS MAXVALUE FROM (SELECT T.ACCOUNT_ID AS RQ,DM.DETAILTYPE_DM, DM.DETAILTYPE_MC AS NAME, SUM(T.JE) AS VALUE" +
		" FROM T_SH_ACCOUNTDETAIL T, T_DM_DETAILTYPE DM" +
		" WHERE  T.DETAILTYPE_DM = DM.DETAILTYPE_DM(+) AND T.ACCOUNTTYPE_DM = '02'" + whereStr + 
		" GROUP BY T.ACCOUNT_ID,DM.DETAILTYPE_DM, DM.DETAILTYPE_MC) A";
		
		int maxvalue = this.jdbcTemplate.queryForObject(sql, Integer.class);
		
		List<String> xAxisList = new ArrayList<String>();
		for(TypeVO vo : dmList){
			xAxisList.add(vo.getType_mc());
		}
		
		jo.put("timelineData", rqList);
		jo.put("maxYAxis", maxvalue);
		jo.put("xAxisData", xAxisList);
		jo.put("dmData", rqJson);
		
		return jo;
	}
}
