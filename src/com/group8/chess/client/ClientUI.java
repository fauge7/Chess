package com.group8.chess.client;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class ClientUI  extends JFrame{
	
	private BoardFrame boardFrame;
	private JTextArea text;
	public ClientUI() {
		// TODO Auto-generated constructor stub
		boardFrame = new BoardFrame();
		boardFrame.setVisible(true);
		text = new JTextArea();
		text.setEditable(false);
		text.setBounds(0, 0, 200, 1024);
		text.setVisible(true);
		this.add(boardFrame);
		this.add(text);
//		text.setLocation(1024, 100);
		this.setPreferredSize(new Dimension(1024,1024));
		this.setSize(new Dimension(1224, 1024));
		
	}

}
