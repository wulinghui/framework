# 缓存系统
### 本模块只是对jdk8-Jcache规范做一些适配。
    1. api使用太繁琐.
    2. @com.wlh.cache.CacheMsgIoc<K, V>
    3. Test com.wlh.cache.CacheDemo.runDemo4()
### 后记
    1. 之后如果采用分布式的话，可能需要池化技术。
    2. 池化技术demo@see com.wlh.cache.MyPooledObjectFactoryExample
### 使用
	```java
	@Test
	public final void testGetCacheString() {
		Cache<String, Integer> cache = CacheMsgIoc.getInstance( new MutableConfiguration<String,Integer>()
			    .setTypes(String.class, Integer.class)
			    
)
			    .setStatisticsEnabled(true)).getCache("testGetCacheString");
		cache.put("111", 111);
		System.out.println(cache.get("111"));
	}
	```

### 依赖
   - Jcache
   - Jcache的实现