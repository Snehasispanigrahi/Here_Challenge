package com.marsrover.here.MarsRover.Model;

import com.marsrover.here.MarsRover.Service.Util;

public class Coordinate {
	private int x;
	private int y;

	@Override
	public String toString() {
		return x + " " + y;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		if (((Coordinate) obj).getX() == getX() && ((Coordinate) obj).getY() == getY())
			return true;
		else
			return false;
	}

	public static Coordinate parseCoordinate(String input) {
		String[] cooridnates = Util.splitByWhitespace(input);
		Coordinate coordinate = new Coordinate(Integer.parseInt(cooridnates[0]), Integer.parseInt(cooridnates[1]));

		return coordinate;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Coordinate(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public boolean withIn(Coordinate floor, Coordinate ceil) {
		if ((this.getX() >= floor.getX() && this.getX() <= ceil.getX())
				&& (this.getY() >= floor.getY() && this.getY() <= ceil.getY()))
			return true;
		return false;
	}

}
