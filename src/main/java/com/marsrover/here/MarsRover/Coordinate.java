package com.marsrover.here.MarsRover;

public class Coordinate {
	int x;
	int y;

	public Coordinate(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return x + " " + y;
	}

	public static Coordinate parseCoordinate(String input) {
		String[] cooridnates = Util.splitByWhitespace(input);
		Coordinate coordinate = new Coordinate(Integer.parseInt(cooridnates[0]), Integer.parseInt(cooridnates[1]));

		return coordinate;
	}

}
