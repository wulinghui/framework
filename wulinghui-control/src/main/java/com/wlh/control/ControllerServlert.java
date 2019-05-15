package com.wlh.control;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ClassUtils;

import com.wlh.aop.factory.JavaUtilFactory;
import com.wlh.ioc.IocManage;
import com.wlh.util.ClassHelper;

public class ControllerServlert extends HttpServlet{
	
	private static final long serialVersionUID = -4757520766420803202L;
	private String perfix;
	private String  packageName;
	private HandlerMapping mapping;
	private HandlerInterceptor[] handlerInterceptors = null;
	
	public ControllerServlert() {
		super();
	}
	public ControllerServlert(String packageName, String perfix , HandlerMapping mapping) {     
//		super(packageName); 2018年9月3日17:57:13s
		this.perfix = perfix;
		this.packageName =packageName;
		this.mapping = mapping;
	} 
	public void init() {
		 Collection<Class<?>> controllerClassSet = ClassHelper.getClassSet(packageName);
		 List<HandlerInterceptor> handlerInterceptorList = IocManage.getBean(List.class,JavaUtilFactory.INSERT_OF_METHOD);
			if (controllerClassSet != null && !controllerClassSet.isEmpty() ) {
	            for (Class<?> controllerClass : controllerClassSet) {
	                Method[] methods = getAllMethods(controllerClass);
	                if (ArrayUtils.isNotEmpty(methods)) {
	                    for (Method method : methods) {
	                        if ( canInitHandler(method) ) {
	                            String uri = toUri(method);
	                            mapping.addHandler(uri, method);
	                        }
	                    }
	                }
	               if( ClassUtils.isAssignable(HandlerInterceptor.class, controllerClass) ){
	            	   handlerInterceptorList.add( (HandlerInterceptor) IocManage.getBean(controllerClass) );
	               }
	            }
	            handlerInterceptorList.toArray(handlerInterceptors);
	        }
	}
	/**获得需要所有过滤的方法
	 * 默认是本来方法不包括父类。
	 * @param controllerClass
	 * @return
	 */
	protected Method[] getAllMethods(Class<?> controllerClass) {
		return controllerClass.getDeclaredMethods();
	}
	/**判断方法是否可以添加到mapping里面去，
	 * 默认为protected void txnUserList(TxnContext context){	}
	 * 
	 * @param method
	 * @return
	 */
	protected boolean canInitHandler(Method method) {
		Class<?>[] parameterTypes = method.getParameterTypes();
//		if(parameterTypes.length==0) return false;
		if(Objects.isNull(parameterTypes) || parameterTypes.length!=1) return false;
		return method.getName().startsWith("txn") && ClassUtils.isAssignable(TxnContext.class, parameterTypes[0]);
	}
	/**对应执行的路径是类名/1234.反射的后缀
	 */
	protected String toUri(Method method) {
		Class<?> declaringClass = method.getDeclaringClass();
		String uri = declaringClass.getSimpleName() + "/";
		String name = method.getName();
		name = name.substring(3, name.length());
		return "/"+uri + name + "." + this.perfix;  
	}
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			TxnContext context = new ServlertContext(req, resp);
			mapping.handleServlet(context , handlerInterceptors);
		}finally{
			TxnContext.getTxnContextLocal().remove();
		}
	}
}
