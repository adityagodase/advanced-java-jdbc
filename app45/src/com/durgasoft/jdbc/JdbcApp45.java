package com.durgasoft.jdbc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;

/**
 * Total salary 
 * 
 * create or replace function getTotalSalary(salRange IN float) return float
AS
totalSalary float;
BEGIN
	select sum(ESAL) into totalSalary from emp1 where ESAL < salRange;
	return totalSalary;
END getTotalSalary;
/
 * 
 * @author ADITYA
 *
 */
public class JdbcApp45 {
	public static void main(String[] args) {
		Connection con =null;
		BufferedReader br = null;
		CallableStatement cst = null;
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","admin");
			br = new BufferedReader(new InputStreamReader(System.in));
			cst = con.prepareCall("{? = call getTotalSalary(?)}");
			
			System.out.println("Enter salary range :");
			int salRange = Integer.parseInt(br.readLine());
			cst.setInt(2, salRange);
			
			cst.registerOutParameter(1, Types.FLOAT);
			cst.execute();
			
			System.out.println("Total salary : "+cst.getFloat(1));
			
		} catch (Exception e) {
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
