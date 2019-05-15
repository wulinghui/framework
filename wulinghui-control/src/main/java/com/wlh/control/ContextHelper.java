package com.wlh.control;

import java.math.BigDecimal;
import java.math.BigInteger;

public  abstract class ContextHelper {
	private ContextHelper(){}
	
	private static TxnContext getContext() {
		return TxnContext.getTxnContextLocal().get();
	}
	public static String getHandlerFlag() {
		return getContext().getHandlerFlag();
	}
	public static Object getRequest() {
		return getContext().getRequest();
	}
	public static Object getResponse() {
		return getContext().getResponse();
	}
	public static Object getPar(String key) {
		return getContext().getPar(key);
	}
	public static <T> T getPar(String key, Class<T> toClass) {
		return getContext().getPar(key, toClass);
	}
	public static Object setPar(String key, Object obj) {
		return getContext().setPar(key, obj);
	}
	public static TxnContext println(String key, Object obj) {
		return getContext().println(key, obj);
	}
	public static TxnContext goPage(String target, String workPropagation) {
		return getContext().goPage(target, workPropagation);
	}
	public static boolean getBoolean(String key) {
		return getContext().getBoolean(key);
	}
	public static byte getByte(String key) {
		return getContext().getByte(key);
	}
	public static double getDouble(String key) {
		return getContext().getDouble(key);
	}
	public static float getFloat(String key) {
		return getContext().getFloat(key);
	}
	public static int getInt(String key) {
		return getContext().getInt(key);
	}
	public static long getLong(String key) {
		return getContext().getLong(key);
	}
	public static short getShort(String key) {
		return getContext().getShort(key);
	}
	public static BigDecimal getBigDecimal(String key) {
		return getContext().getBigDecimal(key);
	}
	public static BigInteger getBigInteger(String key) {
		return getContext().getBigInteger(key);
	}
	public static String getString(String key) {
		return getContext().getString(key);
	}
	public static String[] getStringArray(String key) {
		return getContext().getStringArray(key);
	}
}
