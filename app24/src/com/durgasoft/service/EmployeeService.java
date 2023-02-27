package com.durgasoft.service;

import java.awt.Taskbar.State;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.durgasoft.bean.Employee;

public class EmployeeService {

	Connection con;
	Statement st;
	ResultSet rs;
	
	Employee emp;
	
	public EmployeeService() {

		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "admin");
			st = con.createStatement();

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public Employee search(int eno) {
		try {
			rs = st.executeQuery("select * from emp1 where Eno = "+eno);
			boolean b = rs.next();
			if(b) {
				emp = new Employee();
				emp.setEno(rs.getInt("ENO"));
				emp.setEname(rs.getString("ENAME"));
				emp.setEsal(rs.getFloat("ESAL"));
				emp.setEaddr(rs.getString("EADDR"));
			}else {
				emp=null;
			}
			
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return emp;
	}
}
