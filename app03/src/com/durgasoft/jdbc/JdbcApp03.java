package com.durgasoft.jdbc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Create dynamic data input for table
 * @author ADITYA
 *
 */
public class JdbcApp03 {

	public static void main(String[] args) throws Exception {

		Class.forName("oracle.jdbc.OracleDriver");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "admin");
		Statement st = con.createStatement();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Table name:");
		String tname = br.readLine();
		String query = "create table " + tname + "(";
		String primaryKey = "";
		int primaryKeyCount = 0;
		while (true) {
			System.out.println("Colomn Name : ");
			String colName = br.readLine();
			System.out.println("Colomn Data Type :");
			String colType = br.readLine();
			System.out.println("Colomn Size :");
			int colSize = Integer.parseInt(br.readLine());
			query = query + colName + " " + colType + "(" + colSize + ")";
			System.out.println("Is Primary key[Yes/No]? : ");
			String primaryKeyOption = br.readLine();

			if (primaryKeyOption.equalsIgnoreCase("yes")) {
				if (primaryKeyCount == 0) {
					primaryKey = primaryKey + colName;
					primaryKeyCount = primaryKeyCount + 1;
				} else {
					primaryKey = primaryKey + "," + colName;
				}
			}
			System.out.println("One more colomn[yes/no]? :");
			String colomnOption = br.readLine();

			if (colomnOption.equalsIgnoreCase("yes")) {
				query = query + ",";
				continue;
			} else {
				query = query + "," + "primary key(" + primaryKey + "))";
				break;
			}
		}
		System.out.println(query);
		st.executeUpdate(query);
		System.out.println("table is creted");
		st.close();
		con.close();
		

	}

}
