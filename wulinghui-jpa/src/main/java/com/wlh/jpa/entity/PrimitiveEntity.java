package com.wlh.jpa.entity;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.wlh.exception.ConvertRunException;
import com.wlh.util.FieldUtils;

import static com.wlh.jpa.JpaHelper.*;

/**
 * @author wulinghui
 * 普通的
 */
public class PrimitiveEntity {
	Field field;
	public static final Map<Class,String> type2col = new HashMap<>();
	static{
		type2col.put(int.class, "NUMBER(10)");
		type2col.put(Integer.class, "NUMBER(10)");
		type2col.put(String.class, "VARCHAR(100)");
	}
	public PrimitiveEntity(Field field) {
		super();
		this.field = field;
	}
	public String getFieldName(){
		return field.getName();
	}
	public Field getField() {
		return field;
	}
	/**从target里面获得，数据库真实的值。
	 * 一对一, 一对多，是对应的值。
	 * 多对
	 * @param target
	 * @return
	 */
	public Object getFieldValue(Object target){
		try {
			return field.get(target);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new ConvertRunException(e);
		}
	}
	/**判断field的Value 是否不为null
	 * @param target 
	 * @return false - null
	 */
	public boolean fieldValueisNotNull( Object target){
		try {
			return this.getFieldValue(target) != null;
		} catch (Exception e) {
			return false;
		}
	}
	public String getColumnName(){
		return getFieldName().toUpperCase();
	}
	public String getColumnType(){
		String string = type2col.get( field.getType() );
		// 默认的类型为String类型。
		if( string == null){
			string =  type2col.get( String.class );
		}
		return string;
	}
	public String getTableName(){
		return getHELPER().getTableName( field.getDeclaringClass() );
	}
	/**判断是否相等，用于判断是否进入。
	 * @param name
	 * @return
	 */
	public boolean equals(String name) {
		return getFieldName().equalsIgnoreCase(name);
	}
}
