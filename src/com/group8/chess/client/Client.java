package com.group8.chess.client;

import java.net.Socket;

import com.group8.chess.util.Board;

public class Client {
	@SuppressWarnings("unused")
	static private ClientUI clientUI;
	static private Board board;
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
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
