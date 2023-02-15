package com.durgasoft.jdbc;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Retrive data using HTML tags
 * 
 * @author ADITYA
 *
 */
public class JdbcApp16 {
	public static void main(String[] args) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		FileOutputStream fos = null;

		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "admin");
			st = con.createStatement();
			rs = st.executeQuery("select * from emp1");

			String data = "";
			data = data + "<html><body><table border='1' align = 'center'>";
			data = data + "<tr><th>ENO</th><th>ENAME</th><th>ESAL</th><th>EADDR</th></tr>";
			while (rs.next()) {
				data = data + "<tr>";
				data = data + "<td>" + rs.getInt("ENO") + "</td>";
				data = data + "<td>" + rs.getString("ENAME") + "</td>";
				data = data + "<td>" + rs.getFloat("ESAL") + "</td>";
				data = data + "<td>" + rs.getString("EADDR") + "</td>";
				data = data + "</tr>";
			}
			data = data + "</table></body></html>";
			fos = new FileOutputStream("D:\\DURGASOFT\\Advanced java/emp1.html");
			byte[] b = data.getBytes();
			fos.write(b);
			System.out.println("Data transfer...");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fos.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
