//package com.wlh.config;
//
//import java.util.List;
//
//import org.apache.commons.configuration.ConfigurationException;
//import org.apache.commons.configuration.DatabaseConfiguration;
//import org.apache.commons.configuration.PropertiesConfiguration;
//import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
//<!--    <dependency>  
//            <groupId>commons-configuration</groupId>
//            <artifactId>commons-configuration</artifactId>
//            <version>1.5</version>
//        </dependency>          
//        <dependency>
//            <groupId>commons-codec</groupId>
//            <artifactId>commons-codec</artifactId>
//            <version>1.10</version>
//        </dependency>  -->
///**
// * @author wulinghui
// * https://www.cnblogs.com/parryyang/p/6197685.html   configuration-1.x 版本的。
// * 看DatabaseConfiguration源码。 
// this.nameColumn = nameColumn;    
// this.name = name; 
// 对应的sql:
// 		StringBuffer query = new StringBuffer("SELECT DISTINCT " + keyColumn + " FROM " + table);
//        if (nameColumn != null)
//        {
//            query.append(" WHERE " + nameColumn + "=?");
//        }
// */
//public class ConfigTest {
//	public static final String fileName = "icp.properties";
//
//	public static PropertiesConfiguration cfg = null;
//	public static DatabaseConfiguration db_cfg = null;
//
//	static {
//		try {
//			cfg = new PropertiesConfiguration(fileName);
//		} catch (ConfigurationException e) {
//			e.printStackTrace();
//		}
//		// 当文件的内容发生改变时，配置对象也会刷新
//		cfg.setReloadingStrategy(new FileChangedReloadingStrategy());
//		
//		db_cfg = new DatabaseConfiguration(null, null, null, null);
//		db_cfg.getString("");
//	}
//
//	// 读String
//	public static String getStringValue(String key) {
//		return cfg.getString(key);
//	}
//
//	// 读int
//	public static int getIntValue(String key) {
//		return cfg.getInt(key);
//	}
//
//	// 读boolean
//	public static boolean getBooleanValue(String key) {
//		return cfg.getBoolean(key);
//	}
//
//	// 读List
//	public static List<?> getListValue(String key) {
//		return cfg.getList(key);
//	}
//
//	// 读数组
//	public static String[] getArrayValue(String key) {
//		return cfg.getStringArray(key);
//	}
//	public static void main(String[] args) {
//        String name = ConfigTest.getStringValue("name");
//        System.out.println("name:" + name);
//        int port = ConfigTest.getIntValue("port");
//        System.out.println("port:" + port);
//        boolean flag = ConfigTest.getBooleanValue("flag");
//        System.out.println("flag:" + flag);
//        List<String> users = (List<String>) ConfigTest.getListValue("users");
//        for (String user : users) { 
//            System.out.println("user:" + user);
//        }
//    }
//}
