package com.marsrover.here.MarsRover.Model;

public enum Direction {
	N(0), E(1), S(2), W(3);
	private int index;

	private Direction(int index) {
		this.setIndex(index);
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
