package com.tinykkkaaa.platform.core.console.util.primarykey;

public final class DBPrimaryKeyGeneratorMgr {
	private static final DBPrimaryKeyGeneratorMgr instance;
	private IDBPrimaryKeyGenerator generator = null;

	static {
		instance = new DBPrimaryKeyGeneratorMgr();
	}

	public static final DBPrimaryKeyGeneratorMgr getInstance() {
		return instance;
	}

	public IDBPrimaryKeyGenerator getPrimaryKeyGenerator() {
		assert (this.generator != null) : "没有设置主键生成器！";
		return this.generator;
	}

	public void setPrimaryKeyGenerator(IDBPrimaryKeyGenerator generator)
			throws Exception {
		assert (generator != null) : "主键生成器不能为空！";
		assert (this.generator == null) : "主键生成器已经存在！";
		this.generator = generator;
	}
}