package com.tinykkkaaa.account.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.tinykkkaaa.account.dao.AccountUploadDao;
import com.tinykkkaaa.account.entity.DetailAccount;
import com.tinykkkaaa.account.service.AccountUploadService;
import com.tinykkkaaa.comm.util.CommUtil;
import com.tinykkkaaa.comm.util.FileUtil;
import com.tinykkkaaa.platform.core.SystemManager;
import com.tinykkkaaa.platform.core.service.Service;


public class AccountUploadServiceImpl extends Service implements AccountUploadService {
	
	private AccountUploadDao accountUploadDao;
	
	public AccountUploadServiceImpl(AccountUploadDao accountUploadDao){
		this.accountUploadDao = accountUploadDao;
	}

	@Override
	public String uploadExcel(String filePath) throws Exception {
		
		List<DetailAccount> list = new ArrayList<DetailAccount>();
		
		File file = new File(filePath);
		InputStream is = new FileInputStream(file);
		
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
		
		try {
			for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
				HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
				if (hssfSheet == null) {
					continue;
				}
				// 循环行Row
				for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
					DetailAccount entity = null;
					HSSFRow hssfRow = hssfSheet.getRow(rowNum);
					if(hssfRow.getCell(1)==null || CommUtil.isNull(hssfRow.getCell(1).toString())){
						break;
					}else{
						entity = new DetailAccount();
					}
					for (int i = 1; i < hssfRow.getLastCellNum(); i++) {
						HSSFCell brandIdHSSFCell = hssfRow.getCell(i);
						String cellValue = brandIdHSSFCell.toString();
						if (i == 1) {
							entity.setId(cellValue);
						} else if (i == 2) {
							entity.setAccounttype_dm(
									SystemManager.getInstance().getCodeTableManager().getCode("T_DM_ACCOUNTTYPE", cellValue));
						} else if (i == 3) {
							brandIdHSSFCell = hssfRow.getCell(i-1);
							String accounttype = brandIdHSSFCell.toString();
							
							//如果是收入项, 没有明细类型
							if("收入".equals(accounttype)){
								entity.setDetailtype_dm(null);
							}else{
								entity.setDetailtype_dm(
										SystemManager.getInstance().getCodeTableManager().getCode("T_DM_DETAILTYPE", cellValue));
							}
						} else if (i == 4) {
							entity.setDetailxm_dm(
									SystemManager.getInstance().getCodeTableManager().getCode("T_DM_DETAILXM", cellValue));
						} else if (i == 5) {
							entity.setDetail_content(cellValue);
						} else if (i == 6) {
							entity.setDetail_time(cellValue);
						} else if (i == 7) {
							entity.setDetail_je(Double.valueOf(cellValue));
						}
					}
					list.add(entity);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			is.close();
			hssfWorkbook.close();
			
			//删除临时上传的excel
			FileUtil.delFile(filePath);
		}
		
		return this.accountUploadDao.uploadExcel(list);
	}
}
