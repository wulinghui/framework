package com.wlh.util;

import java.util.Collection;
import java.util.List;


public class ClassUtils extends org.apache.commons.lang3.ClassUtils {
	public static boolean isAssignable(final Class<?> subClass,
			final Class<?> supClass) {
		return isAssignable(subClass, supClass, true);
	}
	public static void main(String[] args) {
		boolean assignable = org.apache.commons.lang3.ClassUtils.isAssignable(List.class	, Collection.class, true);
		System.out.println(assignable);  
	}
}
