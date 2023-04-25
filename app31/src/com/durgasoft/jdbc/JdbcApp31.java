package com.durgasoft.jdbc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Without using sql query we can insert data in databse by using resultset object
 * 
 * @author ADITYA
 *
 */
public class JdbcApp31 {
	public static void main(String[] args) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		BufferedReader br = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/durgadb", "root", "root");
			st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = st.executeQuery("select * from emp1");

			rs.moveToInsertRow();
			br = new BufferedReader(new InputStreamReader(System.in));

			while (true) {
				System.out.println("Enter ENO : ");
				int eno = Integer.parseInt(br.readLine());
				System.out.println("Enter ENAME : ");
				String ename = br.readLine();
				System.out.println("Enter ESAL : ");
				float esal = Float.parseFloat(br.readLine());
				System.out.println("Enter EADDR : ");
				String eaddr = br.readLine();

				rs.updateInt(1, eno);
				rs.updateString(2, ename);
				rs.updateFloat(3, esal);
				rs.updateString(4, eaddr);

				rs.insertRow();
				System.out.println("Do u want to enter one more emp");
				String option = br.readLine();
				if (option.equalsIgnoreCase("yes")) {
					continue;
				} else {
					break;

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
				con.close();

			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}
