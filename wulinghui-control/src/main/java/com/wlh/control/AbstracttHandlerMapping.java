package com.wlh.control;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.MapUtils;

import com.wlh.exception.ConvertRunException;
import com.wlh.ioc.IocManage;

public  class AbstracttHandlerMapping implements HandlerMapping {
	Map<String,Method> handlers;
	
	public AbstracttHandlerMapping(Map<String, Method> handlers) {
		super();
		this.handlers = handlers;
	}
	public AbstracttHandlerMapping() {
		this(Collections.synchronizedMap(new HashMap<>()));
	}

	@Override
	public Method getHandler(String handlerFlag) {
		return handlers.get(handlerFlag);
	}

	@Override
	public synchronized boolean addHandler(String handlerFlag, Method handler) {
		handlers.put(handlerFlag, handler);
		 //最后在设置可以进入。
		handler.setAccessible(true);
		return true;
	} 

	@Override
	public <T> boolean handleServlet(TxnContext context,
			HandlerInterceptor<T>[] handlerInterceptors) {
		try {
			for (HandlerInterceptor<T> handlerInterceptor : handlerInterceptors) {
				if( !handlerInterceptor.preHandle(context) ) return false;
			}
			invokeHandler(context);
			for (HandlerInterceptor<T> handlerInterceptor : handlerInterceptors) {
				handlerInterceptor.postHandle(context);
			}
		} catch (IllegalAccessException | InvocationTargetException
				| RuntimeException e) {
			throw new ConvertRunException(e);
		}finally{
			for (HandlerInterceptor<T> handlerInterceptor : handlerInterceptors) {
				handlerInterceptor.afterCompletion(context);
			}
		}
		return true;
	}
	protected void invokeHandler(TxnContext context)
			throws IllegalAccessException, InvocationTargetException {
		Method handler = this.getHandler(context.getHandlerFlag());
		handler.invoke(IocManage.getBean(handler.getClass()), new Object[]{context});
	}

}
