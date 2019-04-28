package com.wlh.cache;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.CacheEntryListenerConfiguration;
import javax.cache.configuration.Configuration;
import javax.cache.integration.CompletionListener;
import javax.cache.processor.EntryProcessor;
import javax.cache.processor.EntryProcessorException;
import javax.cache.processor.EntryProcessorResult;
import javax.cache.spi.CachingProvider;

/**
 * @author wulinghui
 * 以配置为主，可以生成多个库的多个实例。
 * @param <K>
 * @param <V>
 */
public class CacheMSGimp<K, V> implements ICacheMSG<K, V> {
	Configuration<K, V> config;
	String defaultCacheName;
	Map<String, Cache<K, V>> mapCache = new HashMap<>();

	public CacheMSGimp( Configuration<K, V> config,
			String defaultCacheName) {
		super();
		this.config = config;
		this.defaultCacheName = defaultCacheName;
	}

	public CacheMSGimp( Configuration<K, V> config) {
		this(config, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wlh.cache.ICacheMSG#getSystemDefaultCache(java.lang.String)
	 */
	@Override
	public Cache<K, V> getSystemDefaultCache(String cacheName) {
		return getCache(null, cacheName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wlh.cache.ICacheMSG#getCache(java.lang.String)
	 */
	@Override
	public Cache<K, V> getCache(String cacheName) {
		return getCache(defaultCacheName, cacheName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wlh.cache.ICacheMSG#getCache(java.lang.String, java.lang.String)
	 */
	@Override
	public Cache<K, V> getCache(String providerClass, String cacheName) {
		String key = providerClass + ":" + cacheName;
		Cache<K, V> cache = mapCache.get(key);
		if (cache == null) {
			synchronized (this) {
				cache = mapCache.get(key);
				if (cache == null) {
					CachingProvider cachingProvider = null;
					if (providerClass != null) {
						cachingProvider = Caching
								.getCachingProvider(providerClass);
					} else {
						cachingProvider = Caching.getCachingProvider();
					}
					CacheManager cacheManager = cachingProvider
							.getCacheManager();
					cache = cacheManager.createCache(cacheName, config);
					mapCache.put(key, new CacheProxy(cache, key));
				}
			}
		}
		return cache;
	}
	
	public class CacheProxy extends CacheAdapat<K, V> {
		String key;
		public CacheProxy(Cache<K, V> cache, String key) {
			super(cache);
			this.key = key;
		}

		public void close() {
			cache.close();
			mapCache.remove(key);
		}
	}

}
