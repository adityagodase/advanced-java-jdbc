package com.durgasoft.jdbc;

import java.io.FileWriter;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Use CLOB- data read from DB
 * 
 * @author ADITYA
 *
 */
public class JdbcApp51 {
	public static void main(String[] args) {
		Connection con =null;
		PreparedStatement pst = null;
		ResultSet rs =null;
		Reader r =null;
		FileWriter fw = null;
		 try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","admin");
			pst = con.prepareStatement("select * from apps where APP_NAME = ?");
			pst.setString(1, "app1");
			
			rs = pst.executeQuery();
			rs.next();
			System.out.println("Application name : "+rs.getString("APP_NAME"));
			fw = new FileWriter("D:\\DURGASOFT/web_Fetch.txt");
			r = rs.getCharacterStream(2);
			int val = r.read();
			while(val!=-1) {
				fw.write(val);
				val = r.read();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				r.close();
				fw.close();
				con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}
