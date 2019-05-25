package com.wlh.util;

import org.junit.Test;

public class FieldUtilsTest {

	@Test
	public final void testReadDeclaredFieldObjectString() throws Throwable {
		Object readDeclaredField = FieldUtils.readDeclaredField(new Students("zz"), "id",true);
		System.out.println( readDeclaredField );  
	}

}

