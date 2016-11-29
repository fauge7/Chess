package com.group8.chess.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.group8.chess.util.Board;
import com.group8.chess.util.Coordinate;
import com.group8.chess.util.MovePacket;
import com.group8.chess.util.Packet;
import com.group8.chess.util.PlayerColor;
import com.group8.chess.util.TurnPacket;

public class GameHandler implements Runnable {

	Socket player1,player2;
	Board board;
	private PlayerColor currentPlayersTurn = PlayerColor.WHITE;
	private ObjectOutputStream toPlayer1, toPlayer2;
	private ObjectInputStream fromPlayer1, fromPlayer2;
	
	public GameHandler(Socket player1, Socket player2, ObjectOutputStream toP1, ObjectOutputStream toP2) {
		// TODO Auto-generated constructor stub
		this.player1 = player1;
		this.player2 = player2;
		toPlayer1 = toP1;
		toPlayer2 = toP2;
		board = new Board();
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub

		try {
			
			fromPlayer1 = new ObjectInputStream(player1.getInputStream());
			fromPlayer2 = new ObjectInputStream(player2.getInputStream());
			MovePacket mp = null;
			while(true){
//				main logic loop for server, get the updates from the current player who's turn it is to go and go
//				then switch listeners and keep going
//				will end with the validation of a new move
//				starts with a notification of a turn
				toPlayer1.writeObject(new TurnPacket(currentPlayersTurn));
				toPlayer2.writeObject(new TurnPacket(currentPlayersTurn));
			if(currentPlayersTurn == PlayerColor.WHITE){
				while(!isValidMove(mp)){
					mp =  (MovePacket) fromPlayer1.readObject();
					if(isValidMove(mp)){
						mp.isValid = true;
						toPlayer1.writeObject(mp);
						toPlayer2.writeObject(mp);
					}
				}
			}//end of white turn
			else{
			//start of black turn
				while(!isValidMove(mp)){
					mp =  (MovePacket) fromPlayer1.readObject();
					if(isValidMove(mp)){
						mp.isValid = true;
						toPlayer1.writeObject(mp);
						toPlayer2.writeObject(mp);
						
					}
				}
			}
				
				
				
				
			}//end of turns
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public boolean isValidMove(MovePacket movePacket){
		return (movePacket != null) ? isValidMove(movePacket.getFrom(), movePacket.getTo()) : false;
	}
	public boolean isValidMove(Coordinate from, Coordinate to){
		boolean isValid = false;
		
		return isValid;
	}
	public void sendPacket(ObjectOutputStream toPlayer, Packet sending) throws IOException{
		toPlayer.writeObject(sending);
		toPlayer.flush();
	}
	public void sendBothPacket(Packet sending) throws IOException{
		toPlayer1.writeObject(sending);
		toPlayer2.writeObject(sending);
	}
}
