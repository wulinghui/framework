package com.wlh.aop.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.configuration2.beanutils.BeanCreationContext;
import org.apache.commons.lang3.ClassUtils;

import com.wlh.ioc.AbstractBeanFactory;
import com.wlh.ioc.BeanEntity;
import com.wlh.ioc.IocManage;
import com.wlh.ioc.apache.BeanDeclaration;

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
	public Object createBean0(BeanCreationContext bcc, BeanDeclaration beandel,
			BeanEntity beanFactoryParameter) throws Exception {
		Class<?> beanClass = beanFactoryParameter.getTypeToMatch();
		Object[] parameter = beanFactoryParameter.getArgs();
		if( beanClass != null &&  parameter != null ){
			int flag = Integer.parseInt(parameter[0].toString());
			if( ClassUtils.isAssignable(Map.class, beanClass) ){
				return newMap(flag);
			}else if( ClassUtils.isAssignable(List.class, beanClass) ){
				return newList(flag);
			}else if( ClassUtils.isAssignable(Set.class, beanClass) ){
				return newSet(flag);
			}
		}
		return null;
	}
	@Override
	public int getLevel(BeanDeclaration bcc) {
		String beanClassName = bcc.getBeanClassName();
		if( Map.class.getName().equals(beanClassName) ||  List.class.getName().equals(beanClassName) ||  Set.class.getName().equals(beanClassName)  ){
			return MAX_LEVEL;
		}
		return -1;
	}
	@Override
	public String getScopeFlag0(BeanDeclaration beandel,
			BeanEntity beanFactoryParameter) throws RuntimeException {
		Class<?> beanClass = beanFactoryParameter.getTypeToMatch();
		return beandel.PROTOTYPE;
	}
}
