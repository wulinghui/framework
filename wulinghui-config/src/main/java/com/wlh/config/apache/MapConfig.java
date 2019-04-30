package com.wlh.config.apache;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.configuration2.MapConfiguration;

public class MapConfig extends MapConfiguration {
	static MapFactory factory = new MapFactory();
	public MapConfig() {
		super(newMap());
	}
	public static Map<String, ?> newMap(){
		return factory.getInstance();
	}
	
	public static void setFactory(MapFactory factory) {
		MapConfig.factory = factory;
	}
	public static class MapFactory{
		public Map getInstance(){
			return new HashMap();
		}
	}
}
