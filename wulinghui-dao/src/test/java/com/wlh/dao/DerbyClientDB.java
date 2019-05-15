package com.wlh.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DerbyClientDB {
	 private static String driver = "org.apache.derby.jdbc.ClientDriver";
	    private static String protocol = "jdbc:derby://localhost:1527/db3";

	    public static void main(String[] args) {
	        try {
	            Class.forName(driver).newInstance();
	            System.out.println("Loaded the appropriate driver");
	            Connection conn = DriverManager.getConnection(protocol);
	            Statement stmt = conn.createStatement();
	            for (int i = 1; i < 10; i++) {
	                String sql = "insert into stu(stuname,email) values('user" + i + "','user" + i + "@test.com')";
	                System.out.println(sql);
	                stmt.addBatch(sql);
	            }
	            stmt.executeBatch();
	            System.out.println("insert over");
	            conn.commit();

	            stmt.close();
	            conn.close();

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
}
