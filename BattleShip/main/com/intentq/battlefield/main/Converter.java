package com.intentq.battlefield.main;

import com.intentq.battlefield.dto.Grid;

public class Converter {

	public Grid convertGridStringInputToGridObject(String gridCoordinateInString) {
		
		StringBuilder strBuilder = new StringBuilder(gridCoordinateInString.trim());
		int indexOfComma = strBuilder.indexOf(",");
		int length = strBuilder.length();
		int x= Integer.parseInt(strBuilder.substring(1, indexOfComma));
		int y= Integer.parseInt(strBuilder.substring(indexOfComma+1,length-1));
		return new Grid(x,y);
	}

}
