package com.wlh.config.apache;

import org.apache.commons.configuration2.beanutils.BeanHelper;
import org.apache.commons.configuration2.builder.BasicBuilderProperties;
import org.apache.commons.configuration2.convert.DefaultListDelimiterHandler;

import com.wlh.log.LogMSG;

/**
 * @author wulinghui
 * 这里主要需要和我们的系统进行整合。
 */
public abstract class SystemBuilderConfigFactory {
	private static SystemBuilderConfigFactory systemDefaultBuilderConfig = new SystemBuilderConfigFactory(){};
	
	public  <T extends BasicBuilderProperties> void wrapBuilderConfig(T t){
			t.setConversionHandler(new ConfigConversionHandler());
			t.setLogger(new ConfigLogger(LogMSG.getLogger()));
			t.setThrowExceptionOnMissing(true);
			t.setListDelimiterHandler(new DefaultListDelimiterHandler(';'));
			t.setBeanHelper(new BeanHelper()); // TODO 将来需要和IOC容器整理。
	}
	
	public static final <T extends BasicBuilderProperties> T wrapConfig(T t){
		systemDefaultBuilderConfig.wrapBuilderConfig(t);
		return t;
	}
	public static void setSystemDefaultBuilderConfig(
			SystemBuilderConfigFactory systemDefaultBuilderConfig) {
		SystemBuilderConfigFactory.systemDefaultBuilderConfig = systemDefaultBuilderConfig;
	}
	
}
