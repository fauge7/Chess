package com.group8.chess.piece;

import java.util.List;

import com.group8.chess.util.Board;
import com.group8.chess.util.Coordinate;
import com.group8.chess.util.PlayerColor;
import com.group8.chess.util.Threat;

public class Pawn extends Piece {

	public Pawn(PlayerColor playerColor, Board board, Coordinate pos) {
		super(playerColor, board, pos);
	}

	@Override
	public void getThreats(Threat threat) {
		Coordinate coor;
		for (int x = -1; x < 2; x += 2) {
			coor = getPos().offset(x,getPlayerColor().getForward());
			if (getBoard().inBounds(coor)) {
				threat.getPos().add(coor);
				if (getBoard().isOpponentKing(coor, getPlayerColor())) {
					addDirectThreat(threat, getPos(), coor);
				}
			}
		}
	}

	@Override
	public void buildMoveList(List<Coordinate> bounds, List<Coordinate> invalid) {
		Coordinate coor;
		getMoveList().clear();
		
		// Forward if blank
		coor = getPos().offset(0, getPlayerColor().getForward());
		if (getBoard().inBounds(coor) && getBoard().getPiece(coor) == null) getMoveList().add(coor);

		// Forward 2 if blank and has not moved.
		if (!hasMoved()) {
			coor = getPos().offset(0, getPlayerColor().getForward()*2);
			if (getBoard().inBounds(coor) && getBoard().getPiece(coor) == null) getMoveList().add(coor);
		}
		
		// Diagonal forward if opposing piece.
		for (int x = -1; x <= 1; x += 2) {
			coor = getPos().offset(x, getPlayerColor().getForward());
			if (getBoard().isOpponent(coor,getPlayerColor())) getMoveList().add(coor);
		}
		
		// Check for en-passant capture.
		for (int x = -1; x <= 1; x += 2) {
			coor = getPos().offset(x, 0);
			if (getBoard().getPassant() != null && getBoard().getPassant().getPos().equals(coor)) {
				System.out.println("Moved"); //TODO: remove
				getMoveList().add(coor.offset(0,getPlayerColor().getForward()));
			}
		}

		addLimits(bounds, invalid);
	}


	// If a pawn reaches the opponents back row, replace it with a Queen.
	@Override
	public void move(int x, int y) {
		move(new Coordinate(x, y));
	}

	@Override
	public void move(Coordinate coor) {
		// Check for back row.
		if (coor.getY() == (getPlayerColor() == PlayerColor.WHITE ? getBoard().height-1 : 0)) {
			getBoard().removePiece(this);
			getBoard().removePiece(getBoard().getPiece(coor));
			new Queen(getPlayerColor(), getBoard(), coor);
		} else {
			// Check for capture passant.
			if (getBoard().getPassant() != null) {
				Piece offsetPiece = getBoard().getPiece(coor.offset(0,-getPlayerColor().getForward()));
				if (getBoard().getPassant().equals(offsetPiece)) {
					getBoard().getPieces().remove(getBoard().getPassant());
				}
			}
			// Check for moving forward-2 (set up en-passant possiblity).
			if (coor.getY() == getPos().getY() + getPlayerColor().getForward() * 2) {
				super.move(coor); // super.move() first, as it clears passant.
				getBoard().setPassant(this);
			} else {
				super.move(coor);
				getBoard().setPassant(null);
			}
		}
		
	}


}
