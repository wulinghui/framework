package com.wlh.dao;

public class Stu {

	String name;
	String email;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Stu(String name, String email) {
		super();
		this.name = name;
		this.email = email;
	}
	public Stu() {
		super();
	}

}
