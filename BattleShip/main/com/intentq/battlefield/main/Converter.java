package com.intentq.battlefield.main;

import com.intentq.battlefield.dto.Grid;

public class Converter {

	public Grid convertGridStringInputToGridObject(String gridCoordinateInString) {
		return new Grid(5,7);
	}

}
