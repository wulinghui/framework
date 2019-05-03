package com.wlh.config;

import org.apache.commons.configuration2.AbstractConfiguration;
import org.junit.Test;

/**
 * @author wulinghui
 * 测试静态属性和默认配置的整合。
 * 这样使用默认配置就可以统一管理所有系统的配置了。
 */
public class SystemConfigTest {
	private static  SystemConfigTest obj;  
	private int count;
	public SystemConfigTest setCount(int count) {
		this.count = count;
		return this;
	}
	/**
	 * 可以看到这里面直接set是不能同步的。
	 * 把obj引用给config
	 */
	IConfig iConfig = SystemConfig.get();
	@Test
	public final void testGet() {
		
		SystemConfig.setSystemConfig( new BaseConfig( (AbstractConfiguration) iConfig.getConfiguration() ) );
		obj = new SystemConfigTest().setCount(1);
		IConfig iConfig = SystemConfig.get();
		iConfig.setProperty("single", obj);
		SystemConfigTest single = (SystemConfigTest) iConfig.get(SystemConfigTest.class, "single");
		System.out.println( obj); 
		System.out.println(single == obj); 
		//修改
		iConfig.setProperty("single", new SystemConfigTest().setCount(2));
		single =  (SystemConfigTest) iConfig.get(SystemConfigTest.class, "single");
		System.out.println( obj ); 
		System.out.println(single == obj); 
		System.out.println(  System.getProperties()); 
		System.out.println(new SystemConfigTest() == new SystemConfigTest()); 
	}
	/**
	 * 可以看到把config引用给obj也都不同步
	 */
	@Test
	public final void testGet1() {
		SystemConfig.setSystemConfig( new BaseConfig( (AbstractConfiguration) iConfig.getConfiguration() ) );
		iConfig = SystemConfig.get();
		iConfig.setSingle( new SystemConfigTest().setCount(1) ); 
		obj = iConfig.getSingle(SystemConfigTest.class);
		SystemConfigTest single = iConfig.getSingle(SystemConfigTest.class);
		//修改
		System.out.println(obj.toString() + single );
		
		iConfig.setSingle( new SystemConfigTest().setCount(2) );
		
		System.out.println(obj.toString() + single );
	}
	private static  WrapEntity<SystemConfigTest> wrap;  
	/**
	 *  这时候需要一个包装类，来实现指针的指针。
	 *  @see com.wlh.config.WrapEntity<T>
	 *  但是这里看到了用户使用其他特别麻烦。
	 *  且Object property = iConfig.getProperty(WrapClassConfig.getKey( SystemConfigTest.class));
		这一行代码侵入性和耦合性太强。
	 */
	@Test
	public final void testGet2() {
		SystemConfig.setSystemConfig(new WrapClassConfig( new BaseConfig( (AbstractConfiguration) iConfig.getConfiguration() ) ) );
		iConfig = SystemConfig.get();
		
		wrap = new WrapEntity<SystemConfigTest>(new SystemConfigTest().setCount(1));
		iConfig.setSingle( wrap ); 
		System.out.println(wrap.toString() + iConfig.getSingle( SystemConfigTest.class) );
		
		Object property = iConfig.getProperty(WrapClassConfig.getKey( SystemConfigTest.class));
		if(property instanceof WrapEntity){
			((WrapEntity) property).setWrapObj(new SystemConfigTest().setCount(3));
		}
		System.out.println(wrap.toString() + iConfig.getSingle( SystemConfigTest.class) );
	}
	/**
	 * 建议使用以下的方式,无侵入式包装同步。
	 */
	@Test
	public final void testGet3() {
		SystemConfig.setSystemConfig(new WrapClassConfig2( new BaseConfig( (AbstractConfiguration) iConfig.getConfiguration() ) ) );
		iConfig = SystemConfig.get();
		
		wrap = new WrapEntity<SystemConfigTest>(new SystemConfigTest().setCount(1));
		iConfig.setSingle( wrap ); 
		SystemConfigTest single = iConfig.getSingle( SystemConfigTest.class);
		System.out.println(wrap.toString() + single );
		
		iConfig.setSingle( new SystemConfigTest().setCount(2) );
		single = iConfig.getSingle( SystemConfigTest.class);
		System.out.println(wrap.toString() + single );
	}
	/**
	 *  虽然代码已经做到了无侵入式包装同步，但是以下的示例可以看到如果不给WrapEntity对象赋值的话，iConfig.setSingle( wrap ); 将报错。
	 *  同时这里的代码也有点多。需要进一步整合。
	 */
	@Test
	public final void testGet4() {
		SystemConfig.setSystemConfig(new WrapClassConfig2( new BaseConfig( (AbstractConfiguration) iConfig.getConfiguration() ) ) );
		iConfig = SystemConfig.get();
		
		wrap = new WrapEntity<SystemConfigTest>();
		iConfig.setSingle( wrap ); 
		SystemConfigTest single = iConfig.getSingle( SystemConfigTest.class);
		wrap.setWrapObj(new SystemConfigTest().setCount(1));
		System.out.println(wrap.toString() + single );
		
		iConfig.setSingle( new SystemConfigTest().setCount(2) );
		single = iConfig.getSingle( SystemConfigTest.class);
		System.out.println(wrap.toString() + single );
	}
	/**
	 * 
	 * @see com.wlh.config.WrapEntity.putSystemConfig(); 
	 * 可以少2行代码。
	 */
	@Test
	public final void testGet5() {
		SystemConfig.setSystemConfig(new WrapClassConfig2( new BaseConfig( (AbstractConfiguration) iConfig.getConfiguration() ) ) );
		iConfig = SystemConfig.get();
		
		wrap = new WrapEntity<SystemConfigTest>(new SystemConfigTest().setCount(3)).putSystemConfig();
		SystemConfigTest single = iConfig.getSingle( SystemConfigTest.class );
		System.out.println(wrap.toString() + single );
		
		iConfig.setSingle( new SystemConfigTest().setCount(2) );
		single = iConfig.getSingle( SystemConfigTest.class);
		System.out.println(wrap.toString() + single );
	}
	public String toString() {
		return "SystemConfigTest [count=" + count + "]";
	}
	
}
