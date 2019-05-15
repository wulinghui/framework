package com.wlh.control;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Iterator;

import org.apache.commons.configuration2.AbstractConfiguration;
import org.apache.commons.lang3.Validate;

import com.wlh.config.SystemConfig;
import com.wlh.convert.ConvertUtils;


/**
 * @author wulinghui
 * 注意手动TXN_CONTEXT_LOCAL.remove();  否则OOM
 */
public abstract class TxnContext extends AbstractConfiguration{
	final Object request;
	final Object response;
	public TxnContext(Object request, Object response) {
		this.request = request;
		this.response = response;
		assert TXN_CONTEXT_LOCAL.get() != null : "非法使用，TxnContext仅仅单例于线程";
		TXN_CONTEXT_LOCAL.set(this);
	}
	private static final ThreadLocal<TxnContext> TXN_CONTEXT_LOCAL = new ThreadLocal<>();
	/**这样就可以在各个层次使用了。
	 * @return
	 */
	public static ThreadLocal<TxnContext> getTxnContextLocal() {
		return TXN_CONTEXT_LOCAL;
	}
//	static{
//		ConversionHandler conversionHandler;
//		AbstractConfiguration configuration = (AbstractConfiguration) SystemConfig.get().getConfiguration();
//		conversionHandler = configuration.getConversionHandler();
//		conversionHandler.toArray(src, elemClass, null);
//	}
	public abstract String getHandlerFlag() ;
	public Object getRequest() {
		return request;
	}
	public Object getResponse() {
		return response;
	}
	
	public abstract Object getPar(String key);
	
	public abstract Object setPar(String key,Object obj);
	
	public abstract TxnContext println(String key,Object obj);
//	/**回显   // 这里可以封装到Servlert里面去   http://xxx.xxx.xxx?feedback=[name,password]
//	 * @param key 
//	 * @return
//	 */
//	public abstract TxnContext feedback(String[] key);
	public abstract TxnContext goPage(String target,String workPropagation);
	// 这是给用户使用的。
	public <T> T getPar(String key,Class<T> toClass){
		return (T) ConvertUtils.convert(getPar(key), toClass);
	}
	public boolean getBoolean(String key) {
		return getPar(key,Boolean.class);
	}
	
	public byte getByte(String key) {
		return getPar(key,Byte.class);
	}
	
	public double getDouble(String key) {
		return getPar(key,Double.class);
	}
	
	public float getFloat(String key) {
		return getPar(key,Float.class);
	}
	
	public int getInt(String key) {
		return getPar(key,Integer.class);
	}
	
	public long getLong(String key) {
		return getPar(key,Long.class);
	}
	
	public short getShort(String key) {
		return getPar(key,Short.class);
	}
	
	public BigDecimal getBigDecimal(String key) {
		return getPar(key,BigDecimal.class);
	}
	public BigInteger getBigInteger(String key) {
		return getPar(key,BigInteger.class);
	}
	public String getString(String key) {
		return getPar(key,String.class);
	}
	
	public String[] getStringArray(String key) {
		return getPar(key,String[].class);
	}
	@Override
	protected void addPropertyDirect(String key, Object value) {
		setPar( key, value);
	}
	@Override
	protected void clearPropertyDirect(String key) {
		//不允许删除。
	}
	@Override
	protected Object getPropertyInternal(String key) {
		return getPar(key);
	}
	@Override
	protected boolean isEmptyInternal() {
		return false;
	}
	@Override
	protected boolean containsKeyInternal(String key) {
		return getPropertyInternal(key) != null;
	}
}
