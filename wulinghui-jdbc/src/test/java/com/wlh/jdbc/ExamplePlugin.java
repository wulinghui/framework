package com.wlh.jdbc;

import java.util.Properties;

import org.apache.commons.dbutils.DbUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.springframework.beans.factory.annotation.Value;

//ExamplePlugin.java
@Intercepts({ @org.apache.ibatis.plugin.Signature(type = Executor.class, method = "update", args = {
		MappedStatement.class, Object.class }) })
public class ExamplePlugin implements Interceptor {

	@Value("${fdn.open}")
	private boolean fdnOpen;

	static int MAPPED_STATEMENT_INDEX = 0;// 这是对应上面的args的序号
	static int PARAMETER_INDEX = 1;
	static int ROWBOUNDS_INDEX = 2;
	static int RESULT_HANDLER_INDEX = 3;

	public Object intercept(Invocation invocation) throws Throwable {
		
		if (!fdnOpen) {
			return invocation.proceed();
		}
		final Object[] queryArgs = invocation.getArgs();
		final MappedStatement mappedStatement = (MappedStatement) queryArgs[MAPPED_STATEMENT_INDEX];
		final Object parameter = queryArgs[PARAMETER_INDEX];
		final BoundSql boundSql = mappedStatement.getBoundSql(parameter);

		String sql = boundSql.getSql();
		if (sql.contains("xx_pro")) {
			sql = sql.replace("xx_pro", "xx_pro_fdn");
		}

		// 重新new一个查询语句对像
		BoundSql newBoundSql = new BoundSql(mappedStatement.getConfiguration(),
				sql, boundSql.getParameterMappings(),
				boundSql.getParameterObject());
		// 把新的查询放到statement里
		MappedStatement newMs = copyFromMappedStatement(mappedStatement,
				new BoundSqlSqlSource(newBoundSql));
		for (ParameterMapping mapping : boundSql.getParameterMappings()) {
			String prop = mapping.getProperty();
			if (boundSql.hasAdditionalParameter(prop)) {
				newBoundSql.setAdditionalParameter(prop,
						boundSql.getAdditionalParameter(prop));
			}
		}
		queryArgs[MAPPED_STATEMENT_INDEX] = newMs;
		return invocation.proceed();
	}

	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {

	}

	private MappedStatement copyFromMappedStatement(MappedStatement ms,
			SqlSource newSqlSource) {
		MappedStatement.Builder builder = new MappedStatement.Builder(
				ms.getConfiguration(), ms.getId(), newSqlSource,
				ms.getSqlCommandType());
		builder.resource(ms.getResource());
		builder.fetchSize(ms.getFetchSize());
		builder.statementType(ms.getStatementType());
		builder.keyGenerator(ms.getKeyGenerator());
		if (ms.getKeyProperties() != null && ms.getKeyProperties().length > 0) {
			builder.keyProperty(ms.getKeyProperties()[0]);
		}
		builder.timeout(ms.getTimeout());
		builder.parameterMap(ms.getParameterMap());
		builder.resultMaps(ms.getResultMaps());
		builder.resultSetType(ms.getResultSetType());
		builder.cache(ms.getCache());
		builder.flushCacheRequired(ms.isFlushCacheRequired());
		builder.useCache(ms.isUseCache());
		return builder.build();
	}

	public static class BoundSqlSqlSource implements SqlSource {
		private BoundSql boundSql;

		public BoundSqlSqlSource(BoundSql boundSql) {
			this.boundSql = boundSql;
		}

		public BoundSql getBoundSql(Object parameterObject) {
			return boundSql;
		}
	}
}
