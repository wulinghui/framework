package com.wlh.ioc;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:applicationContext.xml"})

import com.wlh.ioc.apache.BeanFactory;

public class SpringIocTest {
	ClassPathXmlApplicationContext aContext;

	@Before
	public void init() {
		aContext = new ClassPathXmlApplicationContext("applicationContext.xml");
	}

	@After
	public void after() {
		aContext.close();
	}

	@Test
	public void main() {
		System.out.println("start init ioc container");
		System.out.println("end loading xml");
		Person person = (Person) aContext.getBean("person1");
		System.out.println(person);
		System.out.println("close container");
	}

	@Test
	public void main1() {
		BeanFactory factory = new SpringBeanFactory(aContext); 
		IocManage.setBeanFactory("spring-ioc", factory );
		Person person = (Person)IocManage.getBean("person1");
//		Person person = (Person) aContext.getBean("person1");
		System.out.println("===============");
		System.out.println(person);
		System.out.println("===============");
	}
	@Test
	public void main2() {
		BeanFactory factory = new SpringBeanFactory(aContext); 
		IocManage.setBeanFactory("spring-ioc", factory );
		Person person = (Person)IocManage.getBean(Person.class); 
		System.out.println("===============");
		System.out.println(person);
		System.out.println("===============");
	}
	/**
	 *  测试ReflectBeanFactory
	 */
	@Test
	public void main3() {
		BeanFactory factory = new SpringBeanFactory(aContext); 
		IocManage.setBeanFactory("spring-ioc", factory );
		IocManage.setBeanFactory("spring-ioc", new ReflectBeanFactory() );
		Person person = (Person)IocManage.getBean(Person.class); 
//		Person person = (Person) aContext.getBean("person1");
		System.out.println("===============");
		System.out.println(person);
		System.out.println("===============");
	}
	/**
	 *  测试ReflectBeanFactory
	 */
	@Test
	public void main4() {
		BeanFactory factory = new SpringBeanFactory(aContext); 
		IocManage.setBeanFactory("1", factory );
		IocManage.setBeanFactory("2", new ReflectBeanFactory() );
		Person person = (Person)IocManage.getBean(Person.class); 
//		Person person = (Person) aContext.getBean("person1");
		System.out.println("===============");
		System.out.println(person);
		System.out.println("===============");
	}
	@Test
	public void main5() {
		BeanFactory factory = new SpringBeanFactory(aContext); 
		IocManage.setBeanFactory("1", new ReflectBeanFactory() );
		IocManage.setBeanFactory("2", factory );
		Person person = (Person)IocManage.getBean(Person.class); 
//		Person person = (Person) aContext.getBean("person1");
		System.out.println("===============");
		System.out.println(person);
		System.out.println("===============");
	}
}
