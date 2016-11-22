package com.group8.chess.server;

import javax.swing.Popup;

import com.group8.chess.util.PopUp;
import com.group8.chess.util.PopupType;

public class Server {
	// Use board.buildMoveList() once each time the player changes.
	public static void main(String[] args) {
		Server server = new Server();
	}
	public Server() {
		// TODO Auto-generated constructor stub
		PopUp up = new PopUp(PopupType.WHITE_TURN);
	}
}
