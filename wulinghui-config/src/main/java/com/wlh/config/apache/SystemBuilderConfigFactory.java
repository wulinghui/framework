package com.wlh.config.apache;

import org.apache.commons.configuration2.beanutils.BeanHelper;
import org.apache.commons.configuration2.builder.BasicBuilderProperties;
import org.apache.commons.configuration2.convert.DefaultListDelimiterHandler;

import com.wlh.log.LogMSG;
import com.wlh.util.Constant;
import com.wlh.util.SystemUtils;

/**
 * @author wulinghui
 * 这里主要需要和我们的系统进行整合。
 */
public abstract class SystemBuilderConfigFactory {
	private static SystemBuilderConfigFactory systemDefaultBuilderConfig = new SystemBuilderConfigFactory(){};
	private static BeanHelper beanHelper =  new BeanHelper();
	public  <T extends BasicBuilderProperties> void wrapBuilderConfig(T t){
			t.setConversionHandler(new ConfigConversionHandler());
			t.setLogger(new ConfigLogger(LogMSG.getLogger()));
			t.setThrowExceptionOnMissing(SystemUtils.getPropertyAndRemove("wlh:BasicBuilderProperties.setThrowExceptionOnMissing", true));
			t.setListDelimiterHandler(new DefaultListDelimiterHandler(Constant.STRING_SEPARATOR));
			t.setBeanHelper(beanHelper); // TODO 将来需要和IOC容器整理。
	}
	
	public static final <T extends BasicBuilderProperties> T wrapConfig(T t){
		systemDefaultBuilderConfig.wrapBuilderConfig(t);
		return t;
	}
	public static void setSystemDefaultBuilderConfig(
			SystemBuilderConfigFactory systemDefaultBuilderConfig) {
		SystemBuilderConfigFactory.systemDefaultBuilderConfig = systemDefaultBuilderConfig;
	}
	public static void setBeanHelper(BeanHelper beanHelper) {
		SystemBuilderConfigFactory.beanHelper = beanHelper;
	}
	public static BeanHelper getBeanHelper() {
		return beanHelper;
	}
}
