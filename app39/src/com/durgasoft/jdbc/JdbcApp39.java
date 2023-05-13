package com.durgasoft.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Retrive from db 
 * 
 * @author ADITYA
 *
 */
public class JdbcApp39 {

	public static void main(String[] args) {
		Connection con =null;
		PreparedStatement pst =null;
		ResultSet rs =null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/durgadb", "root", "root");
			pst = con.prepareStatement("select * from student where SID = ?");
			
			pst.setString(1, "S-111");
			
			rs = pst.executeQuery();
			boolean b =rs.next();
			if(b) {
				System.out.println("Student details : ");
				System.out.print(rs.getString("SID")+"\t");
				System.out.print(rs.getString("SNAME")+"\t");
				System.out.print(rs.getDate("SDOB")+"\t");
				System.out.print(rs.getDate("SDOJ")+"\n");
			}else {
				System.out.println("Student not present");
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
