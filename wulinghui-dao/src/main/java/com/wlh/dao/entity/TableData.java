package com.wlh.dao.entity;


public interface TableData extends ColumnSet<Record>{
	public Object get(int index,String name);
}
