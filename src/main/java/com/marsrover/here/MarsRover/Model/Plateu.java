package com.marsrover.here.MarsRover.Model;

public final class Plateu {
	private Coordinate topRight;
	private final Coordinate bottomLeft = new Coordinate(0, 0);

	//Singleton
	private static Plateu plateu;

	private Plateu() {
	}

	private Plateu(Coordinate topRight) {
		super();
		this.setTopRight(topRight);
	}

	public boolean insideBoundary(Coordinate roverPosition) {
		if (roverPosition.withIn(getBottomLeft(), getTopRight()))
			return true;
		return false;
	}
	
	public static Plateu getInstance(Coordinate topRight) {
		if (plateu == null)
			plateu = new Plateu(topRight);
		return plateu;
	}

	public Coordinate getTopRight() {
		return topRight;
	}

	private void setTopRight(Coordinate topRight) {
		this.topRight = topRight;
	}

	public Coordinate getBottomLeft() {
		return bottomLeft;
	}

}
