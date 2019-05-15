package com.wlh.aop.factory;

import java.lang.reflect.Field;
import java.util.Objects;

import org.apache.commons.configuration2.beanutils.BeanCreationContext;
import org.apache.commons.lang3.reflect.FieldUtils;

import com.wlh.ioc.AbstractBeanFactory;
import com.wlh.ioc.BeanEntity;
import com.wlh.ioc.IocManage;
import com.wlh.ioc.apache.BeanDeclaration;

public class IocFactory extends AdapatBeanFactory{
	
	public IocFactory(AbstractBeanFactory beanFactory) {
		super(beanFactory);
	}
	
	@Override
	public Object createBean0(BeanCreationContext bcc, BeanDeclaration beandel,
			BeanEntity beanFactoryParameter) throws Exception {
		Object createBean = super.beanFactory.createBean(bcc);
		
		Object[] args = beanFactoryParameter.getArgs();
		if( Objects.nonNull(args)){
			for (Object object : beanFactoryParameter.getArgs()) {
				Field declaredField = FieldUtils.getDeclaredField(createBean.getClass(), object.toString());
				if(Objects.isNull(declaredField)) continue;
				declaredField.setAccessible(true);
				declaredField.set(createBean, IocManage.getBean(declaredField.getDeclaringClass()));
			}
		}
//		List<Field> allFieldsList = FieldUtils.getAllFieldsList(createBean.getClass());
//		for (Field field : allFieldsList) {
//			try {
//				BeanUtils.setProperty(createBean, field.getName(), IocManage.getBean(field.getDeclaringClass()));
//			} catch (IllegalAccessException | InvocationTargetException e) {
//				throw new ConvertRunException(e);
//			}
//		}
		return createBean;
	}

	
	/* 因为这里引用了IocManage.getBean如果优先级不是最低的话，容易死循环。
	 * @see com.wlh.ioc.AbstractBeanFactory#getLevel(com.wlh.ioc.BeanBuildContext)
	 */
	@Override
	public int getLevel(BeanDeclaration bcc) {
		return 0;
	}
	
}
