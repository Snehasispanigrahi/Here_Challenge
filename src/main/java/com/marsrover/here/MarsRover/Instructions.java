package com.marsrover.here.MarsRover;

public enum Instructions {
	L, R, M;

	public static boolean isValid(char instruction) {
		if (instruction == 'L' || instruction == 'M' || instruction == 'R')
			return true;
		return false;
	}

	public static boolean isSpinOperation(char instruction) {
		if (instruction == 'L' || instruction == 'R')
			return true;
		return false;
	}
}
