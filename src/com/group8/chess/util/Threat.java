package com.group8.chess.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.group8.chess.piece.Piece;

/**
 * Defines a list of threats:<br>
 * Positions a king can not move to.<br>
 * Direct threats to a king (check).<br>
 * Indirect threat (if another piece moves, the king would be in check).
 */
public class Threat {
	private Board board;
	private List<Coordinate> pos = new ArrayList<>();
	private List<List<Coordinate>> direct = new ArrayList<>();
	private Map<Piece,List<Coordinate>> indirect = new HashMap<>();
	private Piece bound = null;
		
	public Threat(Board board) {
		this.board = board;
	}
	
	public Piece getBound() {
		return bound;
	}

	public void setBound(Piece bound) {
		this.bound = bound;
	}

	public List<Coordinate> getPos() {
		return pos;
	}

	public List<List<Coordinate>> getDirect() {
		return direct;
	}

	public Map<Piece,List<Coordinate>> getIndirect() {
		return indirect;
	}
	
	/**
	 * Adds a location to the threat list if it's within bounds of the 
	 * 
	 * @param coor
	 * @return True if within bounds and added, false 
	 */
	public boolean addPos(Coordinate coor) {
		if (!board.inBounds(coor)) return false;
		pos.add(coor);
		return true;
	}

}
