package com.marsrover.here.MarsRover;

public class Util {
	public static String[] splitByWhitespace(String input) {
		return input.split(" ");
	}

	public static boolean notEmpty(String input) {
		return input != null && !input.isEmpty();
	}

}
