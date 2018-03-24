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

	public void setInstruction(String instructions) {
		
		//Read the instruction
		//We will do the same operations at a time
		//So, while reading when we see a change in the instruction
		//then rover will operate on the previous instruction
		
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
			moveFwd(sameInstructionCount);
		}
	}

	private void moveFwd(int sameInstructionCount) {
		int x = getPosition().getCoordinate().getX();
		int y = getPosition().getCoordinate().getY();

		switch (getPosition().getDirection()) {
		case N:
			y += sameInstructionCount;
			break;
		case W:
			x -= sameInstructionCount;
			break;
		case S:
			y -= sameInstructionCount;
			break;
		case E:
			x += sameInstructionCount;
			break;
		}

		getPosition().getCoordinate().setX(x);
		getPosition().getCoordinate().setY(y);
	}

	final static char[] NESW = { 'N', 'E', 'S', 'W' };// Right movement sorted
	
	private void spin(char lastInstruction, int sameInstructionCount) {
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
							int possibleBackwardMoves = i - sameInstructionCount;
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
}
