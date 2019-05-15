package com.wlh.util;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.commons.lang3.StringUtils;

import com.wlh.exception.ConvertRunException;
import com.wlh.log.ILogger;
import com.wlh.log.LogMSG;

public class ClassHelper {
	
	public static ILogger logger = LogMSG.getLogger();
	/** 获得类在父类上实现的泛型。自己在强转一下。 
	 * cn.wlh.util.base.ClassUtilTest.getClassArguments()
	 * @param cla 类。
	 * @return
	 */
	public static Type[] getClassArgumentsOfSuper(Class<?> cla) {
		Class<?> claSuper = cla;
		Type genericSuperclass = claSuper.getGenericSuperclass();
		ParameterizedType parameterizedType = null;
		while(  true ) {
			if(genericSuperclass instanceof ParameterizedType) {
				parameterizedType = (ParameterizedType) genericSuperclass;
				break;//
			}else {
				claSuper = claSuper.getSuperclass();
				genericSuperclass = claSuper.getGenericSuperclass();
			}
		}
		return parameterizedType.getActualTypeArguments();
	}
    /**
     * 获取类加载器
     */
	public static java.lang.ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }
    

    /**
     * 获取指定包名下的所有类
     */
    public static Set<Class<?>> getClassSet(String packageName) {
    	return getClassSet(getClassLoader(), packageName);
    }
    public static Set<Class<?>> getClassSet(java.lang.ClassLoader classLoader ,String packageName) {
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        try {
            Enumeration<URL> urls = classLoader.getResources(packageName.replace(".", "/"));
            while (urls.hasMoreElements()) {
            	URL url = urls.nextElement();
                addClassFromUrlToSet(packageName, classSet, url);
            }
        } catch (Throwable e) {
        	throw new ConvertRunException(e);
        }
        return classSet;
    }

	public static void addClassFromUrlToSet(String packageName, Set<Class<?>> classSet, URL url)
			throws IOException {
		if (url != null) {
		    String protocol = url.getProtocol();
		    if (protocol.equals("file")) {
		        String packagePath = url.getPath().replaceAll("%20", " ");
		        addClass(classSet, packagePath, packageName);
		    } else if (protocol.equals("jar")) {
		        JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
		        if (jarURLConnection != null) {
		            JarFile jarFile = jarURLConnection.getJarFile();
		            if (jarFile != null) {
		                Enumeration<JarEntry> jarEntries = jarFile.entries();
		                while (jarEntries.hasMoreElements()) {
		                    JarEntry jarEntry = jarEntries.nextElement();
		                    String jarEntryName = jarEntry.getName();
		                    if (jarEntryName.endsWith(".class")) {
		                        String className = jarEntryName.substring(0, jarEntryName.lastIndexOf(".")).replaceAll("/", ".");
		                        doAddClass(classSet, className);
		                    }
		                }
		            }
		        }
		    }
		}
	}
    /**从CLASS_SET下某父类（或接口）的所有子类（或实现类）
     * @param superClass
     * @param CLASS_SET
     * @return
     */
    public static Set<Class<?>> getClassSetBySuper(Class<?> superClass , Set<Class<?>> CLASS_SET) {
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        for (Class<?> cls : CLASS_SET) {
            if (superClass.isAssignableFrom(cls) && !superClass.equals(cls)) {
                classSet.add(cls);
            }
        }
        return classSet;
    }

    private static void addClass(Set<Class<?>> classSet, String packagePath, String packageName) {
        File[] files = new File(packagePath).listFiles(new FileFilter() {
            public boolean accept(File file) {
                return (file.isFile() && file.getName().endsWith(".class")) || file.isDirectory();
            }
        });
        for (File file : files) {
            String fileName = file.getName();
            if (file.isFile()) {
                String className = fileName.substring(0, fileName.lastIndexOf("."));
                if (StringUtils.isNotEmpty(packageName)) {
                    className = packageName + "." + className;
                }
                doAddClass(classSet, className);
            } else {
                String subPackagePath = fileName;
                if (StringUtils.isNotEmpty(packagePath)) {
                    subPackagePath = packagePath + "/" + subPackagePath;
                }
                String subPackageName = fileName;
                if (StringUtils.isNotEmpty(packageName)) {
                    subPackageName = packageName + "." + subPackageName;
                }
                addClass(classSet, subPackagePath, subPackageName);
            }
        }
    }

    private static void doAddClass(Set<Class<?>> classSet, String className) {
        Class<?> cls = null;
		try {
			cls = org.apache.commons.lang3.ClassUtils.getClass(className, false);
		} catch (ClassNotFoundException e) {
			logger.warn( e);
		}
		if( cls != null ) 	classSet.add(cls);
    }

}
