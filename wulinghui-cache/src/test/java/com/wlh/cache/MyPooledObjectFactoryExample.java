package com.wlh.cache;

import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class MyPooledObjectFactoryExample implements PooledObjectFactory<StringBuffer>{
	private static ObjectPool op = new GenericObjectPool<StringBuffer>(new MyPooledObjectFactoryExample(),new GenericObjectPoolConfig());
	public static void main(String[] args) throws Exception {
		//使用Apache commons-pool2的ObjectPool的默认实现GenericObjectPool
		//从ObjectPool租借对象StringBuffer   
		StringBuffer run = run("aaa");
		StringBuffer run1 = run("1111");
		op.addObject();
		StringBuffer run2 = run("2222");
		StringBuffer run3 = run("3333");
		op.invalidateObject(run3);
		StringBuffer run4 = run("4444");
		op.addObject();
		op.addObject();   // makeObject
		op.clear();       // destroyObject
		op.addObject();
		StringBuffer run5 = user("5555");
		op.addObject();
		StringBuffer run6 = user("6666");
		op.close();
		op.addObject();
	}
	protected static StringBuffer run(String str) throws Exception {
		StringBuffer sb = user(str);
		//归还对象StringBuffer
		op.returnObject(sb);
		return sb;
	}
	// 使用不归还。
	protected static StringBuffer user(String str) throws Exception {
		System.out.println("===============");
		StringBuffer sb = (StringBuffer) op.borrowObject();   // activateObject
		sb.append(str); 
		System.out.println(sb.toString());
		return sb;
	}
	@Override     // 生成的时候。
	public PooledObject<StringBuffer> makeObject() throws Exception {
		System.out.println("makeObject");
		return new DefaultPooledObject<StringBuffer>(new StringBuffer());
	}

	@Override   // op.clear();       // destroyObject
	public void destroyObject(PooledObject<StringBuffer> p) throws Exception {
		System.out.println("destroyObject");
	}

	@Override  
	public boolean validateObject(PooledObject<StringBuffer> p) {
		System.out.println("validateObject");
		return true;
	}

	@Override   // 每次借用
	public void activateObject(PooledObject<StringBuffer> p) throws Exception {
		System.out.println("activateObject");
	}

	@Override // 从活动池到空闲池时调用。
	public void passivateObject(PooledObject<StringBuffer> p) throws Exception {
		System.out.println("passivateObject");
		
	}

}
