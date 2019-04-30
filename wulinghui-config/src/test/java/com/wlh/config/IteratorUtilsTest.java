package com.wlh.config;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.IteratorUtils;
import org.junit.Test;

public class IteratorUtilsTest {
	@Test
	public void chainedIterator(){
		List list1 = new ArrayList<>();
		list1.add(11);
		list1.add(11);
		List list2 = new ArrayList<>();
		list2.add(333);
		Iterator chainedIterator = IteratorUtils.chainedIterator(list1.iterator(), list2.iterator());
		System.out.println(IteratorUtils.toList(chainedIterator));
	}
}
