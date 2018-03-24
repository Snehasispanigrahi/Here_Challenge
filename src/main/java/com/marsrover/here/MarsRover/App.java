package com.marsrover.here.MarsRover;

import java.util.Scanner;

/**
 * @author Snehashish
 *
 */
public class App {
	final static char[] NESW = { 'N', 'E', 'S', 'W' };// Right movement sorted

	public static void main(String[] args) {
		// boundary ip required
		String instructions = "MMRMMRMRRM";
		int x = 3;
		int y = 3;
		char currentOrientation = 'E';

		// Get final coordinate given boundary, initial position, instruction.
		printFinalCoordinate(x, y, currentOrientation, instructions);

	}

	private static void printFinalCoordinate(int x, int y, char currentOrientation, String instructions) {
		int sameMoveSpinCount = 0;// Rover's orientations
		char lastIP = 0;

		for (int j = 0; j < instructions.toCharArray().length; j++) {
			char c = instructions.toCharArray()[j];
			if (c == 'L' || c == 'R' || c == 'M') {
				if (c != lastIP) {
					if (lastIP == 'L' || lastIP == 'R') {
						currentOrientation = findTheOrientation(lastIP, sameMoveSpinCount, currentOrientation);
						System.out.println("Changed Orientation to: " + currentOrientation);
					} else if (lastIP == 'M') {
						switch (currentOrientation) {
						case 'N':
							y += sameMoveSpinCount;
							break;
						case 'W':
							x -= sameMoveSpinCount;
							break;
						case 'S':
							y -= sameMoveSpinCount;
							break;
						case 'E':
							x += sameMoveSpinCount;
							break;
						}
						if (currentOrientation == 'N' || currentOrientation == 'S')
							System.out.println("Moved to y: " + y);
						if (currentOrientation == 'W' || currentOrientation == 'E')
							System.out.println("Moved to x: " + x);
					}
					sameMoveSpinCount = 1;
					lastIP = c;
				} else {
					sameMoveSpinCount++;
					System.out.println("Instruction: " + c + ", occurence: " + sameMoveSpinCount);
				}

				if (j == instructions.toCharArray().length - 1) {
					System.out.println("Last Instruction: " + lastIP);
					if (lastIP == 'L' || lastIP == 'R') {
						currentOrientation = findTheOrientation(lastIP, sameMoveSpinCount, currentOrientation);
						System.out.println("Changed Orientation to: " + currentOrientation);
					} else if (lastIP == 'M') {
						switch (currentOrientation) {
						case 'N':
							y += sameMoveSpinCount;
							break;
						case 'W':
							x -= sameMoveSpinCount;
							break;
						case 'S':
							y -= sameMoveSpinCount;
							break;
						case 'E':
							x += sameMoveSpinCount;
							break;
						}
						if (currentOrientation == 'N' || currentOrientation == 'S')
							System.out.println("Moved to y: " + y);
						if (currentOrientation == 'W' || currentOrientation == 'E')
							System.out.println("Moved to x: " + x);
					}
				}
			} else {
				throw new IllegalArgumentException();
			}
		}
		System.out.println(x + " " + y + " " + currentOrientation);
	}

	private static char findTheOrientation(char lastIP, int sameMoveCount, char currentOrientation) {
		// decide the orientation, same position
		if (lastIP == 'R') {
			if (sameMoveCount > 3)
				sameMoveCount %= 4;

			if (sameMoveCount != 0) {
				int movesPossibleFwdInArray = 0;
				int finalOrientationIndex = -1;
				for (int i = 0; i < NESW.length; i++) {
					if (NESW[i] == currentOrientation) {
						movesPossibleFwdInArray = (NESW.length - 1) - i;
						if (sameMoveCount <= movesPossibleFwdInArray)
							finalOrientationIndex = i + sameMoveCount;
						else
							finalOrientationIndex = sameMoveCount - movesPossibleFwdInArray - 1;

						break;
					}
				}
				currentOrientation = NESW[finalOrientationIndex];
			}
		} else if (lastIP == 'L') {
			if (sameMoveCount > 3)
				sameMoveCount %= 4;
			if (sameMoveCount != 0) {
				int finalOrientationIndex = -1;
				for (int i = 0; i < NESW.length; i++) {
					if (NESW[i] == currentOrientation) {
						if (sameMoveCount <= i)
							finalOrientationIndex = i - sameMoveCount;
						else {
							int possibleBackwardMoves = i - sameMoveCount;
							if (possibleBackwardMoves < 0)
								possibleBackwardMoves = 0;
							sameMoveCount -= possibleBackwardMoves;
							finalOrientationIndex = NESW.length - sameMoveCount;
						}

						break;
					}
				}
				currentOrientation = NESW[finalOrientationIndex];
			}
		}
		return currentOrientation;
	}
}
