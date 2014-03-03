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
	
	/*
	 * Grid coordinate input format = (x,y)
	 */
	public Grid convertGridStringInputToGridObject(String gridCoordinateInString) {
		
		if(!validateGridCoordinateInStringForFormat(gridCoordinateInString)) {
			throwInvalidInputException();
		} 
		return convertToGridObject(gridCoordinateInString);
	}

	/*
	 * Ship position input format
	 * (X1,Y1,O1)<SPACE>(X1,Y1,O1)<SPACE>.........<SPACE>(Xn,Yn,On)<SPACE>
	 */
	public List<Position> convertShipPositionStringInputToPositionObject(String shipCoordinatesAndOrientation) {
		
		if(!Validator.validateInputForShipPosition(shipCoordinatesAndOrientation+" ")) {
			throwInvalidInputException();
		}
		
		List<Position> positions = new ArrayList<Position>();
		String[] shipPositionsArray = shipCoordinatesAndOrientation.split(" ");
		for(String shipPosition: shipPositionsArray) { 
			StringBuilder strBuilder = new StringBuilder(shipPosition.trim());
			int length = strBuilder.length();
			int firstIndexOfComma = strBuilder.indexOf(",");
			int secondIndexOfComma = strBuilder.lastIndexOf(",");
			String xCo = strBuilder.substring(1, firstIndexOfComma);
			String yCo = strBuilder.substring(firstIndexOfComma+1, secondIndexOfComma);
			String orientationStr = strBuilder.substring(secondIndexOfComma+1, length-1);
			positions.add(new Position(Integer.parseInt(xCo),Integer.parseInt(yCo),Orientation.valueOf(orientationStr)));
		}
		return positions;
	}
	
	/*
	 * Ship movements input format (x,y,LMMRRMMLL)
	 */
	public Coordinate convertShotInStringToShotObject(String shotCoordinateInString) {
		
		TwoValuedObject obj = convertTwoValuedInputStringToTwoValuedObject(shotCoordinateInString);
		return new Coordinate(obj.getX(), obj.getY());
	}
	
	public List<Move> convertShipMovementInStringToShipsListOfMove(String shipMovementInString) {
		
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

	private TwoValuedObject convertTwoValuedInputStringToTwoValuedObject(String gridCoordinateInString) {
		
		StringBuilder strBuilder = new StringBuilder(gridCoordinateInString.trim());
		int indexOfComma = strBuilder.indexOf(COMMA);
		int length = strBuilder.length();
		int x= Integer.parseInt(strBuilder.substring(1, indexOfComma));
		int y= Integer.parseInt(strBuilder.substring(indexOfComma+1,length-1));
		TwoValuedObject obj = new TwoValuedObject();
		obj.setX(x);
		obj.setY(y);
		return obj;
	}
	
	private ThreeValuedObject convertThreeValuedInputStringToThreeValuedObject(String shipMovementInString) {
		StringBuilder strBuilder = new StringBuilder(shipMovementInString.trim());
		int length = strBuilder.length();
		int firstIndexOfComma = strBuilder.indexOf(COMMA);
		int secondIndexOfComma = strBuilder.lastIndexOf(COMMA);
		String x = strBuilder.substring(1, firstIndexOfComma);
		String y = strBuilder.substring(firstIndexOfComma+1, secondIndexOfComma);
		String orientationStr = strBuilder.substring(secondIndexOfComma+1, length-1);
		ThreeValuedObject obj = new ThreeValuedObject();
		obj.setValue(orientationStr);
		obj.setX(Integer.parseInt(x));
		obj.setY(Integer.parseInt(y));
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

	private void throwInvalidInputException() {
		throw new InvalidInputException("Input is in not proper format");		
	}

	private boolean validateGridCoordinateInStringForFormat(
			String gridCoordinateInString) {
		return Validator.validateInputForCoordinates(gridCoordinateInString);
	}
}
