package com.group8.chess.util;

public class TurnPacket extends Packet{

	private PlayerColor currentPlayersTurn;
	public TurnPacket(PlayerColor CurrentPlayersTrun) {
		// TODO Auto-generated constructor stub
		super("It is now " + CurrentPlayersTrun  + "'s turn now");
		this.setCurrentPlayersTurn(CurrentPlayersTrun);
	}
	public PlayerColor getCurrentPlayersTurn() {
		return currentPlayersTurn;
	}
	public void setCurrentPlayersTurn(PlayerColor currentPlayersTurn) {
		this.currentPlayersTurn = currentPlayersTurn;
	}
	
	
}
