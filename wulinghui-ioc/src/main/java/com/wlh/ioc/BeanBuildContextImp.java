package com.wlh.ioc;

import java.util.Map;


public class BeanBuildContextImp implements BeanBuildContext{
	String beanName;   
	Class<?> beanClass ;
	Object[] parameter;
	Map<String,BeanFactory> allFactory;
	String factoryName;
	/* (non-Javadoc)
	 * @see com.wlh.ioc1.F#setFactoryName(java.lang.String)
	 */
	@Override
	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}
	/* (non-Javadoc)
	 * @see com.wlh.ioc1.F#getFactoryName()
	 */
	@Override
	public String getFactoryName() {
		return factoryName;
	}
	public BeanBuildContextImp(String beanName, Class<?> beanClass,
			Object[] parameter, Map<String,BeanFactory> allFactory) {
		super();
		this.beanName = beanName;
		this.beanClass = beanClass;
		this.parameter = parameter;
		this.allFactory = allFactory;
	}
	/* (non-Javadoc)
	 * @see com.wlh.ioc1.BeanBuildContext#getBeanName()
	 */
	@Override
	public String getBeanName() {
		return beanName;
	}
	/* (non-Javadoc)
	 * @see com.wlh.ioc1.BeanBuildContext#getBeanClass()
	 */
	@Override
	public Class<?> getBeanClass() {
		return beanClass;
	}
	/* (non-Javadoc)
	 * @see com.wlh.ioc1.BeanBuildContext#getParameter()
	 */
	@Override
	public Object[] getParameter() {
		return parameter;
	}
	/* (non-Javadoc)
	 * @see com.wlh.ioc1.BeanBuildContext#getAllFactory()
	 */
	@Override
	public Map<String,BeanFactory> getAllFactory() {
		return allFactory;
	}
	
}
