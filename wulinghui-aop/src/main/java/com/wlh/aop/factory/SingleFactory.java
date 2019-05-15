package com.wlh.aop.factory;

import java.util.Map;

import org.apache.commons.configuration2.beanutils.BeanCreationContext;

import com.wlh.ioc.AbstractBeanFactory;
import com.wlh.ioc.BeanEntity;
import com.wlh.ioc.IocManage;
import com.wlh.ioc.apache.BeanDeclaration;

public class SingleFactory extends AdapatBeanFactory {
	Map<String, Object> cache ;

	public SingleFactory(AbstractBeanFactory beanFactory,
			Map<String, Object> cache) {
		super(beanFactory);
		this.cache = cache;
	}
	public SingleFactory(AbstractBeanFactory beanFactory) {
		super(beanFactory);
		this.cache =  IocManage.getBean(Map.class,JavaUtilFactory.SELECT_OF_METHOD);
	}
	
	@Override
	public Object createBean0(BeanCreationContext bcc, BeanDeclaration beandel,
			BeanEntity beanFactoryParameter) throws Exception {
		String class1 = getMapKey(beanFactoryParameter);
		Object object = cache.get(class1);
		if(object == null){
			synchronized(this){
				object = cache.get(class1);
				if(object == null ){
					object = getBeanFactory().createBean(bcc);
					cache.put(class1, object);
				}
			}
		}
		return object;
	}
	protected String getMapKey(BeanEntity beanFactoryParameter) {
		return beanFactoryParameter.getClass().getName();
	}
	@Override
	public String getScopeFlag0(BeanDeclaration beandel,
			BeanEntity beanFactoryParameter) throws RuntimeException {
		return beandel.SESSION;
	}
}
