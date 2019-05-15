package com.wlh.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import org.apache.commons.lang3.ArrayUtils;

import com.wlh.dao.entity.ParameterIndex;
import com.wlh.util.MapUtils;

public class SqlSessionNamedPrepared extends SqlSessionToMap {

	public SqlSessionNamedPrepared(SqlSession session) {
		super(session);
	}

	@Override
	public int[] batch(SqlConfig config, List<? extends Map> parameter)
			throws SQLException {
		translatorWrap(config, parameter);
		return super.batch(config, parameter);
	}

	@Override
	public int[] batch0(SqlConfig config, List<?> parameter)
			throws SQLException {
		translatorWrap(config, parameter);
		return super.batch0(config, parameter);
	}

	@Override
	public int update(SqlConfig config, Map parameter) throws SQLException {
		if (MapUtils.isNotEmpty(parameter)) {
			translatorWrap(config, parameter);
		}
		return super.update(config, parameter);
	}

	

	@Override
	public <T> Future<T> select(SqlConfig config, Map parameter) throws SQLException {
		if (MapUtils.isNotEmpty(parameter)) {
			translatorWrap(config, parameter);
		}
		return super.select(config, parameter);
	}


	protected void translatorWrap(SqlConfig config, Object parameter) {
		String sql = getSql(config);
		NamedPreparedTranslator translator = getNamedPreparedTranslator();
		sql = translator.translate(sql);
		config.setConfig(config.SQL,sql);
		//往后添加，但是有个问题，如果有冲突了的话将无法解决，引入ParameterIndex类解决。
		ParameterIndex[] array1 = getFillStatementKeys(config);
		
		ParameterIndex[] array2 = translator.getNamedPreparedArray();
		
//		ParameterIndex[] addAll  = ArrayUtils.addAll(array1, array2); // 不行。这个之后可能会出现?和这个不匹配
		ParameterIndex[] addAll  = new ParameterIndex[translator.getParameterIndex()];
		for (ParameterIndex parameterIndex : array1) {
			addAll [parameterIndex.getIndex() ] = parameterIndex;
		}
		for (ParameterIndex parameterIndex : array2) {
			addAll [parameterIndex.getIndex() ] = parameterIndex;
		}
		config.setConfig(config.FILL_STATEMENT_KEYS,
				addAll );
	}

	protected NamedPreparedTranslator getNamedPreparedTranslator() {
		return new NamedPreparedTranslator('&', ' ');
	}

}
