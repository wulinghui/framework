package com.wlh.util;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;

import com.wlh.exception.ConvertRunException;


public class FieldUtils extends org.apache.commons.lang3.reflect.FieldUtils {
	/**获得所有不为null值的属性。
	 * @param obj
	 * @return 
	 */
	public static Map<String, Object> getAllNotNullField(Object obj){
		List<Field> allFieldsList = getAllFieldsList(obj.getClass());
		Map<String,Object> map = new HashedMap();
		Object value;
		for (Field field : allFieldsList) {
			field.setAccessible(true);
			try {
				value = field.get(obj);
				if( value != null ){
					map.put(field.getName(), value);
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new ConvertRunException(e);
			}
		}
		return map;
	}
}
