package com.intentq.battlefield.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.intentq.battlefield.constants.Move;
import com.intentq.battlefield.constants.Orientation;
import com.intentq.battlefield.dto.Coordinate;
import com.intentq.battlefield.dto.Grid;
import com.intentq.battlefield.dto.Position;
import com.intentq.battlefield.exception.InvalidInputException;
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
		List<Position> pos = new Converter().convertShipPositionStringInputToPositionObject(shipCoordinatesAndOrientation);
		assertEquals(1, pos.get(0).getCurrentCoordinates().getX());
		assertEquals(2, pos.get(0).getCurrentCoordinates().getY());
		assertEquals(Orientation.N,pos.get(0).getOrientation());
	}
	
	@Test
	public void GIVEN_ShipStartingPositionInStringFormat_THEN_convertIntoPositionObject_2() {
		String shipCoordinatesAndOrientation = "(12,2342,E)";
		List<Position> pos = new Converter().convertShipPositionStringInputToPositionObject(shipCoordinatesAndOrientation);
		assertEquals(12, pos.get(0).getCurrentCoordinates().getX());
		assertEquals(2342, pos.get(0).getCurrentCoordinates().getY());
		assertEquals(Orientation.E,pos.get(0).getOrientation());
	}
	
	@Test
	public void GIVEN_shipShotInStringFormat_THEN_convertIntoShotObject() {
		String shotCoordinateInString = "(510,79)";
		Coordinate shot = new Converter().convertShotInStringToShotObject(shotCoordinateInString);
		assertEquals(510, shot.getX());
		assertEquals(79, shot.getY());
	}
	
	@Test
	public void GIVEN_shipShotInStringFormat_THEN_convertIntoShotObject_2() {
		String shotCoordinateInString = "(50,9)";
		Coordinate shot = new Converter().convertShotInStringToShotObject(shotCoordinateInString);
		assertEquals(50, shot.getX());
		assertEquals(9, shot.getY());
	}
	
	@Test
	public void GIVEN_shipShotInStringFormat_THEN_convertIntoShotObject_3() {
		String shotCoordinateInString = "(5,0)";
		Coordinate shot = new Converter().convertShotInStringToShotObject(shotCoordinateInString);
		assertEquals(5, shot.getX());
		assertEquals(0, shot.getY());
	}
	
	@Test
	public void GIVEN_ShipMovementInStringFormat_THEN_ConvertItIntoShipsMovementObject() {
		String shipMovementInString = "(1,2,LMMRRLM)";
		List<Move> nextMoves = new Converter().convertShipMovementInStringToShipsListOfMove(shipMovementInString);
		assertEquals(Move.L, nextMoves.get(0));
		assertEquals(Move.M, nextMoves.get(1));
		assertEquals(Move.M, nextMoves.get(2));
		assertEquals(Move.R, nextMoves.get(3));
		assertEquals(Move.R, nextMoves.get(4));
		assertEquals(Move.L, nextMoves.get(5));
		assertEquals(Move.M, nextMoves.get(6));
	}
	
	@Test
	public void GIVEN_ShipMovementInStringFormat_THEN_ConvertItIntoShipsMovementObject_2() {
		String shipMovementInString = "(1,2,LMR)";
		List<Move> nextMoves = new Converter().convertShipMovementInStringToShipsListOfMove(shipMovementInString);
		assertEquals(Move.L, nextMoves.get(0));
		assertEquals(Move.M, nextMoves.get(1));
		assertEquals(Move.R, nextMoves.get(2));
	}
	
	@Test
	public void GIVEN_ShipMovementInStringFormat_THEN_ConvertItIntoShipsMovementObject_3() {
		String shipMovementInString = "(1,2,MRLM)";
		List<Move> nextMoves = new Converter().convertShipMovementInStringToShipsListOfMove(shipMovementInString);
		assertEquals(Move.M, nextMoves.get(0));
		assertEquals(Move.R, nextMoves.get(1));
		assertEquals(Move.L, nextMoves.get(2));
		assertEquals(Move.M, nextMoves.get(3));
	}
	
	@Test(expected=InvalidInputException.class)
	public void GIVEN_GridInputInIncorrectStringFormat_THEN_InvalidInputException() {
		String gridCoordinateInString = "(5,7";
		new Converter().convertGridStringInputToGridObject(gridCoordinateInString);
	}
	
	@Test(expected=InvalidInputException.class)
	public void GIVEN_GridInputInIncorrectStringFormat_THEN_InvalidInputException_2() {
		String gridCoordinateInString = "5,7)";
		new Converter().convertGridStringInputToGridObject(gridCoordinateInString);
	}
	
	@Test(expected=InvalidInputException.class)
	public void GIVEN_GridInputInIncorrectStringFormat_THEN_InvalidInputException_3() {
		String gridCoordinateInString = "(5x7)";
		new Converter().convertGridStringInputToGridObject(gridCoordinateInString);
	}
	
	@Test(expected=InvalidInputException.class)
	public void GIVEN_ShipPositionInputInIncorrectStringFormat_THEN_InvalidInputException() {
		String shipCoordinatesAndOrientation = "1,2,N)";
		new Converter().convertShipPositionStringInputToPositionObject(shipCoordinatesAndOrientation);
	}
	
	@Test(expected=InvalidInputException.class)
	public void GIVEN_ShipPositionInputInIncorrectStringFormat_THEN_InvalidInputException_2() {
		String shipCoordinatesAndOrientation = "(1,2,X)";
		new Converter().convertShipPositionStringInputToPositionObject(shipCoordinatesAndOrientation);
	}
	
	@Test
	public void GIVEN_MultipleShipPositionInputInStringFormat_THEN_NoInvalidInputException() {
		String shipCoordinatesAndOrientation = "(1,2,N) (12,34,W) (234,567,E)";
		new Converter().convertShipPositionStringInputToPositionObject(shipCoordinatesAndOrientation);
	}
}
