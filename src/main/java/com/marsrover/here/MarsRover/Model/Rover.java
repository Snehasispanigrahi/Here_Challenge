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
		char lastInstruction = 0;
		
		for (int i = 0; i < instructions.toCharArray().length; i++) {
			char currentInstruction = instructions.toCharArray()[i];
			
			if(i == 0) lastInstruction = currentInstruction; 
			if(Instructions.isValid(currentInstruction)) {
				if(currentInstruction != lastInstruction) {
					spinOrMove(sameInstructionCount, lastInstruction);
					sameInstructionCount = 1;
					lastInstruction = currentInstruction;
				} else {
					//Same instruction as previous, store the repetition
					sameInstructionCount++;
				}
				
				if (i == instructions.toCharArray().length - 1) {
					spinOrMove(sameInstructionCount, lastInstruction);
				}
			}
			
		}
	}

	/**
	 * @param sameInstructionCount
	 * @param lastInstruction
	 */
	private void spinOrMove(int sameInstructionCount, char lastInstruction) {
		if(Instructions.isSpinOperation(lastInstruction)) {
			//Spin
			spin(lastInstruction, sameInstructionCount);
		} else {
			//Move fwd
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

	final static char[] NESW = { 'N', 'E', 'S', 'W' };// Right movement sorted
	
	public void spin(char lastInstruction, int sameInstructionCount) {
		if(lastInstruction == 'R') {
			if(sameInstructionCount > 3)
				sameInstructionCount %= 4;
			
			if(sameInstructionCount != 0) {
				int fwdMovesPossibleInArray = 0;
				int finalDirectionIndex = -1;
				for (int i = 0; i < NESW.length; i++) {
					if (getPosition().getDirection().toString().equals(Character.toString(NESW[i]))) {
						fwdMovesPossibleInArray = (NESW.length - 1) - i;
						if (sameInstructionCount <= fwdMovesPossibleInArray)
							finalDirectionIndex = i + sameInstructionCount;
						else
							finalDirectionIndex = sameInstructionCount - fwdMovesPossibleInArray - 1;

						break;
					}
				}
				Direction currentDirection = Direction.valueOf(Character.toString(NESW[finalDirectionIndex]));
				getPosition().setDirection(currentDirection);
			}
		} else if(lastInstruction == 'L') {
			if (sameInstructionCount > 3)
				sameInstructionCount %= 4;
			if (sameInstructionCount != 0) {
				int finalDirectionIndex = -1;
				for (int i = 0; i < NESW.length; i++) {
					if (getPosition().getDirection().toString().equals(Character.toString(NESW[i]))) {
						if (sameInstructionCount <= i)
							finalDirectionIndex = i - sameInstructionCount;
						else {
							int possibleBackwardMoves = i;
							if (possibleBackwardMoves < 0)
								possibleBackwardMoves = 0;
							sameInstructionCount -= possibleBackwardMoves;
							finalDirectionIndex = NESW.length - sameInstructionCount;
						}

						break;
					}
				}
				Direction currentDirection = Direction.valueOf(Character.toString(NESW[finalDirectionIndex]));
				getPosition().setDirection(currentDirection);
			}
		}
	}
	
	public Plateu getPlateu() {
		return plateu;
	}
}
