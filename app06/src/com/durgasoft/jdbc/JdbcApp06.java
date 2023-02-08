package com.durgasoft.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

/**
 * Delete row with the help of its range
 * 
 * @author ADITYA
 *
 */
public class JdbcApp06 {

	public static void main(String[] args) {
		Connection con = null;
		Statement st = null;
		Scanner sc = null;
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "admin");
			st = con.createStatement();
			sc = new Scanner(System.in);
			System.out.println("Enter salary range u want to drop row");
			float salRange = sc.nextFloat();
			
			int rowCount = st.executeUpdate("delete from emp1 where ESAL < "+salRange);
			System.out.println("Employees deleted"+rowCount);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				sc.close();
				st.close();
				con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

}
