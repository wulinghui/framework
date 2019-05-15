package com.wlh.ioc;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.configuration2.beanutils.BeanHelper;
import org.apache.commons.configuration2.beanutils.ConstructorArg;
import org.apache.commons.lang3.reflect.FieldUtils;

import com.wlh.beanutils.BeanUtils;
import com.wlh.exception.ConvertRunException;
import com.wlh.ioc.apache.BeanDeclaration;
import com.wlh.ioc.apache.BeanFactory;
import com.wlh.log.ILogger;
import com.wlh.log.LogMSG;

public class AbstractBeanFactoryManage implements BeanFactoryManage {
	
	public static ILogger logger = LogMSG.getLogger();
	private BeanHelper beanHelper;
	 private final Map<String, org.apache.commons.configuration2.beanutils.BeanFactory> beanFactories;
	public AbstractBeanFactoryManage(BeanHelper beanHelper) {
		super();
		this.beanHelper = beanHelper;
		Field declaredField = FieldUtils.getDeclaredField(BeanHelper.class, "beanFactories",true);
		try {
			beanFactories = (Map<String, org.apache.commons.configuration2.beanutils.BeanFactory>) declaredField.get(beanHelper);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new ConvertRunException(e);
		}
	}
	
	/*
	 *  保证put进去的顺序。
	 *  为了更好的利用AbstractBeanFactory的getLevel()方法
	 */
	@Override
	public Map<String,BeanFactory> getAllFactory() {
		Map<String,BeanFactory> map = new HashMap();
		Set<Entry<String, org.apache.commons.configuration2.beanutils.BeanFactory>> entrySet = beanFactories.entrySet();
		for (Entry<String, org.apache.commons.configuration2.beanutils.BeanFactory> entry : entrySet) {
			map.put(entry.getKey(), (BeanFactory)entry.getValue());
		}
		return map;
	}

	@Override
	public synchronized boolean  setBeanFactory(String name,BeanFactory factory) {
		beanHelper.registerBeanFactory(name, factory);
		return true;
	}
	@Override
	public boolean setBeanFactory(BeanFactory factory) {
		return setBeanFactory(factory.getClass().getName() , factory);
	}

	@Override
	public Object getBean(String name) throws RuntimeException {
		return  getBean(name, null, null);
	}

	@Override
	public <T> T getBean(String name, Class<T> requiredType)
			throws RuntimeException {
		return (T) getBean(name, requiredType, null);
	}

	@Override
	public Object getBean(String name, Object... args) throws RuntimeException {
		return getBean(name, null, args);
	}

	@Override
	public <T> T getBean(Class<T> requiredType) throws RuntimeException {
		return (T) getBean(null, requiredType, null);
	}

	@Override
	public <T> T getBean(Class<T> requiredType, Object... args)
			throws RuntimeException {
		return (T) getBean(null, requiredType, args);
	}

	@Override
	public String getScopeFlag(String name) {
		return getScopeFlag(name,null);
	}
	public String getScopeFlag(String name,Class<?> requiredType) {
//		BeanDeclaration bcc = newBeanDeclaration(name, null, null);
//		
//		BeanFactory lookupBeanFactory = lookupBeanFactory(bcc); 
//		return lookupBeanFactory.getScopeFlag(bcc);
		BeanDeclarationImp bcc = new BeanDeclarationImp();
		Object param = setDeclaration(name, requiredType, bcc, null);
		lookupBeanFactory(bcc);
		String beanFactoryName = bcc.getBeanFactoryName();
		org.apache.commons.configuration2.beanutils.BeanFactory beanFactory = this.beanFactories.get(beanFactoryName);
		if(  beanFactory instanceof BeanFactory){
			BeanFactory beanFactory2 = (BeanFactory) beanFactory;
			return beanFactory2.getScopeFlag(bcc); 
		}
		return "";
	}

	@Override
	public String getScopeFlag(Class<?> requiredType) {
		return getScopeFlag(null,requiredType);
	}

	@Override
	public Class<?> getType(String name) throws RuntimeException {
		return getBean( name ).getClass();
	}
	protected Object getBean(String name,  Class<?> typeToMatch ,Object... args)  {
		BeanDeclarationImp bcc = new BeanDeclarationImp();
		BeanEntity param = setDeclaration(name, typeToMatch, bcc, args);
		lookupBeanFactory(bcc);
		return getBean(bcc, param );
	}

	protected BeanEntity setDeclaration(String name, Class<?> typeToMatch,
			BeanDeclarationImp bcc, Object... args) {
		bcc.setAllFactory(getAllFactory()); 
		bcc.setBeanClassName(typeToMatch);
		BeanEntity param = new BeanEntity(name,typeToMatch,args);
		bcc.setBeanFactoryParameter(param);
		return param;
	}
	/**需要自己指定Factory和其它的参数。
	 * @param bcc
	 * @param param  //这个将传递到BeanFactory里面。
	 * @return
	 */
	protected Object getBean(BeanDeclarationImp bcc , BeanEntity param)  {
		return beanHelper.createBean(bcc, param.getTypeToMatch(), param );
	}
	protected void lookupBeanFactory(BeanDeclarationImp bcc) {
		Set<Entry<String, BeanFactory>> entrySet = bcc.getAllFactory().entrySet();
		BeanFactory beanFactory = null;
		String beanFactoryName = null;
		int max = -1;
		int level2 = -1;
		for (Entry<String, BeanFactory> entry : entrySet) {
			beanFactoryName = entry.getKey();
			beanFactory = entry.getValue();
			level2 = beanFactory .getLevel(bcc);
			if( max < level2 ){
				max = level2;
				bcc.setBeanFactoryName(beanFactoryName);
				if( level2 == BeanFactory.MAX_LEVEL) break;
			}
		}
	}

	@Override
	public boolean isTypeMatch(String name, Class<?> typeToMatch) {
		return typeToMatch.isAssignableFrom(getType(name));
	}
	//相同引用
//	public static void main(String[] args) throws Throwable, IllegalAccessException {
//		
//		Field declaredField = FieldUtils.getDeclaredField(BeanHelper.class, "beanFactories",true);
//		BeanHelper beanHelper2 = new BeanHelper();
//		Object object = declaredField.get(beanHelper2);
//		AbstractBeanFactoryManage abstractBeanFactoryManage = new AbstractBeanFactoryManage(beanHelper2);
//		System.out.println(object);
//		System.out.println(object == abstractBeanFactoryManage.beanFactories);
//	}
	
//	public static void main(String[] args) {
//		boolean assignableFrom = Object.class.isAssignableFrom(AbstractBeanFactoryManage.class);
//		System.out.println(assignableFrom);
//	}
}
