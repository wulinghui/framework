package com.wlh.dao.entity;

import java.util.Map;

public interface Record extends Map<String,Object>{
	Object get(int index);
}