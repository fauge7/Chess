package com.group8.chess.piece;

import com.group8.chess.util.Board;
import com.group8.chess.util.Compass;
import com.group8.chess.util.Coordinate;
import com.group8.chess.util.PlayerColor;
import com.group8.chess.util.Threat;

public class Bishop extends Piece {

	public Bishop(PlayerColor playerColor, Board board, Coordinate pos) {
		super(playerColor, board, pos);
	}

	@Override
	public void getThreats(Threat threat) {
		getRayThreats(threat, Compass.ORDINAL);
	}

}
