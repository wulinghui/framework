package com.wlh.util;

public interface Constant {
	/**
	 * new String[]{"a","b","c"}   <- 互转 -> "a;b;c"
	 */
	char STRING_SEPARATOR  = SystemUtils.getPropertyAndRemove("wlh:STRING_SEPARATOR", ';');
}
