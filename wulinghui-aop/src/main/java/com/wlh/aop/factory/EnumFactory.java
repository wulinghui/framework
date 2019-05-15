package com.wlh.aop.factory;

import java.util.Map;
import java.util.Objects;

import com.wlh.ioc.AbstractBeanFactory;
import com.wlh.ioc.BeanEntity;
import com.wlh.ioc.apache.BeanDeclaration;

/**
 * @author wulinghui
 * 枚举工厂。
 * IocManage.getBean(TestFactory.class,"1");
 */
public class EnumFactory extends SingleFactory {
	public EnumFactory(AbstractBeanFactory beanFactory) {
		super(beanFactory);
	}

	public EnumFactory(AbstractBeanFactory beanFactory,
			Map<String, Object> cache) {
		super(beanFactory, cache);
	}

	@Override
	protected String getMapKey(BeanEntity beanFactoryParameter) {
		return super.getMapKey(beanFactoryParameter) + getFlag(beanFactoryParameter);
	}

	@Override
	public String getScopeFlag0(BeanDeclaration beandel,
			BeanEntity beanFactoryParameter) throws RuntimeException {
		return beandel.ENUM;
	}
	
	public String getFlag(BeanEntity beanFactoryParameter){
		Object[] args = beanFactoryParameter.getArgs();
		if( Objects.nonNull(args)  && args.length > 1)   return "-"+args[0].toString();
		return "";
	}
	
	

}
