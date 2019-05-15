package com.wlh.aop.factory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class EnhancerTest {
	public static void main(String[] args) throws ClassNotFoundException {
		run(); 
		run(); 
	}

	protected static void run() throws ClassNotFoundException {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(TestFactory.class);
		enhancer.setCallback(new MethodInterceptor() {
            public Object intercept(Object targetObject, Method targetMethod, Object[] methodParams, MethodProxy methodProxy) throws Throwable {
            	//将所有参数下移至ProxyChain的属性。
            	System.out.println("===========");  
                return methodProxy.invoke(targetObject, methodParams);
            }
        });
		Class createClass = enhancer.create().getClass(); 
		
		for (Constructor string : createClass.getDeclaredConstructors() ) {
			System.out.println(string); 
		}
		Class.forName(createClass.getName());
	}
}
