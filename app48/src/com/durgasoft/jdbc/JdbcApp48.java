package com.durgasoft.jdbc;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * BLOB-Insertion
 * sql query - create table emp2 (ENO number(5) primary key, EMP_IMAGE blob);
 * 
 * @author ADITYA
 *
 */
public class JdbcApp48 {

	public static void main(String[] args) {
		Connection con =null;
		PreparedStatement pst = null;
		FileInputStream fis =null;
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","admin");
			pst = con.prepareStatement("insert into emp2 values(?,?)");
			pst.setInt(1, 111);
			File file = new File("D:\\DURGASOFT/lamb.jpg");
			fis = new FileInputStream(file);
			pst.setBinaryStream(2, fis,file.length());
			
			pst.executeUpdate();
			System.out.println("Image stored successfully");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				fis.close();
				con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
			

	}

}
