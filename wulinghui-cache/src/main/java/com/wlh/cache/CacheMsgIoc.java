package com.wlh.cache;

import java.io.Serializable;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.Configuration;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.AccessedExpiryPolicy;
import javax.cache.expiry.Duration;
import javax.cache.spi.CachingProvider;

/**
 * @author wulinghui 以配置为主，可以生成多个库的多个实例。
 * @param <K>
 * @param <V>
 */
public class CacheMsgIoc<K, V> implements ICacheMSG<K, V>, Serializable,
		Cloneable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4804103196693325075L;
	Configuration<K, V> config;
	String defaultCacheName;
	// 改成Cache有过期时间的管理
	transient Cache<String, Cache> mapCache;
	private static Cache<Configuration, ICacheMSG> iCacheMsgCache;
	static {
		CacheManager cacheManager = Caching.getCachingProvider()
				.getCacheManager();
		MutableConfiguration<Configuration, ICacheMSG> config00 = new MutableConfiguration<Configuration, ICacheMSG>()
				.setTypes(Configuration.class, ICacheMSG.class)
				.setExpiryPolicyFactory(
						AccessedExpiryPolicy.factoryOf(Duration.ONE_HOUR))
				.setStatisticsEnabled(true);
		String cacheName = "CacheMsgIocICacheMsgCache";
		try {
			iCacheMsgCache = cacheManager.createCache(cacheName, config00);
		} catch (IllegalArgumentException e) {
			iCacheMsgCache = Caching.getCache(cacheName, Configuration.class,
					ICacheMSG.class);
		}
	}

	private CacheMsgIoc(Configuration<K, V> config, String defaultCacheName,
			Duration dt, String key) {
		super();
		this.config = config;
		this.defaultCacheName = defaultCacheName;
		CachingProvider cachingProvider = null;
		if (defaultCacheName != null) {
			cachingProvider = Caching.getCachingProvider(defaultCacheName);
		} else {
			cachingProvider = Caching.getCachingProvider();
		}
		CacheManager cacheManager = cachingProvider.getCacheManager();
		MutableConfiguration<String, Cache> config00 = new MutableConfiguration<String, Cache>()
				.setTypes(String.class, Cache.class)
				.setExpiryPolicyFactory(AccessedExpiryPolicy.factoryOf(dt))
				.setStatisticsEnabled(true);
		mapCache = cacheManager.createCache(key, config00);
	}

	public static <K, V> ICacheMSG<K, V> getInstance(Configuration<K, V> config) {
		return getInstance(config, null);
	}

	public static <K, V> ICacheMSG<K, V> getInstance(
			Configuration<K, V> config, String defaultCacheName) {
		return getInstance(config, defaultCacheName, Duration.TWENTY_MINUTES,
				"CacheMsgIoc" + System.currentTimeMillis());
	}

	public static <K, V> ICacheMSG<K, V> getInstance(
			Configuration<K, V> config, String defaultCacheName, Duration dt,
			String key) {
		ICacheMSG iCacheMSG = iCacheMsgCache.get(config);
		if (iCacheMSG == null) {
			synchronized (iCacheMsgCache) {
				iCacheMSG = iCacheMsgCache.get(config);
				if (iCacheMSG == null) {
					iCacheMSG = new CacheMsgIoc<>(config, defaultCacheName, dt,
							key);
					iCacheMsgCache.put(config, iCacheMSG);
				}
			}
		}
		return iCacheMSG;
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
	}/*
	 * (non-Javadoc) // TODO 这里本来是在过期策略回调时使用的
	 * 
	 * @see java.lang.Object#finalize()
	 */

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		mapCache.close();
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

		/*
		 * (non-Javadoc) // TODO 这里本来是在过期策略回调时使用的
		 * 
		 * @see java.lang.Object#finalize()
		 */
		@Override
		protected void finalize() throws Throwable {
			super.finalize();
			close();
		}
	}

}
