package com.group8.chess.util;

import java.util.ArrayList;
import java.util.List;

import com.group8.chess.piece.King;
import com.group8.chess.piece.Piece;

public class Board {
	private Piece position[][];
	private CheckState checkState;
	
	/** 
	 * Creates a standard 8x8 board.
	 */
	public Board() {
		this(8,8);
		checkState = CheckState.NONE;
	}
	
	/**
	 * Creates a board.
	 * @param width x size.
	 * @param height y size.
	 */
	public Board(int width, int height) {
		 position = new Piece[width][height];		
	}
	
	public CheckState getCheckState() {
		return checkState;
	}
	
	/**
	 * Gets piece at a position,
	 * @param x
	 * @param y
	 * @return Piece, or null if no piece is there at the specified location. 
	 */
	public Piece getPiece(int x, int y) {
		return getPiece(new Coordinate(x,y));
		
	}
	
	public Piece getPiece(Coordinate coor) {
		if (!inBounds(coor)) return null;
		return position[coor.getX()][coor.getY()];
	}
	
	public PlayerColor getColor(Coordinate coor) {
		if (position[coor.getX()][coor.getY()] == null) return PlayerColor.NONE;
		return position[coor.getX()][coor.getY()].getPlayerColor();
	}
	
	public boolean isOpponentKing(Coordinate coor, PlayerColor color) {
		return (getPiece(coor) instanceof King) && 
				(getPiece(coor).getPlayerColor() != color);
	}
	
	/**
	 * Checks if a coordinate is within the bounds of the board.
	 * @param coor
	 * @return 
	 */
	public boolean inBounds(Coordinate coor) {
		return (coor.getX() >= 0 && coor.getX() < position.length && 
				coor.getY() >= 0 && coor.getY() < position[coor.getX()].length);
	}
	
	/**
	 * Builds a moveList for all pieces.
	 * @param color
	 */
	public void buildMoveList(PlayerColor color) {
		Threat threat = new Threat(this);
		for (Piece piece: getPieces(color.getOpponent())) {
			piece.getThreats(threat);
		}
		// TODO
	}
	
	public List<Piece> getPieces(PlayerColor color) {
		List<Piece> results = new ArrayList<>();
		for (int x = 0; x < position.length; x++) {
			for (int y = 0; y < position[x].length; y++) {
				if (getColor(new Coordinate(x,y)) == color) {
					results.add(position[x][y]);
				}
			}
		}
		return results;
	}
}
