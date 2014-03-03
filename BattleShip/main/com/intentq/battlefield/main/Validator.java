package com.intentq.battlefield.main;

public class Validator {
	
	private static final String  REGEX_FOR_COORDINATES = "^\\([\\d]+\\,[\\d]+\\)$";
	private static final String  REGEX_FOR_SHIP_STARTING_POSITION = "\\s*^(\\([\\d]+\\,[\\d]+\\,[NSEW]\\)\\s)+";
	private static final String  REGEX_FOR_SHIP_MOVEMENTS = "^\\([\\d]+\\,[\\d]+\\,[LRM]+\\)$";
	
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
	
	public static boolean validateInputForShipMovements(String shipCoordinatesAndMovements) {
		if(shipCoordinatesAndMovements.matches(REGEX_FOR_SHIP_MOVEMENTS)) {
			return true;
		} 
		return false;
	}
}
