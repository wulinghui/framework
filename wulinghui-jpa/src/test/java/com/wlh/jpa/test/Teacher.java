package com.wlh.jpa.test;

import java.util.List;

import com.wlh.jpa.entity.JpaEntity;

public class Teacher extends JpaEntity {
	String name;
	List<Stuend> list;
	public Teacher(String id, String name) {
		super(id);
		this.name = name;
	}
	public Teacher() {
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Stuend> getList() {
		return list;
	}
	public void setList(List<Stuend> list) {
		this.list = list;
	}
	
}
