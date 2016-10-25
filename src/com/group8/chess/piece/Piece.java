package com.group8.chess.piece;

import java.awt.Image;

import com.group8.chess.util.Coordinate;
import com.group8.chess.util.PlayerColor;

public class Piece {
	private PlayerColor playerColor;
	private Coordinate pos;
	private boolean moved = false;
	private Image image = null;	

	public Piece(PlayerColor playerColor, Coordinate pos) {
		this.playerColor = playerColor;
		this.pos = pos;
	}
	
	public PlayerColor getPlayerColor() {
		return playerColor;
	}
	
	public void setPlayerColor(PlayerColor playerColor) {
		this.playerColor = playerColor;
	}
	
	public Coordinate getPos() {
		return pos;
	}
	
	public void move(Coordinate pos) {
		this.pos = pos;
		moved = true;
	}
	
	public Image getImage() {
		return image;
	}
	
	public void setImage(Image image) {
		this.image = image;
	}
	
	public boolean hasMoved() {
		return moved;
	}

}
