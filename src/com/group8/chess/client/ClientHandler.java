package com.group8.chess.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.group8.chess.util.Packet;

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
		}
		finally{
			os = null;
			is = null;
		}
		Packet p;
		Object recieved;
		while(true){
			try {
				while((recieved  = (Object) is.readObject()) != null){
					p = (Packet) recieved;
					System.out.println(p.getMessage());
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
