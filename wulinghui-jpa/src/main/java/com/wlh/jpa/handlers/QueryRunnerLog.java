package com.wlh.jpa.handlers;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.StatementConfiguration;

import com.wlh.log.ILogger;
import com.wlh.log.LogMSG;
import com.wlh.log.LogUtils;

public class QueryRunnerLog extends QueryRunner {
	private static final ILogger logger = LogMSG.getLogger();
	public QueryRunnerLog() {
		super();
		
	}

	public QueryRunnerLog(boolean pmdKnownBroken) {
		super(pmdKnownBroken);
		
	}

	public QueryRunnerLog(DataSource ds, boolean pmdKnownBroken,
			StatementConfiguration stmtConfig) {
		super(ds, pmdKnownBroken, stmtConfig);
		
	}

	public QueryRunnerLog(DataSource ds, boolean pmdKnownBroken) {
		super(ds, pmdKnownBroken);
		
	}

	public QueryRunnerLog(DataSource ds, StatementConfiguration stmtConfig) {
		super(ds, stmtConfig);
		
	}

	public QueryRunnerLog(DataSource ds) {
		super(ds);
		
	}

	public QueryRunnerLog(StatementConfiguration stmtConfig) {
		super(stmtConfig);
	}

	@Override
	public int[] batch(Connection conn, String sql, Object[][] params)
			throws SQLException {
		debugSqlAndPapa(sql,params);
		return super.batch(conn, sql, params);
	}

	@Override
	public <T> T query(Connection conn, String sql, Object[] params,
			ResultSetHandler<T> rsh) throws SQLException {
		debugSqlAndPapa(sql,params);
		return super.query(conn, sql, params, rsh);
	}

	@Override
	public <T> T query(Connection conn, String sql, ResultSetHandler<T> rsh,
			Object... params) throws SQLException {
		debugSqlAndPapa(sql,params);
		return super.query(conn, sql, rsh, params);
	}

	@Override
	public int update(Connection conn, String sql, Object... params)
			throws SQLException {
		debugSqlAndPapa(sql,params);
		return super.update(conn, sql, params);
	}

	@Override
	public <T> T insert(Connection conn, String sql, ResultSetHandler<T> rsh,
			Object... params) throws SQLException {
		debugSqlAndPapa(sql,params);
		return super.insert(conn, sql, rsh, params);
	}

	@Override
	public <T> T insertBatch(Connection conn, String sql,
			ResultSetHandler<T> rsh, Object[][] params) throws SQLException {
		debugSqlAndPapa(sql,params);
		return super.insertBatch(conn, sql, rsh, params);
	}

	@Override
	public <T> List<T> execute(Connection conn, String sql,
			ResultSetHandler<T> rsh, Object... params) throws SQLException {
		debugSqlAndPapa(sql,params);
		return super.execute(conn, sql, rsh, params);
	}
	protected void debugSqlAndPapa(String sql,Object[] params){
		LogUtils.debugArray(sql + "\n\t params", params);
	}

	@Override
	public int[] batch(String sql, Object[][] params) throws SQLException {
		debugSqlAndPapa(sql,params);
		return super.batch(sql, params);
	}

	@Override
	public <T> T query(Connection conn, String sql, Object param,
			ResultSetHandler<T> rsh) throws SQLException {
		debugSqlAndPapa(sql,null);
		return super.query(conn, sql, param, rsh);
	} 

	@Override
	public <T> T query(Connection conn, String sql, ResultSetHandler<T> rsh)
			throws SQLException {
		debugSqlAndPapa(sql,null);
		return super.query(conn, sql, rsh);
	}

	@Override
	public <T> T query(String sql, Object param, ResultSetHandler<T> rsh)
			throws SQLException {
		debugSqlAndPapa(sql,null);
		return super.query(sql, param, rsh);
	}

	@Override
	public <T> T query(String sql, Object[] params, ResultSetHandler<T> rsh)
			throws SQLException {
		debugSqlAndPapa(sql,params);
		return super.query(sql, params, rsh);
	}

	@Override
	public <T> T query(String sql, ResultSetHandler<T> rsh, Object... params)
			throws SQLException {
		debugSqlAndPapa(sql,params);
		return super.query(sql, rsh, params);
	}

	@Override
	public <T> T query(String sql, ResultSetHandler<T> rsh) throws SQLException {
		debugSqlAndPapa(sql,null);
		return super.query(sql, rsh);
	}

	@Override
	public int update(Connection conn, String sql) throws SQLException {
		debugSqlAndPapa(sql,null);
		return super.update(conn, sql);
	}

	@Override
	public int update(Connection conn, String sql, Object param)
			throws SQLException {
		debugSqlAndPapa(sql,null);
		return super.update(conn, sql, param);
	}

	@Override
	public int update(String sql) throws SQLException {
		debugSqlAndPapa(sql,null);
		return super.update(sql);
	}

	@Override
	public int update(String sql, Object param) throws SQLException {
		debugSqlAndPapa(sql,null);
		return super.update(sql, param);
	}

	@Override
	public int update(String sql, Object... params) throws SQLException {
		debugSqlAndPapa(sql,params);
		return super.update(sql, params);
	}

	@Override
	public <T> T insert(String sql, ResultSetHandler<T> rsh)
			throws SQLException {
		debugSqlAndPapa(sql,null);
		return super.insert(sql, rsh);
	}

	@Override
	public <T> T insert(String sql, ResultSetHandler<T> rsh, Object... params)
			throws SQLException {
		debugSqlAndPapa(sql,params);
		return super.insert(sql, rsh, params);
	}

	@Override
	public <T> T insert(Connection conn, String sql, ResultSetHandler<T> rsh)
			throws SQLException {
		debugSqlAndPapa(sql,null);
		return super.insert(conn, sql, rsh);
	}

	@Override
	public <T> T insertBatch(String sql, ResultSetHandler<T> rsh,
			Object[][] params) throws SQLException {
		debugSqlAndPapa(sql,params);
		return super.insertBatch(sql, rsh, params);
	}

	@Override
	public int execute(Connection conn, String sql, Object... params)
			throws SQLException {
		debugSqlAndPapa(sql,params);
		return super.execute(conn, sql, params);
	}

	@Override
	public int execute(String sql, Object... params) throws SQLException {
		debugSqlAndPapa(sql,params);
		return super.execute(sql, params);
	}

	@Override
	public <T> List<T> execute(String sql, ResultSetHandler<T> rsh,
			Object... params) throws SQLException {
		debugSqlAndPapa(sql,params);
		return super.execute(sql, rsh, params);
	}
	
}
