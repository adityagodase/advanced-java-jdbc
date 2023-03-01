package com.durgasoft.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class EditorService {

	Connection con;
	Statement st;
	ResultSet rs;
	boolean b;

	public EditorService() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "admin");
			st = con.createStatement();
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public boolean execute(String query) {

		try {
			b = st.execute(query);

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return b;
	}

	public ArrayList<String> getData() {
		ArrayList<String> data = new ArrayList<>();
		try {
			rs = st.getResultSet();
			ResultSetMetaData md = rs.getMetaData();
			int colCount = md.getColumnCount();
			String header = "";
			
			for (int i = 1; i <= colCount; i++) {
				header = header + md.getColumnName(i) + " ";
			}
			data.add(header);

			while (rs.next()) {
				String row = "";
				for (int i = 1; i <= colCount; i++) {
					row = row + rs.getString(i) + " ";
				}
				data.add(row);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	public int getRowCount() {
		int rowCount = 0;
		try {
			rowCount = st.getUpdateCount();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowCount;
	}

}
