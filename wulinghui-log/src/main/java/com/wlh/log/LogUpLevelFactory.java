package com.wlh.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUpLevelFactory implements ILogFactory {
	
	@Override
	public ILogger newLogger() {
		Logger logger = LoggerFactory.getLogger(Thread.currentThread().getStackTrace()[2].getClassName());
		return new LogUUID( new LogUpLevel(new LogBase(logger)) );
	}
	public static void main(String[] args) {
		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		for (StackTraceElement string : stackTrace) {
			System.out.println(string);
		}
	}
}
