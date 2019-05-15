package com.wlh.util;

import java.util.List;
import java.util.Map;

public class MapUtils extends org.apache.commons.collections.MapUtils{
	/**有些应用场景仅仅只需要一次就行了，如常量的定义com.wlh.util.Constant,安全性的保护,减少map维护的压力。
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static <K,V> V getPropertyAndRemove(Map<K,V> properties,K key, V defaultValue){
		V orDefault = properties.getOrDefault(key, defaultValue);
		properties.remove(key);
		return orDefault;
	}
	/**从properties里面找到list对应的value放入数组valueArray中。
	 * @param properties
	 * @param list
	 * @param valueArray
	 */
	public static <K,V> void toArrayFromList(Map<K,V> properties ,List<K> list,V[] valueArray){
		if( properties == null || list==null ||  valueArray == null || list.size() > valueArray.length ) return;
		for (int i = 0; i < list.size(); i++) {
			valueArray [i] = properties.get(list.get(i));
		}
	}
}
