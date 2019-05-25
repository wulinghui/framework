package com.wlh.dao.handler;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.wlh.dao.SqlConfig;

public class ScalarHandler extends  AbstractHandler<Object> {

	public ScalarHandler(SqlConfig config) {
		super(config);
	}

	@Override
	public Object handle(ResultSet rs) throws SQLException {
		return ValueHandler.toValue(this, rs);
	}

}	
