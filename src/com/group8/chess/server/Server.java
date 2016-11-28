package com.group8.chess.server;

import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.group8.chess.util.Packet;
import com.group8.chess.util.PopUp;
import com.group8.chess.util.PopupType;

public class Server {
	// Use board.buildMoveList() once each time the player changes.
	
	public static void main(String[] args) {
		Server server = new Server();
	}
	public Server() {
		// TODO Auto-generated constructor stub
		
		try{
			ServerSocket server = new ServerSocket(8000);
			while(true){
				Socket player1 = server.accept();
				ObjectOutputStream toP1 = new ObjectOutputStream(player1.getOutputStream());
//				player 1 connected
				toP1.writeObject(new Packet("Waiting for Player 2 to connect"));
				Socket player2 = server.accept();
				ObjectOutputStream toP2 = new ObjectOutputStream(player2.getOutputStream());
//				player 2 connected
				
				Packet connected  = new Packet("Players are connected");
				toP1.writeObject(connected);
				toP2.writeObject(connected);
				Thread t = new Thread(new GameHandler(player1, player2));
			}
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		PopUp up = new PopUp(PopupType.WHITE_TURN);
	}
}
