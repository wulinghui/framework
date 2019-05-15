package com.wlh.aop.factory;

import static org.junit.Assert.fail;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ClassPathUtils;
import org.apache.commons.lang3.ClassUtils;
import org.junit.Test;

import com.wlh.aop.entity.TestA;
import com.wlh.ioc.IocManage;
import com.wlh.ioc.ReflectBeanFactory;
import com.wlh.ioc.apache.BeanFactory;
import com.wlh.util.ClassHelper;

public class ClassUtilTestTest {
	ClassUtils claUtil = new ClassUtils();
	@Test
	public final void testClassUtils() throws ClassNotFoundException {
		Class<?> class1 = claUtil.getClass("com.wlh.aop.factory.ClassUtilSub" , false );
//		claUtil.getClass("com.wlh.aop.factory.ClassUtilSub" , true );
	}

	@Test
	public final void testGetShortClassNameObjectString() {
		String packageName = claUtil.getPackageName("com.wlh.aop.factory.ClassUtilSub");
		System.out.println(packageName); 
	}

	@Test
	public final void testGetShortClassNameClassOfQ() {
		     
//		claUtil.primitiveToWrapper(getClass());     
//		Iterable<Class<?>> hierarchy = claUtil.hierarchy(getClass());
		claUtil.primitiveToWrapper(Object.class);      
		Iterable<Class<?>> hierarchy = claUtil.hierarchy( Object.class );
		for (Class<?> class1 : hierarchy) {  
			System.out.println(class1 );
		}
		
	}

	@Test
	public final void testGetShortClassNameString() {
		String fullyQualifiedName = ClassPathUtils.toFullyQualifiedName(getClass(), "/");
		System.out.println(fullyQualifiedName); 
		fullyQualifiedName = ClassPathUtils.toFullyQualifiedPath(getClass(), "/");
		System.out.println(fullyQualifiedName); 
		fullyQualifiedName = ClassPathUtils.toFullyQualifiedPath( Package.getPackage("com.wlh.aop.factory"), "/");
		System.out.println(fullyQualifiedName); 
	}

	@Test
	public final void testGetSimpleNameClassOfQ() {
		Class<?> class1 = ClassUtilSub.class;
	}

	@Test
	public final void testGetSimpleNameObjectString() {
		CacheFactory cacheFactory = new CacheFactory( new IocFactory(new ReflectBeanFactory() ) );
		IocManage.setBeanFactory(cacheFactory);
		Map<String,BeanFactory> map =  new LinkedHashMap<>();
//		com.wlh.aop.factory.Test bean = (com.wlh.aop.factory.Test) cacheFactory.createBean(new BeanBuildContextImp(null, com.wlh.aop.factory.Test.class, null,map  ));
		com.wlh.aop.factory.TestFactory bean  = IocManage.getBean(TestFactory.class);;
		System.out.println(bean); 
	}

	@Test
	public final void testGetPackageNameObjectString() {
		Map bean = IocManage.getBean(Map.class,JavaUtilFactory.SELECT_OF_METHOD);
		System.out.println(bean); 
	}

	@Test
	public final void testGetPackageNameClassOfQ() {
		CacheFactory cacheFactory = new CacheFactory((new ReflectBeanFactory() ) );
		IocManage.setBeanFactory(cacheFactory);
		TestFactory bean  = IocManage.getBean(TestFactory.class);
		System.out.println(bean); 
	}

	@Test
	public final void testGetPackageNameString() throws Throwable, IllegalAccessException {
//		(new ReflectBeanFactory() )
		String basePackageNama = "com.wlh.aop.entity";
		Set<Class<?>> classSet = ClassHelper.getClassSet(basePackageNama);
		CacheFactory cacheFactory = new CacheFactory( new AopFactory(basePackageNama ) );
		IocManage.setBeanFactory(cacheFactory);
		TestA bean  = IocManage.getBean(TestA.class);
		System.out.println(bean.getClass()); 
		bean.aaa(); 
		TestA newInstance = bean.getClass().newInstance();
		newInstance.aaa();  
		System.out.println(classSet); 
	}

	@Test
	public final void testGetAbbreviatedNameClassOfQInt() {
		System.out.println(int.class.isPrimitive());
		System.out.println(boolean.class.isPrimitive());
		System.out.println(Boolean.class.isPrimitive());
		//判断是不是包装类
		System.out.println(ClassUtils.isPrimitiveWrapper(boolean.class));
		System.out.println(ClassUtils.isPrimitiveWrapper(Boolean.class));
		//判断是不是基本类和包装类
		System.out.println(ClassUtils.isPrimitiveOrWrapper(boolean.class));
		System.out.println(ClassUtils.isPrimitiveOrWrapper(Boolean.class));
	}

	@Test
	public final void testGetAbbreviatedNameStringInt() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetAllSuperclasses() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetAllInterfaces() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testConvertClassNamesToClasses() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testConvertClassesToClassNames() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testIsAssignableClassOfQArrayClassOfQArray() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testIsAssignableClassOfQArrayClassOfQArrayBoolean() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testIsPrimitiveOrWrapper() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testIsPrimitiveWrapper() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testIsAssignableClassOfQClassOfQ() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testIsAssignableClassOfQClassOfQBoolean() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testPrimitiveToWrapper() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testPrimitivesToWrappers() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testWrapperToPrimitive() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testWrappersToPrimitives() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testIsInnerClass() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetClassClassLoaderStringBoolean() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetClassClassLoaderString() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetClassString() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetClassStringBoolean() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetPublicMethod() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testToClass() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetShortCanonicalNameObjectString() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetShortCanonicalNameClassOfQ() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetShortCanonicalNameString() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetPackageCanonicalNameObjectString() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetPackageCanonicalNameClassOfQ() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetPackageCanonicalNameString() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testHierarchyClassOfQ() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testHierarchyClassOfQInterfaces() {
		fail("Not yet implemented"); // TODO
	}

}
