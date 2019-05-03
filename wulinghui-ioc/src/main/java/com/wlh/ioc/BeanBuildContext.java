package com.wlh.ioc;

import java.util.Map;

public interface BeanBuildContext {

	public abstract String getBeanName();

	public abstract Class<?> getBeanClass();

	public abstract Object[] getParameter();

	public abstract Map<String,BeanFactory> getAllFactory();
	
	public abstract void setFactoryName(String factoryName);

	public abstract String getFactoryName();

}