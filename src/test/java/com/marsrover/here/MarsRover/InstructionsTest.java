package com.marsrover.here.MarsRover;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.marsrover.here.MarsRover.Model.Instruction;

public class InstructionsTest {

	@Test
	public void testIsValid() {
		assertTrue(Instruction.isValid('L'));

		assertTrue(Instruction.isValid('R'));

		assertTrue(Instruction.isValid('M'));

		assertFalse(Instruction.isValid('N'));
	}

	@Test
	public void testIsSpinOperation() {
		assertTrue(Instruction.L.isSpinOperation());
		assertTrue(Instruction.R.isSpinOperation());
		assertFalse(Instruction.M.isSpinOperation());
	}

}
