package com.group8.chess.client;

import java.net.Socket;

import com.group8.chess.util.Board;
import com.group8.chess.util.PlayerColor;

public class Client {
	@SuppressWarnings("unused")
	private static ClientUI clientUI;
	public static Board board;
	public static PlayerColor color;
	Socket toServer;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Client client = new Client();
	}
	public Client() {
		// TODO Auto-generated constructor stub
		board = new Board(); 
		clientUI = new ClientUI(board);
		try {
			toServer = new Socket("localhost", 8000);
			while(!toServer.isConnected()){}
			Thread t = new Thread(new ClientHandler(toServer));
			t.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
