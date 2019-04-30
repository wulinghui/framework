package com.wlh.config;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.commons.collections.IteratorUtils;
import org.apache.commons.configuration2.ConfigurationDecoder;

/**
 * @author wulinghui
 * 装饰有默认值机制。
 * 1. 局部变量大于全局变量
 * 2. 默认值重复
 * 3. 理论可以无限提升。
 */
public class DefaultConfig extends  ConfigAdpat {
	IConfig defaultsuper;
	
	public DefaultConfig(IConfig iConfig, IConfig defaultsuper) {
		super(iConfig);
		this.defaultsuper = defaultsuper;
	}
	@Override
	public Iterator<String> getKeys() {
		return IteratorUtils.chainedIterator(super.getKeys(), defaultsuper.getKeys());
	}
	@Override
	public Iterator<String> getKeys(String prefix) {
		return IteratorUtils.chainedIterator(super.getKeys(prefix), defaultsuper.getKeys(prefix));
	}
	
	@Override
	public boolean containsKey(String key) {
		return super.containsKey(key) || defaultsuper.containsKey(key);
	}
	@Override
	public Object getProperty(String key) {
//		Object property = super.getProperty(key);
//		return property == null ? defaultsuper.getProperty(key) : property;
		if( super.containsKey(key) ){
			return super.getProperty(key);
		}else{
			return defaultsuper.getProperty(key);
		}
	}
	@Override
	public Properties getProperties(String key) {
//		Properties property = super.getProperties(key);
//		return property == null ? defaultsuper.getProperties(key) : property;
		if( super.containsKey(key) ){
			return super.getProperties(key);
		}else{
			return defaultsuper.getProperties(key);
		}
	}
	@Override
	public boolean getBoolean(String key) {
		if( super.containsKey(key) ){
			return super.getBoolean(key);
		}else{
			return defaultsuper.getBoolean(key);
		}
	}
	@Override
	public byte getByte(String key) {
		if( super.containsKey(key) ){
			return super.getByte(key);
		}else{
			return defaultsuper.getByte(key);
		}
	}
	@Override
	public double getDouble(String key) {
		if( super.containsKey(key) ){
			return super.getDouble(key);
		}else{
			return defaultsuper.getDouble(key);
		}
	}
	@Override
	public float getFloat(String key) {
		if( super.containsKey(key) ){
			return super.getFloat(key);
		}else{
			return defaultsuper.getFloat(key);
		}
	}
	@Override
	public int getInt(String key) {
		if( super.containsKey(key) ){
			return super.getInt(key);
		}else{
			return defaultsuper.getInt(key);
		}
	}
	@Override
	public long getLong(String key) {
		if( super.containsKey(key) ){
			return super.getLong(key);
		}else{
			return defaultsuper.getLong(key);
		}
	}
	@Override
	public short getShort(String key) {
		if( super.containsKey(key) ){
			return super.getShort(key);
		}else{
			return defaultsuper.getShort(key);
		}
	}
	@Override
	public BigDecimal getBigDecimal(String key) {
		if( super.containsKey(key) ){
			return super.getBigDecimal(key);
		}else{
			return defaultsuper.getBigDecimal(key);
		}
	}
	@Override
	public BigInteger getBigInteger(String key) {
		if( super.containsKey(key) ){
			return super.getBigInteger(key);
		}else{
			return defaultsuper.getBigInteger(key);
		}
	}
	@Override
	public String getString(String key) {
		if( super.containsKey(key) ){
			return super.getString(key);
		}else{
			return defaultsuper.getString(key);
		}
	}
	@Override
	public String getEncodedString(String key, ConfigurationDecoder decoder) {
		if( super.containsKey(key) ){
			return super.getEncodedString(key,decoder);
		}else{
			return defaultsuper.getEncodedString(key,decoder);
		}
	}
	@Override
	public String getEncodedString(String key) {
		if( super.containsKey(key) ){
			return super.getEncodedString(key);
		}else{
			return defaultsuper.getEncodedString(key);
		}
	}
	@Override
	public String[] getStringArray(String key) {
		if( super.containsKey(key) ){
			return super.getStringArray(key);
		}else{
			return defaultsuper.getStringArray(key);
		}
	}
	@Override
	public List<Object> getList(String key) {
		if( super.containsKey(key) ){
			return super.getList(key);
		}else{
			return defaultsuper.getList(key);
		}
	}
	@Override
	public <T> T get(Class<T> cls, String key) {
		if( super.containsKey(key) ){
			return super.get(cls, key);
		}else{
			return defaultsuper.get(cls, key);
		}
	}
	@Override
	public Object getArray(Class<?> cls, String key) {
		if( super.containsKey(key) ){
			return super.getArray(cls,key);
		}else{
			return defaultsuper.getArray(cls,key);
		}
	}
	@Override
	public <T> List<T> getList(Class<T> cls, String key) {
		if( super.containsKey(key) ){
			return super.getList(cls,key);
		}else{
			return defaultsuper.getList(cls,key);
		}
	}
	@Override
	public <T> Collection<T> getCollection(Class<T> cls, String key,
			Collection<T> target) {
		if( super.containsKey(key) ){
			return super.getCollection( cls,key,target);
		}else{
			return defaultsuper.getCollection( cls,key,target);
		}
	}
	
}
