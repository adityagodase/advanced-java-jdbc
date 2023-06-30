package com.durgasoft.jdbc;

import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * Use CLOB - Insertion
 * 
 * sql query - create table apps(APP_NAME varchar2(10) primary key,CONF_FILE clob);
 * 
 * @author ADITYA
 *
 */
public class JdbcApp50 {

	public static void main(String[] args) {
		Connection con =null;
		PreparedStatement pst =null;
		FileReader fr =null;
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","admin");
			pst = con.prepareStatement("insert into apps values(?,?)");
			pst.setString(1, "app1");
			File file = new File("D:\\DURGASOFT/web.txt");
			fr = new FileReader(file);
			pst.setCharacterStream(2, fr,file.length());
			pst.executeUpdate();
			System.out.println("Saved Successfully");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				fr.close();
				con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

}
