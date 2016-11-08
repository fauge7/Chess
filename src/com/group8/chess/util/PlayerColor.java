package com.group8.chess.util;

public enum PlayerColor {
	WHITE(1),
	BLACK(-1),
	NONE(0);
	
	private int forward;
	
	PlayerColor(int forward) {
		this.forward = forward;
	}
	
	public int getForward() {
		return this.forward;
	}
	
	@SuppressWarnings("incomplete-switch")
	public PlayerColor getOpponent() {
		switch(this) {
			case WHITE: return BLACK;
			case BLACK: return WHITE;
		}
		return NONE;
	}
	
}
