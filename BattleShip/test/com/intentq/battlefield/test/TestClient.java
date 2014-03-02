package com.intentq.battlefield.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.intentq.battlefield.constants.Orientation;
import com.intentq.battlefield.dto.Grid;
import com.intentq.battlefield.dto.Position;
import com.intentq.battlefield.main.Converter;

public class TestClient {

	@Test
	public void GIVEN_GridCoordinatesAsStringInput_THEN_convertToGridObject() {
		String gridCoordinateInString = "(5,7)";
		Grid grid = new Converter().convertGridStringInputToGridObject(gridCoordinateInString);
		assertEquals(5, grid.getMaxCoOrdinates().getX());
		assertEquals(7, grid.getMaxCoOrdinates().getY()); 
	}
	
	@Test
	public void GIVEN_GridCoordinatesAsStringInput_THEN_convertToGridObject_2() {
		String gridCoordinateInString = "(6,8)";
		Grid grid = new Converter().convertGridStringInputToGridObject(gridCoordinateInString);
		assertEquals(6, grid.getMaxCoOrdinates().getX());
		assertEquals(8, grid.getMaxCoOrdinates().getY()); 
	}
	
	@Test
	public void GIVEN_GridInputInSTringFormat_THEN_convertIntoGridObject_3() {
		
		String gridCoordinateInString = "(51,789)";
		Grid grid = new Converter().convertGridStringInputToGridObject(gridCoordinateInString);
		assertEquals(51, grid.getMaxCoOrdinates().getX());
		assertEquals(789, grid.getMaxCoOrdinates().getY()); 
	}
	
	@Test
	public void GIVEN_GridInputInSTringFormat_THEN_convertIntoGridObject_4() {
		
		String gridCoordinateInString = "(531,7289)";
		Grid grid = new Converter().convertGridStringInputToGridObject(gridCoordinateInString);
		assertEquals(531, grid.getMaxCoOrdinates().getX());
		assertEquals(7289, grid.getMaxCoOrdinates().getY()); 
	}
	
	@Test
	public void GIVEN_ShipStartingPositionInStringFormat_THEN_convertIntoPositionObject() {
		String shipCoordinatesAndOrientation = "(1,2,N)";
		Position pos = new Converter().convertShipPositionStringInputToPositionObject(shipCoordinatesAndOrientation);
		assertEquals(1, pos.getCurrentCoordinates().getX());
		assertEquals(2, pos.getCurrentCoordinates().getY());
		assertEquals(Orientation.N,pos.getOrientation());
	}
	
}
