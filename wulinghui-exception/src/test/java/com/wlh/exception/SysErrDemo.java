package com.wlh.exception;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

public class SysErrDemo {
	public static void main(String[] args) throws Exception {
		OutputStream output = new FileOutputStream("c:\\system.out.txt");
		PrintStream printOut = new PrintStream(output);
		System.setOut(printOut);    
		System.setErr(printOut);     
		System.out.println("1111111111");  
		System.err.println("1231");   
	}
	@Test
	@Ignore
	public void exceptionDemo(){
		try {
			throw new FileNotFoundException();
		} catch (FileNotFoundException e) {
			throw new ConvertRunException(e);
		}
	}
	@Test
	public void returnExceptiondemo(){
		ServiceDemoImp demo = new ServiceDemoImp(2);
			try {
				demo.getStr();
			} catch (ReturnException e) {
				List list = e.getObject();
				System.out.println(list);
			}
	}
}
interface IServiceDemo{
	String getStr();
}
class ServiceDemoImp implements IServiceDemo{
	int flag;
	
	public ServiceDemoImp(int flag) {
		this.flag = flag;
	}

	@Override
	public String getStr() throws ReturnException{
		if( 1 == flag) return "1111111";
		else throw new ReturnException(new ArrayList<>());
	}
}