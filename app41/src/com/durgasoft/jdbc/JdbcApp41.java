package com.durgasoft.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class JdbcApp41 {

	public static void main(String[] args) {
		Connection con =null;
		PreparedStatement pst =null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/durgadb", "root", "root");
			pst = con.prepareStatement("insert into emp1 values(?,?,?,?)");
			
			pst.setInt(1, 111);
			pst.setString(2, "kkk");
			pst.setFloat(3, 10000);
			pst.setString(4, "hyd");
			pst.addBatch();
			
			pst.setInt(1, 999);
			pst.setString(2, "lll");
			pst.setFloat(3, 10000);
			pst.setString(4, "hyd");
			pst.addBatch();
			
			pst.setInt(1, 100);
			pst.setString(2, "mmm");
			pst.setFloat(3, 10000);
			pst.setString(4, "hyd");
			pst.addBatch();
			
			int[] rowCounts = pst.executeBatch();
			
			for(int rowcount : rowCounts) {
				System.out.println("Record reported : "+rowcount);
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
