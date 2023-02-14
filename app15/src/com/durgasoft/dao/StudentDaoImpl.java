package com.durgasoft.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.durgasoft.beans.Student;
import com.durgasoft.factory.ConnectionFactory;

public class StudentDaoImpl implements StudentDao {

	@Override
	public String add(Student std) {
		String status = "";
		try {
			Connection con = ConnectionFactory.getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from student where SID = '" + std.getSid() + "'");
			boolean b = rs.next();
			if (b == true) {
				status = "Student already present";
			} else {
				int rowCount = st.executeUpdate("insert into student values('" + std.getSid() + "','" + std.getSname()
						+ "','" + std.getSaddr() + "')");
				if (rowCount == 1) {
					System.out.println("222222222");
					status = "Student inserted successfully";
				} else {
					status = "student insertion failure";
				}
			}
		} catch (Exception e) {
			status = "student insertion failure";
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public Student search(String sid) {
		Student std = null;
		try {
			Connection con = ConnectionFactory.getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from student where SID = '" + sid + "'");
			boolean b = rs.next();
			if (b) {
				std = new Student();
				std.setSid(rs.getString("SID"));
				std.setSname(rs.getString("SNAME"));
				std.setSaddr(rs.getString("SADDR"));
			} else {
				std = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return std;
	}

	@Override
	public String update(Student std) {
		String status = "";
		try {
			Connection con = ConnectionFactory.getConnection();
			Statement st =con.createStatement();
			int rowCount = st.executeUpdate("update student set SNAME = '"+std.getSname()+"', SADDR ='"+std.getSaddr()+"' where SID = '"+std.getSid()+"'");
		if(rowCount == 1) {
			status = "Student updated successfully";
		}else {
			status ="student update fail";
		}
		} catch (Exception e) {
			status = "student update fail";
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public String delete(String sid) {
		String status ="";
		try {
			Connection con = ConnectionFactory.getConnection();
			Statement st = con.createStatement();
			int rowCount = st.executeUpdate("delete from student where SID = '"+sid+"'");
			if(rowCount == 1) {
				status = "deleted successfully";
			}else {
				status = "delete fail";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

}
