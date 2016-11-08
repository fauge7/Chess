package com.group8.chess.piece;

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
		for (int x = -1; x <= 1; x =+ 2) {
			coor = getPos().offset(x,getPlayerColor().getForward());
			if (getBoard().inBounds(coor)) {
				threat.getPos().add(coor);
				if (getBoard().isOpponentKing(coor, getPlayerColor())) {
					addDirectThreat(threat, getPos(), coor);
				}
			}
		}
	}

}
