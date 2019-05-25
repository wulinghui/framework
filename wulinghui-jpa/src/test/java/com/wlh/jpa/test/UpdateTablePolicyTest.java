package com.wlh.jpa.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.junit.Test;

import com.wlh.jpa.JpaRepositoryImp;
import com.wlh.jpa.PrimaryKeyPolicy;
import com.wlh.jpa.UpdateTablePolicy;
import com.wlh.jpa.handlers.CascadeBeanProcessor;
import com.wlh.log.LogUtils;

public class UpdateTablePolicyTest {
	UpdateTablePolicy table ;
	JpaRepositoryImp repository;
	PrimaryKeyPolicy keyPolicy =  new PrimaryKeyPolicy();
	QueryRunner queryRunner;
	public UpdateTablePolicyTest() throws Throwable{
//		Class.forName( "org.apache.derby.jdbc.EmbeddedDriver").newInstance();
//		Connection conn = DriverManager.getConnection("jdbc:derby:db3;create=true");
//		DataSource ps = new DataSourceSingleCon(conn );
		Properties pro = new Properties();
		pro.put("driverClassName", "org.apache.derby.jdbc.EmbeddedDriver");
		pro.put("url", "jdbc:derby:db3;create=true");
		DataSource ps = BasicDataSourceFactory.createDataSource(pro );
		  
		table = new UpdateTablePolicy(ps );
		repository = new JpaRepositoryImp(ps, keyPolicy, table, "com.wlh.jpa.test");
		queryRunner = repository.getQueryRunner();
	}
	@Test
	public final void test() throws Throwable  { 
		Stuend stuend = new Stuend(keyPolicy.getId(Stuend.class), "wode");
		repository.save(stuend); 
		
		
//		List<Stuend> findAll = repository.findAll(Stuend.class);
//		System.out.println(findAll);  
	}
	@Test
	public  void testSaveOneOnOne() throws Throwable  { 
		StuendInfo info = new StuendInfo();
		info.setRuXueShijian("2019");
		info.setXueLi("低");
		Stuend1 stuend1 = new Stuend1();
		stuend1.setName("wlh");
		stuend1.setInfo(info); 
		repository.save(info);
		repository.save(stuend1); 
	}
	@Test
	public  void testSaveOneOnMany() throws Throwable  { 
		StuendInfo info = new StuendInfo();
		info.setRuXueShijian("2019");
		info.setXueLi("低");
		Stuend1 stuend1 = new Stuend1();
		stuend1.setName("wlh");
		stuend1.setInfo(info); 
		stuend1.setStuend1List("one-grades"); 
		Grades grades = new Grades();
		grades.setName("1班");
		List<Stuend1> list = new ArrayList<Stuend1>();
		list.add(stuend1);
		grades.setStuend1List(list); 
		repository.save(info);
		repository.save(stuend1); 
		repository.save(grades); 
	} 
	@Test
	public  void testSelect1() throws Throwable  { 
		
		Class class1 = Stuend1.class; 
		CascadeBeanProcessor convert = new CascadeBeanProcessor(repository,class1);
		BeanHandler handler = new BeanHandler<>(class1, new BasicRowProcessor(convert));
		
		Object query = queryRunner.query("select * from Stuend1", handler);
		System.out.println(query);
		query = queryRunner.query("select * from Stuend_Info", handler);
		System.out.println(query);
	}
	@Test
	public  void testSelect2() throws Throwable  { 
		queryRunner.update("UPDATE Stuend1 SET name = 'wlh' , info = '1'  , id = '1'");
//		queryRunner.update("UPDATE Stuend_Info SET name = 'wlh' , info = '1'  , id = '1'");
	}
	@Test
	public  void testSelect3() throws Throwable  { 
		StuendInfo findOne = repository.findOne(StuendInfo.class, "1");
		System.out.println( findOne );   
		System.out.println( repository.findOne(Stuend1.class, "1") );
	}
	@Test
	public  void testSelect4() throws Throwable  { 
		List<Map<String, Object>> query = queryRunner.query("select * from grades",	new MapListHandler());
		LogUtils.debugCollection(query); 
	} 
	@Test
	public  void testSelectAll() throws Throwable  {  
		LogUtils.debugCollection("StuendInfo" , repository.findAll(StuendInfo.class ) );
		LogUtils.debugCollection( "Stuend1" ,  repository.findAll(Stuend1.class ) );
		LogUtils.debugCollection("Grades", repository.findAll(Grades.class ) );
	} 
	
}
