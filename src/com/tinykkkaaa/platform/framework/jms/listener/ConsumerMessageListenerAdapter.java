package com.tinykkkaaa.platform.framework.jms.listener;

import com.tinykkkaaa.account.dao.AccountUploadDao;
import com.tinykkkaaa.account.service.AccountUploadService;
import com.tinykkkaaa.platform.core.service.ServiceFactory;
import com.tinykkkaaa.platform.framework.jms.vo.Email;
import com.tinykkkaaa.platform.framework.jms.vo.Excel;

public class ConsumerMessageListenerAdapter{
	
	/*
	 * 方法返回值不为null或void时，JMS会将返回内容封装成Message返回给消息队列
	 */
	public String receiveMessage(Email email) {
		System.out.println("监听器ConsumerMessageListenerAdapter接收到一个包含Email的ObjectMessage: "+email);
		try {
			// 模拟业务逻辑处理
			System.out.println("业务接口正在处理消息...");
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		String msg = "监听器ConsumerMessageListenerAdapter处理成功了!";
		return msg;
	}
	
	public String receiveMessage(Excel excel) {
		String message = "00";
		System.out.println("监听器ConsumerMessageListenerAdapter接收到一个Excel对象, filePath: "+ excel.getFilePath());
		
		try {
			// 将excel数据批量导入到数据库
			System.out.println("正在批量导入("+excel.getFilePath()+")...");
//			AccountUploadDao dao = new AccountUploadDaoImpl(SystemManager.getInstance().getDataPersistenceManager().getDefaultDataPersistenceObject());
//			AccountUploadService accountUploadService = new AccountUploadServiceImpl(dao);
			
			AccountUploadService service = ServiceFactory.getInstance()
			.getService("AccountUploadService", AccountUploadService.class, "AccountUploadDao", AccountUploadDao.class);
			message = service.uploadExcel(excel.getFilePath());
			
			System.out.println("批量导入完成...");
		} catch (Exception e) {
			e.printStackTrace();
		}
		//拼接返回消息记录
		String type = "01";
		String status = message;
		String title = "批量导入账单信息提示";
		String content = "01".equals(message) ? "批量导入Excel成功!" : "批量导入Excel失败!";
		String receiver = excel.getRootSign();
		String sender = "admin";
		String msg = type+"#"+status+"#"+title+"#"+content+"#"+receiver+"#"+sender;
		return msg;
	}

}
