package com.marsrover.here.MarsRover.Model;

import com.marsrover.here.MarsRover.Service.Util;

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

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		if (((Position) obj).getCoordinate().equals(getCoordinate()) && ((Position) obj).getDirection() == getDirection())
			return true;
		else
			return false;
	}

	public Coordinate getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}
}
