package com.group8.chess.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

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
				board.buildMoveList(currentPlayersTurn);
//				main logic loop for server, get the updates from the current player who's turn it is to go and go
//				then switch listeners and keep going
//				will end with the validation of a new move
//				starts with a notification of a turn
				toPlayer1.writeObject(new Packet("white"));
				toPlayer2.writeObject(new Packet("black"));
				toPlayer1.writeObject(new TurnPacket(currentPlayersTurn));
				toPlayer2.writeObject(new TurnPacket(currentPlayersTurn));
			if(currentPlayersTurn == PlayerColor.WHITE){
				mp =  (MovePacket) fromPlayer1.readObject();
					if(isValidMove(mp)){
						Server.text.append("Valid move\n");
						mp.isValid = true;
						toPlayer1.writeObject(mp);
						toPlayer2.writeObject(mp);
						currentPlayersTurn = currentPlayersTurn.getOpponent();
						toPlayer1.writeObject(new TurnPacket(currentPlayersTurn));
						toPlayer2.writeObject(new TurnPacket(currentPlayersTurn));
					}
				
			}//end of white turn
			else{
			//start of black turn
				mp =  (MovePacket) fromPlayer2.readObject();
				if(isValidMove(mp)){
					Server.text.append("Valid move\n");
					mp.isValid = true;
					toPlayer1.writeObject(mp);
					toPlayer2.writeObject(mp);
					currentPlayersTurn = currentPlayersTurn.getOpponent();
					toPlayer1.writeObject(new TurnPacket(currentPlayersTurn));
					toPlayer2.writeObject(new TurnPacket(currentPlayersTurn));
				}
			
			}
				
				
				
				
			}//end of turns
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public boolean isValidMove(MovePacket movePacket){
		Server.text.append("MovePacket recieved\n");
//		return true;
		return (movePacket != null) ? isValidMove(movePacket.getFrom(), movePacket.getTo()) : false;
	}
	public boolean isValidMove(Coordinate from, Coordinate to){
		Server.text.append("" + board.getPiece(from).getMoveList().contains(to));	
		List<Coordinate> templist = board.getPiece(from).getMoveList();
		for(Coordinate c : templist){
			if(c.getX() == to.getX() && c.getY() == to.getY()){
				return true;
			}
		}
		return board.getPiece(from).getMoveList().contains(to);
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
