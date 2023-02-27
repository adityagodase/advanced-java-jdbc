package com.durgasoft.gui;

import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.durgasoft.bean.Employee;
import com.durgasoft.service.EmployeeService;

public class EmployeeSearchFrame extends Frame implements ActionListener {

	Employee emp;
	TextField tf1;
	Label l1;
	Button b;

	public EmployeeSearchFrame() {
		this.setVisible(true);
		this.setSize(500, 500);
		this.setTitle("Employee Search Frame");
		this.setLayout(new FlowLayout());
		this.setBackground(Color.green);
		this.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		l1 = new Label("Search employee : ");
		tf1 = new TextField(20);
		b = new Button("Search");

		b.addActionListener(this);

		Font f = new Font("consolas", Font.BOLD, 15);
		l1.setFont(f);
		tf1.setFont(f);
		b.setFont(f);

		this.add(l1);
		this.add(tf1);
		this.add(b);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			int eno = Integer.parseInt(tf1.getText());

			EmployeeService empService = new EmployeeService();
			emp = empService.search(eno);
			repaint();
			
		} catch (Exception e2) {
			e2.printStackTrace();
		}

	}

	@Override
	public void paint(Graphics g) {
		Font f = new Font("consolas", Font.BOLD, 15);
		g.setFont(f);
		
		if(emp==null) {
			g.drawString("Employee not existed ", 50, 200);
		}else {
			g.drawString("Employee number : "+emp.getEno(),50, 200);
			g.drawString("Employee name : "+emp.getEname(), 50, 250);
			g.drawString("Employee salary : "+emp.getEsal(), 50, 300);
			g.drawString("Employee address : "+emp.getEaddr(), 50, 350);
			
		}
		
	}

}
