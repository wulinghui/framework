package com.wlh.util;

import java.util.Collection;

import org.apache.commons.collections.functors.EqualPredicate;

public class CollectionUtils extends org.apache.commons.collections.CollectionUtils{
	public static boolean contains(Collection collection, Object predicate){
		return CollectionUtils.exists(collection,  EqualPredicate.getInstance(predicate));
	} 
//	public static boolean containsIgnoreCase(Collection collection, String predicate){
//		predicate.equalsIgnoreCase(anotherString)
//		return CollectionUtils.exists(collection,  EqualPredicate.getInstance(predicate));
//	} 
}
