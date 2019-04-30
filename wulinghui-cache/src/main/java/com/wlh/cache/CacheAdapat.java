package com.wlh.cache;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Spliterator;
import java.util.function.Consumer;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.configuration.CacheEntryListenerConfiguration;
import javax.cache.configuration.Configuration;
import javax.cache.integration.CompletionListener;
import javax.cache.processor.EntryProcessor;
import javax.cache.processor.EntryProcessorException;
import javax.cache.processor.EntryProcessorResult;

public class CacheAdapat<K, V> implements Cache<K, V> ,Serializable {
	protected transient Cache<K, V> cache;
	public CacheAdapat(Cache<K, V> cache) {
		super();
		this.cache = cache;
	}
	public void close() {
		cache.close();
	}
	public V get(K key) {
		return cache.get(key);
	}
	public Map<K, V> getAll(Set<? extends K> keys) {
		return cache.getAll(keys);
	}
	public boolean containsKey(K key) {
		return cache.containsKey(key);
	}
	public void loadAll(Set<? extends K> keys, boolean replaceExistingValues,
			CompletionListener completionListener) {
		cache.loadAll(keys, replaceExistingValues, completionListener);
	}
	public void put(K key, V value) {
		cache.put(key, value);
	}
	public V getAndPut(K key, V value) {
		return cache.getAndPut(key, value);
	}
	public void putAll(Map<? extends K, ? extends V> map) {
		cache.putAll(map);
	}
	public boolean putIfAbsent(K key, V value) {
		return cache.putIfAbsent(key, value);
	}
	public boolean remove(K key) {
		return cache.remove(key);
	}
	public boolean remove(K key, V oldValue) {
		return cache.remove(key, oldValue);
	}
	public V getAndRemove(K key) {
		return cache.getAndRemove(key);
	}
	public boolean replace(K key, V oldValue, V newValue) {
		return cache.replace(key, oldValue, newValue);
	}
	public boolean replace(K key, V value) {
		return cache.replace(key, value);
	}
	public V getAndReplace(K key, V value) {
		return cache.getAndReplace(key, value);
	}
	public void removeAll(Set<? extends K> keys) {
		cache.removeAll(keys);
	}
	public void removeAll() {
		cache.removeAll();
	}
	public void clear() {
		cache.clear();
	}
	public <C extends Configuration<K, V>> C getConfiguration(Class<C> clazz) {
		return cache.getConfiguration(clazz);
	}
	public <T> T invoke(K key, EntryProcessor<K, V, T> entryProcessor,
			Object... arguments) throws EntryProcessorException {
		return cache.invoke(key, entryProcessor, arguments);
	}
	public <T> Map<K, EntryProcessorResult<T>> invokeAll(Set<? extends K> keys,
			EntryProcessor<K, V, T> entryProcessor, Object... arguments) {
		return cache.invokeAll(keys, entryProcessor, arguments);
	}
	public String getName() {
		return cache.getName();
	}
	public CacheManager getCacheManager() {
		return cache.getCacheManager();
	}
	public boolean isClosed() {
		return cache.isClosed();
	}
	public <T> T unwrap(Class<T> clazz) {
		return cache.unwrap(clazz);
	}
	public void registerCacheEntryListener(
			CacheEntryListenerConfiguration<K, V> cacheEntryListenerConfiguration) {
		cache.registerCacheEntryListener(cacheEntryListenerConfiguration);
	}
	public void deregisterCacheEntryListener(
			CacheEntryListenerConfiguration<K, V> cacheEntryListenerConfiguration) {
		cache.deregisterCacheEntryListener(cacheEntryListenerConfiguration);
	}
	public Iterator<javax.cache.Cache.Entry<K, V>> iterator() {
		return cache.iterator();
	}
	
}
