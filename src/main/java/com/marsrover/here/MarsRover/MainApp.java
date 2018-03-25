package com.marsrover.here.MarsRover;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.marsrover.here.MarsRover.Model.Coordinate;
import com.marsrover.here.MarsRover.Model.Plateu;
import com.marsrover.here.MarsRover.Model.Position;
import com.marsrover.here.MarsRover.Model.Rover;
import com.marsrover.here.MarsRover.Service.Util;

/**
 * @author Snehashish
 *
 */
public class MainApp {
	final static char[] NESW = { 'N', 'E', 'S', 'W' };// Right movement sorted

	public static void main(String[] args) throws FileNotFoundException {
		if (args[0] == null || args[0].isEmpty())
			throw new IllegalArgumentException("No data in file");
		System.out.println("Reading file: " + args[0]);
		// Plateu boundary right top
		String boundary_Input;
		// 1. Rover's initial position (Co-ordinate, Direction)
		String roverPosition_Input;
		// 2. Instruction String
		String roverInstruction_Input;
		File file = new File(args[0]);
		try {
			Scanner sc = new Scanner(file);
			if (sc.hasNextLine())
				boundary_Input = sc.nextLine();
			else
				throw new IllegalArgumentException("Invalid data!");
			while (sc.hasNextLine()) {
				roverPosition_Input = sc.nextLine();
				if (sc.hasNextLine())
					roverInstruction_Input = sc.nextLine();
				else
					throw new IllegalArgumentException("Invalid data!");
				System.out.println("Rover initial position: " + roverPosition_Input + ", Instruction: " + roverInstruction_Input);
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
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException("Invalid file input!");
		}
	}
}
