package com.intentq.battlefield.main;

public class Validator {
	
	private static final String  REGEX_FOR_COORDINATES = "^\\([\\d]+\\,[\\d]+\\)$";
	
	private Validator() {}
	
	public static boolean validateInputForCoordinates(String input) {
		if(input.matches(REGEX_FOR_COORDINATES)) {
			return true;
		} 
		return false;
	}
}
