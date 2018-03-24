package com.marsrover.here.MarsRover;

public class Coordinate {
	private int x;
	private int y;

	@Override
	public String toString() {
		return x + " " + y;
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

}
