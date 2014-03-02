package com.intentq.battlefield.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.intentq.battlefield.dto.Grid;

public class TestClient {

	@Test
	public void GIVEN_GridCoordinatesAsStringInput_THEN_convertToGridObject() {
		String gridCoordinateInString = "(5x7)";
		Grid grid = new Converter().convertGridStringInputToGridObject(gridCoordinateInString);
		assertEquals(5, grid.getMaxCoOrdinates().getX());
		assertEquals(7, grid.getMaxCoOrdinates().getY()); 
	}

}
