package com.intentq.battlefield.main;

public class Validator {
	
	private static final String  REGEX_FOR_COORDINATES = "^\\([\\d]+\\,[\\d]+\\)$";
	private static final String  REGEX_FOR_SHIP_STARTING_POSITION = "\\s*^(\\([\\d]+\\,[\\d]+\\,[NSEW]\\)\\s)+";
	
	private Validator() {}
	
	public static boolean validateInputForCoordinates(String input) {
		if(input.matches(REGEX_FOR_COORDINATES)) {
			return true;
		} 
		return false;
	}
	
	public static boolean validateInputForShipPosition(String shipCoordinatesAndOrientation) {
		if(shipCoordinatesAndOrientation.matches(REGEX_FOR_SHIP_STARTING_POSITION)) {
			return true;
		} 
		return false;
	}
}
