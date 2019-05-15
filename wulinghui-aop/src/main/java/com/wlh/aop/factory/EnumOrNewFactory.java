package com.wlh.aop.factory;

import java.util.Map;

import org.apache.commons.configuration2.beanutils.BeanCreationContext;

import com.wlh.ioc.AbstractBeanFactory;
import com.wlh.ioc.BeanEntity;
import com.wlh.ioc.apache.BeanDeclaration;

public class EnumOrNewFactory extends EnumFactory{
	public static final String NewFlag = "make";
	public EnumOrNewFactory(AbstractBeanFactory beanFactory,
			Map<String, Object> cache) {
		super(beanFactory, cache);
	}

	public EnumOrNewFactory(AbstractBeanFactory beanFactory) {
		super(beanFactory);
	}


	@Override
	public String getScopeFlag0(BeanDeclaration beandel,
			BeanEntity beanFactoryParameter) throws RuntimeException {
		if(NewFlag.equalsIgnoreCase(getMapKey(beanFactoryParameter))){
			return beandel.PROTOTYPE;
		}
		return super.getScopeFlag0(beandel, beanFactoryParameter);
	}

	@Override
	public Object createBean0(BeanCreationContext bcc, BeanDeclaration beandel,
			BeanEntity beanFactoryParameter) throws Exception {
		String scopeFlag0 = getScopeFlag0(beandel, beanFactoryParameter);
		if( beandel.PROTOTYPE.equals(scopeFlag0)){
			return getBeanFactory().createBean0(bcc, beandel, beanFactoryParameter);
		}else{
			return super.createBean0(bcc, beandel, beanFactoryParameter);
		}
	}
}
