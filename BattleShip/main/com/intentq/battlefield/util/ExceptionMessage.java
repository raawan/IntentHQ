package com.intentq.battlefield.util;

import com.intentq.battlefield.exception.InvalidInputException;
import com.intentq.battlefield.exception.InvalidMoveSequenceException;

public class ExceptionMessage {

	private  ExceptionMessage() {}
	
	public static void throwCoordinateInputOutOfMaxIntRange() {
		throw new InvalidInputException("Coordinates input is out of integer range");
	}
	
	public static void throwCoordinatesOutOfGridException() {
		throw new InvalidInputException("Coordinates input is out of Grid range");		
	}

	public static void throwInvalidGridFormatInputException() {
		throw new InvalidInputException("Grid input is in not proper format - Correct format: (X,Y)");		
	}
	
	public static void throwInvalidShipPositionFormatInputException() {
		throw new InvalidInputException("Ship positions input is in not proper format - Correct format: (X1,Y1,O1)<SPACE>(X1,Y1,O1)<SPACE>.........<SPACE>(Xn,Yn,On)");		
	}
	
	public static void throwInvalidShotCoordinateFormatInputException() {
		throw new InvalidInputException("Shot input is in not proper format - Correct format: (X,Y)");		
	}
	
	public static void throwInvalidShipMovementFormatInputException() {
		throw new InvalidInputException("Ship movements input is in not proper format - Correct format: (X,Y,LMMRRMMLL)");		
	}
	
	public static void throwInvalidShipStartingCoordinateException() {
		throw new InvalidInputException("Invalid Ship starting coordinate");
	}
	
	public static void throwInvalidGridCoordinateInputException() {
		throw new InvalidInputException("Grid input coordinate cant be zero");		
	}
	
	public static void throwMoveGoingOutOfGridException() {
		throw new InvalidMoveSequenceException("A move from a moveSequence is going out of grid");
	}
}
