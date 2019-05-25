package com.wlh.jpa.entity;

import static com.wlh.jpa.JpaHelper.getHELPER;

import java.lang.reflect.Field;

import com.wlh.exception.ConvertRunException;
import com.wlh.util.FieldUtils;
import com.wlh.util.TypeResolvable;

public class OneOnOneEntity extends PrimitiveEntity{
	protected Field onField;
	
	public OneOnOneEntity(Field field, Field onField) {
		super(field);
		this.onField = onField;
	}
	
	/**获得连接的类型,一对一的类型
	 * @return
	 */
	public Class getOnClass(){
		TypeResolvable forField = TypeResolvable.forField(field);
		if( forField.hasGenerics() ){
			forField = forField.getGeneric(0);
		}
		return forField.toClass();
	}
	public Field getOnField() {
		return onField;
	}
	public String getOnColumnName(){
		return getOnFieldName().toUpperCase();
	}
	public String getOnFieldName() {
		return onField.getName();
	}
	public Field getOnTableName(){
		getHELPER().getTableName( onField.getDeclaringClass() );
		return onField;
	}

	@Override
	public Object getFieldValue(Object target) {
		Object fieldValue = super.getFieldValue(target);
		try {
			return FieldUtils.readField(fieldValue, "id", true);
		} catch (IllegalAccessException e) {
			throw new ConvertRunException(e);
		}
	}
	
}
