package com.group8.chess.util;

import com.group8.chess.piece.Piece;

public class Board {
	private Piece position[][] = new Piece[8][8];
	
	public Piece getPiece(int x, int y) {
		return position[x][y];
	}
	
	public Piece getPiece(Coordinate coor) {
		return position[coor.getX()][coor.getY()];
	}
	
}
