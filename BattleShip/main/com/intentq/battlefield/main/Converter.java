package com.intentq.battlefield.main;

import java.util.List;

import com.intentq.battlefield.constants.Move;
import com.intentq.battlefield.constants.Orientation;
import com.intentq.battlefield.dto.Coordinate;
import com.intentq.battlefield.dto.Grid;
import com.intentq.battlefield.dto.Position;

public class Converter {
	
	private static final String  COMMA=",";
	
	public Grid convertGridStringInputToGridObject(String gridCoordinateInString) {
		
		TwoValuedObject obj = convertTwoValuedInputStringToTwoValuedObject(gridCoordinateInString);
		return new Grid(obj.getX(),obj.getY());
	}

	public Position convertShipPositionStringInputToPositionObject(String shipCoordinatesAndOrientation) {
		
		StringBuilder strBuilder = new StringBuilder(shipCoordinatesAndOrientation.trim());
		int length = strBuilder.length();
		int firstIndexOfComma = strBuilder.indexOf(",");
		int secondIndexOfComma = strBuilder.lastIndexOf(",");
		String xCo = strBuilder.substring(1, firstIndexOfComma);
		String yCo = strBuilder.substring(firstIndexOfComma+1, secondIndexOfComma);
		String orientationStr = strBuilder.substring(secondIndexOfComma+1, length-1);
		return new Position(Integer.parseInt(xCo),Integer.parseInt(yCo),Orientation.valueOf(orientationStr));
	}

	public Coordinate convertShotInStringToShotObject(String shotCoordinateInString) {
		
		TwoValuedObject obj = convertTwoValuedInputStringToTwoValuedObject(shotCoordinateInString);
		return new Coordinate(obj.getX(), obj.getY());
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

	public List<Move> convertShipMovementInStringToShipsListOfMove(
			String shipMovementInString) {
		return null;
	}
}
