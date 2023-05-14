package com.durgasoft.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 * Batch Update by using statement
 * 
 * @author ADITYA
 *
 */
public class JdbcApp40 {

	public static void main(String[] args) {
		Connection con = null;
		Statement st = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/durgadb", "root", "root");
			st = con.createStatement();
			st.addBatch("insert into emp1 values(777,'fff',40000,'pune')");
			st.addBatch("insert into emp1 values(888,'ggg',80000,'pune')");
			st.addBatch("update emp1 set ESAL = ESAL + 500 where ESAL < 10000");
			st.addBatch("delete from emp1 where ENO = 111");

			int[] rowCounts = st.executeBatch();
			for (int rowCount : rowCounts) {
				System.out.println("Records manipulated :" + rowCount);

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
