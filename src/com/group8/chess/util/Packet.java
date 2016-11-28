package com.group8.chess.util;

public class Packet {

	private String message;
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
