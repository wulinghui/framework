package com.wlh.ioc;

import java.util.Arrays;

public class BeanEntity {

	String name;
	Class<?> typeToMatch ;
	Object[] args;
	public BeanEntity(String name, Class<?> typeToMatch, Object[] args) {
		super();
		this.name = name;
		this.typeToMatch = typeToMatch;
		this.args = args;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Class<?> getTypeToMatch() {
		return typeToMatch;
	}
	public void setTypeToMatch(Class<?> typeToMatch) {
		this.typeToMatch = typeToMatch;
	}
	public Object[] getArgs() {
		return args;
	}
	public void setArgs(Object[] args) {
		this.args = args;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(args);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((typeToMatch == null) ? 0 : typeToMatch.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BeanEntity other = (BeanEntity) obj;
		if (!Arrays.equals(args, other.args))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (typeToMatch == null) {
			if (other.typeToMatch != null)
				return false;
		} else if (!typeToMatch.equals(other.typeToMatch))
			return false;
		return true;
	}

}
