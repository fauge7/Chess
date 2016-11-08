package com.group8.chess.piece;

import com.group8.chess.util.Board;
import com.group8.chess.util.Compass;
import com.group8.chess.util.Coordinate;
import com.group8.chess.util.PlayerColor;
import com.group8.chess.util.Threat;

public class Knight extends Piece {

	public Knight(PlayerColor playerColor, Board board, Coordinate pos) {
		super(playerColor, board, pos);
	}

	@Override
	public void getThreats(Threat threat) {
		Coordinate coor;
		for (Compass compass: Compass.PRINCIPAL) {
			coor = getPos().offset(compass);
			if (getBoard().inBounds(coor)) threat.getPos().add(coor);
			if (getBoard().isOpponentKing(coor, getPlayerColor())) {
				addDirectThreat(threat, getPos(), coor);
			}
		}
	}

}
