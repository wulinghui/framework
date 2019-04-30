package com.wlh.config;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import org.apache.commons.configuration2.AbstractConfiguration;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.ConfigurationDecoder;

/**
 * @author wulinghui
 * 该类对AbstractConfiguration做了个代理。
 * 之前的类方法太多了。
 * 多余的方法都是些获得默认值的方法。
 */
public class BaseConfig implements IConfig {
	protected  AbstractConfiguration abstractConfiguration;
	public static  final String SINGLE_FLAG_UUID =UUID.randomUUID().toString().split("-")[1]+":";
	public BaseConfig(AbstractConfiguration abstractConfiguration) {
		super();
		this.abstractConfiguration = abstractConfiguration;
	} 
//	public static void main(String[] args) {
//		String uuid="0c312388-5d09-4f44-b670-5461605f0b1e";
//		UUID uuid1=UUID.randomUUID();
//		UUID uuid2=UUID.fromString(uuid);
//		System.out.println(uuid1);
//		System.out.println(uuid2);
//		System.out.println(SINGLE_FLAG_UUID);
//	}
	public AbstractConfiguration getConfiguration() {
		return abstractConfiguration;
	}
	@Override
	public <T> T getSingle(Class<T> cls) {
		return get(cls, SINGLE_FLAG_UUID+cls.getName());
	}
	@Override
	public void addSingle(Class<?> cls, Object value) {
		abstractConfiguration.addProperty(SINGLE_FLAG_UUID+cls.getName(), value);
	}
	@Override
	public <T> void addSingle(Object value) {
		addSingle(value.getClass(), value);
	} 
	
	///////////////////////////////////////////////////////
	/* (non-Javadoc)
	 * @see com.wlh.config.IConfig#addProperty(java.lang.String, java.lang.Object)
	 */
	@Override
	public  void addProperty(String key, Object value) {
		abstractConfiguration.addProperty(key, value);
	}

	/* (non-Javadoc)
	 * @see com.wlh.config.IConfig#subset(java.lang.String)
	 */
	@Override
	public Configuration subset(String prefix) {
		return abstractConfiguration.subset(prefix);
	}


	/* (non-Javadoc)
	 * @see com.wlh.config.IConfig#setProperty(java.lang.String, java.lang.Object)
	 */
	@Override
	public  void setProperty(String key, Object value) {
		abstractConfiguration.setProperty(key, value);
	}

	/* (non-Javadoc)
	 * @see com.wlh.config.IConfig#clearProperty(java.lang.String)
	 */
	@Override
	public  void clearProperty(String key) {
		abstractConfiguration.clearProperty(key);
	}

	/* (non-Javadoc)
	 * @see com.wlh.config.IConfig#clear()
	 */
	@Override
	public  void clear() {
		abstractConfiguration.clear();
	}

	/* (non-Javadoc)
	 * @see com.wlh.config.IConfig#getKeys()
	 */
	@Override
	public  Iterator<String> getKeys() {
		return abstractConfiguration.getKeys();
	}

	/* (non-Javadoc)
	 * @see com.wlh.config.IConfig#getKeys(java.lang.String)
	 */
	@Override
	public  Iterator<String> getKeys(String prefix) {
		return abstractConfiguration.getKeys(prefix);
	}

	/* (non-Javadoc)
	 * @see com.wlh.config.IConfig#getProperty(java.lang.String)
	 */
	@Override
	public  Object getProperty(String key) {
		return abstractConfiguration.getProperty(key);
	}

	/* (non-Javadoc)
	 * @see com.wlh.config.IConfig#isEmpty()
	 */
	@Override
	public  boolean isEmpty() {
		return abstractConfiguration.isEmpty();
	}

	/* (non-Javadoc)
	 * @see com.wlh.config.IConfig#size()
	 */
	@Override
	public  int size() {
		return abstractConfiguration.size();
	}

	/* (non-Javadoc)
	 * @see com.wlh.config.IConfig#containsKey(java.lang.String)
	 */
	@Override
	public  boolean containsKey(String key) {
		return abstractConfiguration.containsKey(key);
	}

	/* (non-Javadoc)
	 * @see com.wlh.config.IConfig#getProperties(java.lang.String)
	 */
	@Override
	public Properties getProperties(String key) {
		return abstractConfiguration.getProperties(key);
	}

	/* (non-Javadoc)
	 * @see com.wlh.config.IConfig#getBoolean(java.lang.String)
	 */
	@Override
	public boolean getBoolean(String key) {
		return abstractConfiguration.getBoolean(key);
	}


	/* (non-Javadoc)
	 * @see com.wlh.config.IConfig#getByte(java.lang.String)
	 */
	@Override
	public byte getByte(String key) {
		return abstractConfiguration.getByte(key);
	}


	/* (non-Javadoc)
	 * @see com.wlh.config.IConfig#getDouble(java.lang.String)
	 */
	@Override
	public double getDouble(String key) {
		return abstractConfiguration.getDouble(key);
	}


	/* (non-Javadoc)
	 * @see com.wlh.config.IConfig#getFloat(java.lang.String)
	 */
	@Override
	public float getFloat(String key) {
		return abstractConfiguration.getFloat(key);
	}


	/* (non-Javadoc)
	 * @see com.wlh.config.IConfig#getInt(java.lang.String)
	 */
	@Override
	public int getInt(String key) {
		return abstractConfiguration.getInt(key);
	}


	/* (non-Javadoc)
	 * @see com.wlh.config.IConfig#getLong(java.lang.String)
	 */
	@Override
	public long getLong(String key) {
		return abstractConfiguration.getLong(key);
	}


	/* (non-Javadoc)
	 * @see com.wlh.config.IConfig#getShort(java.lang.String)
	 */
	@Override
	public short getShort(String key) {
		return abstractConfiguration.getShort(key);
	}


	/* (non-Javadoc)
	 * @see com.wlh.config.IConfig#getBigDecimal(java.lang.String)
	 */
	@Override
	public BigDecimal getBigDecimal(String key) {
		return abstractConfiguration.getBigDecimal(key);
	}


	/* (non-Javadoc)
	 * @see com.wlh.config.IConfig#getBigInteger(java.lang.String)
	 */
	@Override
	public BigInteger getBigInteger(String key) {
		return abstractConfiguration.getBigInteger(key);
	}


	/* (non-Javadoc)
	 * @see com.wlh.config.IConfig#getString(java.lang.String)
	 */
	@Override
	public String getString(String key) {
		return abstractConfiguration.getString(key);
	}


	/* (non-Javadoc)
	 * @see com.wlh.config.IConfig#getEncodedString(java.lang.String, org.apache.commons.configuration2.ConfigurationDecoder)
	 */
	@Override
	public String getEncodedString(String key, ConfigurationDecoder decoder) {
		return abstractConfiguration.getEncodedString(key, decoder);
	}

	/* (non-Javadoc)
	 * @see com.wlh.config.IConfig#getEncodedString(java.lang.String)
	 */
	@Override
	public String getEncodedString(String key) {
		return abstractConfiguration.getEncodedString(key);
	}

	/* (non-Javadoc)
	 * @see com.wlh.config.IConfig#getStringArray(java.lang.String)
	 */
	@Override
	public String[] getStringArray(String key) {
		return abstractConfiguration.getStringArray(key);
	}

	/* (non-Javadoc)
	 * @see com.wlh.config.IConfig#getList(java.lang.String)
	 */
	@Override
	public List<Object> getList(String key) {
		return abstractConfiguration.getList(key);
	}

	/* (non-Javadoc)
	 * @see com.wlh.config.IConfig#get(java.lang.Class, java.lang.String)
	 */
	@Override
	public <T> T get(Class<T> cls, String key) {
		return abstractConfiguration.get(cls, key);
	}

	/* (non-Javadoc)
	 * @see com.wlh.config.IConfig#getArray(java.lang.Class, java.lang.String)
	 */
	@Override
	public Object getArray(Class<?> cls, String key) {
		return abstractConfiguration.getArray(cls, key);
	}


	/* (non-Javadoc)
	 * @see com.wlh.config.IConfig#getList(java.lang.Class, java.lang.String)
	 */
	@Override
	public <T> List<T> getList(Class<T> cls, String key) {
		return abstractConfiguration.getList(cls, key);
	}


	/* (non-Javadoc)
	 * @see com.wlh.config.IConfig#getCollection(java.lang.Class, java.lang.String, java.util.Collection)
	 */
	@Override
	public <T> Collection<T> getCollection(Class<T> cls, String key,
			Collection<T> target) {
		return abstractConfiguration.getCollection(cls, key, target);
	}


	/* (non-Javadoc)
	 * @see com.wlh.config.IConfig#append(org.apache.commons.configuration2.Configuration)
	 */
	@Override
	public void append(Configuration c) {
		abstractConfiguration.append(c);
	}

}
