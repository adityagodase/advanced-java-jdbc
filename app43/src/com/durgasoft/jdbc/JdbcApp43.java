package com.durgasoft.jdbc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;

/**
 * Use callabe statement procedure
 * 
 * create or replace procedure getSal(no IN number,sal OUT float)
as
BEGIN
	select ESAL into sal from emp1 where ENO = no;
END getSal;
/
 * @author ADITYA
 *
 */
public class JdbcApp43 {
	public static void main(String[] args) {
		Connection con = null;
		CallableStatement cst = null;
		BufferedReader br =null;
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "admin");
			br = new BufferedReader(new InputStreamReader(System.in));
			cst = con.prepareCall("{call getSal(?,?)}");
			
			System.out.println("Enter emp number :");
			int eno = Integer.parseInt(br.readLine());
			
			cst.setInt(1, eno);
			cst.registerOutParameter(2, Types.FLOAT);
			cst.execute();
			System.out.println("Emp salary : "+cst.getFloat(2));
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				br.close();
				con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	
}
