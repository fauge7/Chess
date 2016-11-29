package com.group8.chess.server;

import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import com.group8.chess.util.Packet;
import com.group8.chess.util.PopUp;
import com.group8.chess.util.PopupType;

public class Server extends JFrame{
	// Use board.buildMoveList() once each time the player changes.
	public static JTextArea text;
	public static void main(String[] args) {
		Server server = new Server();
	}
	public Server() {
		// TODO Auto-generated constructor stub
		text = new JTextArea();
		this.add(text);
		this.setVisible(true);
		this.setSize(400, 600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		try{
			ServerSocket server = new ServerSocket(8000);
			while(true){
				text.append("waiting for Player 1 to connect\n");
				Socket player1 = server.accept();
				ObjectOutputStream toP1 = new ObjectOutputStream(player1.getOutputStream());
//				player 1 connected
				toP1.writeObject(new Packet("Waiting"));
				text.append("Waiting for a second player to connect\n");
				Socket player2 = server.accept();
				ObjectOutputStream toP2 = new ObjectOutputStream(player2.getOutputStream());
//				player 2 connected
				
				Packet connected  = new Packet("Players are connected");
				toP1.writeObject(connected);
				toP2.writeObject(connected);
				Thread t = new Thread(new GameHandler(player1, player2,toP1,toP2));
				t.start();
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
//		PopUp up = new PopUp(PopupType.WHITE_TURN);
	}
}
