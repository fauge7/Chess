package com.group8.chess.client;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Client{

	JPanel main;
	
	public Client() {
	// TODO Auto-generated constructor stub
		ClientUI ui = new ClientUI();
		ui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ui.setVisible(true);
		
		ui.addMouseListener(new InputHandler());
		
		TimerTask timerTask = new TimerTask() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				ui.repaint();
			}
        };
        Timer timer = new Timer();
        timer.schedule(timerTask, 0, 100);
	}
	public static void main(String[] args) {
		Client client = new Client();
	}
}
