package com.wlh.control;

public interface HandlerInterceptor<T> {
	
	/** 是否需要拦截？
	 * @param context
	 * @return true拦截
	 */
	boolean isInterceptor(T request);
	
	boolean  preHandle(TxnContext context);
	
	void  postHandle(TxnContext context);
	
	void  afterCompletion(TxnContext context);
	
}        
