package com.wlh.ioc;

import java.util.Set;

import org.apache.commons.configuration2.beanutils.BeanDeclaration;
import org.apache.commons.configuration2.beanutils.BeanFactory;
import org.apache.commons.configuration2.beanutils.BeanHelper;

public class ApacheBeanHelperTest {
	BeanHelper helper = new BeanHelper();


	public void registerBeanFactory(String name, BeanFactory factory) {
		helper.registerBeanFactory(name, factory);
	}

	public BeanFactory deregisterBeanFactory(String name) {
		return helper.deregisterBeanFactory(name);
	}

	public Set<String> registeredFactoryNames() {
		return helper.registeredFactoryNames();
	}

	public BeanFactory getDefaultBeanFactory() {
		return helper.getDefaultBeanFactory();
	}

	public void initBean(Object bean, BeanDeclaration data) {
		helper.initBean(bean, data);
	}

	public String toString() {
		return helper.toString();
	}

	public Object createBean(BeanDeclaration data, Class<?> defaultClass,
			Object param) {
		return helper.createBean(data, defaultClass, param);
	}

	public Object createBean(BeanDeclaration data, Class<?> defaultClass) {
		return helper.createBean(data, defaultClass);
	}

	public Object createBean(BeanDeclaration data) {
		return helper.createBean(data);
	}
}
