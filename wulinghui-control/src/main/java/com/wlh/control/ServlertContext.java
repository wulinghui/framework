package com.wlh.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.IteratorUtils;
import org.apache.commons.collections.ResettableIterator;
import org.apache.commons.lang3.StringUtils;

import com.wlh.util.Constant;

public class ServlertContext extends TxnContext{
	Collection<String> printlnKeys;
	protected String target, workPropagation;
	public ServlertContext(Object request, Object response) {
		super(request, response);
	}
	public HttpServletRequest	getHttpRequest(){
		return (HttpServletRequest) getRequest();
	}
	public HttpServletResponse	getHttpResponse(){
		return (HttpServletResponse) getResponse();
	}
	@Override
	public String getHandlerFlag() {
//		HttpServletRequest httpRequest = getHttpRequest();
//		String uri = httpRequest.getRequestURI();
		return getHttpRequest().getRequestURI();
	}
	
	@Override
	public Object getPar(String key) {
		String[] parameterValues = getHttpRequest().getParameterValues(key);
		
//		if( Objects.nonNull(parameterValues)  ){
//			return getHttpRequest().getAttribute(key);
//		}else if( parameterValues.length == 1 ){
//			return parameterValues[0];
//		}else{
//			return StringUtils.join(parameterValues, Constant.STRING_SEPARATOR);
//		}
		return StringUtils.join(parameterValues, Constant.STRING_SEPARATOR);
	}
//	public static void main(String[] args) {
//		Object[] parameterValues = {"a","b"};
//		String join = StringUtils.join(parameterValues , ';');
//		System.out.println(join);  
//	}

	@Override
	public Object setPar(String key, Object obj) {
		getHttpRequest().setAttribute(key, obj);
		return Boolean.TRUE;
	}

	@Override
	public TxnContext println(String key, Object obj) {
		setPar(key, obj);  // 放入上下文中
		printlnKeys.add(key);   // 放入待显示的集合中
		return this;
	}

	/* (non-Javadoc)
	 * 暂时不显示。   // 因为还需要有过滤器的操作。
	 * @see com.wlh.control.TxnContext#goPage(java.lang.String, java.lang.String)
	 */
	@Override
	public TxnContext goPage(String target, String workPropagation) {
		this.target = target;
		this.workPropagation = workPropagation;
		return this;
	}
	public String getTarget() {
		return target;
	}
	public String getWorkPropagation() {
		return workPropagation;
	}
	public void outPage() throws ServletException, IOException{
		String[] split = target.split(":");
		if( split[0].startsWith("f") ){
			getHttpRequest().getRequestDispatcher(split[1]).forward(getHttpRequest(), getHttpResponse());
		}else if( split[0].startsWith("r") ){
			getHttpResponse().sendRedirect(split[1]);  
//			if( split[1].toLowerCase().startsWith("http://") ) {
//				getHttpResponse().sendRedirect(split[1]);  
//			}else{
//				getHttpResponse().sendRedirect(getHttpRequest().getContextPath() + split[1]); 
//			}
		}else{
			PrintWriter writer = getHttpResponse().getWriter();
			for (String key : printlnKeys) {
				writer.print(getPar(key));
			}
			writer.flush();
			writer.close(); 
		}
	}
	@Override
	protected Iterator<String> getKeysInternal() {
		//apache强不强?
		Enumeration<String> attributeNames = getHttpRequest().getAttributeNames();
		Enumeration<String> parameterNames = getHttpRequest().getParameterNames();
		Iterator<String> iterator = IteratorUtils.getIterator(attributeNames);
		Iterator<String> iterator2 = IteratorUtils.getIterator(parameterNames);
		return IteratorUtils.chainedIterator(iterator, iterator2);
	}
	
}
