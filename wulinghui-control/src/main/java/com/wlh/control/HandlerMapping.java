package com.wlh.control;

import java.lang.reflect.Method;

public interface HandlerMapping {
	Method getHandler(String handlerFlag);
	boolean addHandler(String flag, Method handlerFlag);
	<T> boolean handleServlet(TxnContext context,HandlerInterceptor<T>[] handlerInterceptors);
}