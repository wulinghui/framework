package com.wlh.dao.entity;

/**
 * @author wulinghui
 * @see com.wlh.dao.SqlSessionNamedPrepared.translatorWrap(SqlConfig, Object)
 * 
 * 用户输入和NamedPrepared解析有冲突了的话将无法判断，那个该往后移那个该被替换那个该往前移。
 */
public class ParameterIndex {
	public static final ParameterIndex[] EMPTY_ARRAY = new ParameterIndex[0];
	int index;
	String name;
	// see com.wlh.dao.AbstractSqlSession.fillStatement(PreparedStatement, ParameterIndex[], Map)
	String setMethodName;
	
	/**
	 * @param index
	 * @param name
	 * @param setMethodName 为fillStatement选择方法。
	 */
	public ParameterIndex(int index, String name, String setMethodName) {
		super();
		this.index = index;
		this.name = name;
		this.setMethodName = setMethodName;
	}
	public ParameterIndex(int index, String name) {
		this(index, name, "");
	}
	public ParameterIndex() {
		super();
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSetMethodName() {
		return setMethodName;
	}
	public void setSetMethodName(String setMethodName) {
		this.setMethodName = setMethodName;
	}
	@Override
	public String toString() {
		return "ParameterIndex [index=" + index + ", name=" + name
				+ ", setMethodName=" + setMethodName + "]";
	}
	
}
