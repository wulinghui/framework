package com.wlh.aop.factory;

import static org.junit.Assert.*;

import org.apache.commons.lang3.ClassPathUtils;
import org.apache.commons.lang3.ClassUtils;
import org.junit.Test;

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
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetPackageNameObjectString() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetPackageNameClassOfQ() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetPackageNameString() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetAbbreviatedNameClassOfQInt() {
		fail("Not yet implemented"); // TODO
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
