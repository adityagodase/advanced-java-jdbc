package com.durgasoft.mysql.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Mysql database connection first program
 * 
 * @author ADITYA
 *
 */
public class JdbcApp29 {
	public static void main(String[] args) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/durgadb", "root", "root");
			st = con.createStatement();
			rs = st.executeQuery("select * from emp1");
			
			System.out.println("ENO\tENAME\tESAL\tEADDR");
			while (rs.next()) {
				System.out.print(rs.getInt("ENO") + "\t");
				System.out.print(rs.getString("ENAME") + "\t");
				System.out.print(rs.getFloat("ESAL") + "\t");
				System.out.print(rs.getString("EADDR") + "\n");
			}

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
