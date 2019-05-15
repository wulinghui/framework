package com.wlh.ioc;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.configuration2.beanutils.ConstructorArg;

import com.wlh.ioc.apache.BeanDeclaration;
import com.wlh.ioc.apache.BeanFactory;


public class BeanDeclarationImp implements BeanDeclaration{
	private String beanFactoryName;
	// apache默认传给工厂
	private Object beanFactoryParameter;
	// 需要实例的类型,默认是反射构造方法
	private String beanClassName;
	// 反省赋值对象
	private Map<String, Object> beanProperties;
	// 这里面的嵌套是Object是BeanDeclaration
	private Map<String, Object> nestedBeanDeclarations;
	// 这里查找必须是完全匹配。
	private Collection<ConstructorArg> constructorArgs;
	//
	private String scopeFlag;
	//
	private Map<String, BeanFactory> allFactory;
	
	public String getBeanFactoryName() {
		return beanFactoryName;
	}
	public void setBeanFactoryName(String beanFactoryName) {
		this.beanFactoryName = beanFactoryName;
	}
	public Object getBeanFactoryParameter() {
		return beanFactoryParameter;
	}
	public void setBeanFactoryParameter(Object beanFactoryParameter) {
		this.beanFactoryParameter = beanFactoryParameter;
	}
	public String getBeanClassName() {
		return beanClassName;
	}
	public void setBeanClassName(String beanClassName) {
		this.beanClassName = beanClassName;
	}
	public void setBeanClassName(Class beanClassName) {
		if( Objects.nonNull(beanClassName))
		this.beanClassName = beanClassName.getName();
	}
	public Map<String, Object> getBeanProperties() {
		return beanProperties;
	}
	public void setBeanProperties(Map<String, Object> beanProperties) {
		this.beanProperties = beanProperties;
	}
	public Map<String, Object> getNestedBeanDeclarations() {
		return nestedBeanDeclarations;
	}
	public void setNestedBeanDeclarations(Map<String, Object> nestedBeanDeclarations) {
		this.nestedBeanDeclarations = nestedBeanDeclarations;
	}
	public Collection<ConstructorArg> getConstructorArgs() {
		return constructorArgs;
	}
	public void setConstructorArgs(Collection<ConstructorArg> constructorArgs) {
		this.constructorArgs = constructorArgs;
	}
	public String getScopeFlag() {
		return scopeFlag;
	}
	public void setScopeFlag(String scopeFlag) {
		this.scopeFlag = scopeFlag;
	}
	public Map<String, BeanFactory> getAllFactory() {
		return allFactory;
	}
	public void setAllFactory(Map<String, BeanFactory> allFactory) {
		this.allFactory = allFactory;
	}
	
}
