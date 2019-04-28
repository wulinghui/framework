package com.wlh.cache;

import javax.cache.Cache;

/**
 * @author wulinghui
 * 仅仅只管理Cache实例
 * 如果需要关闭CacheManager,CachingProvider这2个的关闭需要自己使用原生api关闭。
 * @param <K>
 * @param <V>
 */
public interface ICacheMSG<K, V> {

	public abstract Cache<K, V> getSystemDefaultCache(String cacheName);

	public abstract Cache<K, V> getCache(String cacheName);

	public abstract Cache<K, V> getCache(String providerClass, String cacheName);

}