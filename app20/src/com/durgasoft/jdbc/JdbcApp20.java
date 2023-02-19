package com.durgasoft.jdbc;

import java.awt.Taskbar.State;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Iterator;

/**
 * Used ResultSetMetaData and fetch colomn and its value with the help of index
 * 
 * @author ADITYA
 *
 */
public class JdbcApp20 {
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
			for (int i = 1; i < colCount; i++) {
				System.out.print(md.getColumnName(i) + "\t");
			}
			System.out.println();
			System.out.println(".....................");
			while (rs.next()) {
				for (int i = 1; i < colCount; i++) {
					System.out.print(rs.getString(i) + "\t");

				}
				System.out.println();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
