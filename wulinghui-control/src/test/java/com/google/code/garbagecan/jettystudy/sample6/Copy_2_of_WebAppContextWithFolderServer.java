package com.google.code.garbagecan.jettystudy.sample6;

import org.eclipse.jetty.deploy.DeploymentManager;
import org.eclipse.jetty.deploy.providers.WebAppProvider;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppClassLoader;
import org.eclipse.jetty.webapp.WebAppContext;

public class Copy_2_of_WebAppContextWithFolderServer {
	public static void main(String[] args) throws Exception {
		Server server = new Server(8080);
		System.setProperty("jetty.reload", "automatic");
		WebAppContext context = new WebAppContext();
		context.setContextPath("/myapp");
		String str = "C:/Users/Administrator/workspace/test/src/main/webapp/";
		// web.xml可以改成其他名字，如web1.xml。
		// 如果不指明配置文件的位置，会根据项目资源setResourceBase路径找下面的web.xml。如果找不到，默认页面index.html优先于index.jsp
		context.setDescriptor(str + "WEB-INF/web.xml"); // maven项目web.xml路径
		context.setResourceBase(str);// maven项目资源路径
		// context.setDescriptor("E:/HLZT/chat/WEB-INF/web.xml");
		// //普通项目web.xml路径
		// context.setResourceBase("E:/HLZT/chat/WebRoot");//普通项目资源路径
		context.setParentLoaderPriority(false);
		server.setHandler(context);

		server.start();
		// server.stop();

		server.join();
	}
}
