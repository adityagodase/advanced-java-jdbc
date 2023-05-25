package com.durgasoft.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import oracle.jdbc.internal.OracleTypes;

/**
 * Use CURSOR in callable statement
 * 
 * create or replace procedure getAllEmp(salRange IN float,emps OUT
 * SYS_REFCURSOR) AS BEGIN open emps for select * from emp1 where ESAL <
 * salRange; END getAllEmp; /
 * 
 * @author ADITYA
 *
 */
public class JdbcApp46 {

	public static void main(String[] args) {
		Connection con = null;
		CallableStatement cst = null;
		ResultSet rs = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "admin");
			cst = con.prepareCall("{call getAllEmp(?,?)}");
			cst.setInt(1, 10000);
			cst.registerOutParameter(2, OracleTypes.CURSOR);
			cst.execute();

			rs = (ResultSet) cst.getObject(2);

			while (rs.next()) {
				System.out.print(rs.getInt("ENO") + "\t");
				System.out.print(rs.getString("ENAME") + "\t");
				System.out.print(rs.getFloat("ESAL") + "\t");
				System.out.print(rs.getString("EADDR") + "\n");
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
