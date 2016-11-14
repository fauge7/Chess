package com.group8.chess.client;

import java.awt.Dimension;

import javax.swing.JFrame;

import com.group8.chess.util.Board;

public class ClientUI  extends JFrame{
	
	private BoardFrame boardFrame;
	public ClientUI() {
		// TODO Auto-generated constructor stub
		boardFrame = new BoardFrame();
		boardFrame.setVisible(true);
		this.add(boardFrame);
		this.setPreferredSize(new Dimension(1024,1024));
		this.setSize(new Dimension(1024, 1024));
		
	}

}
