package com.wlh.config;

import org.apache.commons.beanutils.BeanUtilsBean2;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.configuration2.DatabaseConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.BasicConfigurationBuilder;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.convert.DefaultListDelimiterHandler;
import org.apache.commons.configuration2.event.Event;
import org.apache.commons.configuration2.event.EventListener;
import org.apache.commons.configuration2.event.EventType;
import org.apache.commons.configuration2.reloading.ReloadingController;
import org.apache.commons.configuration2.reloading.ReloadingEvent;
import org.junit.Test;


/**
 * @author wulinghui
 * @see 用户指南 http://commons.apache.org/proper/commons-configuration/userguide/user_guide.html
 * http://commons.apache.org/proper/commons-configuration/apidocs/org/apache/commons/configuration2/DatabaseConfiguration.html  
 * configuration-2.x 版本的api。
 * 这里的配置仅仅是读数据库，没有做缓存。
 */
public class Config2Test {
	
	
	public void dbConfigTest() throws Exception{
		//有监听器的过程
		BasicConfigurationBuilder<DatabaseConfiguration> builder =
			     new BasicConfigurationBuilder<DatabaseConfiguration>(DatabaseConfiguration.class);
		 builder.configure(
			     new Parameters().database().setDataSource(null)
			         .setTable("myconfig")
			         .setKeyColumn("key")
			         .setValueColumn("value")
			 );
		 DatabaseConfiguration config = builder.getConfiguration();
			 builder.addEventListener(new EventType<>(null, null), new EventListener(){
					public void onEvent(Event event) {
						
					}} );
			 String value = config.getString("foo");
			 config.clear();
			 //没有监听器
			 DatabaseConfiguration databaseConfiguration = new DatabaseConfiguration();
	}
	@Test 
	public void dbXMLConfigTest1() throws Exception{
		FileBasedConfigurationBuilder<PropertiesConfiguration> builder =
			    new FileBasedConfigurationBuilder<PropertiesConfiguration>(PropertiesConfiguration.class)
			    .configure(new Parameters().properties()
			        .setFileName("icp.properties")
			        .setThrowExceptionOnMissing(true)
			        .setListDelimiterHandler(new DefaultListDelimiterHandler(';'))
			        .setIncludesAllowed(false));
			PropertiesConfiguration config = builder.getConfiguration();
			 while(true){
				 Thread.sleep(1000);
				  String string = config.getString("name");
				  System.out.println("string++"+string); 
			  }
	}
	
	@Test 
	public void dbXMLConfigTest() throws Exception{
		BasicConfigurationBuilder<PropertiesConfiguration> builder =
			     new BasicConfigurationBuilder<PropertiesConfiguration>(PropertiesConfiguration.class);
		//有监听器的过程
		  builder.configure(
			     new Parameters().properties().setFileName("icp.properties")
		 );
		  builder.addEventListener(new EventType<ReloadingEvent>(null, "111"), new EventListener<ReloadingEvent>(){

				@Override
				public void onEvent(ReloadingEvent event) {
					Object source = event.getSource();
					System.out.println(source);
					Object data = event.getData();
					System.out.println(data);
					ReloadingController controller = event.getController();
					System.out.println(controller);
					EventType<? extends Event> eventType = event.getEventType();
					System.out.println(eventType);
				}} );
		  PropertiesConfiguration config = builder.getConfiguration();
		  while(true){
			  String string = config.getString("name");
			  System.out.println("string++"+string); 
		  }
	}
}
