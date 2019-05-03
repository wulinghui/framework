package com.wlh.aop.proxy;

import java.util.List;

public interface IProxyManager {

	public abstract <T> T createProxy(Class<?> targetClass,
			List<Proxy> proxyList);

}