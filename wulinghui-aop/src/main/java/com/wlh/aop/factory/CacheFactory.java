package com.wlh.aop.factory;

import java.util.Map;

import org.apache.commons.configuration2.beanutils.BeanCreationContext;

import com.wlh.ioc.AbstractBeanFactory;
import com.wlh.ioc.BeanEntity;
import com.wlh.ioc.IocManage;
import com.wlh.ioc.apache.BeanDeclaration;

/**
 * @author wulinghui
 * 这个是通过参数来保存实例的。
 */
public class CacheFactory extends AdapatBeanFactory{
	// 这个需要可以序列化的
//	Cache<BeanBuildContext, Object> cache = CacheMsgIoc.getInstance( new MutableConfiguration<BeanBuildContext,Object>()
//		    .setTypes(BeanBuildContext.class, Object.class)
//		    .setExpiryPolicyFactory(AccessedExpiryPolicy.factoryOf(Duration.ONE_DAY))
//		    .setStatisticsEnabled(true)).getCache(CacheFactory.class.getName());
	
	Map<BeanEntity, Object> cache  ;
			//new HashMap<BeanBuildContext, Object>();
	
	@Override
	public Object createBean0(BeanCreationContext bcc, BeanDeclaration beandel,
			BeanEntity beanFactoryParameter) throws Exception {
		Object object = cache.get(beanFactoryParameter);
		if(object == null){
			synchronized(this){
				object = cache.get(beanFactoryParameter);
				if(object == null ){
					object = getBeanFactory().createBean(bcc);
					cache.put(beanFactoryParameter, object);
				}
			}
		}
		return object;
	}

	public CacheFactory(AbstractBeanFactory beanFactory , Map<BeanEntity, Object> cache) {
		super(beanFactory);
		this.cache = cache;
	}
	public CacheFactory(AbstractBeanFactory beanFactory) {
		this(beanFactory, IocManage.getBean(Map.class,JavaUtilFactory.SELECT_OF_METHOD));
	}
}
