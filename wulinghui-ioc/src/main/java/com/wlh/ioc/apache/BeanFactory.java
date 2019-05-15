package com.wlh.ioc.apache;




/**
 * @author wulinghui
 * 如果需要整合其他框架的话，请实现这个接口。
 * 最好继承AbstractBeanFactory
 */
public interface BeanFactory extends org.apache.commons.configuration2.beanutils.BeanFactory{
	int MAX_LEVEL = 999999999;
	abstract int getLevel(BeanDeclaration bcc);
	public abstract String getScopeFlag(BeanDeclaration bcc) throws RuntimeException ;
}
