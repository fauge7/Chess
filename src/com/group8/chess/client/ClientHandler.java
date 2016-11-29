package com.group8.chess.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.group8.chess.piece.Piece;
import com.group8.chess.util.MovePacket;
import com.group8.chess.util.Packet;
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
					if(recieved instanceof TurnPacket){
						TurnPacket tp = (TurnPacket) recieved;
						System.out.println(tp.getMessage());
						
					}
					else if(recieved instanceof MovePacket){
						MovePacket mp = (MovePacket) recieved;
						System.out.println(mp.getMessage());
						if(!mp.isValid){
							new PopUp(PopupType.INVALID_MOVE);
						}
						else{
							Piece movedpiece = ClientUI.board.getPiece(mp.getFrom());
							Piece toBeRemoved = ClientUI.board.getPiece(mp.getTo());
							ClientUI.board.removePiece(toBeRemoved);
							movedpiece.move(mp.getTo());
							ClientUI.updateDisplay();
						}
					}
					else if(recieved instanceof Packet){
					p = (Packet) recieved;
					System.out.println(p.getMessage());
					}
					else{
						System.out.println("Invalid type of packet");
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
