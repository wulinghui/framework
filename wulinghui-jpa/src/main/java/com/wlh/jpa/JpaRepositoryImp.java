package com.wlh.jpa;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.sql.DataSource;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.lang3.ArrayUtils;

import com.wlh.jpa.entity.JpaEntity;
import com.wlh.jpa.entity.ORMEntity;
import com.wlh.jpa.entity.PrimitiveEntity;
import com.wlh.jpa.handlers.CascadeBeanProcessor;
import com.wlh.jpa.handlers.QueryRunnerLog;
import com.wlh.util.ClassHelper;
import com.wlh.util.FieldUtils;
import com.wlh.util.lang.StringBuilderUtil;

import static com.wlh.jpa.JpaHelper.*;
/**
 * @author wulinghui
 *
 */
public class JpaRepositoryImp implements IJpaRepository {
	QueryRunner queryRunner;
	PrimaryKeyPolicy keyPolicy;
	
	public JpaRepositoryImp(DataSource ds , PrimaryKeyPolicy keyPolicy, TablePolicy tablePolicy,String packageName ) {
		queryRunner = new QueryRunnerLog(ds);
		this.keyPolicy = keyPolicy;
		// 建表
		Set<Class<?>> classSet = ClassHelper.getClassSet(packageName);
		tablePolicy.createTable(ClassHelper.getClassSetBySuper(JpaEntity.class, classSet));
	}

	public QueryRunner getQueryRunner() {
		return queryRunner;
	}

	/* (non-Javadoc)
	 * @see com.wlh.jpa.IJpaRepository#save(T)
	 */
	@Override
	public <T extends JpaEntity> int save(T obj) throws SQLException{
		Class<? extends JpaEntity> class1 = getHELPER().getTableClass(obj);
		ORMEntity ormEntity = new ORMEntity(class1);
		String tableName =  getTableName(class1);
		//INSERT INTO user (name,password) VALUES (?,?)
		StringBuilderUtil sb =new StringBuilderUtil( new StringBuilder("INSERT INTO ") );
		StringBuilderUtil sbValue =new StringBuilderUtil( new StringBuilder(") VALUES (") );
		sb.append(tableName);
		sb.append(" (");
		List<PrimitiveEntity> allExecptManyOnMany = ormEntity.getAllExecptManyOnMany();
		Object[] params = new Object[ allExecptManyOnMany.size()];
		// 主键
		int i = 0;
		for (PrimitiveEntity element : allExecptManyOnMany ) {
			if( element.fieldValueisNotNull(obj)){
				saveAppend(sb, sbValue, element);
				params[i++] = element.getFieldValue(obj);
			}
		}
		sbValue.deleteEnd();
		sb.deleteEnd();
		// 其它的值
		sbValue.append(')');
		sb.append(sbValue);
		return this.queryRunner.update(sb.toString() , ArrayUtils.subarray(params, 0, i) );
	}

	protected void saveAppend(StringBuilderUtil sb, StringBuilderUtil sbValue,
			PrimitiveEntity element) {
		sb.append(element.getColumnName());
		sb.append(',');
		sbValue.append('?');
		sbValue.append(',');
	}

	
	/* (non-Javadoc)
	 * @see com.wlh.jpa.IJpaRepository#delete(T)
	 */
	@Override
	public <T extends JpaEntity> int delete(T obj) throws SQLException{
		Class<? extends JpaEntity> class1 = getHELPER().getTableClass(obj);
		ORMEntity ormEntity = new ORMEntity(class1);
		String tableName = getTableName(class1);
		List<PrimitiveEntity> allExecptManyOnMany = ormEntity.getAllExecptManyOnMany();
		Object[] params = new Object[ allExecptManyOnMany.size() ];
		// DELETE FROM user WHERE name = ?
		StringBuilder sb = new StringBuilder("DELETE FROM ");
		sb.append(tableName);
		sb.append(" WHERE 1=1");
		int i = 0;
		for (PrimitiveEntity element : allExecptManyOnMany ) {
			if( element.fieldValueisNotNull(obj)){
				sb.append(" AND ");
				sb.append(element.getColumnName());
				sb.append("=?");
				params[i++] = element.getFieldValue(obj);
			}
		}
		return this.queryRunner.update(sb.toString() , ArrayUtils.subarray(params, 0, i) );
	}
	
	/* (non-Javadoc)
	 * @see com.wlh.jpa.IJpaRepository#update(T)
	 */
	@Override
	public <T extends JpaEntity> int update(T obj) throws Exception{
		// UPDATE user SET password = ? , name = ?  WHERE ID = ?
		Class<? extends JpaEntity> class1 =  getHELPER().getTableClass(obj);
		String tableName = getTableName(class1);
		ORMEntity ormEntity = new ORMEntity(class1);
		List<PrimitiveEntity> allExecptManyOnMany = ormEntity.getAllExecptManyOnMany();
		Object[] params = new Object[ allExecptManyOnMany.size()];
		// ID 的值
		StringBuilder sb = new StringBuilder("UPDATE ");
		sb.append(tableName);
		// SET
		sb.append(" SET ");
		int i = 0;
		String idInner = null;
		for (PrimitiveEntity element : allExecptManyOnMany ) {
			if( element.fieldValueisNotNull(obj)){
				if( "ID".equalsIgnoreCase(element.getColumnName())  ){
					idInner = "WHERE ID=?";
					params[params.length - 1] = element.getFieldValue(obj);
				}else{
					sb.append( element.getColumnName() );
					sb.append("=?,");
					params[i++] = element.getFieldValue(obj);
				}
			}
		}
		sb.deleteCharAt(sb.length()-1);
		// WHERE 
		sb.append(idInner);
		return this.queryRunner.update(sb.toString() , ArrayUtils.subarray(params, 0, i) );
	}
	/* (non-Javadoc)
	 * @see com.wlh.jpa.IJpaRepository#findOne(java.lang.String)
	 */
	@Override
	public <T extends JpaEntity> T findOne(Class<T> cla, String id) throws SQLException{
		CascadeBeanProcessor convert = new CascadeBeanProcessor(this,cla);
		BeanHandler<T> handler = new BeanHandler<>(cla, new BasicRowProcessor(convert));
		return queryRunner.query("SELECT * FROM "+getTableName(cla) + " WHERE ID = ?",handler,id);
	}
	/* (non-Javadoc)
	 * @see com.wlh.jpa.IJpaRepository#findBy(T)
	 */
	@Override
	public <T extends JpaEntity> List<T> findBy(T obj) throws SQLException{
		Class<T> class1 = (Class<T>) getHELPER().getTableClass(obj);
		String tableName = getTableName(class1);
		ORMEntity ormEntity = new ORMEntity(class1);
		List<PrimitiveEntity> allExecptManyOnMany = ormEntity.getAllExecptManyOnMany();
		Object[] params = new Object[ allExecptManyOnMany.size()];
		// DELETE FROM user WHERE name = ?
		StringBuilder sb = new StringBuilder("SELECT * FROM ");
		sb.append(tableName);
		sb.append(" WHERE 1=1");
		int i = 0;
		for (PrimitiveEntity element : allExecptManyOnMany ) {
			if( element.fieldValueisNotNull(obj)){
				sb.append(" AND ");
				sb.append(  element.getColumnName()  );
				sb.append("=?");
				params[i++] =  element.getFieldValue(obj);
			}
		}
		CascadeBeanProcessor convert = new CascadeBeanProcessor(this,class1);
		BeanListHandler<T> handler = new BeanListHandler<>(class1, new BasicRowProcessor(convert));
		return queryRunner.query(sb.toString(), handler, ArrayUtils.subarray(params, 0, i) );
	} 

	protected String getTableName(Class<? extends JpaEntity> class1) {
		return getHELPER().getTableName(class1);
	}
	/* (non-Javadoc)
	 * @see com.wlh.jpa.IJpaRepository#findAll()
	 */
	@Override
	public <T extends JpaEntity> List<T> findAll(Class<T> class1) throws SQLException{
		CascadeBeanProcessor convert = new CascadeBeanProcessor(this,class1);
		BeanListHandler<T> handler = new BeanListHandler<>(class1, new BasicRowProcessor(convert));
		return queryRunner.query("SELECT * FROM "+getTableName(class1), handler);
	}  

}
