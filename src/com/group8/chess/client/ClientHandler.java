package com.group8.chess.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.group8.chess.piece.Piece;
import com.group8.chess.util.MovePacket;
import com.group8.chess.util.Packet;
import com.group8.chess.util.PlayerColor;
import com.group8.chess.util.PopUp;
import com.group8.chess.util.PopupType;
import com.group8.chess.util.TurnPacket;

public class ClientHandler implements Runnable {
	
	public static ObjectOutputStream os;
	public static ObjectInputStream is;
	
	Socket toServer;
	public ClientHandler(Socket toServer) {
		// TODO Auto-generated constructor stub
		this.toServer = toServer;
	}
	@Override
	public void run() {
		
		// TODO Auto-generated method stub
		try {
			os = new ObjectOutputStream(toServer.getOutputStream());
			is = new ObjectInputStream(toServer.getInputStream());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			os = null;
			is = null;
		}
		Packet p;
		Object recieved;
		while(true){
			try {
				while((recieved  = (Object) is.readObject()) != null){
					Client.board.buildMoveList(ClientUI.thisPlayersColor);
					
					if(recieved instanceof TurnPacket){
						TurnPacket tp = (TurnPacket) recieved;
						System.out.println(tp.getMessage());
						ClientUI.CurrentPlayerTurn = tp.getCurrentPlayersTurn();
					}
					else if(recieved instanceof MovePacket){
						MovePacket mp = (MovePacket) recieved;
						System.out.println("IsValid: " + mp.isValid + mp.getMessage());
						if(mp.isValid){
							Piece movedpiece = Client.board.getPiece(mp.getFrom());
							movedpiece.move(mp.getTo());
							ClientUI.updateDisplay();
						}
						else{
							new PopUp(PopupType.INVALID_MOVE);
						}
					}
					else if(recieved instanceof Packet){
						p = (Packet) recieved;
						System.out.println(p.getMessage());
							if(p.getMessage().equals("white")){
								Client.color = PlayerColor.WHITE;
								new PopUp("Player is White");
							}
							else if(p.getMessage().equals("black")){
								Client.color = PlayerColor.BLACK;
								new PopUp("Player is Black");
							}
							else if(p.getMessage().equals("check")){
								new PopUp(PopupType.CHECK);
							}
							else if(p.getMessage().equals("checkmate")){
								new PopUp(PopupType.CHECKMATE);
							}
							else{
								System.out.println("Invalid type of packet");
							}
					}
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}