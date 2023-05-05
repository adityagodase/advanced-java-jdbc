package com.durgasoft.jdbc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 * Use PreparedStatement to inserta value
 * 
 * @author ADITYA
 *
 */
public class JdbcApp34 {
	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pst = null;
		BufferedReader br = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/durgadb", "root", "root");
			pst = con.prepareStatement("insert into emp1 values(?,?,?,?)");
			br = new BufferedReader(new InputStreamReader(System.in));

			while (true) {
				System.out.print("Enter no : ");
				int eno = Integer.parseInt(br.readLine());
				System.out.print("Enter name : ");
				String ename = br.readLine();
				System.out.print("Enter salary : ");
				float esal = Float.parseFloat(br.readLine());
				System.out.print("Enter address : ");
				String eaddr = br.readLine();

				pst.setInt(1, eno);
				pst.setString(2, ename);
				pst.setFloat(3, esal);
				pst.setString(4, eaddr);

				int rowCount = pst.executeUpdate();

				if (rowCount == 1) {
					System.out.println("Data inserted successfully");
				} else {
					System.out.println("Data failed :");
				}

				System.out.println("Do want to add more : ");
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

			} catch (Exception e) {

				e.printStackTrace();
			}

		}

	}
}
