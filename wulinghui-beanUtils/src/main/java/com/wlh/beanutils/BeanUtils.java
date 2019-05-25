package com.wlh.beanutils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;


public class BeanUtils extends org.apache.commons.beanutils.BeanUtils {
	  public static Map<String, String> describe(final Map<String, String> bean)
	            throws IllegalAccessException, InvocationTargetException,
	            NoSuchMethodException {
		  	if(bean instanceof Map){
		  		return bean;
		  	}
	        return org.apache.commons.beanutils.BeanUtils.describe(bean);
	    }
}
