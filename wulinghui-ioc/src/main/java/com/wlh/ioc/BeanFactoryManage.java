package com.wlh.ioc;

import java.util.Map;

public interface BeanFactoryManage {
//	Collection<BeanFactory> getAllFactory(); 之后可能需要用到
	Map<String,BeanFactory> getAllFactory();
	boolean setBeanFactory(String name,BeanFactory factory);
	
	Object getBean(String name) throws RuntimeException;
	<T> T getBean(String name, Class<T> requiredType) throws RuntimeException;
	Object getBean(String name, Object... args) throws RuntimeException;
	<T> T getBean(Class<T> requiredType) throws RuntimeException;
	<T> T getBean(Class<T> requiredType, Object... args) throws RuntimeException;
	String getScopeFlag(String name);
	String getScopeFlag(Class<?> requiredType);
	
	Class<?> getType(String name) throws RuntimeException;
	boolean isTypeMatch(String name, Class<?> typeToMatch);
}
