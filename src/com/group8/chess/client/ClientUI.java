package com.group8.chess.client;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ClientUI  extends JFrame{
	
	private BoardFrame boardFrame;
	private JPanel panel;
	private GridLayout layout;
	private JTextArea console;
	public ClientUI() {
		// TODO Auto-generated constructor stub
		layout = new GridLayout(2, 2);
		setLayout(layout);
		layout.preferredLayoutSize(this);
		panel = new JPanel();
		console = new JTextArea();
		boardFrame = new BoardFrame();
		boardFrame.setVisible(true);
		console.setText("asfgasfhafha");
		layout.addLayoutComponent("board", boardFrame);
		layout.addLayoutComponent("text", console);
		this.setPreferredSize(new Dimension(492,492));
		this.setSize(new Dimension(492, 522));
//		grid.add(text);
//		this.add(boardFrame);
//		this.add(text);
	}

}
