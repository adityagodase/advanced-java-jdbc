package com.durgasoft.jdbc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * 
 * Update salary by using preparedstatement
 * 
 * @author ADITYA
 *
 */
public class JdbcApp35 {
	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pst;
		BufferedReader br = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/durgadb", "root", "root");
			pst = con.prepareStatement("update emp1 set ESAL = ESAL + ? where ESAL < ?");
			br = new BufferedReader(new InputStreamReader(System.in));

			System.out.println("Enter salary u want to increase");
			float salInc = Float.parseFloat(br.readLine());
			System.out.println("Enter salary range is : ");
			float salRange = Float.parseFloat(br.readLine());

			pst.setFloat(1, salInc);
			pst.setFloat(2, salRange);
			int rowCount = pst.executeUpdate();
			System.out.println("Employees updated : " + rowCount);
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			try {
				br.close();
				con.close();

			} catch (Exception e) {

				e.printStackTrace();
			}

		}
	}

}
