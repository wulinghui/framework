package com.wlh.config;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author wulinghui
 * 一个包装类，来实现指针的指针。
 * 测试静态属性和默认配置的整合。
 * 这样使用默认配置就可以统一管理所有系统的配置了。
 * @param <T> 包装的类
 * @see com.wlh.config.SystemConfigTest.testGet5()
 */
public class WrapEntity<T> implements Serializable,Cloneable {
	private static final long serialVersionUID = -6439486762427808380L;
	T wrapObj;
	
	public WrapEntity() {
	}

	public WrapEntity(T wrapObj) {
		this.wrapObj = wrapObj;
	}

	public T getWrapObj() {
		return wrapObj;
	}

	public WrapEntity<T> setWrapObj(T wrapObj) {
		this.wrapObj = wrapObj;
		return this;
	}

	@Override
	public String toString() {
		return wrapObj.toString();
	}
	
	/** put this to @see SystemConfig
	 * @return true put成功
	 */
	public WrapEntity<T> putSystemConfig(){
//		Objects.requireNonNull(wrapObj,"WrapEntity field is null");
		assert wrapObj == null : "WrapEntity field is null";
		SystemConfig.get().setSingle( this );
		return this;
	}
	/**为了能使属性变成final
	 * @param cls
	 * @return
	 */
	public static <T> WrapEntity<T> getWrapEntityBySystemConfig(Class<T> cls){
		String singleKey = BaseConfig.getSingleKey(cls);
		return getWrapEntityBySystemConfig(singleKey);
	}

	public static  WrapEntity getWrapEntityBySystemConfig(String singleKey) {
		IConfig iConfig = SystemConfig.get();
		Object property = iConfig.getProperty(singleKey);
		if(property == null || !( property instanceof WrapEntity) ){
			synchronized(iConfig){
				property = iConfig.getProperty(singleKey);
				if(property == null || !( property instanceof WrapEntity) ){
					property = new WrapEntity(property);
					iConfig.addProperty(singleKey, property);
				}
			}
		}
		return  (WrapEntity) property;
	}
	public boolean isEmpty(){
		return wrapObj == null;
	}
}