package com.wlh.config;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Properties;

/**
 * @author wulinghui
 */
@Deprecated
public  abstract class AbstractConfig implements IConfig {

	@Override
	public final Object getProperty(String key) {
		return get(Object.class,key);
	}

	@Override
	public  final  boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public final Properties getProperties(String key) {
		return get(Properties.class,key);
	}

	@Override
	public final boolean getBoolean(String key) {
		return get(boolean.class,key);
	}

	@Override
	public final byte getByte(String key) {
		return get(byte.class,key);
	}

	@Override
	public final double getDouble(String key) {
		
		return get(double.class,key);
	}

	@Override
	public final float getFloat(String key) {
		
		return get(float.class,key);
	}

	@Override
	public final int getInt(String key) {
		
		return get(int.class,key);
	}

	@Override
	public final long getLong(String key) {
		
		return get(long.class,key);
	}

	@Override
	public final short getShort(String key) {
		
		return get(short.class,key);
	}

	@Override
	public final BigDecimal getBigDecimal(String key) {
		
		return get(BigDecimal.class,key);
	}

	@Override
	public final BigInteger getBigInteger(String key) {
		
		return get(BigInteger.class,key);
	}

	@Override
	public final String getString(String key) {
		
		return get(String.class,key);
	}


	@Override
	public final String getEncodedString(String key) {
		return get(String.class,key);
	}

	@Override
	public final String[] getStringArray(String key) {
		
		return get(String[].class,key);
	}
	
}
