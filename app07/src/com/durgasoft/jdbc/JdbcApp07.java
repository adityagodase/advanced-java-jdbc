package com.durgasoft.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Check row int value return 0 when row is not created
 * 
 * @author ADITYA
 *
 */
public class JdbcApp07 {
	public static void main(String[] args) {
		Connection con = null;
		Statement st = null;
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","admin");
			st = con.createStatement();
			
			int rowCount1 = st.executeUpdate("create table std (ENO number(3) primary key,ENAME varchar2(10), ESAL float(5),EADDR varchar2(10))");
			System.out.println("create row count :"+rowCount1);
			
			int rowCount2 = st.executeUpdate("drop table std");
			System.out.println("drop table count :"+rowCount2);
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				st.close();
				con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

}
