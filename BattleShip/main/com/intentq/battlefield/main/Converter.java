package com.intentq.battlefield.main;

import java.util.ArrayList;
import java.util.List;

import com.intentq.battlefield.constants.Move;
import com.intentq.battlefield.constants.Orientation;
import com.intentq.battlefield.dto.Coordinate;
import com.intentq.battlefield.dto.Grid;
import com.intentq.battlefield.dto.Position;
import com.intentq.battlefield.exception.InvalidInputException;

public class Converter {
	
	private static final String  COMMA=",";
	private static final String  SPACE=" ";
	/*
	 * Grid coordinate input format = (x,y)
	 */
	public Grid convertGridStringInputToGridObject(String gridCoordinateInString) {
		
		if(!validateGridCoordinateInStringForFormat(gridCoordinateInString)) {
			throwInvalidGridFormatInputException();
		} 
		return convertToGridObject(gridCoordinateInString);
	}

	/*
	 * Ship position input format
	 * (X1,Y1,O1)<SPACE>(X1,Y1,O1)<SPACE>.........<SPACE>(Xn,Yn,On)<SPACE>
	 */
	public List<Position> convertShipPositionStringInputToPositionObject(String shipCoordinatesAndOrientation, Grid grid) {
		
		if(!validateshipCoordinatesAndOrientationForFormat(shipCoordinatesAndOrientation)) {
			throwInvalidShipPositionFormatInputException();
		} 
		return convertToPositionListObject(shipCoordinatesAndOrientation,grid);
	}
	
	
	/*
	 * Shot coordinate input format = (x,y)
	 */
	public Coordinate convertShotInStringToShotObject(String shotCoordinateInString, Grid grid) {
		
		if(!validateShotCoordinateInStringForFormat(shotCoordinateInString)) {
			throwInvalidShotCoordinateFormatInputException();
		} 
		return convertToShotObject(shotCoordinateInString,grid);
	}
	

	/*
	 * Ship movements input format (x,y,LMMRRMMLL)
	 */
	public List<Move> convertShipMovementInStringToShipsListOfMove(String shipMovementInString) {
		
		if(!validateshipCoordinatesAndMovementsForFormat(shipMovementInString)) {
			throwInvalidShipMovementFormatInputException();
		}
		return convertToMoveListObject(shipMovementInString);
	}


	private boolean validateshipCoordinatesAndMovementsForFormat(String shipMovementInString) {
		return Validator.validateInputForShipMovements(shipMovementInString);
	}

	private List<Move> convertToMoveListObject(String shipMovementInString) {
		ThreeValuedObject obj = convertThreeValuedInputStringToThreeValuedObject(shipMovementInString);
		StringBuilder moves = new StringBuilder(obj.getValue());
		int index=0;
		List<Move> movesList = new ArrayList<Move>();
		while(index<moves.length()) {
			movesList.add(Move.valueOf(moves.charAt(index)+""));
			index++;
		}
		return movesList;
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
			throw new InvalidInputException("Coordinates input is out of integer range");
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
			throw new InvalidInputException("Coordinates input is out of integer range");
		}
		obj.setX(x);
		obj.setY(y);
		return obj;
	}
	
	private static class TwoValuedObject {
		
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
	
	private static class ThreeValuedObject extends TwoValuedObject{
		String value;

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}
	
	private Grid convertToGridObject(String gridCoordinateInString) {
		TwoValuedObject obj = convertTwoValuedInputStringToTwoValuedObject(gridCoordinateInString);
		return new Grid(obj.getX(),obj.getY());		
	}

	private boolean validateGridCoordinateInStringForFormat(
			String gridCoordinateInString) {
		return Validator.validateInputForCoordinates(gridCoordinateInString);
	}
	
	private boolean validateShotCoordinateInStringForFormat(
			String gridCoordinateInString) {
		return Validator.validateInputForCoordinates(gridCoordinateInString);
	}
	
	private boolean validateshipCoordinatesAndOrientationForFormat(String shipCoordinatesAndOrientation) {
		return Validator.validateInputForShipPosition(shipCoordinatesAndOrientation+" ");
	}

	private List<Position> convertToPositionListObject(String shipCoordinatesAndOrientation, Grid grid) {
		List<Position> positions = new ArrayList<Position>();
		String[] shipPositionsArray = shipCoordinatesAndOrientation.split(SPACE);
		for(String shipPosition: shipPositionsArray) { 
			ThreeValuedObject obj = convertThreeValuedInputStringToThreeValuedObject(shipPosition);
			if(!Validator.validateCoordinatesWithinGridRange(obj.getX(),obj.getY(),grid)) {
				throwCoordinatesOutOfGridException();
			}
			positions.add(new Position(obj.getX(),obj.getY(),Orientation.valueOf(obj.getValue())));
		}
		return positions;
	}
	
	private Coordinate convertToShotObject(String shotCoordinateInString, Grid grid) {
		TwoValuedObject obj = convertTwoValuedInputStringToTwoValuedObject(shotCoordinateInString);
		if(!Validator.validateCoordinatesWithinGridRange(obj.getX(), obj.getY(), grid)) {
			throwCoordinatesOutOfGridException();
		}
		return new Coordinate(obj.getX(), obj.getY());	
	}
	
	private void throwCoordinatesOutOfGridException() {
		throw new InvalidInputException("Coordinates input is out of Grid range");		
	}

	private void throwInvalidGridFormatInputException() {
		throw new InvalidInputException("Grid input is in not proper format - Correct format: (X,Y)");		
	}
	
	private void throwInvalidShipPositionFormatInputException() {
		throw new InvalidInputException("Ship positions input is in not proper format - Correct format: (X1,Y1,O1)<SPACE>(X1,Y1,O1)<SPACE>.........<SPACE>(Xn,Yn,On)");		
	}
	
	private void throwInvalidShotCoordinateFormatInputException() {
		throw new InvalidInputException("Shot input is in not proper format - Correct format: (X,Y)");		
	}
	
	private void throwInvalidShipMovementFormatInputException() {
		throw new InvalidInputException("Ship movements input is in not proper format - Correct format: (X,Y,LMMRRMMLL)");		
	}
}
