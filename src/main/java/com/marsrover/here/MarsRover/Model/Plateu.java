package com.marsrover.here.MarsRover.Model;

public final class Plateu {
	Coordinate topRight;
	Coordinate bottomLeft = new Coordinate(0, 0);

	public Plateu(Coordinate topRight) {
		super();
		this.topRight = topRight;
	}
}