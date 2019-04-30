package com.wlh.config.apache;

import org.apache.commons.configuration2.io.ConfigurationLogger;

import com.wlh.log.ILogger;

public class ConfigLogger extends ConfigurationLogger {
	ILogger logger;

	public ConfigLogger(ILogger logger) {
		super();
		this.logger = logger;
	}
	
	@Override
	public boolean isDebugEnabled() {
		return logger.isDebugEnabled();
	}


	@Override
	public boolean isInfoEnabled() {
		return logger.isInfoEnabled();
	}


	public void debug(String arg0) {
		 logger.debug(arg0);
	}


	public void error(String arg0, Throwable arg1) {
		 logger.error(arg0, arg1);
	}

	public void error(String arg0) {
		 logger.error(arg0);
	}


	public void info(String arg0) {
		 logger.info(arg0);
	}


	public void warn(String arg0, Throwable arg1) {
		 logger.warn(arg0, arg1);
	}

	public void warn(String arg0) {
		 logger.warn(arg0);
	}
	
	
}
