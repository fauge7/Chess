package com.group8.chess.util;

import java.util.EnumSet;

public enum Compass {
	N(0,1),
	NNE(1,2),
	NE(1,1),
	ENE(2,1),
	E(1,0),
	ESE(2,-1),
	SE(1,-1),
	SSE(1,-2),
	S(0,-1),
	SSW(-1,-2),
	SW(-1,-1),
	WSW(-2,-1),
	W(-1,0),
	WNW(-2,1),
	NW(-1,1),
	NNW(-1,2);
	
	private final int x, y;
	
	/**
	 * All Cardinal (horizontal and vertical) directions.
	 */
	public static final EnumSet<Compass> CARDINAL = EnumSet.of(
			Compass.N, Compass.E, Compass.S, Compass.W);

	/**
	 * All ordinal (diagonal) directions.
	 */
	public static final EnumSet<Compass> ORDINAL = EnumSet.of(
			Compass.NE, Compass.SE, Compass.SW, Compass.NW);

	/**
	 * All cardinal and ordinal (horizontal, vertical, and diagonal).
	 */
	public static final EnumSet<Compass> EIGHT = EnumSet.of(
			Compass.N, Compass.E, Compass.S, Compass.W,
			Compass.NE, Compass.SE, Compass.SW, Compass.NW);

	/**
	 * All Principal direction (example: North-by-Northwest).
	 */
	public static final EnumSet<Compass> PRINCIPAL = EnumSet.of(
			Compass.NNE, Compass.ENE, Compass.ESE, Compass.SSE,    
			Compass.SSW, Compass.WSW, Compass.WNW, Compass.NNW); 

	
	Compass(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

}
	
