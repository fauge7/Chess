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

	private Coordinate pos;
	private boolean hasMoved;
	private Image image = null;	
	private List<Coordinate> moveList = new ArrayList<>();	

	public Piece(PlayerColor playerColor, Board board, Coordinate pos) {
		this.playerColor = playerColor;
		this.board = board;
		this.pos = pos;
	}
	
	public PlayerColor getPlayerColor() {
		return playerColor;
	}
	
	public void setPlayerColor(PlayerColor playerColor) {
		this.playerColor = playerColor;
	}

	public Board getBoard() {
		return board;
	}

	public Coordinate getPos() {
		return pos;
	}
	
	public void move(Coordinate pos) {
		this.pos = pos;
		hasMoved = true;
	}
	
	public Image getImage() {
		return image;
	}
	
	public void setImage(Image image) {
		this.image = image;
	}
	
	public boolean hasMoved() {
		return hasMoved;
	}

	public List<Coordinate> getMoveList() {
		return moveList;
	}
	
	abstract public void getThreats(Threat threat);
	
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
			if (board.getColor(coor) == getPlayerColor()) {
				return threat;
			}
			if (board.getColor(coor) != PlayerColor.NONE) {
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

}
