package com.google.code.garbagecan.jettystudy.sample6;

import java.io.File;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.SystemUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.webapp.WebAppContext;

public class WebAppContextSub extends WebAppContext {

	@Override
	protected void doStart() throws Exception {
		super.doStart();
		
	}

	@Override
	protected void loadSystemClasses() {
		
		super.loadSystemClasses();
		test();
	}

	@Override
	protected void loadConfigurations() throws Exception {
		super.loadConfigurations();
		test();
	}

	@Override
	protected void startContext() throws Exception {
		// TODO Auto-generated method stub
		super.startContext();
		test();
	}

	
	
	@Override
	protected void doStop() throws Exception {
//		test(); 
		
		super.doStop();
	}

	protected void test()  {
		try {
			System.out.println("\n=======test==============");
			Map<String, Object> _managedAttributes = new HashMap<String, Object>();
			 Field declaredField = FieldUtils.getDeclaredField(ContextHandler.class, "_managedAttributes",true);
			 declaredField = FieldUtils.getField(ContextHandler.class, "_managedAttributes",true);
			 Field[] allFields = FieldUtils.getAllFields(this.getClass());
			 for (Field field : allFields) {    
//				if(field.getName().equals("_initParams")){
//					field.setAccessible(true); 
//					_managedAttributes = (Map<String, Object>) declaredField.get(this);
//				}
				field.setAccessible(true);   
					System.out.println( field+ "==="+ declaredField.get(this));
				}   
//         _managedAttributes = (Map<String, Object>) declaredField.get(context);
			 System.out.println("_managedAttributes=="+ this.getInitParams());  
//			 MapUtils.debugPrint(System.out,"wode",System.getProperties()); 
			   
//			MapUtils.debugPrint(out, label, map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}    
//	public static void main1(String[] args) { 
//		//1.数组转换为list，map   
//		String[] arr = {"123","456","789","123"}; 
//		List<String> list = Arrays.asList(arr); 
//		//数组转换为map,一维数组时，奇数为key,偶数为value,奇数个元素，最后一个舍掉 
//		//.二维数组当做两个一维数组 
//		Map map = MapUtils.putAll(new HashMap(), new String[]{}); 
//
//		String[][] ss = {{"a","b","e"},{"c","d","f"}}; 
//		MapUtils.debugPrint(System.out,"wode",map); 
//		MapUtils.putAll(map, arr); 
////		MapUtils.debugPrint(System.out,arr,map); 
//		MapUtils.putAll(map, ss); 
////		MapUtils.verbosePrint(System.out,arr,MapUtils.invertMap(map)); 
//
//		//list 转换为arr 
//		List<String> ls = new ArrayList<String>(); 
//		ls.add("wch"); 
//		ls.add("name"); 
////		String[] as = (String[]) ls.toArray(); 
//
//		} 
	
}
