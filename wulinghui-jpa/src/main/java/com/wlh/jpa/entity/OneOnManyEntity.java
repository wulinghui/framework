package com.wlh.jpa.entity;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Iterator;

import com.wlh.exception.ConvertRunException;

public class OneOnManyEntity extends OneOnOneEntity{

	public OneOnManyEntity(Field field, Field onField) {
		super(field, onField);
	}

	@Override
	public Object getFieldValue(Object target) {
		try {
			Object object = field.get(target);
			if (object instanceof Collection) {
				Collection new_name = (Collection) object;
				Iterator iterator = new_name.iterator();
				if( iterator.hasNext() ){
					object = iterator.next();
					return onField.get(object);
				}
			}
			return null;
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new ConvertRunException(e);
		}
	}
	
}
