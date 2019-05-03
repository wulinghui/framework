package com.wlh.ioc;

import com.wlh.exception.ConvertRunException;

/**
 * @author wulinghui
 * 仅仅通过反射实现new对象
 */
public class ReflectBeanFactory extends AbstractBeanFactory{

	@Override
	public Object createBean(BeanBuildContext bcc) throws RuntimeException {
//		new BeanHelper().createBean(data, defaultClass, param)
		try {
			return bcc.getBeanClass().newInstance();
		} catch (Exception e) {
			throw new ConvertRunException(e);
		}
	}

	@Override
	public String getScopeFlag(BeanBuildContext bcc) throws RuntimeException {
		return PROTOTYPE;
	}
}
