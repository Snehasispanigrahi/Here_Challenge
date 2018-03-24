package com.marsrover.here.MarsRover;

public class Position {
	private Coordinate coordinate;
	private Direction direction;

	public static Position parsePosition(String input) {
		String[] position_Str = Util.splitByWhitespace(input);
		if (position_Str.length != 3)
			throw new IllegalArgumentException("Invalid Co-oridantes, direction input");
		Coordinate coordinate = new Coordinate(Integer.parseInt(position_Str[0]), Integer.parseInt(position_Str[1]));
		Direction direction = Direction.valueOf(position_Str[2]);

		Position position = new Position();
		position.setCoordinate(coordinate);
		position.setDirection(direction);

		return position;
	}
	
	@Override
	public String toString() {
		return coordinate.toString() + " " + direction.toString();
	}

	public Coordinate getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	public Direction getdirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}
}
