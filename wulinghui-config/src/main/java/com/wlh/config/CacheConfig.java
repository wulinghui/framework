package com.wlh.config;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Properties;

import javax.cache.Cache;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.AccessedExpiryPolicy;
import javax.cache.expiry.Duration;

import org.apache.commons.configuration2.ConfigurationDecoder;

import com.wlh.cache.CacheMsgIoc;


/**
 * @author wulinghui
 * 装饰缓存-过期机制
 */
public class CacheConfig extends ConfigAdpat {
	Cache<String, Object> cache;
	public CacheConfig(IConfig config,Cache<String, Object> cache) {
		super(config);
		this.cache = cache;
	}
	/**
	 * @param config
	 * 以下@see Jcache-api
	 * @param providerClass  
	 * @param dt
	 * @param key
	 */
	public CacheConfig(IConfig config, String providerClass,Duration dt,String key) {
		super(config);
		MutableConfiguration<String, Object> config00 =
				    new MutableConfiguration<String, Object>()
				    .setTypes(String.class, Object.class)
				    .setExpiryPolicyFactory(AccessedExpiryPolicy.factoryOf(dt))
				    .setStatisticsEnabled(true);
		this.cache =  CacheMsgIoc.getInstance(config00,providerClass).getCache(key);
	}
	/**缓存默认的过期时间是20分钟
	 * @param config
	 */
	public CacheConfig(IConfig config) {
		this(config, null ,Duration.TWENTY_MINUTES , "CacheConfig"+System.currentTimeMillis() );
	}
	@Override
	public void clearProperty(String key) {
		cache.remove(key);
		super.clearProperty(key);
	}
	@Override
	public void clear() {
		cache.clear();
		super.clear();
	}
	private <T> T getObjByCache(String key , Class<T> cla){
		return (T) getObjByCache(cla.getSimpleName() ,key);
	}
	private <T> void putObjByCache(String key , Class<T> cla ,T t){
		putObjByCache(  key,cla.getSimpleName(),t);
	}
	private <T> T getObjByCache(String key , String cla){
		return (T) cache.get(cla +":"+key);
	}
	private  void putObjByCache(String key , String cla ,Object t){
		 cache.put(cla +":"+key,t);
	}
	@Override
	public Object getProperty(String key) {
		Object object = getObjByCache(key,Object.class);
		if( object == null){
			synchronized (cache) {
				object = getObjByCache(key,Object.class);
				if( object == null){
					object = super.getProperty(key);
					putObjByCache(key,Object.class, object);
				}
			}
		}
		return object;
	}
	@Override
	public Properties getProperties(String key) {
		Properties object = getObjByCache(key,Properties.class);
		if( object == null){
			synchronized (cache) {
				object =  getObjByCache(key,Properties.class);
				if( object == null){
					object = super.getProperties(key);
					putObjByCache(key,Properties.class, object);
				}
			}
		}
		return object;
	}
	@Override
	public boolean getBoolean(String key) {
		Boolean object = getObjByCache(key,Boolean.class);
		if( object == null){
			synchronized (cache) {
				object = getObjByCache(key,Boolean.class);
				if( object == null){
					object = super.getBoolean(key);
					putObjByCache(key,Boolean.class, object);
				}
			}
		}
		return object;
	}
	@Override
	public byte getByte(String key) {
		Byte object = getObjByCache(key,Byte.class);
		if( object == null){
			synchronized (cache) {
				object = getObjByCache(key,Byte.class);
				if( object == null){
					object = super.getByte(key);
					putObjByCache(key,Byte.class, object);
				}
			}
		}
		return object;
	}
	@Override
	public double getDouble(String key) {
		Double object = getObjByCache(key,Double.class);
		if( object == null){
			synchronized (cache) {
				object = getObjByCache(key,Double.class);
				if( object == null){
					object = super.getDouble(key);
					putObjByCache(key,Double.class, object);
				}
			}
		}
		return object;
	}
	@Override
	public float getFloat(String key) {
		Float object = getObjByCache(key,Float.class);
		if( object == null){
			synchronized (cache) {
				object = getObjByCache(key,Float.class);
				if( object == null){
					object = super.getFloat(key);
					putObjByCache(key,Float.class, object);
				}
			}
		}
		return object;
	}
	@Override
	public int getInt(String key) {
		Integer object = getObjByCache(key,Integer.class);
		if( object == null){
			synchronized (cache) {
				object = getObjByCache(key,Integer.class);
				if( object == null){
					object = super.getInt(key);
					putObjByCache(key,Integer.class, object);
				}
			}
		}
		return object;
	}
	@Override
	public long getLong(String key) {
		Long object = getObjByCache(key,Long.class);
		if( object == null){
			synchronized (cache) {
				object = getObjByCache(key,Long.class);
				if( object == null){
					object = super.getLong(key);
					putObjByCache(key,Long.class, object);
				}
			}
		}
		return object;
	}
	@Override
	public short getShort(String key) {
		Short object = getObjByCache(key,Short.class);
		if( object == null){
			synchronized (cache) {
				object = getObjByCache(key,Short.class);
				if( object == null){
					object = super.getShort(key);
					putObjByCache(key,Short.class, object);
				}
			}
		}
		return object;
	}
	@Override
	public BigDecimal getBigDecimal(String key) {
		BigDecimal object = getObjByCache(key,BigDecimal.class);
		if( object == null){
			synchronized (cache) {
				object = getObjByCache(key,BigDecimal.class);
				if( object == null){
					object = super.getBigDecimal(key);
					putObjByCache(key,BigDecimal.class, object);
				}
			}
		}
		return object;
	}
	@Override
	public BigInteger getBigInteger(String key) {
		BigInteger object = getObjByCache(key,BigInteger.class);
		if( object == null){
			synchronized (cache) {
				object = getObjByCache(key,BigInteger.class);
				if( object == null){
					object = super.getBigInteger(key);
					putObjByCache(key,BigInteger.class, object);
				}
			}
		}
		return object;
	}
	@Override
	public String getString(String key) {
		String object = getObjByCache(key,String.class);
		if( object == null){
			synchronized (cache) {
				object = getObjByCache(key,String.class);
				if( object == null){
					object = super.getString(key);
					putObjByCache(key,String.class, object);
				}
			}
		}
		return object;
	}
	@Override
	public String getEncodedString(String key, ConfigurationDecoder decoder) {
		String simpleName = ConfigurationDecoder.class.getSimpleName();
		String object = getObjByCache(key,simpleName);
		if( object == null){
			synchronized (cache) {
				object = getObjByCache(key,simpleName);
				if( object == null){
					object = super.getEncodedString(key,decoder);
					putObjByCache(key,simpleName, object);
				}
			}
		}
		return object;
	}
	@Override
	public String getEncodedString(String key) {
		String cla = "getEncodedString";
		String object = getObjByCache(key,cla);
		if( object == null){
			synchronized (cache) {
				object = getObjByCache(key,cla);
				if( object == null){
					object = super.getEncodedString(key);
					putObjByCache(key,cla, object);
				}
			}
		}
		return object;
	}
	@Override
	public String[] getStringArray(String key) {
		String[] object = getObjByCache(key,String[].class);
		if( object == null){
			synchronized (cache) {
				object = getObjByCache(key,String[].class);
				if( object == null){
					object = super.getStringArray(key);
					putObjByCache(key,String[].class, object);
				}
			}
		}
		return object;
	}
	@Override
	public List<Object> getList(String key) {
		String cla = "List<Object>";
		List<Object> object = getObjByCache(key,cla);
		if( object == null){
			synchronized (cache) {
				object = getObjByCache(key,cla);
				if( object == null){
					object = super.getList(key);
					putObjByCache(key,cla, object);
				}
			}
		}
		return object;
	}
	@Override
	public <T> T get(Class<T> cls, String key) {
		String cla = cls.getSimpleName();
		T object = getObjByCache(key,cla);
		if( object == null){
			synchronized (cache) {
				object = getObjByCache(key,cla);
				if( object == null){
					object = super.get(cls, key);
					putObjByCache(key,cla, object);
				}
			}
		}
		return object;
	}
	@Override
	public Object getArray(Class<?> cls, String key) {
		String cla = cls.getSimpleName();
		Object object = getObjByCache(key,cla);
		if( object == null){
			synchronized (cache) {
				object = getObjByCache(key,cla);
				if( object == null){
					object = super.getArray(cls, key);
					putObjByCache(key,cla, object);
				}
			}
		}
		return object;
	}
	@Override
	public <T> List<T> getList(Class<T> cls, String key) {
		String cla = "List"+cls.getSimpleName();
		List<T> object = getObjByCache(key,cla);
		if( object == null){
			synchronized (cache) {
				object = getObjByCache(key,cla);
				if( object == null){
					object = super.getList(cls, key);
					putObjByCache(key,cla, object);
				}
			}
		}
		return object;
	}
//	@Override
//	public <T> Collection<T> getCollection(Class<T> cls, String key,
//			Collection<T> target) {
//		String cla = "Collection"+cls.getSimpleName();
//		Collection<T> object = getObjByCache(key,cla);
//		if( object == null){
//			synchronized (cache) {
//				object = getObjByCache(key,cla);
//				if( object == null){
//					object = super.getCollection(cls, key,target);
//					putObjByCache(key,cla, object);
//				}
//			}
//		}
//		return object;
//	}
}
