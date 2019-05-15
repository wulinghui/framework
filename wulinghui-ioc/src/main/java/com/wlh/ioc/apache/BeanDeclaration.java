package com.wlh.ioc.apache;

import java.util.Map;


public interface BeanDeclaration extends org.apache.commons.configuration2.beanutils.BeanDeclaration {
	String SINGLETON = "singleton";
	String ENUM = "Enum";
	String PROTOTYPE = "prototype";
	String REQUEST = "request";
	String SESSION = "session";
	String GLOBAL_SESSION = "global Session";
	String getScopeFlag() throws RuntimeException;
	public abstract Map<String,BeanFactory> getAllFactory();
}
