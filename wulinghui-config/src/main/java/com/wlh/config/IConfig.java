package com.wlh.config;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.ConfigurationDecoder;

public interface IConfig {
	Configuration getConfiguration();
	
	/** 有很多情况是需要单例的。
	 * @param cls
	 * @return
	 */
	public abstract <T> T getSingle(Class<T> cls);
	///////////////////////////////////////////////////////
	//
	//   addProperty(value.getClass().getName,value);就行了。有时候是父类需要单例。
	//
	public abstract void setSingle(Class<?> cls, Object value);
	public abstract <T> void setSingle(Object value);
	public abstract void addProperty(String key, Object value);

	public abstract Configuration subset(String prefix);
	
	public abstract void setProperty(String key, Object value);
	
	public abstract void clearProperty(String key);

	public abstract void clear();

	public abstract Iterator<String> getKeys();

	public abstract Iterator<String> getKeys(String prefix);

	public abstract Object getProperty(String key);

	public abstract boolean isEmpty();

	public abstract int size();

	public abstract boolean containsKey(String key);

	public abstract Properties getProperties(String key);

	public abstract boolean getBoolean(String key);

	public abstract byte getByte(String key);

	public abstract double getDouble(String key);

	public abstract float getFloat(String key);

	public abstract int getInt(String key);

	public abstract long getLong(String key);

	public abstract short getShort(String key);

	public abstract BigDecimal getBigDecimal(String key);

	public abstract BigInteger getBigInteger(String key);

	public abstract String getString(String key);

	public abstract String getEncodedString(String key,
			ConfigurationDecoder decoder);

	public abstract String getEncodedString(String key);

	public abstract String[] getStringArray(String key);

	public abstract List<Object> getList(String key);
	

	public abstract <T> T get(Class<T> cls, String key);

	public abstract Object getArray(Class<?> cls, String key);

	public abstract <T> List<T> getList(Class<T> cls, String key);

	public abstract <T> Collection<T> getCollection(Class<T> cls, String key,
			Collection<T> target);

	public abstract void append(Configuration c);

}