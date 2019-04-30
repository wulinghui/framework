package com.wlh.config;

import java.util.ArrayList;

import org.apache.commons.configuration2.AbstractConfiguration;
import org.apache.commons.configuration2.SystemConfiguration;
import org.apache.commons.configuration2.builder.BasicBuilderParameters;
import org.apache.commons.configuration2.builder.BasicConfigurationBuilder;
import org.apache.commons.configuration2.ex.ConfigurationException;

import com.wlh.config.apache.SystemBuilderConfigFactory;
import com.wlh.exception.ConvertRunException;
import com.wlh.log.LogMSG;

/**
 * @author wulinghui
 * 系统级别(对应一个classLoad)默认的配置。
 * 可以自定义。
 * 当第3方jar引入的时候建议在jar入口(MANIFEST.MF)的class类中使用以下代码配置:
 * static{  // 在加载这个类
 * 		IConfig iConfig = SystemConfig.get();
		boolean containsKey = iConfig.containsKey("wlh.log.sytem");
		// 判断有无配置
		if( !containsKey ){
		// 没有就添加配置。
			iConfig.addSingle(LogMSG.getLogger());
			iConfig.addProperty("wlh.log.list", new ArrayList());
		}
 * }
 * 如何加载这个类?提供一系列的静态方法或者MANIFEST.MF。。。
 */
public abstract class SystemConfig {
	private static IConfig systemConfig;
	static{
		BasicConfigurationBuilder<SystemConfiguration> basicConfigurationBuilder = new BasicConfigurationBuilder<SystemConfiguration>(SystemConfiguration.class);
		BasicBuilderParameters setFileName1 = new BasicBuilderParameters();
		SystemBuilderConfigFactory.wrapConfig(setFileName1);
		basicConfigurationBuilder.configure(setFileName1);
		AbstractConfiguration abstractConfiguration = null;
		try {
			// System.getProperties() 
			/* 如果是对于网状的类加载器的话，极大的节约了内存
			 * 但是有个问题,类加载用的是同一个资源。
			 */
			abstractConfiguration = basicConfigurationBuilder.getConfiguration();
			abstractConfiguration = (AbstractConfiguration) abstractConfiguration.subset("wlh:");
		} catch (ConfigurationException e) {
			throw new ConvertRunException(e);
		}
		systemConfig = new BaseConfig(abstractConfiguration);
	}
//	static{
		// TODO 这个对于每个类加载器都是独立的,但是内存消耗太大,我们这里先采用上面的方式。
//		BasicConfigurationBuilder<MapConfig> basicConfigurationBuilder = new BasicConfigurationBuilder<MapConfig>(MapConfig.class);
//		BasicBuilderParameters setFileName1 = new BasicBuilderParameters();
//		SystemBuilderConfigFactory.wrapConfig(setFileName1);
//		basicConfigurationBuilder.configure(setFileName1);
//		MapConfiguration abstractConfiguration = null;
//		try {
//			abstractConfiguration = basicConfigurationBuilder.getConfiguration();
//		} catch (ConfigurationException e) {
//			throw new ConvertRunException(e);
//		}
//		systemConfig = new BaseConfig(abstractConfiguration);
//	}
	
	public static IConfig get() {
		return systemConfig;
	}
	public static void setSystemConfig(IConfig systemConfig) {
		SystemConfig.systemConfig = systemConfig;
	}

	
}
