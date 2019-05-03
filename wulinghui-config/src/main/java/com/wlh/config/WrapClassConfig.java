package com.wlh.config;

/**
 * @author wulinghui
 * @deprecated 见原因 com.wlh.config.SystemConfigTest.testGet2()
 */
@Deprecated
public class WrapClassConfig extends ConfigAdpat {

	public WrapClassConfig(IConfig config) {
		super(config);
	}
	
//	@Override  // 这种实现有问题，不安全。会破坏之前的配置。
//	public <T> void setSingle(Object value) {
//		if(value instanceof WrapEntity ){
//			super.setSingle(((WrapEntity) value).getWrapObj().getClass(), value);
//		}else{
//			super.setSingle( value);
//		}
//	}
	@Override  // 这种实现有问题，不安全。会破坏之前的配置。
	public <T> void setSingle(Object value) {
		if(value instanceof WrapEntity ){
			this.setSingle( ((WrapEntity) value).getWrapObj().getClass(), value);
		}else{
			super.setSingle(value);
		}
	}
	@Override
	public void setSingle(Class<?> cls, Object value) {
		if(value instanceof WrapEntity ){
			super.setProperty(  getKey(cls) , value);
		}else{
			super.setSingle(cls, value);
		}
	}

	
	

	@Override
	public <T> T getSingle(Class<T> cls) {
		T single = null;
		try {
			single = super.getSingle(cls);
		} catch (Exception e) {
			
		}
		if(single == null){
			single = (T) super.get(WrapEntity.class, getKey(cls)).getWrapObj();
		}
		return single;
	}
	
	public static String getKey(Class<?> cls) {
		return WrapEntity.class + ":" + cls;
	}

}
