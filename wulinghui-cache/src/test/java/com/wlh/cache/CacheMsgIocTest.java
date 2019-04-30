package com.wlh.cache;

import static org.junit.Assert.*;

import javax.cache.Cache;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.AccessedExpiryPolicy;
import javax.cache.expiry.Duration;

import org.junit.Test;

public class CacheMsgIocTest {

	@Test
	public final void testGetCacheString() {
		Cache<String, Integer> cache = CacheMsgIoc.getInstance( new MutableConfiguration<String,Integer>()
			    .setTypes(String.class, Integer.class)
			    .setExpiryPolicyFactory(AccessedExpiryPolicy.factoryOf(Duration.ONE_HOUR))
			    .setStatisticsEnabled(true)).getCache("1111");
		cache.put("111", 111);
		System.out.println(cache.get("111"));
	}


}
