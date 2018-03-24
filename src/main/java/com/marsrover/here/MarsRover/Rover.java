package com.marsrover.here.MarsRover;

public class Rover {
	private Position position;

	public Rover(Position initialPosition) {
		super();
		this.position = initialPosition;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
	
	public Position spin(Direction direction) {
		
		return position;
	}
	
	public Position forwardMove(int moves) {
		
		return position;
	}

	public void setInstruction(String instruction) {
		//
	}
}
