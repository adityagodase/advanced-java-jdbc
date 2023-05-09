package com.durasoft.jdbc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Use select query in pst
 * 
 * @author ADITYA
 *
 */
public class JdbcApp37 {

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pst = null;
		BufferedReader br = null;
		ResultSet rs =null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/durgadb", "root", "root");
			pst = con.prepareStatement("select * from emp1 where ESAL < ?");
			
			br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Enter salary u want to display : ");
			float salRange = Float.parseFloat(br.readLine());
			
			pst.setFloat(1, salRange);
			
			rs = pst.executeQuery();
			while(rs.next()) {
				System.out.print(rs.getInt("ENO")+"\t");
				System.out.print(rs.getString("ENAME")+"\t");
				System.out.print(rs.getFloat(3)+"\t");
				System.out.print(rs.getString("EADDR")+"\n");
			}
			
			
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
