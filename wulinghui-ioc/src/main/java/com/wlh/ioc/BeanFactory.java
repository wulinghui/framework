package com.wlh.ioc;

/**
 * @author wulinghui
 * 如果需要整合其他框架的话，请实现这个接口。
 * 最好继承AbstractBeanFactory
 */
public interface BeanFactory {
	String SINGLETON = "singleton";
	String PROTOTYPE = "prototype";
	String REQUEST = "request";
	String SESSION = "session";
	String GLOBAL_SESSION = "global Session";
	
	int getLevel(BeanBuildContext bcc);
	
	Object createBean(BeanBuildContext bcc) throws RuntimeException;
	
	String getScopeFlag(BeanBuildContext bcc) throws RuntimeException;
}
