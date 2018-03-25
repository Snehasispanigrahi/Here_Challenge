package com.marsrover.here.MarsRover;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.marsrover.here.MarsRover.Model.Instructions;

public class InstructionsTest {

	@Test
	public void testIsValid() {
		assertTrue(Instructions.isValid('L'));

		assertTrue(Instructions.isValid('R'));

		assertTrue(Instructions.isValid('M'));

		assertFalse(Instructions.isValid('N'));
	}
	
	@Test
	public void testIsSpinOperation() {
		assertTrue(Instructions.isSpinOperation('L'));
		assertTrue(Instructions.isSpinOperation('R'));
		assertFalse(Instructions.isSpinOperation('M'));
	}
	
}
