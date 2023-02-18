package com.durgasoft.jdbc;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

/**
 * Used ResultSetMetaData and fetch it with the help of index
 * 
 * @param args
 */
public class JdbcApp19 {

	public static void main(String[] args) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "admin");
			st = con.createStatement();
			rs = st.executeQuery("select * from emp1");

			ResultSetMetaData md = rs.getMetaData();
			int colCount = md.getColumnCount();
			System.out.println("No of col : " + colCount);

			for (int i = 1; i < colCount; i++) {
				System.out.println("Colomn Name : " + md.getColumnName(i));
				System.out.println("Colomn Type : " + md.getColumnTypeName(i));
				System.out.println("Colomn Size : " + md.getColumnDisplaySize(i));
				System.out.println("..........................");
			}
			System.out.println();

		} catch (Exception e) {
			e.printStackTrace();
		}
		try {

			con.close();
		} catch (Exception e2) {
			e2.printStackTrace();
		}

	}
}
