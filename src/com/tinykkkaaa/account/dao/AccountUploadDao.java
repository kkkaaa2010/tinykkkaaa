package com.tinykkkaaa.account.dao;

import java.util.List;

import com.tinykkkaaa.account.entity.DetailAccount;

public interface AccountUploadDao {
	public String uploadExcel(List<DetailAccount> list) throws Exception;
}
