package com.marsrover.here.MarsRover.Model;

public enum Instruction {
	L(OperationType.Spin), R(OperationType.Spin), M(OperationType.Step);

	private OperationType type;

	private Instruction(OperationType type) {
		this.type = type;
	}

	public static boolean isValid(char instructionChar) {
		for (Instruction instruction : Instruction.values()) {
			if (instruction.name().equals(Character.toString(instructionChar)))
				return true;
		}
		return false;
	}

	public boolean isSpinOperation() {
		return this.type == OperationType.Spin ? true : false;
	}
}

enum OperationType {
	Spin, Step;
}