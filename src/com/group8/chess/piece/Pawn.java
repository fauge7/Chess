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

		addLimits(bounds, invalid);
	}

}
