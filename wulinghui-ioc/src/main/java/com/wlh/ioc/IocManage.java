package com.wlh.ioc;

import java.util.Map;








import org.apache.commons.configuration2.AbstractConfiguration;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.beanutils.BeanHelper;





import com.wlh.config.SystemConfig;
//import com.wlh.config.SystemConfig;
import com.wlh.config.WrapEntity;
import com.wlh.config.apache.SystemBuilderConfigFactory;
import com.wlh.ioc.apache.BeanFactory;

/**
 * @author wulinghui
 * 该类的使用请查看com.wlh.ioc.BeanFactoryManage
 * 如果想修改默认的BeanFactoryManage实现，请查看
 * `SystemConfig.get().setSingle(BeanFactoryManage.class, new AbstractBeanFactoryManage());`
 */
public  abstract class IocManage {
	private final static WrapEntity<BeanFactoryManage> beanFactoryManage = WrapEntity.getWrapEntityBySystemConfig(BeanFactoryManage.class);
	IocManage(){}
//	static{
//		SystemConfig.get().setSingle(BeanFactoryManage.class, new AbstractBeanFactoryManage());
//	}
	static{
		if( beanFactoryManage.isEmpty() ){
			BeanHelper beanHelper = SystemBuilderConfigFactory.getBeanHelper();
			AbstractBeanFactoryManage abstractBeanFactoryManage = new AbstractBeanFactoryManage(beanHelper);
			beanFactoryManage.setWrapObj(abstractBeanFactoryManage);
		}
	}
	public static boolean setBeanFactory(BeanFactory factory) {
		return beanFactoryManage.getWrapObj().setBeanFactory( factory 	);
	}
	public  static Map<String,BeanFactory> getAllFactory() {
		return beanFactoryManage.getWrapObj().getAllFactory();
	}
	public static boolean setBeanFactory(String name, BeanFactory factory) {
		return beanFactoryManage.getWrapObj().setBeanFactory(name, factory);
	}
	public static Object getBean(String name) throws RuntimeException {
		return beanFactoryManage.getWrapObj().getBean(name);
	}
	public static <T> T getBean(String name, Class<T> requiredType)
			throws RuntimeException {
		return beanFactoryManage.getWrapObj().getBean(name, requiredType);
	}
	public static Object getBean(String name, Object... args) throws RuntimeException {
		return beanFactoryManage.getWrapObj().getBean(name, args);
	}
	public static <T> T getBean(Class<T> requiredType) throws RuntimeException {
		return beanFactoryManage.getWrapObj().getBean(requiredType);
	}
	public static <T> T getBean(Class<T> requiredType, Object... args)
			throws RuntimeException {
		return beanFactoryManage.getWrapObj().getBean(requiredType, args);
	}
	public static String getScopeFlag(String name) {
		return beanFactoryManage.getWrapObj().getScopeFlag(name);
	}
	public static String getScopeFlag(Class<?> requiredType) { 
		return beanFactoryManage.getWrapObj().getScopeFlag(requiredType);
	}
	public static Class<?> getType(String name) throws RuntimeException {
		return beanFactoryManage.getWrapObj().getType(name);
	}
	public static boolean isTypeMatch(String name, Class<?> typeToMatch) {
		return beanFactoryManage.getWrapObj().isTypeMatch(name, typeToMatch);
	}
}
