package com.durgasoft.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import oracle.jdbc.internal.OracleTypes;

/**
 * Use function of callable statement
 * 
 * create or replace function getAllStudents return SYS_REFCURSOR AS students
 * SYS_REFCURSOR; BEGIN open students for select * from emp1; return students;
 * END getAllStudents; /
 * 
 * @author ADITYA
 *
 */
public class JdbcApp47 {

	public static void main(String[] args) {
		Connection con =null;
		CallableStatement cst = null;
		ResultSet rs =null;
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","admin");
			cst = con.prepareCall("{? = call getAllStudents}");
			cst.registerOutParameter(1, OracleTypes.CURSOR);
			cst.execute();
			rs = (ResultSet)cst.getObject(1);
			
			while(rs.next()) {
				System.out.print(rs.getInt("ENO")+"\t");
				System.out.print(rs.getString("ENAME")+"\t");
				System.out.print(rs.getFloat("ESAL")+"\t");
				System.out.print(rs.getString("EADDR")+"\n");
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
