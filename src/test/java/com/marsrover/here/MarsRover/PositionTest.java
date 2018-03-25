package com.marsrover.here.MarsRover;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.marsrover.here.MarsRover.Model.Coordinate;
import com.marsrover.here.MarsRover.Model.Direction;
import com.marsrover.here.MarsRover.Model.Position;

public class PositionTest {

	Position position;
	Coordinate coordinate;
	Direction direction;
	
	@Before
	public void setup() {
		position = new Position();
		coordinate = new Coordinate(3, 3);
		direction = Direction.E;
		position.setCoordinate(coordinate);
		position.setDirection(direction);
	}
	
	@Test
	public void testPositionNotNull() {
		assertNotNull(position);
	}
	
	@Test
	public void testParsePosition() {
		assertEquals(position, Position.parsePosition("3 3 E"));
	}

}
