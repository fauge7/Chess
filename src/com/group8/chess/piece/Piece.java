package com.group8.chess.piece;

import java.awt.Image;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import com.group8.chess.util.Board;
import com.group8.chess.util.Compass;
import com.group8.chess.util.Coordinate;
import com.group8.chess.util.PlayerColor;
import com.group8.chess.util.Threat;

public abstract class Piece {
	private PlayerColor playerColor;
	private Board board;

	private Coordinate coordinate;
	private boolean hasMoved;
	private Image image = null;	
	private List<Coordinate> moveList = new ArrayList<>();	

	public Piece(PlayerColor playerColor, Board board, Coordinate pos) {
		this.playerColor = playerColor;
		this.board = board;
		this.coordinate = pos;
	}
	
	public PlayerColor getPlayerColor() {
		return playerColor;
	}

	public Board getBoard() {
		return board;
	}

	public Coordinate getPos() {
		return coordinate;
	}
	
	public void move(int x, int y) {
		move(new Coordinate(x,y));
	}
	
	public void move(Coordinate coordinate) {
		board.removePiece(board.getPiece(coordinate));
		this.coordinate = coordinate;
		hasMoved = true;
	}
	
	public boolean hasMoved() {
		return hasMoved;
	}

	public boolean canMove() {
		return (moveList != null) && (moveList.size() > 0);
	}
	
	public List<Coordinate> getMoveList() {
		return moveList;
	}
	
	public void setMoveList(List<Coordinate> moveList) {
		this.moveList = moveList;
	}

	public Image getImage() {
		return image;
	}
	
	public void setImage(Image image) {
		this.image = image;
	}
	
	
	abstract public void getThreats(Threat threat);
	
	/**
	 * Generate a move list.
	 * @param bounds list of coordinates a piece must move to.
	 * @param invalid list of coordinates a piece can't move to.
	 */
	abstract public void buildMoveList(List<Coordinate> bounds, List<Coordinate> invalid);
	
	/**
	 * Generates a move list based on rays (can move until a location is found).
	 * 
	 * @param directions
	 * @param bounds
	 * @param invalid
	 */
	protected void buildRayMoveList(EnumSet<Compass> directions, List<Coordinate> bounds, List<Coordinate> invalid) {
		getMoveList().clear();
		for (Compass dir: directions) {
			buildRayMoveList(dir);
		}
//		addLimits(bounds, invalid);
	}
	
	private void buildRayMoveList(Compass direction) {
		Coordinate coor = getPos().offset(direction);
		while (board.inBounds(coor) && board.getPlayerColor(coor) != getPlayerColor()) {
			getMoveList().add(coor);
			coor = coor.offset(direction);
		}
	}
	
	/**
	 * Generates a set of threats based on pieces which can move 
	 * any number of spaces in a set of directions, but not jump
	 * over other pieces.
	 * @param directions
	 * @return
	 */
	protected void getRayThreats(Threat threat, EnumSet<Compass> directions) {
		for (Compass dir: directions) {
			getRayThreat(threat, dir);
		}
	}
	
	private Threat getRayThreat(Threat threat, Compass dir) {
		// Runs through to check for direct (check) state, then
		// indirect (Opposing blocking check) threats.
		Piece indirect = null;
		Coordinate coor = getPos().offset(dir);
		List<Coordinate> ray = new ArrayList<>();
		ray.add(getPos());

		while (board.inBounds(coor)) {
			if (indirect == null) threat.addPos(coor);
			ray.add(coor);
			if (board.getPlayerColor(coor) == getPlayerColor()) {
				return threat;
			}
			if (board.getPlayerColor(coor) != PlayerColor.NONE) {
				// Check for Direct Threat
				if (board.getPiece(coor) instanceof King) {
					if (indirect == null) {
						threat.getDirect().add(ray);
					} else {
						threat.getIndirect().put(indirect, ray);
					}
					// King can not move away from a direct threat.
					if (indirect == null) threat.addPos(coor.offset(dir));
					return threat;
				}
				// Start checking for Indirect Threats.
				indirect = board.getPiece(coor);
			}
			coor = coor.offset(dir);	
		}
		return threat;
	}
	
	protected void addDirectThreat(Threat threat, Coordinate from, Coordinate to) {
		List<Coordinate> direct = new ArrayList<>();
		direct.add(getPos());
		direct.add(to);
		threat.getDirect().add(direct);		
	}

	/**
	 * Add coordinate bounds and limits.
	 * @param bounds Reduces moveList to only coordinates within the bounds.
	 * @param invalid Reduces moveList by any invalid coordinates.
	 */
	protected void addLimits(List<Coordinate> bounds, List<Coordinate> invalid) {
		addBounds(bounds);
		addInvalid(invalid);
	}

	/**
	 * @param bounds Reduces moveList to only coordinates within the bounds.
	 */
	protected void addBounds(List<Coordinate> bounds) {
		if (bounds == null) return;
		int i = 0;
		while (i < moveList.size()) {
			if (bounds.contains(moveList.get(i))) {
				i++;
			} else {
				moveList.remove(i);
			}
		}
	}
	
	/**
	 * @param invalid Reduces moveList by any invalid coordinates.
	 */
	protected void addInvalid(List<Coordinate> invalid) {
		if (invalid == null) return;
		for (Coordinate coor: invalid) {
			int index = moveList.indexOf(coor);
			if (index > -1) moveList.remove(index);
		}
	}
	
	/**
	 * Returns a string value for a piece's name and color.
	 * Ex: White Queen would be WQu
	 * @return
	 */
	@Override
	public String toString() {
		return getPlayerColor().name().charAt(0) + 
				getClass().getSimpleName().substring(0, 2);
	}

}
