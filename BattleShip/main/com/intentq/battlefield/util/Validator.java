package com.intentq.battlefield.util;

import static com.intentq.battlefield.util.ExceptionMessage.throwCoordinateInputOutOfMaxIntRange;
import static com.intentq.battlefield.util.ExceptionMessage.throwCoordinatesOutOfGridException;
import static com.intentq.battlefield.util.ExceptionMessage.throwInvalidGridFormatInputException;
import static com.intentq.battlefield.util.ExceptionMessage.throwInvalidShipMovementFormatInputException;
import static com.intentq.battlefield.util.ExceptionMessage.throwInvalidShipPositionFormatInputException;
import static com.intentq.battlefield.util.ExceptionMessage.throwInvalidShipStartingCoordinateException;
import static com.intentq.battlefield.util.ExceptionMessage.throwInvalidShotCoordinateFormatInputException;

import java.util.ArrayList;
import java.util.List;

import com.intentq.battlefield.dto.Coordinate;
import com.intentq.battlefield.dto.Grid;
import com.intentq.battlefield.dto.Ship;

public class Validator {
	
	private static final String  REGEX_FOR_COORDINATES = "^\\([\\d]+\\,[\\d]+\\)$";
	private static final String  REGEX_FOR_SHIP_STARTING_POSITION = "\\s*^(\\([\\d]+\\,[\\d]+\\,[NSEW]\\)\\s)+";
	private static final String  REGEX_FOR_SHIP_MOVEMENTS = "^\\([\\d]+\\,[\\d]+\\,[LRM]+\\)$";
	private static final String  COMMA=",";
	private static final String  SPACE=" ";
	
	public static class TwoValuedObject {
		
		int x;
		int y;
		public int getX() {
			return x;
		}
		public void setX(int x) {
			this.x = x;
		}
		public int getY() {
			return y;
		}
		public void setY(int y) {
			this.y = y;
		}
	}
	
	public static class ThreeValuedObject extends TwoValuedObject{
		String value;

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}
	
	
	public TwoValuedObject validateGridCoordinate(String gridCoordinateInString) {
		TwoValuedObject obj = null;
		if(!validateGridCoordinateInStringForFormat(gridCoordinateInString)) {
			throwInvalidGridFormatInputException();
		} else {
			obj = convertTwoValuedInputStringToTwoValuedObject(gridCoordinateInString);
		} 
		return obj;
	}
	

	public List<ThreeValuedObject> validateShipPositions(
			String shipCoordinatesAndOrientation, Grid grid) {
		if(!validateshipCoordinatesAndOrientationForFormat(shipCoordinatesAndOrientation)) {
			throwInvalidShipPositionFormatInputException();
		} 
		List<ThreeValuedObject> objs = convertToPositionListObject(shipCoordinatesAndOrientation,grid);
		for(ThreeValuedObject obj : objs) {
			if(!validateCoordinatesWithinGridRange(obj.getX(),obj.getY(),grid)) {
				throwCoordinatesOutOfGridException();
			}
		}
		return objs;
	}
	
	
	public TwoValuedObject validateShotCoordinates(String shotCoordinateInString, Grid grid) {
		TwoValuedObject obj = null;
		if(!validateInputForCoordinates(shotCoordinateInString)) {
			throwInvalidShotCoordinateFormatInputException();
		} else {
			obj = convertTwoValuedInputStringToTwoValuedObject(shotCoordinateInString);
			if(!validateCoordinatesWithinGridRange(obj.getX(), obj.getY(), grid)) {
				throwCoordinatesOutOfGridException();
			}
		}
		return obj;
	}
	
	public ThreeValuedObject validateShipMovement(String shipMovementInString, Grid grid) {
		ThreeValuedObject obj = null;
		if(!validateInputForShipMovements(shipMovementInString)) {
			throwInvalidShipMovementFormatInputException();
		} else {
			obj = convertThreeValuedInputStringToThreeValuedObject(shipMovementInString);
			if (!validateShipStartingCoordinateMatchesWithTheShipsOnGrid(obj.getX(),obj.getY(),grid)) {
				throwInvalidShipStartingCoordinateException();
			}
		}
		return obj;
	}
	
	private boolean validateGridCoordinateInStringForFormat(
			String gridCoordinateInString) {
		return validateInputForCoordinates(gridCoordinateInString);
	}
	
	
	private boolean validateshipCoordinatesAndOrientationForFormat(String shipCoordinatesAndOrientation) {
		return validateInputForShipPosition(shipCoordinatesAndOrientation+" ");
	}

	
	private List<ThreeValuedObject> convertToPositionListObject(String shipCoordinatesAndOrientation, Grid grid) {
		 List<ThreeValuedObject> objs = new ArrayList<ThreeValuedObject>();
		String[] shipPositionsArray = shipCoordinatesAndOrientation.split(SPACE);
		for(String shipPosition: shipPositionsArray) { 
			ThreeValuedObject obj = convertThreeValuedInputStringToThreeValuedObject(shipPosition);
			objs.add(obj);
		}
		return objs;
	}
	
	
	private boolean validateInputForCoordinates(String input) {
		if(input.matches(REGEX_FOR_COORDINATES)) {
			return true;
		} 
		return false;
	}
	
	
	private boolean validateInputForShipPosition(String shipCoordinatesAndOrientation) {
		if(shipCoordinatesAndOrientation.matches(REGEX_FOR_SHIP_STARTING_POSITION)) {
			return true;
		} 
		return false;
	}
	
	
	private boolean validateInputForShipMovements(String shipCoordinatesAndMovements) {
		if(shipCoordinatesAndMovements.matches(REGEX_FOR_SHIP_MOVEMENTS)) {
			return true;
		} 
		return false;
	}
	
	
	private boolean validateCoordinatesWithinGridRange(int x, int y, Grid grid) {
		if(x>grid.getMaxCoOrdinates().getX() || y>grid.getMaxCoOrdinates().getY()) {
			return false;
		}
		return true;
	}
	
	
	private boolean validateShipStartingCoordinateMatchesWithTheShipsOnGrid(int x, int y, Grid grid) {
		Coordinate coordinates = new Coordinate(x, y);
		
		for(Ship ship:grid.getShipsOnGrid()) {
			if(ship.getCurrentCoordinate().equals(coordinates)) {
				return true;
			}
		}
		return false;
	}
	
	/*
	 * created to reuse
	 */
	private TwoValuedObject convertTwoValuedInputStringToTwoValuedObject(String gridCoordinateInString) {
		
		StringBuilder strBuilder = new StringBuilder(gridCoordinateInString.trim());
		int indexOfComma = strBuilder.indexOf(COMMA);
		int length = strBuilder.length();
		int x= 0;
		int y= 0;
		try {
			x= Integer.parseInt(strBuilder.substring(1, indexOfComma));
			y= Integer.parseInt(strBuilder.substring(indexOfComma+1,length-1));
		}
		catch(NumberFormatException nfe) {
			throwCoordinateInputOutOfMaxIntRange();
		}
		TwoValuedObject obj = new TwoValuedObject();
		obj.setX(x);
		obj.setY(y);
		return obj;
	}
	
	/*
	 * created to reuse
	 */
	private ThreeValuedObject convertThreeValuedInputStringToThreeValuedObject(String shipMovementInString) {
		StringBuilder strBuilder = new StringBuilder(shipMovementInString.trim());
		int length = strBuilder.length();
		int firstIndexOfComma = strBuilder.indexOf(COMMA);
		int secondIndexOfComma = strBuilder.lastIndexOf(COMMA);
		String xVal = strBuilder.substring(1, firstIndexOfComma);
		String yVal = strBuilder.substring(firstIndexOfComma+1, secondIndexOfComma);
		String orientationStr = strBuilder.substring(secondIndexOfComma+1, length-1);
		ThreeValuedObject obj = new ThreeValuedObject();
		obj.setValue(orientationStr);
		int x =0;
		int y = 0;
		try {
			x= Integer.parseInt(xVal);
			y= Integer.parseInt(yVal);
		}
		catch(NumberFormatException nfe) {
			throwCoordinateInputOutOfMaxIntRange();
		}
		obj.setX(x);
		obj.setY(y);
		return obj;
	}
}
