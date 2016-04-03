package com.tinykkkaaa.platform.core.console.util;

import com.tinykkkaaa.platform.core.console.util.primarykey.DBPrimaryKeyGeneratorMgr;

public class DBAccessHelper {
	public static final String getNextKey(String tableName,int length){
		DBPrimaryKeyGeneratorMgr instance = DBPrimaryKeyGeneratorMgr.getInstance();
		return instance.getPrimaryKeyGenerator().getNextKey(tableName,length);
	}
}
