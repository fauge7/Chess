package com.group8.chess.piece;

import java.util.List;

import com.group8.chess.util.Board;
import com.group8.chess.util.Compass;
import com.group8.chess.util.Coordinate;
import com.group8.chess.util.PlayerColor;
import com.group8.chess.util.Threat;

public class Rook extends Piece {

	public Rook(PlayerColor playerColor, Board board, Coordinate pos) {
		super(playerColor, board, pos);
	}

	@Override
	public void getThreats(Threat threat) {
		getRayThreats(threat, Compass.CARDINAL);
	}

	@Override
	public void buildMoveList(List<Coordinate> bounds, List<Coordinate> invalid) {
		buildRayMoveList(Compass.CARDINAL, bounds, invalid);
	}


}
