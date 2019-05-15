package com.wlh.aop.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.configuration2.beanutils.BeanCreationContext;

import com.wlh.aop.proxy.Proxy;
import com.wlh.aop.proxy.ProxyManager;
import com.wlh.exception.ConvertRunException;
import com.wlh.ioc.AbstractBeanFactory;
import com.wlh.ioc.BeanEntity;
import com.wlh.ioc.apache.BeanDeclaration;
import com.wlh.log.ILogger;
import com.wlh.log.LogMSG;
import com.wlh.util.ClassHelper;

/**
 * @author wulinghui
 * 和ReflectBeanFactory是同级的。
 */
public class AopFactory extends AbstractBeanFactory{
	private static ILogger logger = LogMSG.getLogger();
	private final Set<Class<?>> CLASS_SET;
	private Map<Class<?>, List<Proxy>> targetMap;
	/**
	 * @param basePackageNama 从应用包包括子包下找代理和被代理类。
	 */
	public AopFactory(String basePackageNama) {
		this(ClassHelper.getClassSet(basePackageNama));
	}
	/**
	 * @param cLASS_SET 从set里面找代理和被代理类。
	 */
	public AopFactory(Set<Class<?>> cLASS_SET ) {
		CLASS_SET = cLASS_SET;
		init();
	}
	public void init() {
		try {
			//获得所有的切面代理类
            Map<Class<?>, Set<Class<?>>> proxyMap = createProxyMap();
            //
//          Map<Class<?>, List<Proxy>> targetMap = createTargetMap(proxyMap);
            this.targetMap = createTargetMap(proxyMap);
            for (Map.Entry<Class<?>, List<Proxy>> targetEntry : targetMap.entrySet()) {
                try {
					Class<?> targetClass = targetEntry.getKey();
					List<Proxy> proxyList = targetEntry.getValue();
					//这里报错，就不放入Map里面了。
					Object proxy = ProxyManager.createProxy(targetClass, proxyList);
				} catch (Throwable e) { 
					logger.warn(e);
				}
            }
          
        } catch (Exception e) {
            throw new ConvertRunException(e);
        }
	}

    protected  Map<Class<?>, Set<Class<?>>> createProxyMap() throws Exception {
        Map<Class<?>, Set<Class<?>>> proxyMap = new HashMap<Class<?>, Set<Class<?>>>();
        Set<Class<?>> proxyClassSet = ClassHelper.getClassSetBySuper(Proxy.class, CLASS_SET);
        for (Class<?> proxyClass : proxyClassSet) {   
        	Set<Class<?>> targetClassSet = createTargetClassSet(proxyClass , proxyClassSet);
        	//建立联系
        	proxyMap.put(proxyClass, targetClassSet);
        }
        return proxyMap;
    }
    /**创建Target类集合。
     * @param proxyClassSet 
     * @param proxyClass 
     * @param aspect
     * @return
     * @throws Exception
     */
    protected  Set<Class<?>> createTargetClassSet(Class<?> proxyClass, Set<Class<?>> proxyClassSet) throws Exception {
    	//TODO 默认为CLASS_SET里面不是Proxy的就都是。这里是超粗粒度的控制。
    	Set<Class<?>> targetClassSet = new HashSet<Class<?>>();
    	targetClassSet.addAll(CLASS_SET);
    	targetClassSet.removeAll(proxyClassSet);
        return targetClassSet;
    }

    protected  Map<Class<?>, List<Proxy>> createTargetMap(Map<Class<?>, Set<Class<?>>> proxyMap) throws Exception {
        Map<Class<?>, List<Proxy>> targetMap = new HashMap<Class<?>, List<Proxy>>();
        //遍历proxyMap，反转成targetMap
        for (Map.Entry<Class<?>, Set<Class<?>>> proxyEntry : proxyMap.entrySet()) {
            Class<?> proxyClass = proxyEntry.getKey();
            Set<Class<?>> targetClassSet = proxyEntry.getValue();
            //遍历targetClassSet放入代理类。
            for (Class<?> targetClass : targetClassSet) {
                try {	//这里报错就不放入List里面。
					Proxy proxy = (Proxy) proxyClass.newInstance();
					//目标类有代理类了。
					if (targetMap.containsKey(targetClass)) {
					    targetMap.get(targetClass).add(proxy);
					} else {//目标类没有代理类。
					    List<Proxy> proxyList = new ArrayList<Proxy>();
					    proxyList.add(proxy);
					    //目标类和代理面类。
					    targetMap.put(targetClass, proxyList);
					}
				} catch (Throwable e) {
				}
            }
        }
        return targetMap;
    }

   
    
	/**如果CLASS_SET被更改后建议再手动调用init方法同步Map;
	 * @return the cLASS_SET
	 */
	public Set<Class<?>> getCLASS_SET() {
		return CLASS_SET;
	}
//	@Override
//	public Object createBean(BeanBuildContext bcc) throws RuntimeException {
//		Class targetClass = bcc.getBeanClass();
//		targetClass= ProxyManager.getProxyClass(targetClass , this.targetMap.get(targetClass) );
//		BeanBuildContextImp beanBuildContextImp = new BeanBuildContextImp(bcc.getBeanName(), targetClass, bcc.getParameter(), bcc.getAllFactory());
//		return beanFactory.createBean(beanBuildContextImp);
//	}
	@Override
	 /**新new代理对象。无属性
		 */
	public Object createBean0(BeanCreationContext bcc,
			BeanDeclaration beandel, BeanEntity beanFactoryParameter)
			throws Exception {
		Class targetClass = beanFactoryParameter.getTypeToMatch();
		return ProxyManager.createProxy(targetClass, this.targetMap.get(targetClass));
//		if (createProxy instanceof TestA) {
//			TestA new_nam1 = (TestA) createProxy;
//			new_nam1.aaa();
//		}
//		targetClass= ProxyManager.getProxyClass(targetClass , this.targetMap.get(targetClass) );
//		if (beandel instanceof BeanDeclarationImp) {
//			BeanDeclarationImp new_name = (BeanDeclarationImp) beandel;
//			new_name.setBeanClassName(targetClass);
//			beanFactoryParameter.setTypeToMatch(targetClass); 
//		}
//		return beanFactory.createBean0(bcc, beandel, beanFactoryParameter);
	}
	@Override
	public String getScopeFlag0(BeanDeclaration beandel,
			BeanEntity beanFactoryParameter) throws RuntimeException {
		return beandel.PROTOTYPE;
	}
}
