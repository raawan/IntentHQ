package com.intentq.battlefield.main;

import com.intentq.battlefield.constants.Orientation;
import com.intentq.battlefield.dto.Coordinate;
import com.intentq.battlefield.dto.Grid;
import com.intentq.battlefield.dto.Position;

public class Converter {

	public Grid convertGridStringInputToGridObject(String gridCoordinateInString) {
		
		StringBuilder strBuilder = new StringBuilder(gridCoordinateInString.trim());
		int indexOfComma = strBuilder.indexOf(",");
		int length = strBuilder.length();
		int x= Integer.parseInt(strBuilder.substring(1, indexOfComma));
		int y= Integer.parseInt(strBuilder.substring(indexOfComma+1,length-1));
		return new Grid(x,y);
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
		return new Coordinate(510, 79);
	}

}
