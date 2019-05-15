package com.wlh.dao;

import java.sql.SQLException;
import java.util.concurrent.Future;

public class SqlSessionToMap extends DecorateSqlSession {

	public SqlSessionToMap(SqlSession session) {
		super(session);
	}

	@Override
	public int update(SqlConfig config, Object parameter) throws SQLException {
		return super.update(config,toMap( parameter ) );
	}

	@Override
	public <T> Future<T> select(SqlConfig config, Object parameter) throws SQLException {
		return super.select(config, toMap(parameter));
	}

}
