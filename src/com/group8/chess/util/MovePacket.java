package com.group8.chess.util;

public class MovePacket extends Packet{
	
	private Coordinate from,to;
	public boolean isValid;
	
	public MovePacket(boolean isValid){
		isValid =  isValid;
	}
	public MovePacket(Coordinate from, Coordinate to) {
		// TODO Auto-generated constructor stub
		super("Piece moved from " + from.toString() + " to " + to.toString());
		this.from = from;
		this.to = to;
		isValid = true;
	}
	
	public MovePacket() {
		// TODO Auto-generated constructor stub
	}

	public Coordinate getFrom() {
		return from;
	}

	public void setFrom(Coordinate from) {
		this.from = from;
	}

	public Coordinate getTo() {
		return to;
	}

	public void setTo(Coordinate to) {
		this.to = to;
	}

}
