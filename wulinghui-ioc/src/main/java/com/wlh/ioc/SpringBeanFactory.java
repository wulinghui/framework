package com.wlh.ioc;

import org.springframework.context.ApplicationContext;

/**
 * @author wulinghui
 * test com.wlh.ioc.SpringIocTest.main1()
 */
public class SpringBeanFactory extends AbstractBeanFactory {
	ApplicationContext context;
	
	public SpringBeanFactory(ApplicationContext context) {
		this.context = context;
	}

	@Override
	public Object createBean(BeanBuildContext bcc) throws RuntimeException {
		Class<?> beanClass = bcc.getBeanClass();
		String beanName = bcc.getBeanName();
		Object[] parameter = bcc.getParameter();
		if(beanClass !=null && beanName == null && parameter == null){
			return context.getBean(beanClass);
		}else if(beanClass ==null && beanName != null && parameter == null ){
			return context.getBean(beanName);
		}else if(beanClass !=null && beanName == null && parameter != null ){
			return context.getBean(beanClass, parameter);
		}else if(beanClass !=null && beanName != null && parameter == null ){
			return context.getBean(beanName, beanClass);
		}else if(beanClass ==null && beanName != null && parameter != null ){
			return context.getBean(beanName, parameter);
		}else {
			return null;
		}
	}

	@Override
	public String getScopeFlag(BeanBuildContext bcc) throws RuntimeException {
		if( context.isSingleton(bcc.getBeanName()) ) {
			return SINGLETON;
		}else if( context.isPrototype(bcc.getBeanName()) ){
			return PROTOTYPE;
		}else{
			return "";
		}
	}

}
