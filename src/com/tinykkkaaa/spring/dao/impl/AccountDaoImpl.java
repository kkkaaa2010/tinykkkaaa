package com.tinykkkaaa.spring.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.tinykkkaaa.comm.util.CommUtil;
import com.tinykkkaaa.comm.util.DateUtil;
import com.tinykkkaaa.comm.util.QueryUtil;
import com.tinykkkaaa.platform.framework.dao.JdbcTemplateCallBack;
import com.tinykkkaaa.spring.dao.AccountDao;
import com.tinykkkaaa.spring.vo.DetailAccountVO;
import com.tinykkkaaa.spring.vo.DetailAccountXmVO;
import com.tinykkkaaa.spring.vo.DetailXmVO;
import com.tinykkkaaa.spring.vo.TypeVO;
import com.tinykkkaaa.spring.vo.ValueVO;

@Repository("accountDao")
public class AccountDaoImpl implements AccountDao {
	
	@Resource
	private JdbcTemplate jdbcTemplate;

	/*
	 * 获取账单明细类型
	 * @see com.tinykkkaaa.spring.dao.AccountDao#getDetailType()
	 */
	@Override
	public JSONArray getDetailType() throws Exception {
		JSONArray dyJA = new JSONArray();
		String sql = "SELECT DETAILTYPE_DM DM, DETAILTYPE_MC MC FROM T_DM_DETAILTYPE WHERE XY_BZ = '1'";
		
		List<TypeVO> dTypeList = this.jdbcTemplate.query(sql, new RowMapper<TypeVO>(){
			@Override
			public TypeVO mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				TypeVO vo = new TypeVO();
				vo.setType_dm(rs.getString("DM"));
				vo.setType_mc(rs.getString("MC"));
				return vo;
			}
		});
		
		for(TypeVO vo : dTypeList){
			JSONObject jo = new JSONObject();
			jo.put("detailtype_dm", vo.getType_dm());
			jo.put("detailtype_mc", vo.getType_mc());
			dyJA.add(jo);
		}
		return dyJA;
	}
	
	/**
	 * 获取消费项目
	 */
	@Override
	public JSONArray getDetailXm(String dm, String accounttype_dm) throws Exception {
		JSONArray dyJA = new JSONArray();
		String whereStr = "";
		if(dm!=null && !"".equals(dm.trim())){
			whereStr += " AND DETAILTYPE_DM = '"+ dm +"'";
		}
		if(accounttype_dm!=null && "01".equals(accounttype_dm.trim())){
			whereStr += " AND DETAILTYPE_DM IS NULL";
		}else if(accounttype_dm!=null && "02".equals(accounttype_dm.trim())){
			whereStr += " AND DETAILTYPE_DM IS NOT NULL";
		}
		String sql = "SELECT DETAILXM_DM DM, DETAILXM_MC MC FROM T_DM_DETAILXM WHERE XY_BZ = '1'"+whereStr;
		
		List<TypeVO> dTypeList = this.jdbcTemplate.query(sql, new RowMapper<TypeVO>(){
			@Override
			public TypeVO mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				TypeVO vo = new TypeVO();
				vo.setType_dm(rs.getString("DM"));
				vo.setType_mc(rs.getString("MC"));
				return vo;
			}
		});
		
		for(TypeVO vo : dTypeList){
			JSONObject jo = new JSONObject();
			jo.put("detailxm_dm", vo.getType_dm());
			jo.put("detailxm_mc", vo.getType_mc());
			dyJA.add(jo);
		}
		return dyJA;
	}

	/**
	 * 获取明细账单列表
	 */
	@Override
	public JSONObject getAccountList(String accounttype_dm) throws Exception {
		JSONObject jo = new JSONObject();
		
		if(accounttype_dm==null || "".equals(accounttype_dm.trim())){
			accounttype_dm = "02";
		}
		
		String sql = "SELECT COUNT(1) AS NUM, SUM(JE) AS JE FROM T_SH_ACCOUNTDETAIL" +
				" WHERE TRUNC(LR_SJ,'DD') = TRUNC(SYSDATE,'DD') AND ACCOUNTTYPE_DM = ?";
		Map<String, String> totalMap = this.jdbcTemplate.queryForObject(sql, new Object[]{accounttype_dm}, new RowMapper<Map<String, String>>(){
			@Override
			public Map<String, String> mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				Map<String, String> map = new HashMap<String, String>();
				map.put("num", rs.getInt("NUM")+"");
				map.put("je", rs.getDouble("JE")+"");
				return map;
			}
		});
		
		sql = "SELECT T.DETAIL_ID, T.DETAILTYPE_DM, TYPE1.DETAILTYPE_MC, T.DETAILXM_DM, TYPE2.DETAILXM_MC, T.DETAIL_CONTENT, T.DETAIL_TIME, T.JE" +
			  " FROM T_SH_ACCOUNTDETAIL T, T_DM_DETAILTYPE TYPE1, T_DM_DETAILXM TYPE2 WHERE T.DETAILTYPE_DM = TYPE1.DETAILTYPE_DM(+)" +
			  " AND T.DETAILXM_DM = TYPE2.DETAILXM_DM(+) AND TRUNC(T.LR_SJ,'DD') = TRUNC(SYSDATE,'DD') AND T.ACCOUNTTYPE_DM = ?" +
			  " ORDER BY T.DETAIL_ID";
		List<DetailAccountVO> list = this.jdbcTemplate.query(sql, new Object[]{accounttype_dm}, new RowMapper<DetailAccountVO>(){

			@Override
			public DetailAccountVO mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				DetailAccountVO vo = new DetailAccountVO();
				vo.setDetailtype_dm(rs.getString("DETAILTYPE_DM"));
				vo.setDetailtype_mc(rs.getString("DETAILTYPE_MC"));
				vo.setDetailxm_dm(rs.getString("DETAILXM_DM"));
				vo.setDetailxm_mc(rs.getString("DETAILXM_MC"));
				vo.setDetail_content(rs.getString("DETAIL_CONTENT"));
				vo.setDetail_je(rs.getDouble("JE"));
				vo.setDetail_time(rs.getString("DETAIL_TIME"));
				return vo;
			}
			
		});
		
		jo.put("total", totalMap.get("num"));
		
		//账单列表
		JSONArray jsonRows = new JSONArray();
		for(DetailAccountVO vo : list){
			JSONObject row = new JSONObject();
			row.put("detailtype_dm", vo.getDetailtype_dm());
			row.put("detailtype_mc", vo.getDetailtype_mc());
			row.put("detailxm_dm", vo.getDetailxm_dm());
			row.put("detailxm_mc", vo.getDetailxm_mc());
			row.put("detail_content", vo.getDetail_content());
			row.put("detail_je", vo.getDetail_je());
			row.put("detail_time", vo.getDetail_time());
			jsonRows.add(row);
		}
		jo.put("rows", jsonRows);

		//总计数据
		JSONArray jsonFooters = new JSONArray();
		JSONObject footer = new JSONObject();
		//footer.put("detail_time", "总计消费：");
		footer.put("detail_je", "总计消费："+totalMap.get("je"));
		jsonFooters.add(footer);
		jo.put("footer", jsonFooters);
		
		return jo;
	}

	/**
	 * 保存账单
	 */
	@Override
	public String saveAccount(List<DetailAccountVO> list, String accounttype_dm) throws Exception {
		String result = "保存成功";
		
		String accountid = DateUtil.getDate("01");
		
		if(accounttype_dm==null || "".equals(accounttype_dm.trim())){
			accounttype_dm = "02";
		}
		
		//String deleteSql = "DELETE FROM T_SH_ACCOUNT T WHERE T.ACCOUNT_ID = ?";
		//this.jdbcTemplate.update(deleteSql, new Object[]{accountid});		
		String deleteSql = "DELETE FROM T_SH_ACCOUNTDETAIL T WHERE T.ACCOUNT_ID = ? AND T.ACCOUNTTYPE_DM = ?";
		this.jdbcTemplate.update(deleteSql, new Object[]{accountid,accounttype_dm});
	
		String insertSql = "INSERT INTO T_SH_ACCOUNTDETAIL(DETAIL_ID, ACCOUNT_ID, ACCOUNTTYPE_DM, DETAILTYPE_DM, DETAILXM_DM, DETAIL_CONTENT, DETAIL_TIME, JE, LR_SJ)" +
						   " VALUES (?, ?, ?, ?, ?, ?, ?, ?, SYSDATE)";
		Object[] objs = null;
		
		double totalJe = 0;
		for(DetailAccountVO vo : list){
			String detailid = vo.getId();
			String detailtype_dm = vo.getDetailtype_dm();
			String detailxm_dm = vo.getDetailxm_dm();
			String detail_content = vo.getDetail_content();
			double detail_je = vo.getDetail_je();
			String detail_time = vo.getDetail_time();
			
//			String selectSql = "";
//			selectSql += "SELECT '"+detailid+"', '"+accountid+"', '02', '"+detailtype_dm+"', '"
//						 +detailxm_dm+"', '"+detail_content+"', '"+ detail_time + "', "+detail_je+", SYSDATE FROM DUAL "
//						 +"UNION ALL ";	
//			selectSql = selectSql.substring(0,selectSql.length()-10);
			
			objs = new Object[8];
			objs[0] = detailid;
			objs[1] = accountid;
			objs[2] = accounttype_dm;
			objs[3] = detailtype_dm;
			objs[4] = detailxm_dm;
			objs[5] = detail_content;
			objs[6] = detail_time;
			objs[7] = detail_je;
			//添加明细账单
			this.jdbcTemplate.update(insertSql, objs);
			
			totalJe += detail_je;
		}
		
		//添加今日账单总表信息
		int i = 0;
		String updateSql = "";
		final String finalSelectSql = "SELECT ACCOUNT_ID FROM T_SH_ACCOUNT WHERE ACCOUNT_ID = ?";
		final String finalAccountid = accountid;
		//String aid = this.jdbcTemplate.queryForObject(selectSql, new Object[]{accountid}, String.class);
		String aid = this.queryNullAble(new JdbcTemplateCallBack<String>() {
			@Override
			public String queryForObject(JdbcTemplate jdbcTemplate) {
				return jdbcTemplate.queryForObject(finalSelectSql, new Object[]{finalAccountid}, String.class);
			}
		});
		if(aid==null || "".equals(aid.trim())){
			if("01".equals(accounttype_dm)){
				insertSql = "INSERT INTO T_SH_ACCOUNT(ACCOUNT_ID, INCOME, ACCOUNT_DATE) "
					+"SELECT '"+accountid+"', "+ totalJe +", SYSDATE FROM DUAL";
			}else{
				insertSql = "INSERT INTO T_SH_ACCOUNT(ACCOUNT_ID, EXPENSES, ACCOUNT_DATE) "
					+"SELECT '"+accountid+"', "+ totalJe +", SYSDATE FROM DUAL";
			}
			i = this.jdbcTemplate.update(insertSql);
		}else{
			if("01".equals(accounttype_dm)){
				updateSql = "UPDATE T_SH_ACCOUNT T SET T.INCOME = ? WHERE T.ACCOUNT_ID = ?";
			}else{
				updateSql = "UPDATE T_SH_ACCOUNT T SET T.EXPENSES = ? WHERE T.ACCOUNT_ID = ?";
			}
			i = this.jdbcTemplate.update(updateSql, new Object[]{totalJe, accountid});
		}
		
		if(i<=0){
			result = "保存失败";
		}
		return result;
	}
	
	/**
	 * 获取当月支出账单列表
	 */
	@Override
	public JSONObject getAccountxmList(String id) throws Exception {
		JSONObject jo = new JSONObject();
		
		String todayMonth = DateUtil.getDate("01").substring(0,6);
		String day01 = todayMonth+"01";
		String day15 = todayMonth+"15";
		String day16 = todayMonth+"16";
		
		String sql = "SELECT XM.DETAILXM_DM,XM.DETAILXM_MC," +
				" CASE WHEN T.LR_SJ >= TO_DATE('"+ day01 +"','yyyymmdd') AND T.LR_SJ < TO_DATE('"+ day16 +"','yyyymmdd') THEN '本月1号-15号'" +
				" WHEN T.LR_SJ >= TO_DATE('"+ day15 +"','yyyymmdd') THEN '本月16号-月末' END AS GROUPRQ, SUM(T.JE) AS JE" +
				" FROM   T_SH_ACCOUNTDETAIL T, T_DM_DETAILXM XM " +
				" WHERE  T.DETAILXM_DM = XM.DETAILXM_DM AND T.ACCOUNTTYPE_DM = '02'" +
				" AND    T.DETAILTYPE_DM = '"+ id +"' AND SUBSTR(T.ACCOUNT_ID,1,6) = TO_CHAR(SYSDATE, 'YYYYMM')" +
				" GROUP BY XM.DETAILXM_DM, XM.DETAILXM_MC," +
				" CASE WHEN T.LR_SJ >= TO_DATE('"+ day01 +"','yyyymmdd') AND T.LR_SJ < TO_DATE('"+ day16 +"','yyyymmdd') THEN '本月1号-15号' WHEN T.LR_SJ >= TO_DATE('"+ day15 +"','yyyymmdd') THEN '本月16号-月末' END";
		List<DetailAccountXmVO> list = this.jdbcTemplate.query(sql, new RowMapper<DetailAccountXmVO>(){

			@Override
			public DetailAccountXmVO mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				DetailAccountXmVO vo = new DetailAccountXmVO();
				vo.setName(rs.getString("DETAILXM_MC"));
				vo.setValue(rs.getDouble("JE")+"");
				vo.setGroup(rs.getString("GROUPRQ"));
				return vo;
			}
			
		});
		
		//本月支出账单列表
		JSONArray jsonRows = new JSONArray();
		int total = 0;
		for(DetailAccountXmVO vo : list){
			JSONObject row = new JSONObject();
			row.put("name", vo.getName());
			row.put("value", vo.getValue());
			row.put("group", vo.getGroup());
			jsonRows.add(row);
			total++;
		}
		jo.put("total", total);
		jo.put("rows", jsonRows);
		
		return jo;
	}

	/**
	 * 初始化获取当月账单合计
	 */
	@Override
	public JSONObject getBillTotal() throws Exception {
		JSONObject jsonBill = new JSONObject();
		
		//1. 获取当月支出与收入总计
		String sql = "SELECT SUM(T.INCOME) AS INCOME, SUM(T.EXPENSES) AS EXPENSES" +
				" FROM   T_SH_ACCOUNT T " +
				" WHERE  SUBSTR(T.ACCOUNT_ID,1,6) = TO_CHAR(SYSDATE,'YYYYMM')";
		Map<String, String> totalMap = this.jdbcTemplate.queryForObject(sql, new RowMapper<Map<String, String>>(){
			@Override
			public Map<String, String> mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				Map<String, String> map = new HashMap<String, String>();
				map.put("income", rs.getDouble("INCOME")+"");
				map.put("expenses", rs.getDouble("EXPENSES")+"");
				return map;
			}
		});
		jsonBill.put("income", totalMap.get("income"));
		jsonBill.put("expenses", totalMap.get("expenses"));
		
		//2. 获取当月支出各项总计
		sql = "SELECT T.DETAILTYPE_DM,SUM(T.JE) AS JE" +
			  " FROM   T_SH_ACCOUNTDETAIL T " +
			  " WHERE  T.ACCOUNTTYPE_DM = '02' AND SUBSTR(T.ACCOUNT_ID,1,6) = TO_CHAR(SYSDATE,'YYYYMM')" +
			  " GROUP  BY T.DETAILTYPE_DM";
		
		List<ValueVO> list = this.jdbcTemplate.query(sql, new RowMapper<ValueVO>(){

			@Override
			public ValueVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				ValueVO vo = new ValueVO();
				vo.setName(rs.getString("DETAILTYPE_DM"));
				vo.setValue(rs.getDouble("JE")+"");
				return vo;
			}
		});
		for(ValueVO vo : list){
			jsonBill.put("expenses"+vo.getName(), vo.getValue());
		}
		
		return jsonBill;
	}

	@Override
	public JSONObject getBillList(Map<String, String> params) throws Exception {
		JSONObject jo = new JSONObject();
		
		String whereStr = "";
		String orderStr = " ORDER BY T.LR_SJ";
		
		String fssj_q = params.get("fssj_q");
		String fssj_z = params.get("fssj_z");
		String zdlx = params.get("zdlx");
		String zdxm = params.get("zdxm");
		String je_q = params.get("je_q");
		String je_z = params.get("je_z");
		
		if(fssj_q!=null && !"".equals(fssj_q.trim())){
			whereStr += " AND T.ACCOUNT_ID >= TO_CHAR(TO_DATE('"+ fssj_q +"','YYYY-MM-DD'),'YYYYMMDD')";
		}
		if(fssj_z!=null && !"".equals(fssj_z.trim())){
			whereStr += " AND T.ACCOUNT_ID <= TO_CHAR(TO_DATE('"+ fssj_z +"','YYYY-MM-DD'),'YYYYMMDD')";
		}
		if(zdlx!=null && !"".equals(zdlx.trim())){
			whereStr += " AND T.ACCOUNTTYPE_DM = '" + zdlx + "'";
		}
		if(zdxm!=null && !"".equals(zdxm.trim())){
			whereStr += " AND T.DETAILXM_DM = '" + zdxm + "'";
		}
		if(je_q!=null && !"".equals(je_q.trim())){
			whereStr += " AND T.JE >= " + je_q;
		}
		if(je_z!=null && !"".equals(je_z.trim())){
			whereStr += " AND T.JE <= " + je_z;
		}
		
		String sql = "SELECT COUNT(1) AS NUM FROM T_SH_ACCOUNTDETAIL T WHERE 1=1" + whereStr;
		Map<String, String> totalMap = this.jdbcTemplate.queryForObject(sql, new RowMapper<Map<String, String>>(){
			@Override
			public Map<String, String> mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				Map<String, String> map = new HashMap<String, String>();
				map.put("num", rs.getInt("NUM")+"");
				return map;
			}
		});
		
		sql = "SELECT T.DETAIL_ID,T.ACCOUNT_ID,T.ACCOUNTTYPE_DM,TYPE.ACCOUNTTYPE_MC,DTYPE.DETAILTYPE_MC,XM.DETAILXM_MC,T.DETAIL_CONTENT,T.JE,TO_CHAR(T.LR_SJ,'YYYY-MM-DD hh:MI:ss') AS LRSJ" +
				" FROM   T_SH_ACCOUNTDETAIL T, T_DM_ACCOUNTTYPE   TYPE, T_DM_DETAILTYPE    DTYPE, T_DM_DETAILXM      XM " +
				" WHERE  T.ACCOUNTTYPE_DM = TYPE.ACCOUNTTYPE_DM(+) AND    T.DETAILTYPE_DM = DTYPE.DETAILTYPE_DM(+) AND    T.DETAILXM_DM = XM.DETAILXM_DM(+)"
				+ whereStr + orderStr;
		List<DetailAccountVO> list = this.jdbcTemplate.query(sql, new RowMapper<DetailAccountVO>(){

			@Override
			public DetailAccountVO mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				DetailAccountVO vo = new DetailAccountVO();
				vo.setDetailid(rs.getString("DETAIL_ID"));
				vo.setId(rs.getString("ACCOUNT_ID"));
				vo.setAccounttype_dm(rs.getString("ACCOUNTTYPE_DM"));
				vo.setAccounttype_mc(rs.getString("ACCOUNTTYPE_MC"));
				vo.setDetailtype_mc(rs.getString("DETAILTYPE_MC"));
				vo.setDetailxm_mc(rs.getString("DETAILXM_MC"));
				vo.setDetail_content(rs.getString("DETAIL_CONTENT"));
				vo.setDetail_je(rs.getDouble("JE"));
				vo.setLrsj(rs.getString("LRSJ"));
				return vo;
			}
			
		});
		
		jo.put("total", totalMap.get("num"));
		
		//账单列表
		JSONArray jsonRows = new JSONArray();
		for(DetailAccountVO vo : list){
			JSONObject row = new JSONObject();
			row.put("detailid", vo.getDetailid());
			row.put("id", vo.getId());
			row.put("accounttype_dm", vo.getAccounttype_dm());
			row.put("accounttype_mc", vo.getAccounttype_mc());
			row.put("detailtype_mc", vo.getDetailtype_mc());
			row.put("detailxm_mc", vo.getDetailxm_mc());
			row.put("detail_content", vo.getDetail_content());
			row.put("detail_je", vo.getDetail_je());
			row.put("lrsj", vo.getLrsj());
			jsonRows.add(row);
		}
		jo.put("rows", jsonRows);
		
		return jo;
	}

	@Override
	public JSONObject getDetailXmList() throws Exception {
		JSONObject jo = new JSONObject();
		
		String sql = "SELECT COUNT(1) AS NUM FROM T_DM_DETAILXM WHERE XY_BZ = '1'";
		Map<String, String> totalMap = this.jdbcTemplate.queryForObject(sql, new RowMapper<Map<String, String>>(){
			@Override
			public Map<String, String> mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				Map<String, String> map = new HashMap<String, String>();
				map.put("num", rs.getInt("NUM")+"");
				return map;
			}
		});
		
		sql = "SELECT XM.DETAILXM_DM,XM.DETAILXM_MC,TYPE.DETAILTYPE_DM,TYPE.DETAILTYPE_MC FROM T_DM_DETAILXM XM, T_DM_DETAILTYPE TYPE WHERE XM.DETAILTYPE_DM = TYPE.DETAILTYPE_DM(+) AND XM.XY_BZ = '1'" +
			  " ORDER BY TYPE.DETAILTYPE_DM, XM.DETAILXM_DM";
		List<DetailAccountVO> list = this.jdbcTemplate.query(sql, new RowMapper<DetailAccountVO>(){

			@Override
			public DetailAccountVO mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				DetailAccountVO vo = new DetailAccountVO();
				vo.setDetailtype_dm(rs.getString("DETAILTYPE_DM"));
				vo.setDetailtype_mc(rs.getString("DETAILTYPE_MC"));
				vo.setDetailxm_dm(rs.getString("DETAILXM_DM"));
				vo.setDetailxm_mc(rs.getString("DETAILXM_MC"));
				return vo;
			}
			
		});
		
		jo.put("total", totalMap.get("num"));
		
		//账单列表
		JSONArray jsonRows = new JSONArray();
		for(DetailAccountVO vo : list){
			JSONObject row = new JSONObject();
			
			String dm = vo.getDetailtype_dm();
			if(dm == null || "".equals(dm)){
				row.put("accounttype_dm", "01");
				row.put("accounttype_mc", "收入");
			}else{
				row.put("accounttype_dm", "02");
				row.put("accounttype_mc", "支出");	
			}
			row.put("detailtype_dm", vo.getDetailtype_dm());
			row.put("detailtype_mc", vo.getDetailtype_mc());
			
			row.put("xmdm", vo.getDetailxm_dm());
			row.put("xm", vo.getDetailxm_mc());
			jsonRows.add(row);
		}
		jo.put("rows", jsonRows);
		
		return jo;
	}

	@Override
	public String delDetailXm(String dms) throws Exception {
		String result = "删除成功";
		
		String dmArray[] = dms.split(",");
		String whereSql = " AND DETAILXM_DM IN (";
		for(int i=0; i<dmArray.length; i++){
			whereSql += "'"+dmArray[i]+"', ";
		}
		whereSql = whereSql.substring(0, whereSql.length()-2) + ")";
		
		String sql = "DELETE FROM T_DM_DETAILXM T WHERE 1=1"+whereSql;
		
		int i = this.jdbcTemplate.update(sql);
		if(i <= 0){
			result = "删除失败";
		}
		
		return result;
	}

	@Override
	public String addDetailXm(DetailXmVO vo) throws Exception {
		String result = "新增成功";
		String sql = "INSERT INTO T_DM_DETAILXM(DETAILXM_DM, DETAILXM_MC, XY_BZ, DETAILTYPE_DM) VALUES(?, ?, '1', ?)";
		String key = QueryUtil.getNextPrimaryKey("T_DM_DETAILXM", "DETAILXM_DM", 2);
		int i = this.jdbcTemplate.update(sql, new Object[]{key,vo.getXmmc(),vo.getDetailtype_dm()});
		if(i <= 0){
			result = "新增失败";
		}
		return result;
	}

	@Override
	public DetailXmVO queryDetailXm(String xmdm) throws Exception {
		DetailXmVO vo = new DetailXmVO();
		String sql = "SELECT DETAILXM_DM AS xmdm, DETAILXM_MC AS xmmc, DETAILTYPE_DM AS detailtype_dm," +
					 " CASE WHEN DETAILTYPE_DM IS NULL THEN '01' ELSE '02' END AS accounttype_dm" +
					 " FROM T_DM_DETAILXM WHERE XY_BZ = '1' AND DETAILXM_DM = ?";
		
		vo = this.jdbcTemplate.queryForObject(sql, new Object[]{xmdm}, vo);
		
		return vo;
	}

	@Override
	public String updateDetailXm(DetailXmVO vo) throws Exception {
		String result = "修改成功";
		String sql = "UPDATE T_DM_DETAILXM T SET T.DETAILXM_MC = ?, T.DETAILTYPE_DM = ? WHERE T.DETAILXM_DM = ?";
		Object[] objs = new Object[3];
		objs[0] = vo.getXmmc();
		objs[1] = vo.getDetailtype_dm();
		objs[2] = vo.getXmdm();
		
		int i = this.jdbcTemplate.update(sql, objs);
		if(i <= 0){
			result = "修改失败";
		}
		return result;
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

	@Override
	public DetailAccountVO queryDetailAccount(Map<String, String> map)
			throws Exception {
		Object[] objs = new Object[3];
		objs[0] = map.get("detailid");
		objs[1] = map.get("accountid");
		objs[2] = map.get("accounttype_dm");
		DetailAccountVO vo = new DetailAccountVO();
		String sql = "SELECT DETAIL_ID AS detailid, ACCOUNT_ID AS id, ACCOUNTTYPE_DM AS accounttype_dm," +
				" DETAILTYPE_DM AS detailtype_dm, DETAILXM_DM AS detailxm_dm, DETAIL_CONTENT AS detail_content, JE AS detail_je, DETAIL_TIME AS detail_time" +
				" FROM T_SH_ACCOUNTDETAIL WHERE DETAIL_ID = ? AND ACCOUNT_ID = ? AND ACCOUNTTYPE_DM = ?";

		vo = this.jdbcTemplate.queryForObject(sql, objs, new RowMapper<DetailAccountVO>(){
			@Override
			public DetailAccountVO mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				DetailAccountVO tmp = new DetailAccountVO();
				tmp.setDetailid(rs.getString("detailid"));
				tmp.setId(rs.getString("id"));
				tmp.setAccounttype_dm(rs.getString("accounttype_dm"));
				tmp.setDetailtype_dm(rs.getString("detailtype_dm"));
				tmp.setDetailxm_dm(rs.getString("detailxm_dm"));
				tmp.setDetail_content(rs.getString("detail_content"));
				tmp.setDetail_je(rs.getDouble("detail_je"));
				tmp.setDetail_time(rs.getString("detail_time"));
				return tmp;
			}
		});
		
		return vo;
	}

	@Override
	public String updateDetailAccount(DetailAccountVO vo) throws Exception {
		String result = "修改成功";
		String sql = "UPDATE T_SH_ACCOUNTDETAIL T SET T.DETAILTYPE_DM = ?, T.DETAILXM_DM = ?, DETAIL_CONTENT = ?, T.JE = ?, T.DETAIL_TIME = ?" +
				" WHERE T.ACCOUNT_ID = ? AND T.ACCOUNTTYPE_DM = ? AND T.DETAIL_ID = ?";
		Object[] objs = new Object[8];
		objs[0] = vo.getDetailtype_dm();
		objs[1] = vo.getDetailxm_dm();
		objs[2] = vo.getDetail_content();
		objs[3] = vo.getDetail_je();
		objs[4] = vo.getDetail_time();
		objs[5] = vo.getId();
		objs[6] = vo.getAccounttype_dm();
		objs[7] = vo.getDetailid();
		
		int i = this.jdbcTemplate.update(sql, objs);
		setTotalAccount(vo.getId());
		if(i <= 0){
			result = "修改失败";
		}
		return result;
	}

	@Override
	public JSONObject getDetailAccountById(Map<String, String> params)
			throws Exception {
		Object[] objs = new Object[3];
		objs[0] = params.get("detailid");
		objs[1] = params.get("accountid");
		objs[2] = params.get("accounttype_dm");
		String sql = "SELECT T.DETAIL_ID,T.ACCOUNT_ID,T.ACCOUNTTYPE_DM,TYPE.ACCOUNTTYPE_MC,DTYPE.DETAILTYPE_MC,XM.DETAILXM_MC,T.DETAIL_CONTENT,T.JE,TO_CHAR(T.LR_SJ,'YYYY-MM-DD hh:MI:ss') AS LRSJ" +
				" FROM   T_SH_ACCOUNTDETAIL T, T_DM_ACCOUNTTYPE   TYPE, T_DM_DETAILTYPE    DTYPE, T_DM_DETAILXM      XM " +
				" WHERE  T.ACCOUNTTYPE_DM = TYPE.ACCOUNTTYPE_DM(+) AND    T.DETAILTYPE_DM = DTYPE.DETAILTYPE_DM(+) AND    T.DETAILXM_DM = XM.DETAILXM_DM(+)" +
				" AND T.DETAIL_ID = ? AND T.ACCOUNT_ID = ? AND T.ACCOUNTTYPE_DM = ?";

		JSONObject jo = this.jdbcTemplate.queryForObject(sql, objs, new RowMapper<JSONObject>(){
			@Override
			public JSONObject mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				JSONObject row = new JSONObject();
				row.put("detailid", rs.getString("DETAIL_ID"));
				row.put("id", rs.getString("ACCOUNT_ID"));
				row.put("accounttype_dm", rs.getString("ACCOUNTTYPE_DM"));
				row.put("accounttype_mc", rs.getString("ACCOUNTTYPE_MC"));
				row.put("detailtype_mc", rs.getString("DETAILTYPE_MC"));
				row.put("detailxm_mc", rs.getString("DETAILXM_MC"));
				row.put("detail_content", rs.getString("DETAIL_CONTENT"));
				row.put("detail_je", rs.getDouble("JE"));
				row.put("lrsj", rs.getString("LRSJ"));
				return row;
			}
		});
		
		return jo;
	}

	@Override
	public String delDetailAccountById(Map<String, String> params)
			throws Exception {
		String message = "删除失败";
		Object[] objs = new Object[3];
		objs[0] = params.get("detailid");
		objs[1] = params.get("accountid");
		objs[2] = params.get("accounttype_dm");
		
		String sql = "DELETE FROM T_SH_ACCOUNTDETAIL T WHERE T.DETAIL_ID = ? AND T.ACCOUNT_ID = ? AND T.ACCOUNTTYPE_DM = ?";
		int i = this.jdbcTemplate.update(sql, objs);
		
		setTotalAccount(params.get("accountid"));
		
		if(i > 0){
			message = "删除成功";
		}
		return message;
	}
	
	/**
	 * 操作账单完成，设置T_SH_ACCOUNT
	 * @param accountid
	 * @return
	 */
	public boolean setTotalAccount(String accountid){
		boolean sign = false;
		String sql = "SELECT SUM(CASE WHEN T.ACCOUNTTYPE_DM='01' THEN T.JE ELSE 0 END) AS INCOME, " +
					 " SUM(CASE WHEN T.ACCOUNTTYPE_DM='02' THEN T.JE ELSE 0 END) AS EXPENSES" +
					 " FROM T_SH_ACCOUNTDETAIL T WHERE T.ACCOUNT_ID = ?";
		Map<String, Double> totalMap = this.jdbcTemplate.queryForObject(sql, new Object[]{accountid}, new RowMapper<Map<String, Double>>(){
			@Override
			public Map<String, Double> mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				Map<String, Double> tmpMap = new HashMap<String, Double>();
				tmpMap.put("INCOME", rs.getDouble("INCOME"));
				tmpMap.put("EXPENSES", rs.getDouble("EXPENSES"));
				return tmpMap;
			}
		});
		
		sql = "UPDATE T_SH_ACCOUNT T SET T.INCOME = ?, T.EXPENSES = ? WHERE T.ACCOUNT_ID = ?";
		int i = this.jdbcTemplate.update(sql, new Object[]{totalMap.get("INCOME"), totalMap.get("EXPENSES"), accountid});
		
		if(i > 0){
			sign = true;
		}
		return sign;
	}

	@Override
	public String addBill(Map<String, String> params) throws Exception {
		String message = "00";
		String accounttype = params.get("accounttype");
		String accounttime = params.get("accounttime");
		
		String sr_srxm = params.get("sr_srxm");
		String sr_srsm = params.get("sr_srsm");
		String sr_srsj = params.get("sr_srsj");
		String sr_srje = params.get("sr_srje");
		
		String zc_zclx = params.get("zc_zclx");
		String zc_zcxm = params.get("zc_zcxm");
		String zc_zcsm = params.get("zc_zcsm");
		String zc_zcsj = params.get("zc_zcsj");
		String zc_zcje = params.get("zc_zcje");
		
		String tmpAccountid = DateUtil.strConvertStr(accounttime);
		
		//1. 获取最大DETAIL_ID
		final String selectSql = "SELECT TO_NUMBER(MAX(DETAIL_ID)) AS DETAILID" +
				" FROM T_SH_ACCOUNTDETAIL WHERE ACCOUNT_ID = '"+ tmpAccountid +"' AND ACCOUNTTYPE_DM = '"+ accounttype +"'";
		Integer detailid = this.queryNullAble(new JdbcTemplateCallBack<Integer>() {
			@Override
			public Integer queryForObject(JdbcTemplate jdbcTemplate) {
				return jdbcTemplate.queryForObject(selectSql, Integer.class);
			}
		});
		if(detailid == null){
			detailid = 1;
		}else{
			detailid += 1;
		}
		
		//2. 查询账单表里时候存在该日记录信息
		final String selectSql2 = "SELECT DISTINCT ACCOUNT_ID FROM T_SH_ACCOUNTDETAIL WHERE ACCOUNT_ID = '"+ tmpAccountid +"'";
		String hisID = this.queryNullAble(new JdbcTemplateCallBack<String>() {
			@Override
			public String queryForObject(JdbcTemplate jdbcTemplate) {
				return jdbcTemplate.queryForObject(selectSql2, String.class);
			}
		});
		
		//3. 补录账单
		String insertSql = "INSERT INTO T_SH_ACCOUNTDETAIL(DETAIL_ID, ACCOUNT_ID, ACCOUNTTYPE_DM, DETAILTYPE_DM, DETAILXM_DM, DETAIL_CONTENT, DETAIL_TIME, JE, LR_SJ)" +
		   " VALUES (?, ?, ?, ?, ?, ?, ?, ?, SYSDATE)";
		
		Object[] objs = new Object[8];
		if("01".equals(accounttype)){
			objs[0] = detailid;
			objs[1] = tmpAccountid;
			objs[2] = accounttype;
			objs[3] = null;
			
			objs[4] = sr_srxm;
			objs[5] = sr_srsm;
			objs[6] = sr_srsj;
			objs[7] = Double.valueOf(sr_srje);
		}else{
			objs[0] = detailid;
			objs[1] = tmpAccountid;
			objs[2] = accounttype;
			objs[3] = zc_zclx;
			
			objs[4] = zc_zcxm;
			objs[5] = zc_zcsm;
			objs[6] = zc_zcsj;
			objs[7] = Double.valueOf(zc_zcje);
		}
		int i = this.jdbcTemplate.update(insertSql, objs);
		
		//4. 更新账单总表T_SH_ACCOUNT
		if(CommUtil.isNull(hisID)){
			if("01".equals(accounttype)){
				insertSql = "INSERT INTO T_SH_ACCOUNT(ACCOUNT_ID, INCOME, ACCOUNT_DATE) "
					+"SELECT '"+tmpAccountid+"', "+ sr_srje +", SYSDATE FROM DUAL";
			}else{
				insertSql = "INSERT INTO T_SH_ACCOUNT(ACCOUNT_ID, EXPENSES, ACCOUNT_DATE) "
					+"SELECT '"+tmpAccountid+"', "+ zc_zcje +", SYSDATE FROM DUAL";
			}
			this.jdbcTemplate.update(insertSql);
		}else{
			this.setTotalAccount(tmpAccountid);
		}
		
		if(i > 0){
			message = "01";
		}
		return message;
	}
}
