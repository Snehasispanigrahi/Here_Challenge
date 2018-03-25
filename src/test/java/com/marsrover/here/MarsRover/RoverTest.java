package com.marsrover.here.MarsRover;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.marsrover.here.MarsRover.Model.Coordinate;
import com.marsrover.here.MarsRover.Model.Direction;
import com.marsrover.here.MarsRover.Model.Position;
import com.marsrover.here.MarsRover.Model.Rover;

public class RoverTest {
	
	Rover rover;
	Position initialPosition;
	Coordinate coordinate;
	Direction direction;
	
	Position expectedPosition;
	
	@Before
	public void setup() {
		initialPosition = new Position();
		coordinate = new Coordinate(3, 3);
		direction = Direction.E;
		initialPosition.setCoordinate(coordinate);
		initialPosition.setDirection(direction);
		rover = new Rover(initialPosition);
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
	
	@Ignore
	public void testRoverSpin() {
		rover.spin('R', 3);
		assertEquals("N", rover.getPosition().getDirection().toString());
	}
	
	@Ignore
	public void testRoverMoveFwd() {
		rover.forwardMove(3);
		coordinate = new Coordinate(6, 3);
		assertEquals(coordinate, rover.getPosition().getCoordinate());
	}
	
	@Ignore
	public void testRoverFinalPosition() {
		expectedPosition = new Position();
		coordinate = new Coordinate(5, 1);
		direction = Direction.E;
		expectedPosition.setCoordinate(coordinate);
		expectedPosition.setDirection(direction);
		
		rover.setInstruction("MMRMMRMRRM");
		assertEquals(expectedPosition, rover.getPosition());
	}

}
