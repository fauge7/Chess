package com.group8.chess.piece;

import java.util.List;

import com.group8.chess.util.Board;
import com.group8.chess.util.Compass;
import com.group8.chess.util.Coordinate;
import com.group8.chess.util.PlayerColor;
import com.group8.chess.util.Threat;

public class King extends Piece {

	public King(PlayerColor playerColor, Board board, Coordinate pos) {
		super(playerColor, board, pos);
	}

	@Override
	public void getThreats(Threat threat) {
		Coordinate coordinate;
		for (Compass compass: Compass.EIGHT) {
			coordinate = getPos().offset(compass);
			if (getBoard().inBounds(coordinate)) threat.getPos().add(coordinate);
		}
	}

	@Override
	public void buildMoveList(List<Coordinate> bounds, List<Coordinate> invalid) {
		Coordinate coordinate;
		getMoveList().clear();
		for (Compass dir: Compass.EIGHT){
			coordinate = getPos().offset(dir);
			if (getBoard().inBounds(coordinate) && getBoard().getPlayerColor(coordinate) != getPlayerColor()) 
					getMoveList().add(coordinate);
		}
		//Castling
		if (!hasMoved()) {
			if (getUnmovedRook(getPos(),Compass.E) != null) getMoveList().add(getPos().offset(2,0));
			if (getUnmovedRook(getPos(),Compass.W) != null) getMoveList().add(getPos().offset(-2,0));
		}
		
		addLimits(bounds, invalid);
	}
	
	// Check for Castling: Unmoved player rook in direction without other pieces in the way.
	private Piece getUnmovedRook(Coordinate coor, Compass dir) {
		coor = coor.offset(dir);
		while (getBoard().inBounds(coor)) {
			Piece piece = getBoard().getPiece(coor);
			if (piece != null) {
				// Is rook, same color, and hasn't moved.
				if ((piece instanceof Rook) && getPlayerColor() == piece.getPlayerColor() && !piece.hasMoved()) {
					return piece;
				} else {
					return null;
				}
			}
			coor = coor.offset(dir);
		}
		return null;
	}
	
	// King Castle
	@Override
	public void move(int x, int y) {
		move(new Coordinate(x, y));
	}

	@Override
	public void move(Coordinate coor) {
		// Check for castling: move by 2
		if (Math.abs(coor.getX() - getPos().getX()) == 2) {
			Compass dir = (coor.getX() - getPos().getX() > 0 ? Compass.E : Compass.W); 
			Piece rook = getUnmovedRook(getPos(),dir);
			if (rook != null) {
				rook.move(getPos().offset(dir));
			}
		}
		super.move(coor);
		
	}
	
	

}
