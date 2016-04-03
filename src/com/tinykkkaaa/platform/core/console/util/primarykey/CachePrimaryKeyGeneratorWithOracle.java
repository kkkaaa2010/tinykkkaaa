package com.tinykkkaaa.platform.core.console.util.primarykey;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import com.tinykkkaaa.platform.core.util.ConvertUtil;
import com.tinykkkaaa.platform.core.util.NumUtil;

public class CachePrimaryKeyGeneratorWithOracle implements
		IDBPrimaryKeyGenerator {

	private static final String SELECT_SQL = "SELECT SEQUENCE_VALUE AS VALUE FROM SEQUENCE_GENERATOR WHERE SEQUENCE_ID = ?";
	private static final String INSERT_SQL = "INSERT INTO SEQUENCE_GENERATOR (SEQUENCE_ID,SEQUENCE_VALUE) VALUES (?,?)";
	private static final String UPDATE_SQL = "UPDATE SEQUENCE_GENERATOR SET SEQUENCE_VALUE=SEQUENCE_VALUE + ? WHERE SEQUENCE_ID = ?";
	
	private String dbDriverClassName = "oracle.jdbc.driver.OracleDriver";
	private String jdbcUrl = "jdbc:oracle:thin:@127.0.0.1:1521:ORCL";
	private String username = "DB_KA";
	private String password = "DB_KA";
	
	/**
	 * 区域码
	 */
	private String siteCode = "";

	/**
	 * 默认散列码长度
	 */
	private int hashCodeLength = 2;

	/**
	 * 默认的主键长度
	 */
	private int keyLength = 18;

	/**
	 * 主键的缓存个数
	 */
	private int fetchSize = 20;

	/**
	 * 主键中序列号的步进
	 */
	private int increment = 1;

	private Map<String, KeyCache> keysCache = new HashMap<String, KeyCache>();

	/**
	 * 构造函数
	 * 
	 * @param parameters
	 *            配置文件中传来的参数
	 */
	public CachePrimaryKeyGeneratorWithOracle(Map<String, String> parameters) {

		Object tmp = null;
		tmp = parameters.get("siteCode");
		if (tmp != null) {
			this.siteCode = tmp.toString().trim();
		}
		tmp = parameters.get("hashCodeLength");
		if (tmp != null) {
			if (NumUtil.isInt(tmp.toString())) {
				this.hashCodeLength = Integer.parseInt(tmp.toString());
			}
		}
		tmp = parameters.get("keyLength");
		if (tmp != null) {
			if (NumUtil.isInt(tmp.toString())) {
				this.keyLength = Integer.parseInt(tmp.toString());
			}
		}
		tmp = parameters.get("fetchSize");
		if (tmp != null) {
			if (NumUtil.isInt(tmp.toString())) {
				this.fetchSize = Integer.parseInt(tmp.toString());
			}
		}
		tmp = parameters.get("increment");
		if (tmp != null) {
			if (NumUtil.isInt(tmp.toString())) {
				this.increment = Integer.parseInt(tmp.toString());
			}
		}
	}

	/**
	 * 负责缓存主键
	 */
	private static class KeyCache {
		// private int runNum=0;
		private long begin = 0;
		private long end = 0;
		private long increment = 0;

		public KeyCache(long begin, long end, long increment) {
			this.begin = begin;
			this.end = end;
			this.increment = increment;
		}

		public long nextKey() {
			// this.runNum++;
			long tmp = begin;
			begin += increment;
			return tmp;
		}

		public boolean isEmpty() {
			return begin > end;
		}

		public String toString() {
			return "[KeyCatch:Begin=" + begin + ",end=" + end + ",increment="
					+ increment + "]";
		}
	}

	/**
	 * update at 2015-07-09 by luliang 修改主键生成方式,原主键生成方式为tableName.toUpperCase()
	 * + sequencePrefix + 序列增加码 改为tableName.toUpperCase() + 序列增加码 原方法在备份在上面
	 */
	@Override
	public String getNextKey(String tableName, int length, int hashCodeLength) {
		assert tableName != null && tableName.trim().length() > 0 : "表名不能为空！";
		String sequencePrefix = this.siteCode;
		// if (hashCodeLength > 0) {
		// sequencePrefix += ConvertUtil.StrToStr(String.valueOf(RANDOM
		// .nextInt((int) Math.pow(10, hashCodeLength))),
		// hashCodeLength);
		// }
		// 序列号的长度
		int sequenceLength = length - sequencePrefix.length();
		if (sequenceLength < 0) {
			throw new IllegalStateException("生成主键错误-主键长度小于主键前缀长度！");
		}
		// 唯一的主键序列id
		// String sequenceId = tableName.toUpperCase() + sequencePrefix;
		String sequenceId = tableName.toUpperCase();
		synchronized (keysCache) {
			String key = null;
			KeyCache keyCatch = null;
			// 缓存中存在主键序列
			keyCatch = (KeyCache) keysCache.get(sequenceId);
			if (keyCatch == null) {
				keyCatch = fetchSequences(sequenceId, fetchSize, increment);
				keysCache.put(sequenceId, keyCatch);
			}
			// 获取主键序列号中的第一个
			key = String.valueOf(keyCatch.nextKey());
			// 如果序列号长度已经超出长度抛出异常
			if (key.length() > sequenceLength) {
				throw new IllegalStateException("生成主键错误-主键序列溢出！");
			}
			if (keyCatch.isEmpty()) {
				keysCache.remove(sequenceId);
			}
			return sequencePrefix + ConvertUtil.StrToStr(key, sequenceLength);
		}
	}

	@Override
	public String getNextKey(String tableName, int length) {
		return this.getNextKey(tableName, length, this.hashCodeLength);
	}

	@Override
	public String getNextKey(String tableName) {
		return this.getNextKey(tableName, this.keyLength);
	}
	
	@Override
	public String getSequence(String sequenceName, int length) {
		assert length > 0 : "序列号长度必须大于0！";
		String sequence = this.getSequence(sequenceName);
		if (sequence.length() > length) {
			throw new IllegalStateException("生成序列号错误-序列号长度["
					+ sequence.length() + "]大于要求的序列号长度[" + length + "]！");
		}
		return ConvertUtil.StrToStr(sequence, length);
	}

	@Override
	public String getSequence(String sequenceName) {
		assert sequenceName != null && sequenceName.trim().length() > 0 : "序列号名不能为空！";
		// 唯一的主键序列id
		String sequenceId = sequenceName.toUpperCase();
		synchronized (keysCache) {
			String key = null;
			KeyCache keyCatch = null;
			// 缓存中存在主键序列
			keyCatch = (KeyCache) keysCache.get(sequenceId);
			if (keyCatch == null) {
				keyCatch = fetchSequences(sequenceId, fetchSize, 1);
				keysCache.put(sequenceId, keyCatch);
			}
			// 获取主键序列号中的第一个
			key = String.valueOf(keyCatch.nextKey());
			if (keyCatch.isEmpty()) {
				keysCache.remove(sequenceId);
			}
			return key;
		}
	}
	
    /**
     * 取回指定sequenceId的一些序列值
     * @param sequenceId 序列id
     * @param fetchSize 取回的序列号数量
     * @param increment 序列的步进
     * @return LinkedList 返回的序列号list
     */
	private KeyCache fetchSequences(String sequenceId, int fetchSize,
			int increment) {
		assert increment > 0 : "序列号步进要大于0！";
		assert fetchSize > 0 : "获取序列号的数量要大于0！";
		Connection conn = this.getConnection();
		assert conn != null : "无法获取数据库连接！";
		long begin = 1;
		PreparedStatement statement = null;
		ResultSet result = null;
		boolean oldState = false;
		boolean hasSetState = false;
		try {
			oldState = conn.getAutoCommit();
			conn.setAutoCommit(false);
			hasSetState = true;

			// 先执行更新，同时对记录上锁
			statement = conn.prepareStatement(UPDATE_SQL);
			statement.setLong(1, fetchSize * increment);
			statement.setString(2, sequenceId);
			statement.executeUpdate();
			// 得到更新后的序列号值
			statement = conn.prepareStatement(SELECT_SQL);
			statement.setString(1, sequenceId);
			result = statement.executeQuery();
			if (result.next()) {
				begin = result.getLong("value") - (fetchSize - 1) * increment;
			} else {
				// 没有记录，需要插入一条新记录
				statement = conn.prepareStatement(INSERT_SQL);
				statement.setString(1, sequenceId);
				statement.setLong(2, fetchSize * increment);
				statement.executeUpdate();
			}
			conn.commit();
			return new KeyCache(begin, begin + (fetchSize - 1) * increment, increment);
		} catch (Throwable t) {
			try {
				if (conn != null) {
					conn.rollback();
				}
			} catch (Throwable t2) {
			}
			throw new IllegalStateException("生成主键错误-" + t);
		} finally {
			try {// 关闭结果集
				if (result != null) {
					result.close();
					result = null;
				}
			} catch (Throwable t) {
			}
			try {// 关闭statement
				if (statement != null) {
					statement.close();
					statement = null;
				}
			} catch (Throwable t) {
			}
			try {// 还原连接原始状态
				if (conn != null) {
					if (hasSetState) {
						conn.setAutoCommit(oldState);
					}
				}
			} catch (Throwable t) {
			}

			try {// 关闭连接
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (Throwable t) {
			}
		}
	}

	/**
	 * 根据设置的连接参数创建一个新的连接实例 子类可以覆盖该方法
	 * @return Connection 如果获取连接失败返回null
	 * @throws Exception
	 */
	protected Connection getConnection() {
		try {
			Class.forName(this.dbDriverClassName);
			return DriverManager.getConnection(this.jdbcUrl, this.username,
					this.password);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
