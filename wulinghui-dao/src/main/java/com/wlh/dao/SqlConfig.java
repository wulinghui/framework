package com.wlh.dao;

import java.sql.Connection;


/**
 * @author wulinghui
 * 
 */ 
public interface SqlConfig {
	public static final String SQL_DEFAULT_CONFIG = "dao-SqlDefaultConfig";
	String SQL = "SQL"; 
	String RESULT_SET_HANDLER = "org.apache.commons.dbutils.ResultSetHandler<T>";
	String IS_FUTURE = "Future";
	String COLUMN_NAME = "columnName";
	String COLUMN_MAX = "columnMax";
	String ROW_INDEXS = "RowIndexs"; // 第几行的数据可以被放到list里面。
	String CONNECTION = "java.sql.Connection";
	/**
	 * 用于设置输入的map或bean对应设置值
	 * stmt.setObject(i + 1, object);
	 */
	String FILL_STATEMENT_KEYS = "fillStatementKeys";
	SqlConfig setConfig(String key , Object value);
	<T> T getConfig(String key);
}
