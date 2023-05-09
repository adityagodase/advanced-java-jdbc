package com.durgasoft.jdbc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class JdbcApp36 {

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pst = null;
		BufferedReader br = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "admin");
			pst = con.prepareStatement("delete from emp1 where ESAL < ?");
			
			br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Enter salary range u want to delete : ");
			float salRange = Float.parseFloat(br.readLine());
			
			pst.setFloat(1,salRange);
			int rowCount = pst.executeUpdate();
			System.out.println("Employee deleted is  : "+rowCount);
			
	}catch (Exception e) {
		e.printStackTrace();
	}finally {
		try {
			br.close();
			con.close();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
}
}
