package com.wlh.jpa.test;

import java.util.List;

import com.wlh.jpa.entity.JpaEntity;

public class Grades extends JpaEntity {
	String name;
	List<Stuend1> Stuend1List;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Stuend1> getStuend1List() {
		return Stuend1List;
	}
	public void setStuend1List(List<Stuend1> stuend1List) {
		Stuend1List = stuend1List;
	}
	
}
