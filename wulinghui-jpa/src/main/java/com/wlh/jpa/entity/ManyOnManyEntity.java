package com.wlh.jpa.entity;

import java.lang.reflect.Field;

import static com.wlh.jpa.JpaHelper.*;

import com.wlh.util.TypeResolvable;

public class ManyOnManyEntity extends OneOnManyEntity{

	public ManyOnManyEntity(Field field, Field onField) {
		super(field, onField);
	}
 
	/**获得多对多建表的sql语句。
	 * @return
	 */
	public String getManyOnManyCreatTableSql(){
		// 这里默认3个字段，id , 一表名 ,  二表名。
		StringBuilder sb = new StringBuilder("CREATE TABLE ");
		sb.append(getManyOnManyTableName());
		sb.append('(');
		sb.append("id VARCHAR(100) PRIMARY KEY,");
		sb.append( getManyColumName() );
		sb.append(" VARCHAR(100) ,"); 
		sb.append(  getOnManyColumName() );
		sb.append(" VARCHAR(100) ");
		sb.append(')');
		return sb.toString();
	}
	public String getManyColumName() {
		return getHELPER().getTableName(getGenericClass(field) );
	}
	
	public String getOnManyColumName() {
		return getHELPER().getTableName(getGenericClass(onField) );
	}
	/**
	 * @return 查询多对多关联列的sql语句
	 */
	public String getSelectOnColumSql(){
		StringBuilder sb = new StringBuilder("SELECT ");
		sb.append(getOnTableName());
		sb.append(".ID ");
		sb.append("FROM ");
		sb.append(getManyOnManyTableName());
		sb.append(',');
		sb.append(getOnTableName());
		sb.append("WHERE ");
		sb.append(getOnTableName());
		sb.append(".ID=");
		sb.append(getManyOnManyTableName());
		sb.append('.');
		sb.append( getOnManyColumName() );
		sb.append(" AND ");
		sb.append(getManyOnManyTableName());
		sb.append('.');
		sb.append(getManyColumName());
		sb.append("=?");
		return sb.toString();
	}

	public String getManyOnManyTableName(){
		return getHELPER().getManyOnManyTableName(
				getGenericClass(field),
				getGenericClass(onField));
	}

	protected Class<?> getGenericClass(Field field) {
		return TypeResolvable.forField(field).getGeneric(0).toClass();
	}

	@Override
	public Object getFieldValue(Object target) {
		return null;
	}
}
