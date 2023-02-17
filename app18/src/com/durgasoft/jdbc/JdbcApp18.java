package com.durgasoft.jdbc;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Used DatabaseMetaData
 * 
 * @author ADITYA
 *
 */
public class JdbcApp18 {

	public static void main(String[] args) {
		Connection con = null;

		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "admin");

			DatabaseMetaData md = con.getMetaData();
			System.out.println(md.getDatabaseProductName());
			System.out.println(md.getDatabaseProductVersion());
			System.out.println(md.getDriverMajorVersion());
			System.out.println(md.getDriverMinorVersion());
			System.out.println(md.getSQLKeywords());
			System.out.println(md.getStringFunctions());
			System.out.println("numeric functions : " + md.getNumericFunctions());
			System.out.println("batch :" + md.supportsBatchUpdates());
			System.out.println("stored :" + md.supportsStoredProcedures());

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
