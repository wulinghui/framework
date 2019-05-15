package com.wlh.dao;

import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.translate.CharSequenceTranslator;

import com.wlh.beanutils.BeanUtils;
import com.wlh.dao.entity.ParameterIndex;
import com.wlh.exception.ConvertRunException;

/**
 * @author wulinghui
 * 该类必须先执行translate方法。
 */
@Deprecated
public class NamedPreparedTranslator extends CharSequenceTranslator {
	private final char prefix;
	private final char suffix;
	private final char out;
	private final List<ParameterIndex> namedPrepared = new ArrayList<ParameterIndex>(10);
//	private final AtomicInteger currentIndex = new AtomicInteger(1);
	private int currentIndex = 1;
	public NamedPreparedTranslator(char prefix, char suffix, char out) {
		super();
		this.prefix = prefix;
		this.suffix = suffix;
		this.out = out;
	} 
	public NamedPreparedTranslator(char prefix, char suffix) {
		this(prefix, suffix, '?');
	}

	public List<ParameterIndex> getNamedPrepared() {
		return namedPrepared;
	}
	public ParameterIndex[] getNamedPreparedArray() {
		ParameterIndex [] keyArray = new ParameterIndex[this.namedPrepared.size()];
		namedPrepared.toArray(keyArray);
		return keyArray;
	}
	/**从map里面找到对应namedPrepared的值放入数组中。
	 * @param map
	 * @return
	 */
	public Object[] getParams(Map<String,Object>  map){
		if(map == null ) return ArrayUtils.EMPTY_OBJECT_ARRAY;
		Object [] valueArray = new Object[currentIndex];
		ParameterIndex parameterIndex;
		for (int i = 0; i < namedPrepared.size(); i++) {
			parameterIndex = namedPrepared.get(i);
			valueArray[parameterIndex.getIndex() - 1] = map.get( parameterIndex.getName() );
		}
		return valueArray;
	}
	public Object[] getParams(Object bean){
		try {
			Map<String, String> describe = BeanUtils.describe(bean);
			return getParams(describe);
		} catch (IllegalAccessException | InvocationTargetException
				| NoSuchMethodException e) {
			throw new ConvertRunException(e);
		}
	}
	@Override
	public int translate(CharSequence input, int index, Writer out)
			throws IOException {
		if(input.charAt(index) == this.out ){
			currentIndex++;
			return 0;
		}
		if( input.charAt(index) == prefix ){
			List<Character> list = new ArrayList<Character>(10);
			char charAt;
			do{
				index++;
				charAt = input.charAt(index);
				if(input.charAt(index) != suffix){
					list.add(charAt);
				}else{
					namedPrepared.add(  new ParameterIndex(currentIndex++, StringUtils.join(list,""))  );
					out.write(this.out); 
					return list.size()+2;
				}
			}while(index < input.length());
		}
		return 0;
	}
	public int getParameterIndex() {
		return currentIndex;
	}
	
}
