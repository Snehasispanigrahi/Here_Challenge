package com.marsrover.here.MarsRover;

import java.util.Scanner;

/**
 * @author Snehashish
 *
 */
public class App {
	final static char[] NESW = { 'N', 'E', 'S', 'W' };// Right movement sorted

	public static void main(String[] args) {
		// Read from scanner
		Scanner sc = new Scanner(System.in);
		// Plateu boundary right top
		String boundary_Input = sc.nextLine();
		// 1. Rover's initial position (Co-ordinate, Direction)
		String roverPosition_Input = sc.nextLine();
		// 2. Instruction String
		String roverInstruction_Input = sc.nextLine();

		if (Util.notEmpty(boundary_Input) && Util.notEmpty(roverPosition_Input) && Util.notEmpty(roverInstruction_Input)) {
			// Get the co-ordinates out of the boundary co-ordinate and rover position
			Coordinate boundaryCoordinate = Coordinate.parseCoordinate(boundary_Input);
			Position roverInitialPosition = Position.parsePosition(roverPosition_Input);
			Plateu plateu = new Plateu(boundaryCoordinate);
			Rover rover = new Rover(roverInitialPosition);
			rover.setInstruction(roverInstruction_Input);
			System.out.println(rover.getPosition().toString());
		} else {
			throw new IllegalArgumentException("Invalid input");
		}
	}
}
