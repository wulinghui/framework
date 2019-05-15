package com.wlh.aop.proxy;

import java.lang.reflect.Method;
import java.util.List;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import com.wlh.aop.entity.TestA;
import com.wlh.config.WrapEntity;

/**
 *代理管理器
 * @since 1.0.0
 * 如需改变默认实现请使用。
 * `SystemConfig.get().setSingle( this );`
 */
public abstract class ProxyManager {
	private static WrapEntity<IProxyManager> entity = new WrapEntity<IProxyManager>(new IProxyManager(){
		
		public  <T> T createProxy(final Class<?> targetClass, final List<Proxy> proxyList) {
	    	Enhancer enhancer = new Enhancer();
	    	
//	    	enhancer.setCallback(callback);
//	    	enhancer.create();
	    	//静态方法创建代理对象。
	        return (T) Enhancer.create(targetClass, new MethodInterceptor() {
	            public Object intercept(Object targetObject, Method targetMethod, Object[] methodParams, MethodProxy methodProxy) throws Throwable {
	            	//将所有参数下移至ProxyChain的属性。
	                return new ProxyChain(targetClass, targetObject, targetMethod, methodProxy, methodParams, proxyList).doProxyChain();
	            }
	        });
	    }
	}).putSystemConfig();
    
	
	public static <T> T createProxy(final Class<?> targetClass, final List<Proxy> proxyList) {
		return entity.getWrapObj().createProxy(targetClass, proxyList);
	}
	public static Class<?> getProxyClass(final Class<?> targetClass, final List<Proxy> proxyList) {
//		return entity.getWrapObj().createProxy(targetClass, proxyList).getClass();
		Class<? extends Object> class1 = entity.getWrapObj().createProxy(targetClass, proxyList).getClass();
		Object newInstance = null;
		try {
			newInstance = class1.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (newInstance instanceof TestA) {
			TestA new_name = (TestA) newInstance;
			new_name.aaa();
		}
		return class1;
	}
}