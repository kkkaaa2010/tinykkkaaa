package com.tinykkkaaa.account.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.tinykkkaaa.account.dao.AccountUploadDao;
import com.tinykkkaaa.account.entity.DetailAccount;
import com.tinykkkaaa.platform.core.dao.DataPersistenceObject;
import com.tinykkkaaa.platform.core.dao.DataSet;

public class AccountUploadDaoImpl implements AccountUploadDao {
	
	private DataPersistenceObject dpo;
	
	public AccountUploadDaoImpl(DataPersistenceObject dpo){
		this.dpo = dpo;
	}

	@Override
	public String uploadExcel(List<DetailAccount> list) throws Exception {
		String message = "00";
		
		String selectSql = "SELECT DETAIL_ID FROM T_SH_ACCOUNTDETAIL WHERE ACCOUNT_ID = ? AND ACCOUNTTYPE_DM = ?";
		String selectSql2 = "SELECT TO_CHAR(MAX(TO_NUMBER(DETAIL_ID))) AS DETAILID FROM T_SH_ACCOUNTDETAIL WHERE ACCOUNT_ID = ? AND ACCOUNTTYPE_DM = ?";
		String insertSql = "INSERT INTO T_SH_ACCOUNTDETAIL(DETAIL_ID, ACCOUNT_ID, ACCOUNTTYPE_DM, DETAILTYPE_DM, DETAILXM_DM, DETAIL_CONTENT, DETAIL_TIME, JE, LR_SJ)" +
		   " VALUES (?, ?, ?, ?, ?, ?, ?, ?, SYSDATE)";
		Object[] objs = null;
		int i = 0;
		boolean sign = false;
		for(DetailAccount vo : list){
			String accountid = vo.getId();
			String accounttype_dm = vo.getAccounttype_dm();

			//获取detailid
			String tmpdetailid = "0";
			DataSet ds = this.dpo.executeQuery(selectSql, new Object[]{accountid, accounttype_dm});
			if(ds.hasNext()){
				ds = this.dpo.executeQuery(selectSql2, new Object[]{accountid, accounttype_dm});
				if(ds.hasNext()){
					ds.next();
					tmpdetailid = ds.getString("DETAILID");
				}
			}
			String detailid = String.valueOf(Integer.valueOf(tmpdetailid)+1);
			
			String detailtype_dm = vo.getDetailtype_dm();
			String detailxm_dm = vo.getDetailxm_dm();
			String detail_content = vo.getDetail_content();
			double detail_je = vo.getDetail_je();
			String detail_time = vo.getDetail_time();
			
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
			i = this.dpo.executeUpdate(insertSql, objs);
			
			sign = this.setTotalAccount(accountid);
		}
		
		if(i > 0 && sign){
			message = "01";
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
		
		DataSet ds = this.dpo.executeQuery(sql, new Object[]{accountid});
		Map<String, Double> totalMap = new HashMap<String, Double>();
		if(ds.hasNext()){
			ds.next();
			totalMap.put("INCOME", ds.getDouble("INCOME"));
			totalMap.put("EXPENSES", ds.getDouble("EXPENSES"));
		}else{
			totalMap.put("INCOME", null);
			totalMap.put("EXPENSES", null);
		}
		
		//如果是第一次导入, 新增记录
		sql = "SELECT 1 FROM T_SH_ACCOUNT WHERE ACCOUNT_ID = ?";
		ds = this.dpo.executeQuery(sql, new Object[]{accountid});
		if(ds.hasNext()){
			sql = "UPDATE T_SH_ACCOUNT T SET T.INCOME = ?, T.EXPENSES = ? WHERE T.ACCOUNT_ID = ?";
		}else{
			sql = "INSERT INTO T_SH_ACCOUNT(INCOME, EXPENSES, ACCOUNT_ID, ACCOUNT_DATE) VALUES (?, ?, ?, SYSDATE)";
		}
		
		int i = this.dpo.executeUpdate(sql, new Object[]{totalMap.get("INCOME"), totalMap.get("EXPENSES"), accountid});
		if(i > 0){
			sign = true;
		}
		return sign;
	}
}
