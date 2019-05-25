package com.wlh.jpa;

import java.util.HashMap;
import java.util.Map;

import com.wlh.jpa.entity.JpaEntity;
import com.wlh.jpa.handlers.CamelCase2UnderScoreCaseTranslator;

public abstract class JpaHelper{
	private static JpaHelper HELPER = new JpaHelper() {
	};
	Map<String,String> manyOnManyTemp = new HashMap<>();
	
	public String getTableName(Class cla) {
		return camelCase2UnderScore( 	cla.getSimpleName() );
	}
	//
	public String camelCase2UnderScore(String camelCase){
		return CamelCase2UnderScoreCaseTranslator.SINGLE.translate(camelCase);
	}
	public <T extends JpaEntity> Class<? extends JpaEntity> getTableClass(T obj) {
		return obj.getClass();
	}
	/** 获得多对多的中间表，线程安全的。
	 * 不分先后，用$连接。
	 * @param one
	 * @param two
	 * @return
	 */
	public synchronized String getManyOnManyTableName(Class one , Class two){
		String key = two.getName() + "=" +  one.getName();
		String value = manyOnManyTemp.get(key);
		//交换位置再试一下。
		if( value == null){
			key = one.getName() + "=" +  two.getName();
			value = manyOnManyTemp.get(key);
		}
		// 2个都没有，就是真的没有。
		if( value == null ){
			value = one.getSimpleName() + "_REF_" + two.getSimpleName();
			manyOnManyTemp.put(key, value );
		}
		return value;
	}

	public static JpaHelper getHELPER() {
		return HELPER;
	}

	public static void setHELPER(JpaHelper hELPER) {
		HELPER = hELPER;
	}
}
