package com.durgasoft.jdbc;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Retrive data in form of text field
 * 
 * @author ADITYA
 *
 */
public class JdbcApp17 {

	public static void main(String[] args) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		FileOutputStream fos =null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","admin");
			st = con.createStatement();
			rs = st.executeQuery("select * from emp1");
			
			String data="";
			data = data + "ENO,ENAME,ESAL,EADDR\n";
			while(rs.next()) {
				data = data + rs.getInt("ENO")+",";
				data = data + rs.getString("ENAME")+",";
				data = data + rs.getFloat("ESAL")+",";
				data = data + rs.getString("EADDR")+"\n";
				
			}
			fos = new FileOutputStream("D:\\DURGASOFT\\Advanced java/emp1.txt");
			byte[] b = data.getBytes();
			fos.write(b);
			System.out.println("Data transfer...");
		} catch (Exception e) {
			try {
				fos.close();
				con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

	}

}
