package com.wlh.cache;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.AccessedExpiryPolicy;
import javax.cache.expiry.Duration;
import javax.cache.spi.CachingProvider;

import org.junit.Test;

public class CacheDemo {
	
	@Test
	public void runDemo1(){
		//resolve a cache manager 
		// javax.cache.spi.CachingProvider
		 CachingProvider cachingProvider = Caching.getCachingProvider();
		 run(cachingProvider);
	}
	@Test
	public void runDemo2(){
		//resolve a cache manager 
		// javax.cache.spi.CachingProvider
		 CachingProvider cachingProvider = Caching.getCachingProvider("org.apache.commons.jcs.jcache.JCSCachingProvider");
		 run(cachingProvider);
	}
	@Test
	public void runDemo3(){
		//resolve a cache manager 
		// javax.cache.spi.CachingProvider
		
		 CachingProvider cachingProvider = Caching.getCachingProvider("org.apache.commons.jcs.jcache.JCSCachingProvider");
		 run(cachingProvider);
		 /**
		  * javax.cache.CacheException: cache simpleCache already exists
		  */
		 run(cachingProvider); 
		 //这里也就可以做个适配
	}
	@Test
	public void runDemo4(){
		ICacheMSG<String, Integer> cacheMSG = new CacheMSGimp<String, Integer>(  new MutableConfiguration<String,Integer>()
			    .setTypes(String.class, Integer.class)
			    .setExpiryPolicyFactory(AccessedExpiryPolicy.factoryOf(Duration.ONE_HOUR))
			    .setStatisticsEnabled(true) ); 
		Cache<String, Integer> cache = cacheMSG.getCache("simpleCache");
		 print(cache);
	}
	protected void run(CachingProvider cachingProvider) {
		System.out.println(cachingProvider.getClass().getName()); 
		CacheManager cacheManager = cachingProvider.getCacheManager();
		 //configure the cache
		 MutableConfiguration config =
		    new MutableConfiguration<String,Integer>()
		    .setTypes(String.class, Integer.class)
		    .setExpiryPolicyFactory(AccessedExpiryPolicy.factoryOf(Duration.ONE_HOUR))
		    .setStatisticsEnabled(true);
		 //create the cache
		 Cache cache = cacheManager.createCache("simpleCache", config);
		 System.out.println(cacheManager.getURI());
		 //cache operations 
		 print(cache);
	}
	protected void print(Cache cache) {
		String key = "key";
		 Integer value1 = 1;
		 cache.put("key", value1);
		 Integer value2 = (Integer) cache.get(key);
		 cache.remove(key);
		 System.out.println(value2);
	}

	
}
