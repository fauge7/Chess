package com.group8.chess.util;

public class Coordinate {
	private int x, y;

	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public Coordinate offset(int x, int y) {
		return new Coordinate(this.x + x, this.y + y);
	}

	public Coordinate offset(Compass compass) {
		return new Coordinate(this.x + compass.getX(), this.y + compass.getY());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		return (x == ((Coordinate)obj).x) && (y == ((Coordinate)obj).y);
	}

}
