package com.durgasoft.service;

import java.awt.Taskbar.State;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EmployeeService {
	Connection con;
	Statement st;
	ResultSet rs ;
	public EmployeeService() {
		
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","admin");
			st = con.createStatement();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public String add(int eno,String ename,float esal,String eaddr) {
		String status = "";
		
		try {
			rs = st.executeQuery("select * from emp1 where ENO = "+eno);
			boolean b= rs.next();
			if(b) {
				status = "Employee existed already";
			}else {
				int rowCount = st.executeUpdate("insert into emp1 values("+eno+",'"+ename+"',"+esal+",'"+eaddr+"')");
				if(rowCount==1) {
					status = "employee inserted successfully";
				}else{
					status ="employee inserted failed";
				}
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		return status;
		
	}
}
