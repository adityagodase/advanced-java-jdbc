package com.durgasoft.jdbc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Dynamically insert values into table
 * 
 * @author ADITYA
 *
 */
public class JdbcApp04 {
	public static void main(String[] args) {
		Connection con = null;
		Statement st = null;
		BufferedReader br = null;

		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "admin");
			st = con.createStatement();
			br = new BufferedReader(new InputStreamReader(System.in));

			while (true) {
				System.out.println("Employee no : ");
				int eno = Integer.parseInt(br.readLine());
				System.out.println("Employee name : ");
				String ename = br.readLine();
				System.out.println("Employee salary");
				float esal = Float.parseFloat(br.readLine());
				System.out.println("Employee address : ");
				String eaddr = br.readLine();

				String query = "insert into emp1 values(" + eno + ",'" + ename + "'," + esal + ",'" + eaddr + "')";
				st.executeUpdate(query);
				System.out.println("Employee inserted successfully");
				System.out.println("One more employee added [yes/no]? :");

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
				st.close();
			} catch (Exception e) {

				e.printStackTrace();
			}

		}
	}

}
