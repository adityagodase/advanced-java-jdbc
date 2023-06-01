package com.durgasoft.jdbc;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * BLOB-Read data from database
 * 
 * @author ADITYA
 *
 */
public class JdbcApp49 {
	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		FileOutputStream fos = null;
		InputStream is = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "admin");
			pst = con.prepareStatement("select * from emp2 where ENO = ?");
			pst.setInt(1, 111);
			rs = pst.executeQuery();
			rs.next();
			System.out.println("Employee number : " + rs.getInt("ENO"));

			fos = new FileOutputStream("D:\\DURGASOFT/banner.jpg");
			is = rs.getBinaryStream(2);
			int val = is.read();
			while (val != -1) {
				fos.write(val);
				val = is.read();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
				fos.close();
				con.close();
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}
