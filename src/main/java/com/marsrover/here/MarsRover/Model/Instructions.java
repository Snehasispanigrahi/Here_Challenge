package com.marsrover.here.MarsRover.Model;

public enum Instructions {
	L, R, M;

	public static boolean isValid(char instructionChar) {
		for (Instructions instruction : Instructions.values()) {
			if (instruction.name().equals(Character.toString(instructionChar)))
				return true;
		}
		return false;
	}

	public static boolean isSpinOperation(char instruction) {
		if (instruction == 'L' || instruction == 'R')
			return true;
		return false;
	}
}
