package com.wlh.ioc;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.wlh.log.ILogger;
import com.wlh.log.LogMSG;

public class AbstractBeanFactoryManage implements BeanFactoryManage {
	
	public static ILogger logger = LogMSG.getLogger();
	/*
	 *  保证put进去的顺序。
	 *  为了更好的利用AbstractBeanFactory的getLevel()方法
	 */
	Map<String,BeanFactory> map =  new LinkedHashMap<>();   // new Hashtable<>();
	@Override
	public Map<String,BeanFactory> getAllFactory() {
		return new LinkedHashMap<>(map);
	}

	@Override
	public synchronized boolean  setBeanFactory(String name,BeanFactory factory) {
		 map.put(name, factory);
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
		BeanBuildContext bcc = newBeanBuildContext(name, null, null);
		
		BeanFactory lookupBeanFactory = lookupBeanFactory(bcc); 
		
		return lookupBeanFactory.getScopeFlag(bcc);
	}

	@Override
	public String getScopeFlag(Class<?> requiredType) {
		BeanBuildContext bcc = newBeanBuildContext(null, requiredType, null);
		
		BeanFactory lookupBeanFactory = lookupBeanFactory(bcc); 
		
		return lookupBeanFactory.getScopeFlag(bcc);
	}

	@Override
	public Class<?> getType(String name) throws RuntimeException {
		return getBean( name ).getClass();
	}
	protected Object getBean(String name,  Class<?> typeToMatch ,Object... args)  {
		BeanBuildContext bcc = newBeanBuildContext(name, typeToMatch, args);
		Object createBean = null;
		BeanFactory lookupBeanFactory = null;
		do{
			try {
				lookupBeanFactory = lookupBeanFactory(bcc); 
				
				createBean = lookupBeanFactory.createBean(bcc);
				if(createBean != null ){
					//从bcc里面移除当前的BeanFactory。
					AbstractBeanFactory.getAllFactoryNotMyself(lookupBeanFactory, bcc);
					break;
				}
			} catch (RuntimeException e) {
				logger.warn("lookupBeanFactory.createBean(bcc)有异常"+lookupBeanFactory , e);
			}
		}while(true);
		 
		 return createBean;
	}

	protected BeanBuildContext newBeanBuildContext(String name,
			Class<?> typeToMatch, Object... args) {
		return new BeanBuildContextImp(name, typeToMatch, args, getAllFactory());
	}
	protected BeanFactory lookupBeanFactory(BeanBuildContext bcc){
		int max = -1;
		int level2 = -1;
		BeanFactory returnFactory = null ;
		BeanFactory beanFactory = null;
		for (Entry<String, BeanFactory> element : map.entrySet()) {
			beanFactory = element.getValue();
			level2 = beanFactory .getLevel(bcc);
			if( max < level2 ){
				max = level2;
				returnFactory = beanFactory;
				bcc.setFactoryName(element.getKey());
				if( level2 == BeanFactory.MAX_LEVEL) break;
			}
		}
		return returnFactory;
	}
	@Override
	public boolean isTypeMatch(String name, Class<?> typeToMatch) {
		return typeToMatch.isAssignableFrom(getType(name));
	}


//	public static void main(String[] args) {
//		boolean assignableFrom = Object.class.isAssignableFrom(AbstractBeanFactoryManage.class);
//		System.out.println(assignableFrom);
//	}
}
