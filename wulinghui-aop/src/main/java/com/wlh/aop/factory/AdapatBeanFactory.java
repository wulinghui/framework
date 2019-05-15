package com.wlh.aop.factory;

import org.apache.commons.configuration2.beanutils.BeanCreationContext;

import com.wlh.ioc.AbstractBeanFactory;
import com.wlh.ioc.BeanEntity;
import com.wlh.ioc.apache.BeanDeclaration;

public abstract class AdapatBeanFactory extends AbstractBeanFactory {

	protected AbstractBeanFactory beanFactory;

	public AdapatBeanFactory(AbstractBeanFactory beanFactory) {
		super();
		this.beanFactory = beanFactory;
	}

	public AbstractBeanFactory getBeanFactory() {
		return beanFactory;
	}

	public Object createBean0(BeanCreationContext bcc,
			BeanDeclaration beandel, BeanEntity beanFactoryParameter)
			throws Exception {
		return beanFactory.createBean0(bcc, beandel, beanFactoryParameter);
	}

	@Override
	public String getScopeFlag0(BeanDeclaration beandel,
			BeanEntity beanFactoryParameter) throws RuntimeException {
		return beanFactory.getScopeFlag0(beandel, beanFactoryParameter);
	}
	
}
