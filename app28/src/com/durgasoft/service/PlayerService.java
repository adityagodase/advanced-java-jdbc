package com.durgasoft.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.durgasoft.bean.Employee;

public class PlayerService {

	Connection con;
	Statement st;
	ResultSet rs;
	
	boolean status;
	
	public PlayerService() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "admin");
			st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = st.executeQuery("select * from emp1");
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public Employee getEmployeeData(String label) {
		Employee emp = null;
		
		
		try {
			if (label.equals("First")) {
				status = rs.first();
			}

			if (label.equals("Next")) {
				status = rs.next();
			}
			if (label.equals("Previous")) {
				status = rs.previous();
			}
			if (label.equals("Last")) {
				status = rs.last();
			}
			
			if(status) {
				emp = new Employee();
				emp.setEno(rs.getInt("ENO"));
				emp.setEname(rs.getString("ENAME"));
				emp.setEsal(rs.getFloat("ESAL"));
				emp.setEaddr(rs.getString("EADDR"));
			}else {
				emp = null;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return emp;
	}
}
