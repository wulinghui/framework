package com.wlh.config;

public class WrapClassConfig2 extends ConfigAdpat {

	public WrapClassConfig2(IConfig config) {
		super(config);
	}

	@Override
	public <T> void setSingle(Object value) {
		Class<?> cls = null;
		if (value instanceof WrapEntity) {
			Object wrapObj = ((WrapEntity) value).getWrapObj();
			cls = wrapObj.getClass();
		} else {
			cls = value.getClass();
		}
		this.setSingle(cls,value);
	}

	@Override // 不变
	public void setSingle(Class<?> cls, Object value) {
		Object single = null;
		try {
			single = getValue(cls);
		} catch (Exception e) {
			
		}
		// 没有
		if( single == null){
			super.setSingle(cls,value);
		}else{
			// 有的话，只把里面的值放进去。不替换包装对象
			if (single instanceof WrapEntity) {
				WrapEntity new_name = (WrapEntity) single;
				if (value instanceof WrapEntity) {
					value = ((WrapEntity) value).getWrapObj();
				}
				new_name.setWrapObj(value);
			} else {
				super.setSingle(cls, value);
			}
		}
		
	}

	/** 
	 * 
	 */
	@Override
	public <T> T getSingle(Class<T> cls) {
		Object value = null;
		try {
			value = getValue(cls);
		} catch (Exception e) {
			
		}
		if (value instanceof WrapEntity) {
			value = ((WrapEntity) value).getWrapObj();
		} 
		return (T) value;
	}

	/**这个方法和
	 * @param cls
	 * @return
	 */
	protected <T> Object getValue(Class<T> cls) {
//		return super.getProperty(BaseConfig.SINGLE_FLAG_UUID+cls.getName());
		return super.getProperty(BaseConfig.getSingleKey(cls)) ;
	}
}
