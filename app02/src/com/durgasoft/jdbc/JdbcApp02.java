package com.durgasoft.jdbc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Static data input
 * 
 * @author ADITYA
 *
 */
public class JdbcApp02 {

	public static void main(String[] args) throws Exception {
		Class.forName("oracle.jdbc.OracleDriver");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","admin");
		Statement st = con.createStatement();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Table name: ");
		String tname = br.readLine();
		String query = "create table "+tname+" (ENO number(3) primary key,ENAME varchar2(10), ESAL float(5),EADDR varchar2(10))";
		
		st.executeUpdate(query);
		System.out.println("Table "+tname +" creted successfully");
		
		st.close();
		con.close();
		
	}

}
