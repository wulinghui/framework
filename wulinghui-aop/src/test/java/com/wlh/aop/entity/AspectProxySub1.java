package com.wlh.aop.entity;

import com.wlh.aop.proxy.Proxy;
import com.wlh.aop.proxy.ProxyChain;

public class AspectProxySub1 implements Proxy {

	public Object doProxy(ProxyChain proxyChain) throws Throwable {
		System.out.println("AspectProxySub111111111111111");
		Object doProxyChain = proxyChain.doProxyChain();
		System.out.println("AspectProxySub2222222222222222");
		return doProxyChain;
	}    

}
