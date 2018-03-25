package com.marsrover.here.MarsRover;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.marsrover.here.MarsRover.Model.Coordinate;
import com.marsrover.here.MarsRover.Model.Plateu;

public class PlateuTest {

	Plateu plateu;
	Coordinate rightTopBoundary;
	Coordinate roverOutOfBoundaryCoordinate;

	@Before
	public void setup() {
		rightTopBoundary = new Coordinate(3, 3);
		plateu = Plateu.getInstance(rightTopBoundary);
	}

	@Test
	public void testOutsideBoundary_MaxX() {
		roverOutOfBoundaryCoordinate = new Coordinate(4, 3);
		assertFalse(plateu.insideBoundary(roverOutOfBoundaryCoordinate));
	}

	@Test
	public void testOutsideBoundary_MaxY() {
		roverOutOfBoundaryCoordinate = new Coordinate(3, 4);
		assertFalse(plateu.insideBoundary(roverOutOfBoundaryCoordinate));
	}
	
	@Test
	public void testOutsideBoundary_MinX() {
		roverOutOfBoundaryCoordinate = new Coordinate(-1, 3);
		assertFalse(plateu.insideBoundary(roverOutOfBoundaryCoordinate));
	}
	
	@Test
	public void testOutsideBoundary_MinY() {
		roverOutOfBoundaryCoordinate = new Coordinate(3, -1);
		assertFalse(plateu.insideBoundary(roverOutOfBoundaryCoordinate));
	}

	@Test
	public void testInsideBoundary() {
		roverOutOfBoundaryCoordinate = new Coordinate(3, 3);
		assertTrue(plateu.insideBoundary(roverOutOfBoundaryCoordinate));
	}

	@Test
	public void testInsideBoundary_1() {
		roverOutOfBoundaryCoordinate = new Coordinate(0, 0);
		assertTrue(plateu.insideBoundary(roverOutOfBoundaryCoordinate));
	}
	
	@Test
	public void testDuplicateInstance() {
		rightTopBoundary = new Coordinate(5, 5);
		plateu = Plateu.getInstance(rightTopBoundary);
		assertFalse(plateu.getTopRight().equals(rightTopBoundary));
	}

}
