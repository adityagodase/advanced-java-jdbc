package com.durgasoft.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

/**
 * updated table salary column with its range
 * 
 * @author ADITYA
 *
 */
public class JdbcApp05 {
	public static void main(String[] args) {
		Connection con = null;
		Statement st = null;
		Scanner sc = null;

		try {

			Class.forName("oracle.jdbc.OracleDriver");
			
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "admin");
			st = con.createStatement();

			sc = new Scanner(System.in);

			System.out.print("Increase bonus : ");
			int bonus = sc.nextInt();
			System.out.print("Range of salary : ");
			float salRange = sc.nextFloat();

			int rowCount = st.executeUpdate("update emp1 set ESAL = ESAL+" + bonus + "where ESAL<=" + salRange);
			System.out.println("Employee updated " + rowCount);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

				con.close();
				sc.close();
				st.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
