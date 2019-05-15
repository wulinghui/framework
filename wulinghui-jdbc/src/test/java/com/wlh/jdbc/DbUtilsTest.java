package com.wlh.jdbc;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.text.translate.LookupTranslator;
import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class DbUtilsTest {
	@Test
	public  void main() {
//		DbUtils.close(stmt);
		 QueryRunner queryRunner = new QueryRunner();
//		 queryRunner.insert(sql, rsh, params)
//		 queryRunner.update(conn, "INSERT INTO persons(name, age) VALUES(?, ?)", "阡陌", 24);
//		 StringEscapeUtils.unescapeHtml4(input)
	}
	@Test
	public  void main1() {
		NamedParameterJdbcTemplate jdbcTemplate = null;
		JdbcTemplate jdbc = null;
		String sql = null;
		SqlParameterSource paramMap = null;
		RowMapper requiredType = null;
		Object queryForObject = jdbcTemplate.queryForObject(sql, paramMap, requiredType);
		List<Map<String, Object>> queryForList = jdbcTemplate.queryForList(sql, paramMap);
		Map<String, Object> queryForMap = jdbcTemplate.queryForMap(sql, paramMap);
		SqlRowSet queryForRowSet = jdbcTemplate.queryForRowSet(sql, paramMap);
//		 jdbcTemplate.queryForObject(sql, paramMap, rowMapper);
		
		DataSource dataSource = null;
		TransactionFactory transactionFactory = new JdbcTransactionFactory();
		Environment environment = new Environment("development", transactionFactory, dataSource);
		Configuration configuration = new Configuration(environment);
		configuration.addMapper(null);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
		SqlSession session = sqlSessionFactory.openSession();
//		sqlSessionFactory.openSession(execType, level)
		 String statement = "me.gacl.mapping.userMapper.getUser";
		 Object parameter = null;
		Object selectOne = session.selectOne(statement, parameter);
		Cursor<Object> selectCursor = session.selectCursor(statement, parameter);
		List<Object> selectList = session.selectList(statement, parameter);
		Map<Object, Object> selectMap = session.selectMap(statement, parameter, "");
//		session.selectList(statement, obj);
//		for (Object object : selectCursor) {
//			
//		}
	}
	@Test
	public  void main2() {
		NamedParameterJdbcTemplate jdbcTemplate = null;
		JdbcTemplate jdbc = null;
		String sql = null;
		SqlParameterSource paramMap = null;
		RowMapper requiredType = null;
		Object queryForObject = jdbcTemplate.queryForObject(sql, paramMap, requiredType);
		List<Map<String, Object>> queryForList = jdbcTemplate.queryForList(sql, paramMap);
		Map<String, Object> queryForMap = jdbcTemplate.queryForMap(sql, paramMap);
		SqlRowSet queryForRowSet = jdbcTemplate.queryForRowSet(sql, paramMap);
//		 jdbcTemplate.queryForObject(sql, paramMap, rowMapper);
		
		DataSource dataSource = null;
		TransactionFactory transactionFactory = new JdbcTransactionFactory();
		Environment environment = new Environment("development", transactionFactory, dataSource);
		Configuration configuration = new Configuration(environment);
		configuration.addMapper(DbUtilsTest.class);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
		SqlSession session = sqlSessionFactory.openSession();
		 String statement = "me.gacl.mapping.userMapper.getUser";
		 Object parameter = null;
		 Object selectOne = session.selectOne(statement, parameter);
		Cursor<Object> selectCursor = session.selectCursor(statement, parameter);
		List<Object> selectList = session.selectList(statement, parameter);
		Map<Object, Object> selectMap = session.selectMap(statement, parameter, "");
	}
	@Test
	public void run1(){
		String hexString = Integer.toHexString(12);
		System.out.println(hexString );
		hexString = Integer.toHexString(12).toUpperCase(Locale.ENGLISH);
		System.out.println(hexString );
		
	}
	String str = "select * from people where (first_name = :name or last_name = :name)"
			+ " and address = :address";
	@Test
	public void run2(){
		Map<CharSequence, CharSequence> map = new HashMap<CharSequence, CharSequence>();
		map.put("a", "111");
		map.put("b", "222");
		LookupTranslator translator = new LookupTranslator(map );
		String translate = translator.translate("a=b=c");
		System.out.println(translate); 
	}
	
}
