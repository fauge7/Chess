package com.group8.chess.util;

import java.io.Serializable;

public class Packet implements Serializable{

	String message;
	public Packet() {
		// TODO Auto-generated constructor stub
		message = "";
	}
	public Packet(String message){
		this.message = message;
	}
	
	public String getMessage(){ return message;}
	public void setMessage(String message) { this.message = message;}
	
}
