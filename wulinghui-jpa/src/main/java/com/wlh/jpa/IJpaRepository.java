package com.wlh.jpa;

import java.sql.SQLException;
import java.util.List;

import com.wlh.jpa.entity.JpaEntity;

/**
 * @author wulinghui
 * 传统的jpa有:
 * ORM映射
 * 建表
 * 主键自增
 * 级联操作: sql 和 dml
 * 缓存 
 * 自己的sql语句解析。
 * 但是我们这里只实现 : 
 * ORM映射
 * 建表
 * 主键自增
 * 级联操作: sql
 */
public interface IJpaRepository {

	/** 增
	 * @param obj
	 * @return
	 * @throws SQLException 
	 */
	public abstract <T extends JpaEntity> int save(T obj) throws SQLException;

	/** 删
	 * @param obj
	 * @return
	 * @throws SQLException 
	 */
	public abstract <T extends JpaEntity> int delete(T obj) throws SQLException;

	/**改
	 * @param obj
	 * @return
	 * @throws Exception 
	 */
	public abstract <T extends JpaEntity> int update(T obj) throws Exception;

	/**通过主键id查询
	 * @param id
	 * @return
	 * @throws SQLException 
	 */
	public abstract <T extends JpaEntity> T findOne(Class<T> cla,String id) throws SQLException;

	/**通过T过滤条件查询。
	 * @param obj
	 * @return
	 * @throws SQLException 
	 */
	public abstract <T extends JpaEntity> List<T> findBy(T obj) throws SQLException;

	/**查询所有
	 * @return
	 * @throws SQLException 
	 */
	public abstract <T extends JpaEntity> List<T> findAll(Class<T> cla) throws SQLException;

}