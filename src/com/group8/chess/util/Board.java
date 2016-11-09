package com.group8.chess.util;

import java.util.ArrayList;
import java.util.List;

import com.group8.chess.piece.*;

public class Board {
	private List<Piece> pieces = new ArrayList<>();
	private int xSize, ySize;
	private CheckState checkState;
	
	/** 
	 * Creates a standard 8x8 board.
	 */
	public Board() {
		this(8,8);
		checkState = CheckState.NONE;
		
		// Default Board state
		for (int y = 0; y < 8; y++) {
			pieces.add(new Pawn(PlayerColor.WHITE, this, new Coordinate(1,y)));
			pieces.add(new Pawn(PlayerColor.BLACK, this, new Coordinate(7,y)));
		}
		
		pieces.add(new Rook(PlayerColor.WHITE, this, new Coordinate(0,0)));
		pieces.add(new Knight(PlayerColor.WHITE, this, new Coordinate(1,0)));
		pieces.add(new Bishop(PlayerColor.WHITE, this, new Coordinate(2,0)));
		pieces.add(new Queen(PlayerColor.WHITE, this, new Coordinate(3,0)));
		pieces.add(new King(PlayerColor.WHITE, this, new Coordinate(4,0)));
		pieces.add(new Bishop(PlayerColor.WHITE, this, new Coordinate(5,0)));
		pieces.add(new Knight(PlayerColor.WHITE, this, new Coordinate(6,0)));
		pieces.add(new Rook(PlayerColor.WHITE, this, new Coordinate(7,0)));
		
		pieces.add(new Rook(PlayerColor.BLACK, this, new Coordinate(0,7)));
		pieces.add(new Knight(PlayerColor.BLACK, this, new Coordinate(1,7)));
		pieces.add(new Bishop(PlayerColor.BLACK, this, new Coordinate(2,7)));
		pieces.add(new Queen(PlayerColor.BLACK, this, new Coordinate(3,7)));
		pieces.add(new King(PlayerColor.BLACK, this, new Coordinate(4,7)));
		pieces.add(new Bishop(PlayerColor.BLACK, this, new Coordinate(5,7)));
		pieces.add(new Knight(PlayerColor.BLACK, this, new Coordinate(6,7)));
		pieces.add(new Rook(PlayerColor.BLACK, this, new Coordinate(7,7)));
		
	}
	
	/**
	 * Creates a board.
	 * @param width x size.
	 * @param height y size.
	 */
	public Board(int width, int height) {
		 xSize = 8;
		 ySize = 8;
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
		for (Piece piece: pieces) {
			if (piece.getPos().equals(coor)) return piece;
		}
		return null;
	}
	
	public PlayerColor getColor(Coordinate coor) {
		if (!inBounds(coor)) return PlayerColor.NONE;
		for (Piece piece: pieces) {
			if (piece.getPos().equals(coor)) return piece.getPlayerColor();
		}
		return PlayerColor.NONE;
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
		return (coor.getX() >= 0 && coor.getX() < xSize && 
				coor.getY() >= 0 && coor.getY() < ySize);
	}
	
	/**
	 * Builds a moveList for all pieces.
	 * @param color
	 */
	public void buildMoveList(PlayerColor color) {

		// Build threat list to determine moves limits first.
		Threat threat = new Threat(this);
		for (Piece piece: getPieces(color.getOpponent())) {
			piece.getThreats(threat);
		}
		
		List<Piece> colorPieces = getPieces(color);
		//TODO
		
	}

	public List<Piece> getPieces() {
		return pieces;
	}

	public List<Piece> getPieces(PlayerColor color) {
		List<Piece> result = new ArrayList<>();
		for (Piece piece: pieces) {
			if (piece.getPlayerColor() == color) result.add(piece);
		}
		return result;
	}

	public void removePiece(Piece piece) {
		if (pieces.contains(piece)) {
			pieces.remove(pieces.indexOf(piece));
		}
	}
}
