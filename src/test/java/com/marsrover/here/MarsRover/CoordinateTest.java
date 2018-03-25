package com.marsrover.here.MarsRover;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.marsrover.here.MarsRover.Model.Coordinate;

public class CoordinateTest {
	
	Coordinate roverCoordinate;
	Coordinate maxLimit;
	Coordinate minLimit;

	@Before
	public void setup() {
		maxLimit = new Coordinate(5, 5);
		minLimit = new Coordinate(0, 0);
	}
	
	@Test
	public void testCompareWithIn() {
		roverCoordinate = new Coordinate(3, 3);
		assertTrue(roverCoordinate.withIn(minLimit, maxLimit));
	}
	
	@Test
	public void testCompareOusideMax() {
		roverCoordinate = new Coordinate(6, 5);
		assertFalse(roverCoordinate.withIn(minLimit, maxLimit));
	}

	@Test
	public void testCompareOusideMin() {
		roverCoordinate = new Coordinate(5, -1);
		assertFalse(roverCoordinate.withIn(minLimit, maxLimit));
	}
}
