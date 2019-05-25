package com.wlh.dao;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.concurrent.Future;

import org.apache.commons.lang3.reflect.MethodUtils;
import org.apache.commons.text.CaseUtils;
import org.junit.Test;

import com.wlh.util.TypeResolvable;

/**
 * @author wulinghui
 *junit-Test
 *Eclipse中开启的办法
Preferences->java->Compiler下勾选Store information about method parameters选项。
这样在使用eclipse编译java文件的时候就会将参数名称编译到class文件中。

Maven install时生效 :
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-compiler-plugin</artifactId>
    <version>3.3</version>
    <configuration>
        <source>1.8</source>
        <target>1.8</target>
        <compilerArgs>
            <arg>-parameters</arg>
        </compilerArgs>
    </configuration>
</plugin>
 */
public class ParametersTest {
	@Test   
	 public  void main111() throws Exception{  
	        Class clazz = Class.forName("com.wlh.dao.ParametersTest");  
	        Method[] methods = clazz.getDeclaredMethods();           
	        Constructor[] constructors = clazz.getConstructors();     
	        for (Constructor constructor : constructors) {  
	            System.out.println("+++" + constructor.getName());  
	            Parameter[] parameters = constructor.getParameters();  
	            for (Parameter parameter : parameters) {  
	                printParameter(parameter);  
	            }  
	        }
	  
	        System.out.println("------------------");  
	        for (Method method : methods) {     
	        	System.out.println(method);  
	        	System.out.println(method.toString().split(" ")[2]);  
	            System.out.println(method.getName());  
	            Parameter[] parameters = method.getParameters();  
	            for (Parameter parameter : parameters) {  
	                printParameter(parameter);  
	            }  
	        }  
	    }  
	@Test   
	 public  void main22() throws Exception{  
		Method method = ParametersTest.class.getMethod("printParameter1");
		TypeResolvable forMethodReturnType = TypeResolvable.forMethodReturnType(method);
		System.out.println(forMethodReturnType.toClass() );
		if( forMethodReturnType.getType() instanceof Future ){
			System.out.println("is Future"); 
		}
	}
	public Future<String> printParameter1() {  
		   return null;
	 }
	 public  void printParameter1111(int parameter,String str) {  
		   
	 }
	    private static void printParameter(Parameter parameter) {  
//	    	parameter.getAnnotation(annotationClass);
	    	
	        //参数名  
	        System.out.println("\t\t" + parameter.getName());  
	        //是否在源码中隐式声明的参数名  
	        System.out.println("\t\t\t implicit:" + parameter.isImplicit());  
	        //类文件中，是否存在参数名  
	        System.out.println("\t\t\t namePresent:" + parameter.isNamePresent());  
	        //是否为虚构参数  
	        System.out.println("\t\t\t synthetic:" + parameter.isSynthetic());  
	        System.out.println("\t\t\t VarArgs:" + parameter.isVarArgs());  
	    }  
}
