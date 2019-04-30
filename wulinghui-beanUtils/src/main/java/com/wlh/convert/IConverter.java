package com.wlh.convert;

import org.apache.commons.beanutils.Converter;

/**
 * @author wulinghui
 * - 这里的apache-confing2依赖于BeanUtils-1.9。  BeanUtils2目前在Maven中还没有
   - apache-Convert模块在maven中没有，就先使用BeanUtils中的Convert。   
     但是为了之后的升级所有的转化都使用   @see com.wlh.convert.IConverter
 */
public interface IConverter extends Converter {
	  
}
