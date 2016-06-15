package com.tinykkkaaa.test;

import com.tinykkkaaa.account.dao.AccountUploadDao;
import com.tinykkkaaa.account.service.AccountUploadService;
import com.tinykkkaaa.platform.core.service.ServiceFactory;

public class ServiceTest {
	
	/*
	 * 由于存在SystemManager调用, 无法在main函数里测试, 可以改为调用SpringUtil加载bean; 
	 * 只是做测试,可将该方法挂到页面进行查看.
	 */
	public static void testService(){
		AccountUploadService service = (AccountUploadService) ServiceFactory.getInstance()
										.getService("AccountUploadService", AccountUploadService.class, "AccountUploadDao", AccountUploadDao.class);
		try {
			service.uploadExcel(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
