package com.wlh.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

/**
 * @author wulinghui
 * uri映射不冲突的Servlert。
 */
public class CombinedServlert extends HttpServlet{
	final HttpServlet[] httpServlets;

	public CombinedServlert(HttpServlet... httpServlets) {
		super();
		assert httpServlets == null : "httpServlets is null";
		this.httpServlets = httpServlets;
	}

	@Override
	public void service(ServletRequest req, ServletResponse res)
			throws ServletException, IOException {
		for (HttpServlet element : httpServlets) {
			element.service(req, res);
		}
	}
}
