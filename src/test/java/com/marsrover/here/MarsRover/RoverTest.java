package com.marsrover.here.MarsRover;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.marsrover.here.MarsRover.Model.Coordinate;
import com.marsrover.here.MarsRover.Model.Direction;
import com.marsrover.here.MarsRover.Model.Instruction;
import com.marsrover.here.MarsRover.Model.Plateu;
import com.marsrover.here.MarsRover.Model.Position;
import com.marsrover.here.MarsRover.Model.Rover;

public class RoverTest {

	Rover rover;
	Position initialPosition;
	Coordinate coordinate;
	Direction direction;

	Position expectedFinalPosition;
	
	Plateu plateu;

	@Before
	public void setup() {
		setup(null, null);
	}
	
	public void setup(Direction direction, Coordinate coordinate) {
		initialPosition = new Position();
		if(coordinate == null)
			coordinate = new Coordinate(3, 3);
		if(direction == null)
			direction = Direction.E;
		initialPosition.setCoordinate(coordinate);
		initialPosition.setDirection(direction);
		
		plateu = Plateu.getInstance(new Coordinate(5, 5));
		rover = new Rover(plateu);
		rover.setPosition(initialPosition);
	}

	@Test
	public void testRoverNotNull() {
		assertNotNull(rover);
	}

	@Test
	public void testRoverPositionNotNull() {
		assertNotNull(rover.getPosition().getCoordinate());
		assertNotNull(rover.getPosition().getDirection());
		assertNotNull(rover.getPosition());
	}
	
	@Test
	public void testRoverPlateuNotNull() {
		assertNotNull(rover.getPlateu());
	}
	
	@Test
	public void testRoverFinalPosition_1() {
		setup(Direction.N, new Coordinate(1, 2));
		rover.setInstruction("LMLMLMLMM");
		
		expectedFinalPosition = new Position();
		coordinate = new Coordinate(1, 3);
		direction = Direction.N;
		expectedFinalPosition.setCoordinate(coordinate);
		expectedFinalPosition.setDirection(direction);

		assertEquals(expectedFinalPosition, rover.getPosition());
	}
	
	@Test
	public void testRoverFinalPosition_2() {
		setup(Direction.E, new Coordinate(3, 3));
		rover.setInstruction("MMRMMRMRRM");
		
		expectedFinalPosition = new Position();
		coordinate = new Coordinate(5, 1);
		direction = Direction.E;
		expectedFinalPosition.setCoordinate(coordinate);
		expectedFinalPosition.setDirection(direction);

		assertEquals(expectedFinalPosition, rover.getPosition());
	}
	
	@Test
	public void testRoverFinalPosition_3() {
		setup(Direction.N, new Coordinate(2, 2));
		rover.setInstruction("LMMMMMMLMMMMM");
		
		expectedFinalPosition = new Position();
		coordinate = new Coordinate(0, 0);
		direction = Direction.S;
		expectedFinalPosition.setCoordinate(coordinate);
		expectedFinalPosition.setDirection(direction);

		assertEquals(expectedFinalPosition, rover.getPosition());
	}

	@Test
	public void testRoverSpin() {
		setup();
		rover.spin(Instruction.R, 3);
		assertEquals("N", rover.getPosition().getDirection().toString());
	}

	@Test
	public void testRoverMoveFwd() {
		setup();
		rover.forwardMove(2);
		coordinate = new Coordinate(5, 3);
		assertEquals(coordinate, rover.getPosition().getCoordinate());
	}

	@Test
	public void testRoverMoveFwdBeyondMaxX() {
		setup(Direction.E, null);
		rover.forwardMove(3);
		assertEquals(plateu.getTopRight().getX(), rover.getPosition().getCoordinate().getX());
	}

	@Test
	public void testRoverMoveFwdBeyondMaxY() {
		setup(Direction.N, null);
		rover.forwardMove(3);
		assertEquals(plateu.getTopRight().getY(), rover.getPosition().getCoordinate().getY());
	}

	@Test
	public void testRoverMoveFwdBeyondMinY() {
		setup(Direction.S, null);
		rover.forwardMove(4);
		assertEquals(plateu.getBottomLeft().getY(), rover.getPosition().getCoordinate().getY());
	}

	@Test
	public void testRoverMoveFwdBeyondMinX() {
		setup(Direction.W, null);
		rover.forwardMove(4);
		assertEquals(plateu.getBottomLeft().getX(), rover.getPosition().getCoordinate().getX());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRoverInvalidInitialPosition() {
		setup(null, new Coordinate(-1, 0));
	}
}
