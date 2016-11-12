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
		addLimits(bounds, invalid);
	}
	
	

}
