package com.wlh.util;

import com.wlh.convert.ConvertUtils;



public class SystemUtils extends org.apache.commons.lang3.SystemUtils{
	/**有些应用场景仅仅只需要一次就行了，如常量的定义com.wlh.util.Constant,安全性的保护。
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static <K,V> V getPropertyAndRemove(K key, V defaultValue){
		Object propertyAndRemove = MapUtils.getPropertyAndRemove(System.getProperties(), key, defaultValue);
		return (V) ConvertUtils.convert(propertyAndRemove, defaultValue.getClass());
	}
}
