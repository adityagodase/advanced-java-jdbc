package com.durgasoft.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Used execute() method and explicitly used of this query 
 * 
 * @author ADITYA
 *
 */
public class JdbcApp10 {
	public static void main(String[] args) {
		Connection con = null;
		Statement st = null;
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "admin");
			st = con.createStatement();
			
			boolean b = st.execute("update emp1 set ESAL = ESAL + 500 where ESAL<6000");
			System.out.println(b);
			int rowCount = st.getUpdateCount();
			System.out.println("records updated : "+rowCount);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

}
