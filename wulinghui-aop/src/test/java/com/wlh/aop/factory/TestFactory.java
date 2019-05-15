package com.wlh.aop.factory;

public class TestFactory {
	int name;
	int age;
	public TestFactory(int name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	public TestFactory(int name) {
		super();
		this.name = name;
	}
	public TestFactory() {
		super();
	}
}
