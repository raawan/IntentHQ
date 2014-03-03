package com.intentq.battlefield.api.validator;

import static com.intentq.battlefield.api.util.ExceptionMessage.throwCoordinateInputOutOfMaxIntRange;
import static com.intentq.battlefield.api.util.ExceptionMessage.throwCoordinatesOutOfGridException;
import static com.intentq.battlefield.api.util.ExceptionMessage.throwZeroGridCoordinateInputException;
import static com.intentq.battlefield.api.util.ExceptionMessage.throwInvalidGridFormatInputException;
import static com.intentq.battlefield.api.util.ExceptionMessage.throwInvalidShipMovementFormatInputException;
import static com.intentq.battlefield.api.util.ExceptionMessage.throwInvalidShipPositionFormatInputException;
import static com.intentq.battlefield.api.util.ExceptionMessage.throwInvalidShipStartingCoordinateException;
import static com.intentq.battlefield.api.util.ExceptionMessage.throwInvalidShotCoordinateFormatInputException;

import java.util.ArrayList;
import java.util.List;

import com.intentq.battlefield.api.constants.LifeStatus;
import com.intentq.battlefield.api.dto.Coordinate;
import com.intentq.battlefield.api.dto.Grid;
import com.intentq.battlefield.api.dto.Ship;

public class Validator implements IValidator {
	
	private static final String  REGEX_FOR_COORDINATES = "^\\([\\d]+\\,[\\d]+\\)$";
	private static final String  REGEX_FOR_SHIP_STARTING_POSITION = "\\s*^(\\([\\d]+\\,[\\d]+\\,[NSEW]\\)\\s)+";
	private static final String  REGEX_FOR_SHIP_MOVEMENTS = "^\\([\\d]+\\,[\\d]+\\,[LRM]+\\)$";
	private static final String  COMMA=",";
	private static final String  SPACE=" ";
	private static final int  ZERO_VALUE_COORDINATE = 0;
	
	public static class ThreeValuedObject extends Coordinate{
		public ThreeValuedObject(int x, int y) {
			super(x, y);
		}

		String value;

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}
	
	@Override
	public Coordinate validateGridCoordinate(String gridCoordinateInString) {
		Coordinate obj = null;
		if(!validateGridCoordinateInStringForFormat(gridCoordinateInString)) {
			throwInvalidGridFormatInputException();
		} else {
			obj = convertTwoValuedInputStringToTwoValuedObject(gridCoordinateInString);
			if(isGridZero(obj)) {
				throwZeroGridCoordinateInputException();
			}
		} 
		return obj;
	}
	

	@Override
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
	
	@Override
	public Coordinate validateShotCoordinates(String shotCoordinateInString, Grid grid) {
		Coordinate obj = null;
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
	
	@Override
	public ThreeValuedObject validateShipMovement(String shipMovementInString, Grid grid, LifeStatus lifeStatus) {
		ThreeValuedObject obj = null;
		if(lifeStatus.equals(LifeStatus.SUNK)) {
			return null;//The ship should not move
		}
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
	
	@Override
	public boolean validateShipAlive(Ship s) {
		if(s.getLifeStatus().equals(LifeStatus.SUNK))
			return false;
		else
			return true;
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
	private Coordinate convertTwoValuedInputStringToTwoValuedObject(String gridCoordinateInString) {
		
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
		Coordinate obj = new Coordinate(x,y);
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
		int x =0;
		int y = 0;
		try {
			x= Integer.parseInt(xVal);
			y= Integer.parseInt(yVal);
		}
		catch(NumberFormatException nfe) {
			throwCoordinateInputOutOfMaxIntRange();
		}
		ThreeValuedObject obj = new ThreeValuedObject(x,y);
		obj.setValue(orientationStr);
		obj.setX(x);
		obj.setY(y);
		return obj;
	}
	
	private boolean isEqual(int number, int valueToComapreForEquality) {
		if(number==valueToComapreForEquality) {
			return true;
		}
		return false;
	}
	
	private boolean isGridZero(Coordinate obj) {
		return (isEqual(obj.getX(), ZERO_VALUE_COORDINATE) && isEqual(obj.getY(), ZERO_VALUE_COORDINATE));
	}
	
}
