package com.durgasoft.jdbc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Types;

/**
 * Use callbale Function 
 * 
 * create or replace function getAVG(no1 IN number, no2 IN number) return float
AS
sal1 float;
sal2 float;
BEGIN
	select ESAL into sal1 from emp1 where ENO = no1;
      select ESAL into sal2 from emp1 where ENO = no2;
	return (sal1 + sal2)/2;
END getAVG;
/
 * 
 * @author ADITYA
 *
 */
public class JdbcApp44 {

	public static void main(String[] args) {
		Connection con =null;
		BufferedReader br = null;
		CallableStatement cst = null;
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","admin");
			br = new BufferedReader(new InputStreamReader(System.in));
			cst = con.prepareCall("{? = call getAVG(?,?)}");
			
			System.out.println("Enter first ID : ");
			int no1 = Integer.parseInt(br.readLine());
			System.out.println("Enter second ID : ");
			int no2 = Integer.parseInt(br.readLine());
			
			cst.setInt(2, no1);
			cst.setInt(3, no2);
			cst.registerOutParameter(1, Types.FLOAT);
			cst.execute();
			
			System.out.println("AVG is : "+cst.getFloat(1));
			
			
			
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
