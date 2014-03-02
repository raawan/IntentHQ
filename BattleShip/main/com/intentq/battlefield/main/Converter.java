package com.intentq.battlefield.main;

import com.intentq.battlefield.dto.Grid;

public class Converter {

	public Grid convertGridStringInputToGridObject(String gridCoordinateInString) {
		StringBuilder strBuilder = new StringBuilder(gridCoordinateInString);
		int x = Integer.parseInt(strBuilder.charAt(1)+"");
		int y = Integer.parseInt(strBuilder.charAt(3)+"");
		
		return new Grid(x,y);
	}

}
