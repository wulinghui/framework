package com.google.code.garbagecan.jettystudy.sample6;

import org.eclipse.jetty.deploy.DeploymentManager;
import org.eclipse.jetty.deploy.PropertiesConfigurationManager;
import org.eclipse.jetty.deploy.bindings.DebugListenerBinding;
import org.eclipse.jetty.deploy.providers.WebAppProvider;
import org.eclipse.jetty.server.DebugListener;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.WebAppContext;

public class CopyOfWebAppContextWithFolderServer {  
	public static void main(String[] args) throws Exception {    
		Server server = new Server(8080);  
		WebAppContext context = new WebAppContext();
		context.setContextPath("/myapp"); //org.apache.jasper.runtime.TldScanner
		String str = "C:/Users/Administrator/workspace/test/src/main/webapp/";
		// web.xml可以改成其他名字，如web1.xml。  
		// 如果不指明配置文件的位置，会根据项目资源setResourceBase路径找下面的web.xml。如果找不到，默认页面index.html优先于index.jsp
		context.setDescriptor(str + "WEB-INF/web.xml"); // maven项目web.xml路径
		context.setResourceBase(str);// maven项目资源路径
		// //普通项目web.xml路径
		//普通项目资源路径
		context.setParentLoaderPriority(true);
		
		HandlerCollection handlers = new HandlerCollection();
        ContextHandlerCollection contexts = new ContextHandlerCollection();
        handlers.setHandlers(new Handler[] { contexts, context });
        server.setHandler(handlers);
		 
		 DeploymentManager deployer = new DeploymentManager();
	        DebugListener debug = new DebugListener(System.err,true,true,true);
	        server.addBean(debug);
	        deployer.addLifeCycleBinding(new DebugListenerBinding(debug));
	        deployer.setContexts(contexts);
	        deployer.setContextAttribute(
	                "org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern",
	                ".*/[^/]*servlet-api-[^/]*\\.jar$|.*/javax.servlet.jsp.jstl-.*\\.jar$|.*/[^/]*taglibs.*\\.jar$");

	        WebAppProvider webapp_provider = new WebAppProvider();
	        webapp_provider.setMonitoredDirName(str);
//	        webapp_provider.setMonitoredDirName(jetty_base + "/webapps");
//	        webapp_provider.setDefaultsDescriptor(jetty_home + "/etc/webdefault.xml");
	        webapp_provider.setScanInterval(30);
	        webapp_provider.setExtractWars(true);
	        webapp_provider.setConfigurationManager(new PropertiesConfigurationManager());

	        deployer.addAppProvider(webapp_provider);
	        server.addBean(deployer);

	        // === setup jetty plus ==
	        Configuration.ClassList classlist = Configuration.ClassList
	                .setServerDefault( server );
	        classlist.addAfter(
	                "org.eclipse.jetty.webapp.FragmentConfiguration",
	                "org.eclipse.jetty.plus.webapp.EnvConfiguration",
	                "org.eclipse.jetty.plus.webapp.PlusConfiguration");

	        classlist.addBefore("org.eclipse.jetty.webapp.JettyWebXmlConfiguration",
	                            "org.eclipse.jetty.annotations.AnnotationConfiguration");
		server.start();
		server.join();
	}
}
