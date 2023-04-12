package com.durgasoft.gui;

import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.durgasoft.bean.Employee;
import com.durgasoft.service.PlayerService;

public class PlayerFrame extends Frame implements ActionListener {

	
	Button b1, b2, b3, b4;
	boolean status;
	Employee emp;
	PlayerService empService;

	public PlayerFrame() {
		this.setVisible(true);
		this.setSize(500, 700);
		this.setTitle("Execute Query");
		this.setLayout(new FlowLayout());
		this.setBackground(Color.gray);
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		

		b1 = new Button("First");
		b2 = new Button("Next");
		b3 = new Button("Previous");
		b4 = new Button("Last");

		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);

		Font f = new Font("consolas", Font.BOLD, 20);

		b1.setFont(f);
		b2.setFont(f);
		b3.setFont(f);
		b4.setFont(f);

		

		this.add(b1);
		this.add(b2);
		this.add(b3);
		this.add(b4);

		empService = new PlayerService();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			String label = e.getActionCommand();

			emp = empService.getEmployeeData(label);

		} catch (Exception e2) {
			e2.printStackTrace();
		}
		repaint();
	}

	@Override
	public void paint(Graphics g) {
		Font f = new Font("consolas", Font.BOLD, 15);
		g.setFont(f);

		if (emp == null) {
			g.drawString("No More", 50, 250);
		} else {
			g.drawString("Employee NO : " + emp.getEno(), 50, 250);
			g.drawString("Employee Name : " + emp.getEname(), 50, 300);
			g.drawString("Employee Salary : " + emp.getEsal(), 50, 350);
			g.drawString("Employee Address : " + emp.getEaddr(), 50, 400);
		}
	}
}
