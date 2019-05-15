package com.wlh.dao;

import static org.junit.Assert.fail;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import net.sf.cglib.core.TypeUtils;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.junit.Test;
import org.springframework.core.ResolvableType;

import com.wlh.config.SystemConfig;
import com.wlh.dao.entity.ColumnSet;
import com.wlh.dao.entity.Record;
import com.wlh.dao.entity.RecordImp;
import com.wlh.dao.entity.TableData;
import com.wlh.dao.entity.TableDataImp;
import com.wlh.dao.entity.Value;
import com.wlh.util.ClassHelper;
import com.wlh.util.MapUtils;

public class SqlHelperTest {
	 private static String driver = "org.apache.derby.jdbc.EmbeddedDriver";
	 private static String protocol = "jdbc:derby:db3;create=true"; // 在工程目录下创建数据库
		SqlSession session = 
				 new SqlSessionJDBC();
	static{
		System.setProperty("wlh:BasicBuilderProperties.setThrowExceptionOnMissing", "false");
		Map<String,Object> SYSTEM_MAP = new HashMap<String, Object>();
         System.out.println("Loaded the appropriate driver");
         Connection conn = null;
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(protocol);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		SYSTEM_MAP.put(SqlConfig.CONNECTION, conn);
		SystemConfig.get().setProperty(SqlConfig.SQL_DEFAULT_CONFIG, SYSTEM_MAP);
	}
	@Test
	public final void testBatchStringObjectArrayArray() throws SQLException, InterruptedException, ExecutionException {
		session =  new SqlSessionJDBC();
		Future<TableData> selectTableData = session.selectTableData("select * from stu", MapUtils.EMPTY_MAP);
		System.out.println(selectTableData.getClass()); 
		TableData tableData = selectTableData.get();
		System.out.println(tableData); 
	}

	@Test
	public final void testBatchSqlConfigObjectArrayArray() throws Exception {
		session = new SqlSessionNamedPrepared(new SqlSessionJDBC()) ;
		Future<TableData> selectTableData = session.selectTableData("select * from stu", MapUtils.EMPTY_MAP);
		System.out.println(selectTableData.getClass()); 
		TableData tableData = selectTableData.get();
		System.out.println(tableData); 
	}

	@Test
	public final void testBatchSqlConfigListOfMap() throws SQLException, Exception, ExecutionException { 
		session =	new SqlSessionNamedPrepared(new SqlSessionFuture()) ;
		Future<TableData> selectTableData = session.selectTableData("select * from stu", MapUtils.EMPTY_MAP);
	 	System.out.println(selectTableData.getClass()); 
		TableData tableData = selectTableData.get();
		System.out.println(tableData); 
	}

	@Test
	public final void testBatch0SqlConfigListOfObject() throws Exception {
		Future<TableData> selectTableData = SqlHelper.selectTableData("select * from stu", MapUtils.EMPTY_MAP);
	 	System.out.println(selectTableData.getClass()); 
		TableData tableData = selectTableData.get(); 
		System.out.println(tableData); 
	}

	@Test
	public final void testBatch0StringListOfObject() throws Exception {
		SqlHelper.batch("insert into stu(stuname,email) values(? ,? )", new Object[][]{
				{"xiaoming" , "qq"}	,
				{"xiaoming" , "qq111"}	
		}  );
	}

	@Test
	public final void testBatchStringListOfMap() throws Exception {
		Future<Value> selectTableData = SqlHelper.selectValue("select count(*) from stu", MapUtils.EMPTY_MAP);
		Value value = selectTableData.get();
		System.out.println(value); 
	}

	@Test
	public final void testUpdateSqlConfigMap() throws Exception {
		TableDataImp dataImp = new TableDataImp();
		RecordImp recordImp;
		for (int i = 0; i < 5; i++) {
			recordImp = new RecordImp();
			recordImp.put("name", "wlh"+i);
			recordImp.put("email", "qq"+i);
			dataImp.add(recordImp);
		}
		SqlHelper.batch("insert into stu(stuname,email) values(&name  , &email )", dataImp );
	}

	@Test
	public final void testUpdateSqlConfigObject() throws SQLException {
		List<Stu> dataImp = new ArrayList<Stu>();
		for (int i = 0; i < 5; i++) {
			dataImp.add(new Stu("stu-name"+i , "stu-email"+i));
		} 
		SqlHelper.batch0("insert into stu(stuname,email) values(&name  , &email )", dataImp );
	}

	@Test
	public final void testUpdateStringObject() throws Exception {
		session = new SqlSessionNamedPrepared(new SqlSessionJDBC()) ;
		Future<Record> selectRecord = session.selectRecord("select * from stu", MapUtils.EMPTY_MAP);
//		Future<Record> selectRecord = SqlHelper.selectRecord("select * from stu", MapUtils.EMPTY_MAP);
		System.out.println(selectRecord.get()); 
	}

	@Test
	public final void testUpdateStringMap() throws Exception {
		Future<Record> selectRecord = SqlHelper.selectRecord("select * from stu", MapUtils.EMPTY_MAP);
		System.out.println(selectRecord.get());  
	}

	@Test
	public final void testSelectResultSetSqlConfigMap() throws Exception {
		Future<ColumnSet> selectRecord = SqlHelper.selectColumnSet("select stuname from stu", MapUtils.EMPTY_MAP);
		System.out.println(selectRecord.get());  
	}
    private static List<Map<String, List<Object>>> list = new ArrayList<>();

    public static void main(String[] args) throws NoSuchFieldException {
        // 获取成员泛型参数类型
        Field field = SqlHelperTest.class.getDeclaredField("list");
        ResolvableType resolvableType = ResolvableType.forField(field);
        resolvableType = ResolvableType.forInstance(list);
        ResolvableType.forMethodReturnType(null);
        System.out.println(resolvableType);  
        System.out.println(resolvableType.getGeneric(0));
        System.out.println(resolvableType.getType());
        System.out.println(resolvableType.getGeneric(0).getType());
        
        System.out.println(field.getType().getGenericSuperclass()); 
//        TypeUtils.
    }
	@Test
	public final void testSelectResultSetSqlConfigObject() {
		ArrayList<String> wo = new ArrayList<String>(){};
		 
		Class<? extends List> class1 = wo.getClass();
		System.out.println(class1); 
		String typeName = class1.getTypeName();
		System.out.println(typeName);
		TypeVariable<?> typeVariable = class1.getTypeParameters()[0];
		System.out.println(typeVariable);
		Class<? extends TypeVariable> class2 = typeVariable.getClass();
		System.out.println(class2); 
		
		Type type = ((ParameterizedType)wo.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		 System.out.println(type);
		 System.out.println( type.getClass());
		 
		
	}
	List<Map<String, List<Object>>> list111 = new ArrayList<>();
	@Test
	public final void testSelectResultSetStringObject() { 
		
	Field[] fs = this.getClass().getDeclaredFields(); // 得到所有的fields  
	for(Field f : fs)   
	{   
		
//		System.out.println(classArgumentsOfSuper[0]); 
	    Class fieldClazz = f.getType(); // 得到field的class及类型全路径  
	  
	    if(fieldClazz.isPrimitive())  continue;  //【1】 //判断是否为基本类型  
	  
	    if(fieldClazz.getName().startsWith("java.lang")) continue; //getName()返回field的类型全路径；  
	  
	    if(fieldClazz.isAssignableFrom(List.class)) //【2】  
	    {   
	             Type fc = f.getGenericType(); // 关键的地方，如果是List类型，得到其Generic的类型  
	  
	             if(fc == null) continue;    
	             System.out.println("fc="+ fc);
	             if(fc instanceof ParameterizedType) // 【3】如果是泛型参数的类型   
	            {   
	                   ParameterizedType pt = (ParameterizedType) fc;  
	  
	                   Class genericClazz = (Class)pt.getActualTypeArguments()[0]; //【4】 得到泛型里的class类型对象。  

	                   System.out.println("m.put"+ f.getName() + "=="+genericClazz);
	             }   
	      }   
	}  
	}

	@Test
	public final void testSelectResultSetStringMap() throws NoSuchFieldException, SecurityException {
		Field field = SqlHelperTest.class.getDeclaredField("list");
		Class fieldClazz = field.getType();
		Type genericType = field.getGenericType();
		Type[] actualTypeArguments = ((ParameterizedType)genericType).getActualTypeArguments();
		System.out.println( actualTypeArguments );  
		System.out.println( actualTypeArguments[0] );
//		actualTypeArguments[0].
	}

	@Test
	public final void testSelectSqlConfigMapClassOfT() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testSelectSqlConfigObjectClassOfT() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testSelectStringObjectClassOfT() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testSelectStringMapClassOfT() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testSelectValueSqlConfigMap() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testSelectValueSqlConfigObject() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testSelectValueStringObject() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testSelectValueStringMap() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testSelectRecordSqlConfigMap() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testSelectRecordSqlConfigObject() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testSelectRecordStringObject() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testSelectRecordStringMap() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testSelectTableDataSqlConfigMap() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testSelectTableDataSqlConfigObject() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testSelectTableDataStringObject() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testSelectTableDataStringMap() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testSelectColumnSetSqlConfigMap() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testSelectColumnSetSqlConfigObject() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testSelectColumnSetStringObject() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testSelectColumnSetStringMap() {
		fail("Not yet implemented"); // TODO
	}
}
