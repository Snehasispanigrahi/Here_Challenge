package com.marsrover.here.MarsRover.Model;

public class Rover {
	private Position position;
	private final Plateu plateu;

	public Rover(Plateu plateu) {
		super();
		this.plateu = plateu;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		if (position.getCoordinate().getX() < 0 || position.getCoordinate().getY() < 0)
			throw new IllegalArgumentException("Invalid initial position");
		this.position = position;
	}

	public void setInstruction(String instructions) {

		int sameInstructionCount = 0;
		Instruction lastInstruction = null;

		for (int i = 0; i < instructions.toCharArray().length; i++) {
			char currentInstructionChar = instructions.toCharArray()[i];
			if (!Instruction.isValid(currentInstructionChar))
				throw new IllegalArgumentException("Instruction not recognized");
			Instruction currentInstruction = Instruction.valueOf(Character.toString(currentInstructionChar));

			if (i == 0)
				lastInstruction = currentInstruction;
			if (currentInstruction != lastInstruction) {
				spinOrMove(sameInstructionCount, lastInstruction);
				sameInstructionCount = 1;
				lastInstruction = currentInstruction;
			} else {
				// Same instruction as previous, store the repetition
				sameInstructionCount++;
			}

			if (i == instructions.toCharArray().length - 1) {
				spinOrMove(sameInstructionCount, lastInstruction);
			}

		}
	}

	/**
	 * @param sameInstructionCount
	 * @param lastInstruction
	 */
	private void spinOrMove(int sameInstructionCount, Instruction lastInstruction) {
		if (lastInstruction.isSpinOperation()) {
			// Spin
			spin(lastInstruction, sameInstructionCount);
		} else {
			// Move fwd
			forwardMove(sameInstructionCount);
		}
	}

	public void forwardMove(int sameInstructionCount) {
		int x = getPosition().getCoordinate().getX();
		int y = getPosition().getCoordinate().getY();

		switch (getPosition().getDirection()) {
		case N:
			if (plateu.insideBoundary(new Coordinate(x, y + sameInstructionCount)))
				y += sameInstructionCount;
			else
				y = plateu.getTopRight().getY();
			break;
		case W:
			if (plateu.insideBoundary(new Coordinate(x - sameInstructionCount, y)))
				x -= sameInstructionCount;
			else
				x = plateu.getBottomLeft().getX();
			break;
		case S:
			if (plateu.insideBoundary(new Coordinate(x, y - sameInstructionCount)))
				y -= sameInstructionCount;
			else
				y = plateu.getBottomLeft().getY();
			break;
		case E:
			if (plateu.insideBoundary(new Coordinate(x + sameInstructionCount, y)))
				x += sameInstructionCount;
			else
				x = plateu.getTopRight().getX();
			break;
		}

		getPosition().getCoordinate().setX(x);
		getPosition().getCoordinate().setY(y);
	}

	public void spin(Instruction lastInstruction, int sameInstructionCount) {
		switch (lastInstruction) {
		case R:
			if (sameInstructionCount > 3)
				sameInstructionCount %= 4;

			if (sameInstructionCount != 0) {
				int fwdMovesPossibleInArray = 0;
				int finalDirectionIndex = -1;
				int index = getPosition().getDirection().getIndex();
				fwdMovesPossibleInArray = (Direction.values().length - 1) - index;
				if (sameInstructionCount <= fwdMovesPossibleInArray)
					finalDirectionIndex = index + sameInstructionCount;
				else
					finalDirectionIndex = sameInstructionCount - fwdMovesPossibleInArray - 1;
				for (Direction d : Direction.values()) {
					if (d.getIndex() == finalDirectionIndex)
						getPosition().setDirection(d);
				}
			}
			break;
		case L:
			if (sameInstructionCount > 3)
				sameInstructionCount %= 4;

			if (sameInstructionCount != 0) {
				int finalDirectionIndex = -1;
				int index = getPosition().getDirection().getIndex();
				if (sameInstructionCount <= index)
					finalDirectionIndex = index - sameInstructionCount;
				else {
					int possibleBackwardMoves = index;
					if (possibleBackwardMoves < 0)
						possibleBackwardMoves = 0;
					sameInstructionCount -= possibleBackwardMoves;
					finalDirectionIndex = Direction.values().length - sameInstructionCount;
				}
				for (Direction d : Direction.values()) {
					if (d.getIndex() == finalDirectionIndex)
						getPosition().setDirection(d);
				}
			}
			break;
		}

	}

	public Plateu getPlateu() {
		return plateu;
	}
}
