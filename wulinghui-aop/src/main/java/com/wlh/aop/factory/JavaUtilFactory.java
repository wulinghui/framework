package com.wlh.aop.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.lang3.ClassUtils;

import com.wlh.ioc.AbstractBeanFactory;
import com.wlh.ioc.BeanBuildContext;
import com.wlh.ioc.IocManage;

public class JavaUtilFactory extends AbstractBeanFactory{
	static{
		IocManage.setBeanFactory( new JavaUtilFactory() );
	}
	JavaUtilFactory(){}
	/** 适用于在方法体里面使用查询和修改 */  // 采用uuid避免有人直接写-1 , 1  , 0 的硬编码。
	public static final int SELECT_OF_METHOD = UUID.randomUUID().variant();
	/** 适用于在属性级别里面使用查询和修改 */
	public static final int SELECT_OF_FIELD = UUID.randomUUID().variant();
	/** 适用于在方法体里面使用增删 */
	public static final int INSERT_OF_METHOD = UUID.randomUUID().variant();
	/** 适用于在属性级别里面使用增删 */
	public static final int INSERT_OF_FIELD = UUID.randomUUID().variant();
	/*
	 * 集合的flag默认 
	 */
	public static List newList(int flag) {
		return new ArrayList();
	}
	public static Set newSet(int flag) {
		return new HashSet();
	}
	public static Map newMap(int flag) {
		return new HashMap<>();
	}
	

	@Override
	public Object createBean(BeanBuildContext bcc) throws RuntimeException {
		Class<?> beanClass = bcc.getBeanClass();
		Object[] parameter = bcc.getParameter();
		if( beanClass != null &&  parameter != null ){
			int flag = Integer.parseInt(parameter[0].toString());
			if( ClassUtils.isAssignable(Map.class, beanClass) ){
				newMap(flag);
			}else if( ClassUtils.isAssignable(List.class, beanClass) ){
				newList(flag);
			}else if( ClassUtils.isAssignable(Set.class, beanClass) ){
				newSet(flag);
			}
		}
		return null;
	}

}
