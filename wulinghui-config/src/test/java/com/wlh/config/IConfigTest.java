package com.wlh.config;

import java.util.Collections;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.builder.fluent.PropertiesBuilderParameters;
import org.apache.commons.configuration2.convert.DefaultListDelimiterHandler;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.wlh.config.apache.SystemBuilderConfigFactory;

/**
 * @author wulinghui
 */
public class IConfigTest {
	// public static void main(String[] args) {
	// IConfig iConfig = SystemConfig.get();
	// iConfig.addProperty("111", 111);
	// System.out.println(iConfig.getString("111"));
	// }
	@Test
	public void testSystem(){
		Map<String, String> getenv = System.getenv();
		System.out.println(getenv);
		Properties properties = System.getProperties();
		System.out.println(properties);
		IConfig iConfig = SystemConfig.get();
		iConfig.addProperty("IConfigTest", new IConfigTest());
		System.out.println(iConfig.get(IConfigTest.class, "IConfigTest") );  
	}
	@Test
	public void dbXMLConfigTest1() throws Exception {

		FileBasedConfigurationBuilder<PropertiesConfiguration> builder = new FileBasedConfigurationBuilder<PropertiesConfiguration>(
				PropertiesConfiguration.class).configure(new Parameters()
				.properties().setFileName("icp.properties")
				.setThrowExceptionOnMissing(true)
				.setListDelimiterHandler(new DefaultListDelimiterHandler(';'))
				.setIncludesAllowed(false));
		PropertiesConfiguration config = builder.getConfiguration();
		builder = new FileBasedConfigurationBuilder<PropertiesConfiguration>(
				PropertiesConfiguration.class).configure(new Parameters()
				.properties().setFileName("icp-default.properties")
				.setThrowExceptionOnMissing(true)
				.setListDelimiterHandler(new DefaultListDelimiterHandler(';'))
				.setConversionHandler(null).setIncludesAllowed(false));
		PropertiesConfiguration config1 = null;
		try {
			config1 = builder.getConfiguration();
		} catch (Exception e) {
			e.printStackTrace();
		}
		IConfig iConfig = null;
		// 没有默认文件
		if (config1 != null) {
			iConfig = new DefaultConfig(new BaseConfig(config), new BaseConfig(
					config1));
		} else {
			iConfig = new BaseConfig(config);
		}
		iConfig = new CacheConfig(iConfig);
		String string = config.getString("name");
		System.out.println("string++" + string);
	}

	@Test
	public void testSystemBuilderConfigFactory() throws Exception {
		PropertiesBuilderParameters setFileName1 = SystemBuilderConfigFactory
				.wrapConfig(new Parameters().properties()).setFileName(
						"icp.properties");
		PropertiesBuilderParameters setFileName = SystemBuilderConfigFactory
				.wrapConfig(new Parameters().properties()).setFileName(
						"icp-default.properties");
		FileBasedConfigurationBuilder<PropertiesConfiguration> builder = new FileBasedConfigurationBuilder<PropertiesConfiguration>(
				PropertiesConfiguration.class).configure(setFileName1);
		PropertiesConfiguration config = builder.getConfiguration();
		builder = new FileBasedConfigurationBuilder<PropertiesConfiguration>(
				PropertiesConfiguration.class).configure(setFileName);
		PropertiesConfiguration config1 = null;
		try {
			config1 = builder.getConfiguration();
		} catch (Exception e) {
			e.printStackTrace();
		}
		IConfig iConfig = null;
		// 没有默认文件
		if (config1 != null) {
			iConfig = new DefaultConfig(new BaseConfig(config), new BaseConfig(
					config1));
		} else {
			iConfig = new BaseConfig(config);
		}
		iConfig = new CacheConfig(iConfig);
		String string = config.getString("name");
		System.out.println("string++" + string);
	}
}
