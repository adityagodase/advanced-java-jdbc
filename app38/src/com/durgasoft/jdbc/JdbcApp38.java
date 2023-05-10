package com.durgasoft.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Date;

/**
 * Use Date class in both sql and util package 
 * 
 * @author ADITYA
 *
 */
public class JdbcApp38 {

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pst = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/durgadb", "root", "root");
			pst = con.prepareStatement("insert into student values(?,?,?,?)");

			pst.setString(1, "S-111");
			pst.setString(2, "AAA");

			java.sql.Date dob = java.sql.Date.valueOf("1998-12-22");
			pst.setDate(3, dob);

			Date d1 = new Date();
			long time = d1.getTime();
			java.sql.Date d2 = new java.sql.Date(time);
			pst.setDate(4, d2);

			int rowCount = pst.executeUpdate();
			System.out.println("Student inserted successfully :"+rowCount);
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
