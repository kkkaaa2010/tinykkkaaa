package com.tinykkkaaa.platform.core.console.util.primarykey;

public interface IDBPrimaryKeyGenerator {
	public String getNextKey(String tableName, int length, int hashCodeLength);

	public String getNextKey(String tableName, int length);

	public String getNextKey(String tableName);

	public String getSequence(String sequenceName);

	public String getSequence(String sequenceName, int length);
}