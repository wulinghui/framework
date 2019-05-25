package com.wlh.jpa.test;

import com.wlh.jpa.entity.JpaEntity;
import com.wlh.util.FieldUtils;

public class Stuend1 extends JpaEntity {
	String name;
	StuendInfo info;
	String Stuend1List;
	public String getStuend1List() {
		return Stuend1List;
	}
	public void setStuend1List(String stuend1List) {
		Stuend1List = stuend1List;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public StuendInfo getInfo() {
		return info;
	}
	public void setInfo(StuendInfo info) {
		this.info = info;
	}
	@Override
	public String toString() {
		return "Stuend1 [name=" + name + ", info=" + info + ", id=" + id + "]";
	}
	public static void main(String[] args) throws Throwable {
		Stuend1 stuend1 = new Stuend1();  
//		Object readDeclaredField = FieldUtils.readDeclaredField(stuend1, "id", true);
		Object readDeclaredField = FieldUtils.readField(stuend1, "id", true);
		System.out.println(readDeclaredField);   
	}
}
