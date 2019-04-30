package com.wlh.config;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.ConfigurationDecoder;

public class ConfigAdpat implements IConfig{
	protected IConfig config;
 
	public ConfigAdpat(IConfig config) {
		super();
		this.config = config;
	}

	public Configuration getConfiguration() {
		return config.getConfiguration();
	}

	public void addProperty(String key, Object value) {
		config.addProperty(key, value);
	}

	public Configuration subset(String prefix) {
		return config.subset(prefix);
	}

	public void setProperty(String key, Object value) {
		config.setProperty(key, value);
	}

	public void clearProperty(String key) {
		config.clearProperty(key);
	}

	public void clear() {
		config.clear();
	}

	public Iterator<String> getKeys() {
		return config.getKeys();
	}

	public Iterator<String> getKeys(String prefix) {
		return config.getKeys(prefix);
	}

	public Object getProperty(String key) {
		return config.getProperty(key);
	}

	public boolean isEmpty() {
		return config.isEmpty();
	}

	public int size() {
		return config.size();
	}

	public boolean containsKey(String key) {
		return config.containsKey(key);
	}

	public Properties getProperties(String key) {
		return config.getProperties(key);
	}

	public boolean getBoolean(String key) {
		return config.getBoolean(key);
	}

	public byte getByte(String key) {
		return config.getByte(key);
	}

	public double getDouble(String key) {
		return config.getDouble(key);
	}

	public float getFloat(String key) {
		return config.getFloat(key);
	}

	public int getInt(String key) {
		return config.getInt(key);
	}

	public long getLong(String key) {
		return config.getLong(key);
	}

	public short getShort(String key) {
		return config.getShort(key);
	}

	public BigDecimal getBigDecimal(String key) {
		return config.getBigDecimal(key);
	}

	public BigInteger getBigInteger(String key) {
		return config.getBigInteger(key);
	}

	public String getString(String key) {
		return config.getString(key);
	}

	public String getEncodedString(String key, ConfigurationDecoder decoder) {
		return config.getEncodedString(key, decoder);
	}

	public String getEncodedString(String key) {
		return config.getEncodedString(key);
	}

	public String[] getStringArray(String key) {
		return config.getStringArray(key);
	}

	public List<Object> getList(String key) {
		return config.getList(key);
	}

	public <T> T get(Class<T> cls, String key) {
		return config.get(cls, key);
	}

	public Object getArray(Class<?> cls, String key) {
		return config.getArray(cls, key);
	}

	public <T> List<T> getList(Class<T> cls, String key) {
		return config.getList(cls, key);
	}

	public <T> Collection<T> getCollection(Class<T> cls, String key,
			Collection<T> target) {
		return config.getCollection(cls, key, target);
	}

	public void append(Configuration c) {
		config.append(c);
	}

	public void addSingle(Class<?> cls, Object value) {
		config.addSingle(cls, value);
	}

	public <T> void addSingle(Object value) {
		config.addSingle(value);
	}

	@Override
	public <T> T getSingle(Class<T> cls) {
		return config.getSingle(cls);
	}
	
}
