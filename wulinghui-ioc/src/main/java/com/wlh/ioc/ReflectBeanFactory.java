package com.wlh.ioc;

import org.apache.commons.configuration2.beanutils.BeanCreationContext;
import org.apache.commons.lang3.reflect.ConstructorUtils;

import com.wlh.exception.ConvertRunException;
import com.wlh.ioc.apache.BeanDeclaration;

/**
 * @author wulinghui
 * 仅仅通过反射构造方法实现new对象
 * 注意:bcc.getParameter()就是构造方法参数
 */
public class ReflectBeanFactory extends AbstractBeanFactory{

	@Override
	public Object createBean0(BeanCreationContext bcc,
			BeanDeclaration beandel, BeanEntity beanFactoryParameter)
			throws Exception {
		try {  
//			return bcc.getBeanClass().newInstance();  
			// apache强不强大。
			return ConstructorUtils.invokeConstructor(beanFactoryParameter.getTypeToMatch(), beanFactoryParameter.getArgs());
		} catch (Exception e) {
			throw new ConvertRunException(e);
		}
	}

	@Override
	public String getScopeFlag0(BeanDeclaration beandel,
			BeanEntity beanFactoryParameter) throws RuntimeException {
		return beandel.PROTOTYPE;
	}
}
