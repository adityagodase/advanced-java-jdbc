package com.durgasoft.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.durgasoft.beans.Student;
import com.durgasoft.factory.StudentServiceFactory;
import com.durgasoft.service.StudentService;

/**
 * Perform JDBC CRUD operation
 * 
 * @author ADITYA
 *
 */
public class Test {

	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			while (true) {
				System.out.println("1. ADD");
				System.out.println("2. SEARCH");
				System.out.println("3. UPDATE");
				System.out.println("4. DELETE");
				System.out.println("5. EXIT");
				System.out.println("YOUR Option[1,2,3,4,5] :");
				int option = Integer.parseInt(br.readLine());
				String sid = "";
				String sname = "";
				String saddr = "";
				Student std = null;
				StudentService studentService = null;
				switch (option) {
				case 1:
					studentService = StudentServiceFactory.getStudentService();
					System.out.println("Enter student id :");
					sid = br.readLine();
					System.out.println("Enter student name :");
					sname = br.readLine();
					System.out.println("Enter student address :");
					saddr = br.readLine();

					std = new Student();
					std.setSid(sid);
					std.setSname(sname);
					std.setSaddr(saddr);
					String status = studentService.addStudent(std);
					System.out.println("status : " + status);
					break;

				case 2:
					studentService = StudentServiceFactory.getStudentService();

					System.out.println("Enter student id to search :");
					sid = br.readLine();

					std = studentService.searchStudent(sid);
					if (std == null) {
						System.out.println("student does not exit");
					} else {
						System.out.println("Student details***");
						System.out.println("Student id :" + std.getSid());
						System.out.println("Student name :" + std.getSname());
						System.out.println("Student addr :" + std.getSaddr());
					}
					break;

				case 3:
					System.out.println("Enter id of student : ");
					sid = br.readLine();

					studentService = StudentServiceFactory.getStudentService();
					std = studentService.searchStudent(sid);
					if (std == null) {
						System.out.println("Student is not available");
					} else {
							System.out.print("Student Name [Old Name] : "+std.getSname());
							String sname_New = br.readLine();
							if(sname_New==null || sname_New.equals("")) {
								sname_New = std.getSname();
							}
								
							System.out.println("Student address [Old Addr] : "+std.getSaddr());
							String saddr_New = br.readLine();
							if(saddr_New == null || saddr_New.equals("") ) {
								saddr_New = std.getSaddr();
							}
							Student std_New = new Student();
							std_New.setSid(sid);
							std_New.setSname(sname_New);
							std_New.setSaddr(saddr_New);
							status = studentService.updateStudent(std_New);
							System.out.println("Status :"+status);
					}
					break;

				case 4:
					System.out.println("Enter student id : ");
					sid = br.readLine();
					
					studentService = StudentServiceFactory.getStudentService();
					std = studentService.searchStudent(sid);
					
					if(std==null) {
						System.out.println("Student is not present");
					}else {
						status = studentService.deleteStudent(sid);
						System.out.println("status : "+status);
					}
					break;

				case 5:
					System.out.println("**ThankQ**");
					System.exit(0);
					break;
				default:
					System.out.println("provide no 1-5");
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
