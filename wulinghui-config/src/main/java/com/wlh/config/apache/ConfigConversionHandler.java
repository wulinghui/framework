package com.wlh.config.apache;

import org.apache.commons.configuration2.convert.DefaultConversionHandler;
import org.apache.commons.configuration2.interpol.ConfigurationInterpolator;

import com.wlh.convert.ConvertUtils;

/**
 * @author wulinghui
 * 整合ConvertUtils的转化。
 */
public class ConfigConversionHandler extends DefaultConversionHandler {
	@Override
	protected <T> T convertValue(Object src, Class<T> targetCls,
			ConfigurationInterpolator ci) {
		try {
			return super.convertValue(src, targetCls, ci);
		} catch (Exception e) {
			return (T) ConvertUtils.convert(src, targetCls);
		}
	}
	
	
	
}
