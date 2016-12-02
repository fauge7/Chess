package com.group8.chess.util;

import java.util.ArrayList;
import java.util.List;

import com.group8.chess.piece.*;

public class Board {
	private List<Piece> pieces;
	public final int width, height;
	private CheckState checkState = CheckState.NONE;
	private Threat threat;
	private Piece passant = null;
	
	/** 
	 * Creates a standard 8x8 board.
	 */
	public Board() {
		this(8,8, new ArrayList<>());
		
		// Default Board state
		for (int x = 0; x < 8; x++) {
			new Pawn(PlayerColor.WHITE, this, new Coordinate(x,1));
			new Pawn(PlayerColor.BLACK, this, new Coordinate(x,6));
		}
		
		new Rook(PlayerColor.WHITE, this, new Coordinate(0,0));
		new Knight(PlayerColor.WHITE, this, new Coordinate(1,0));
		new Bishop(PlayerColor.WHITE, this, new Coordinate(2,0));
		new Queen(PlayerColor.WHITE, this, new Coordinate(3,0));
		new King(PlayerColor.WHITE, this, new Coordinate(4,0));
		new Bishop(PlayerColor.WHITE, this, new Coordinate(5,0));
		new Knight(PlayerColor.WHITE, this, new Coordinate(6,0));
		new Rook(PlayerColor.WHITE, this, new Coordinate(7,0));
		
		new Rook(PlayerColor.BLACK, this, new Coordinate(0,7));
		new Knight(PlayerColor.BLACK, this, new Coordinate(1,7));
		new Bishop(PlayerColor.BLACK, this, new Coordinate(2,7));
		new Queen(PlayerColor.BLACK, this, new Coordinate(3,7));
		new King(PlayerColor.BLACK, this, new Coordinate(4,7));
		new Bishop(PlayerColor.BLACK, this, new Coordinate(5,7));
		new Knight(PlayerColor.BLACK, this, new Coordinate(6,7));
		new Rook(PlayerColor.BLACK, this, new Coordinate(7,7));
		
	}
	
	/**
	 * Creates a board with various sizes and starting positions.
	 * @param width x size.
	 * @param height y size.
	 */
	public Board(int width, int height, List<Piece> pieces) {
		 this.width = width;
		 this.height = height;
		 this.pieces = pieces;
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
	
	public PlayerColor getPlayerColor(Coordinate coor) {
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
	
	public boolean isOpponent(Coordinate coor, PlayerColor color) {
		return (getPiece(coor) != null) && 
				(getPiece(coor).getPlayerColor() != color);
	}
	
	/**
	 * Checks if a coordinate is within the bounds of the board.
	 * @param coor
	 * @return 
	 */
	public boolean inBounds(Coordinate coor) {
		return (coor.getX() >= 0 && coor.getX() < width && 
				coor.getY() >= 0 && coor.getY() < height);
	}
	
	/**
	 * Builds a moveList for all pieces.
	 * @param color Current Player
	 * @return CheckState
	 */
	public CheckState buildMoveList(PlayerColor color) {
		if (!canCheckMate(PlayerColor.WHITE) && !canCheckMate(PlayerColor.BLACK)) {
			return CheckState.STALE_MATE;
		}
		
		// Build threat list to determine moves limits first.
		threat = new Threat(this);
		int moveCount = 0;
		
		for (Piece piece: getPieces(color.getOpponent())) {
			piece.getThreats(threat);
		}
		
		// Build moveLists.
		
		for (Piece piece: getPieces(color)) {
			if (piece instanceof King) {
				piece.buildMoveList(null, new ArrayList<>(threat.getPos()));
				moveCount += piece.getMoveList().size();
			} else {
				// A single direct threat can be blocked or taken if the piece is not
				// already blocking an indirect threat. 
				// 2+ direct threats means only a king can move.
				switch (threat.getDirect().size()) {
				case 0:
					piece.buildMoveList(null, threat.getIndirect(piece));
					moveCount += piece.getMoveList().size();
					break;
				case 1:
					piece.buildMoveList(threat.getDirect().get(0), threat.getIndirect(piece));
					moveCount += piece.getMoveList().size();
					break;
				default:
					piece.setMoveList(null);
					break;
				}
			}				
		}
		
		// Calculate Check State
		if (moveCount == 0) {
			checkState = (threat.getDirect().size() > 0)
					? CheckState.CHECK_MATE
					: CheckState.STALE_MATE;
		} else {
			checkState = (threat.getDirect().size() > 0)
					? CheckState.CHECK
					: CheckState.NONE;
		}
		return checkState;
	}

	// Need at least a King and either 2 other pieces, or one non-Knight.
	private boolean canCheckMate(PlayerColor color) {
		if (getPieces(color).size() > 2) return true; //Over 2 Pieces.
		if (getPieces(color).size() < 2) return false; //Only King left.
		for (Piece piece: getPieces(color)) {
			if (!(piece instanceof King) && !(piece instanceof Knight)) return true;
		}
		return false;
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
		if (piece != null && pieces.contains(piece)) {
			pieces.remove(pieces.indexOf(piece));
		}
	}
	
	public Threat getThreat() {
		return threat;
	}
	
	
	/**
	 * Tracks Pawn movements for en-passant.
	 * @return last moved Pawn if the pawn moved forward two, else null.
	 */
	public Piece getPassant() {
		return passant;
	}

	// Tracks pawn movements for en-passant.
	public void setPassant(Piece piece) {
		passant = piece;
	}

}
