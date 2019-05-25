package com.wlh.jpa.test;

import java.util.List;

import com.wlh.jpa.entity.JpaEntity;

public class Stuend extends JpaEntity {
	String name;
	List<Teacher> list; 
	public Stuend(String id, String name) {
		super(id);
		this.name = name;
	}
	public Stuend() {
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Teacher> getList() {
		return list;
	}
	public void setList(List<Teacher> list) {
		this.list = list;
	}
}
