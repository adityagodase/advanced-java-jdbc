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
import java.util.ArrayList;

import com.durgasoft.service.EditorService;

public class SQLEditorFrame extends Frame implements ActionListener {

	TextField tf1;
	Label l1;
	Button b;
	boolean status;
	EditorService editorService;

	public SQLEditorFrame() {
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
		l1 = new Label("Enter Query");
		tf1 = new TextField(20);
		b = new Button("Query");

		b.addActionListener(this);

		Font f = new Font("consolas", Font.BOLD, 20);
		l1.setFont(f);
		tf1.setFont(f);
		b.setFont(f);

		this.add(l1);
		this.add(tf1);
		this.add(b);

		editorService = new EditorService();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			String query = tf1.getText();

			status = editorService.execute(query);

			repaint();

		} catch (Exception e2) {
			e2.printStackTrace();
		}

	}

	@Override
	public void paint(Graphics g) {
		Font f = new Font("consolas", Font.BOLD, 15);
		g.setFont(f);
		
		if(status) {
			ArrayList<String> data = editorService.getData();
			g.drawString(data.get(0), 50, 300);
			int y=300;
			for(int i=1;i<data.size();i++) {
				String row = data.get(i);
				y+=40;
				g.drawString(row, 50, y);
			}
		}else {
			int rowCount= editorService.getRowCount();
			g.drawString("Row count : "+rowCount, 50, 350);
		}
	}

}
