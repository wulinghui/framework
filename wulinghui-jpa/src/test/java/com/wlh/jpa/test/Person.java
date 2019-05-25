package com.wlh.jpa.test;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.List;

import com.wlh.util.TypeResolvable;

public class Person {
	private String name;
	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAbc() {
		return null;
	}
	public List<Person> getPersonList(){
		return null;
	} 

	public static void main(String[] args) throws Throwable {
		Person p = new Person();
		BeanInfo beanInfo = Introspector.getBeanInfo(p.getClass());
		PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
		for (PropertyDescriptor pd : pds) {
			pd.getName(); 
//			System.out.println(pd.getName());
			System.out.println(pd.getPropertyType());  
			System.out.println(pd.getReadMethod().getDeclaringClass());   
			System.out.println( pd.getPropertyType() );
			TypeResolvable forClass = TypeResolvable.forMethodReturnType(pd.getReadMethod()) ;
			if( forClass.hasGenerics()){ 
				System.out.println(forClass.getGeneric(0)   );
			}
		}
		System.out.println("==============");
		//
		PropertyDescriptor pd = new PropertyDescriptor("age", Person.class);
		p = new Person();
		Method setAgeMethod = pd.getWriteMethod();
		setAgeMethod.invoke(p,25); 
		Method getAgeMethod = pd.getReadMethod();  
		System.out.println(getAgeMethod.invoke(p));
	}
}
