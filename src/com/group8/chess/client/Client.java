package com.group8.chess.client;

import com.group8.chess.util.Board;

public class Client {
	@SuppressWarnings("unused")
	static private ClientUI clientUI;
	static private Board board;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		board = new Board(); 
		clientUI = new ClientUI(board);

	}


}
