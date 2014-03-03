package com.intentq.battlefield.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.intentq.battlefield.constants.LifeStatus;
import com.intentq.battlefield.constants.Move;
import com.intentq.battlefield.constants.Orientation;
import com.intentq.battlefield.dto.Coordinate;
import com.intentq.battlefield.dto.Grid;
import com.intentq.battlefield.dto.Position;
import com.intentq.battlefield.dto.Ship;
import com.intentq.battlefield.exception.InvalidInputException;
import com.intentq.battlefield.exception.InvalidMoveSequenceException;
import com.intentq.battlefield.main.Converter;
import com.intentq.battlefield.validator.Validator;

import static com.intentq.battlefield.constants.Move.*;

public class TestInputConversionAndValidation {
	
	private Converter converter;
	
	@Before
	public void setUp() {
		converter = new Converter(new Validator());
	}
	
	@Test
	public void GIVEN_GridCoordinatesAsStringInput_THEN_convertToGridObject() {
		String gridCoordinateInString = "(5,7)";
		Grid grid = converter.convertGridStringInputToGridObject(gridCoordinateInString);
		assertEquals(5, grid.getMaxCoOrdinates().getX());
		assertEquals(7, grid.getMaxCoOrdinates().getY()); 
	}
	
	@Test
	public void GIVEN_GridCoordinatesAsStringInput_THEN_convertToGridObject_2() {
		String gridCoordinateInString = "(6,8)";
		Grid grid = converter.convertGridStringInputToGridObject(gridCoordinateInString);
		assertEquals(6, grid.getMaxCoOrdinates().getX());
		assertEquals(8, grid.getMaxCoOrdinates().getY()); 
	}
	
	@Test
	public void GIVEN_GridInputInSTringFormat_THEN_convertIntoGridObject_3() {
		
		String gridCoordinateInString = "(51,789)";
		Grid grid = converter.convertGridStringInputToGridObject(gridCoordinateInString);
		assertEquals(51, grid.getMaxCoOrdinates().getX());
		assertEquals(789, grid.getMaxCoOrdinates().getY()); 
	}
	
	@Test
	public void GIVEN_GridInputInSTringFormat_THEN_convertIntoGridObject_4() {
		
		String gridCoordinateInString = "(531,7289)";
		Grid grid = converter.convertGridStringInputToGridObject(gridCoordinateInString);
		assertEquals(531, grid.getMaxCoOrdinates().getX());
		assertEquals(7289, grid.getMaxCoOrdinates().getY()); 
	}
	
	@Test
	public void GIVEN_ShipStartingPositionInStringFormat_THEN_convertIntoPositionObject() {
		String shipCoordinatesAndOrientation = "(1,2,N)";
		List<Position> pos = converter.convertShipPositionStringInputToPositionObject(shipCoordinatesAndOrientation,getStubbedGridObject());
		assertEquals(1, pos.get(0).getCurrentCoordinates().getX());
		assertEquals(2, pos.get(0).getCurrentCoordinates().getY());
		assertEquals(Orientation.N,pos.get(0).getOrientation());
	}
	
	@Test
	public void GIVEN_ShipStartingPositionInStringFormat_THEN_convertIntoPositionObject_2() {
		String shipCoordinatesAndOrientation = "(12,2342,E)";
		List<Position> pos = converter.convertShipPositionStringInputToPositionObject(shipCoordinatesAndOrientation,getStubbedGridObject());
		assertEquals(12, pos.get(0).getCurrentCoordinates().getX());
		assertEquals(2342, pos.get(0).getCurrentCoordinates().getY());
		assertEquals(Orientation.E,pos.get(0).getOrientation());
	}
	
	@Test
	public void GIVEN_shipShotInStringFormat_THEN_convertIntoShotObject() {
		String shotCoordinateInString = "(510,79)";
		Coordinate shot = converter.convertShotInStringToShotObject(shotCoordinateInString,getStubbedGridObject());
		assertEquals(510, shot.getX());
		assertEquals(79, shot.getY());
	}
	
	@Test
	public void GIVEN_shipShotInStringFormat_THEN_convertIntoShotObject_2() {
		String shotCoordinateInString = "(50,9)";
		Coordinate shot = converter.convertShotInStringToShotObject(shotCoordinateInString,getStubbedGridObject());
		assertEquals(50, shot.getX());
		assertEquals(9, shot.getY());
	}
	
	@Test
	public void GIVEN_shipShotInStringFormat_THEN_convertIntoShotObject_3() {
		String shotCoordinateInString = "(5,0)";
		Coordinate shot = converter.convertShotInStringToShotObject(shotCoordinateInString,getStubbedGridObject());
		assertEquals(5, shot.getX());
		assertEquals(0, shot.getY());
	}
	
	@Test
	public void GIVEN_ShipMovementInStringFormat_THEN_ConvertItIntoShipsMovementObject() {
		String shipMovementInString = "(1,2,LMMRRLM)";
		Grid g = createGridWithShipCoordinatesAndOrientation("(1,2,E) (1,45,W) (23,67,S)"); 
		List<Move> nextMoves = converter.convertShipMovementInStringToShipsListOfMove(shipMovementInString,g,getStubbedLifeStatus());
		assertEquals(Move.L, nextMoves.get(0));
		assertEquals(Move.M, nextMoves.get(1));
		assertEquals(Move.M, nextMoves.get(2));
		assertEquals(Move.R, nextMoves.get(3));
		assertEquals(Move.R, nextMoves.get(4));
		assertEquals(Move.L, nextMoves.get(5));
		assertEquals(Move.M, nextMoves.get(6));
	}
	

	private LifeStatus getStubbedLifeStatus() {
		return LifeStatus.ALIVE;
	}

	@Test
	public void GIVEN_ShipMovementInStringFormat_THEN_ConvertItIntoShipsMovementObject_2() {
		String shipMovementInString = "(1,2,LMR)";
		Grid g = createGridWithShipCoordinatesAndOrientation("(1,2,E) (1,45,W) (23,67,S)"); 
		List<Move> nextMoves = converter.convertShipMovementInStringToShipsListOfMove(shipMovementInString,g,getStubbedLifeStatus());
		assertEquals(Move.L, nextMoves.get(0));
		assertEquals(Move.M, nextMoves.get(1));
		assertEquals(Move.R, nextMoves.get(2));
	}
	
	@Test
	public void GIVEN_ShipMovementInStringFormat_THEN_ConvertItIntoShipsMovementObject_3() {
		String shipMovementInString = "(1,2,MRLM)";
		Grid g = createGridWithShipCoordinatesAndOrientation("(78,2,E) (1,2,W) (23,67,S)"); 
		List<Move> nextMoves = converter.convertShipMovementInStringToShipsListOfMove(shipMovementInString,g,getStubbedLifeStatus());
		assertEquals(Move.M, nextMoves.get(0));
		assertEquals(Move.R, nextMoves.get(1));
		assertEquals(Move.L, nextMoves.get(2));
		assertEquals(Move.M, nextMoves.get(3));
	}
	
	@Test(expected=InvalidInputException.class)
	public void GIVEN_GridInputInIncorrectStringFormat_THEN_InvalidInputException() {
		String gridCoordinateInString = "(5,7";
		converter.convertGridStringInputToGridObject(gridCoordinateInString);
	}
	
	@Test(expected=InvalidInputException.class)
	public void GIVEN_GridInputInIncorrectStringFormat_THEN_InvalidInputException_2() {
		String gridCoordinateInString = "5,7)";
		converter.convertGridStringInputToGridObject(gridCoordinateInString);
	}
	
	@Test(expected=InvalidInputException.class)
	public void GIVEN_GridInputInIncorrectStringFormat_THEN_InvalidInputException_3() {
		String gridCoordinateInString = "(5x7)";
		converter.convertGridStringInputToGridObject(gridCoordinateInString);
	}
	
	@Test(expected=InvalidInputException.class)
	public void GIVEN_ShipPositionInputInIncorrectStringFormat_THEN_InvalidInputException() {
		String shipCoordinatesAndOrientation = "1,2,N)";
		converter.convertShipPositionStringInputToPositionObject(shipCoordinatesAndOrientation,getStubbedGridObject());
	}
	
	@Test(expected=InvalidInputException.class)
	public void GIVEN_ShipPositionInputInIncorrectStringFormat_THEN_InvalidInputException_2() {
		String shipCoordinatesAndOrientation = "(1,2,X)";
		converter.convertShipPositionStringInputToPositionObject(shipCoordinatesAndOrientation,getStubbedGridObject());
	}
	
	@Test
	public void GIVEN_MultipleShipPositionInputInStringFormat_THEN_NoInvalidInputException() {
		String shipCoordinatesAndOrientation = "(1,2,N) (12,34,W) (234,567,E)";
		List<Position> pos = converter.convertShipPositionStringInputToPositionObject(shipCoordinatesAndOrientation,getStubbedGridObject());
		assertEquals(new Position(1, 2, Orientation.N),pos.get(0));
		assertEquals(new Position(12, 34, Orientation.W),pos.get(1));
		assertEquals(new Position(234, 567, Orientation.E),pos.get(2));
	}
	
	@Test
	public void GIVEN_MultipleShipPositionInputInStringFormat_THEN_NoInvalidInputException_2() {
		String shipCoordinatesAndOrientation = "(1,2,N) (12,34,W) (234,567,E) (12,34,S)";
		List<Position> pos =   converter.convertShipPositionStringInputToPositionObject(shipCoordinatesAndOrientation,getStubbedGridObject());
		assertEquals(new Position(1, 2, Orientation.N),pos.get(0));
		assertEquals(new Position(12, 34, Orientation.W),pos.get(1));
		assertEquals(new Position(234, 567, Orientation.E),pos.get(2));
		assertEquals(new Position(12, 34, Orientation.S),pos.get(3));
	}
	
	@Test(expected=InvalidInputException.class)
	public void GIVEN_ShipMovementsInputInIncorrectStringFormat_THEN_InvalidInputException() {
		String shipMovementInString = "(1,2)";
		converter.convertShipMovementInStringToShipsListOfMove(shipMovementInString,getStubbedGridObject(),getStubbedLifeStatus());
	}
	
	@Test(expected=InvalidInputException.class)
	public void GIVEN_ShipMovementsInputInIncorrectStringFormat_THEN_InvalidInputException_2() {
		String shipMovementInString = "(1,2,LLMM";
		converter.convertShipMovementInStringToShipsListOfMove(shipMovementInString,getStubbedGridObject(),getStubbedLifeStatus());
	}
	
	@Test(expected=InvalidInputException.class)
	public void GIVEN_ShipMovementsInputInIncorrectStringFormat_THEN_InvalidInputException_3() {
		String shipMovementInString = "1,2,LLMM";
		converter.convertShipMovementInStringToShipsListOfMove(shipMovementInString,getStubbedGridObject(),getStubbedLifeStatus());
	}
	
	@Test(expected=InvalidInputException.class)
	public void GIVEN_ShipsShotCoordinateInInvalidStringFormat_THEN_InvalidInputException() {
		String shotCoordinateInString = "(510,79";
		converter.convertShotInStringToShotObject(shotCoordinateInString,getStubbedGridObject());
	}
	
	@Test(expected=InvalidInputException.class)
	public void GIVEN_ShipsShotCoordinateInInvalidStringFormat_THEN_InvalidInputException_2() {
		String shotCoordinateInString = "510,79";
		converter.convertShotInStringToShotObject(shotCoordinateInString,getStubbedGridObject());
	}
	
	@Test(expected=InvalidInputException.class)
	public void GIVEN_negativeXgridCoordinate_THEN_InvalidInputException() {
		String gridCoordinateInString = "(-5,7)";
		converter.convertGridStringInputToGridObject(gridCoordinateInString);
	}
	
	@Test(expected=InvalidInputException.class)
	public void GIVEN_negativeYgridCoordinate_THEN_InvalidInputException() {
		String gridCoordinateInString = "(5,-7)";
		converter.convertGridStringInputToGridObject(gridCoordinateInString);
	}
	
	@Test(expected=InvalidInputException.class)
	public void GIVEN_negativeXshotCoordinate_THEN_InvalidInputException() {
		String gridCoordinateInString = "(-5,7)";
		converter.convertShotInStringToShotObject(gridCoordinateInString,getStubbedGridObject());
	}
	
	@Test(expected=InvalidInputException.class)
	public void GIVEN_negativeYshotCoordinate_THEN_InvalidInputException() {
		String gridCoordinateInString = "(5,-7)";
		converter.convertShotInStringToShotObject(gridCoordinateInString,getStubbedGridObject());
	}
	
	@Test(expected=InvalidInputException.class)
	public void GIVEN_NegativeXcoordinateOfShipPosition_THEN_InvalidInputException() {
		String shipCoordinatesAndOrientation = "(-1,2,N)";
		converter.convertShipPositionStringInputToPositionObject(shipCoordinatesAndOrientation,getStubbedGridObject());
	}
	
	@Test(expected=InvalidInputException.class)
	public void GIVEN_NegativeYcoordinateOfShipPosition_THEN_InvalidInputException() {
		String shipCoordinatesAndOrientation = "(1,-2,N)";
		converter.convertShipPositionStringInputToPositionObject(shipCoordinatesAndOrientation,getStubbedGridObject());
	}
	
	@Test(expected=InvalidInputException.class)
	public void GIVEN_NegativeYcoordinateOfShipPosition_THEN_InvalidInputException_2() {
		String shipCoordinatesAndOrientation = "(1,2,N) (-1,4,W)";
		converter.convertShipPositionStringInputToPositionObject(shipCoordinatesAndOrientation,getStubbedGridObject());
	}
	
	@Test(expected=InvalidInputException.class)
	public void GIVEN_GridInputXOutOfMaxIntRange_THEN_InavlidInputException() {
		String gridCoordinateInString = "(52134567898765435678987654356789,7)";
		converter.convertGridStringInputToGridObject(gridCoordinateInString);
	}
	

	@Test(expected=InvalidInputException.class)
	public void GIVEN_GridInputYOutOfMaxIntRange_THEN_InavlidInputException() {
		String gridCoordinateInString = "(5,52134567898765435678987654356789)";
		converter.convertGridStringInputToGridObject(gridCoordinateInString);
	}
	
	@Test(expected=InvalidInputException.class)
	public void GIVEN_shotInputXOutOfMaxIntRange_THEN_InavlidInputException() {
		String shotCoordinateInString = "(52134567898765435678987654356789,7)";
		converter.convertShotInStringToShotObject(shotCoordinateInString,getStubbedGridObject());
	}
	
	@Test(expected=InvalidInputException.class)
	public void GIVEN_shotInputYOutOfMaxIntRange_THEN_InavlidInputException() {
		String shotCoordinateInString = "(7,52134567898765435678987654356789)";
		converter.convertShotInStringToShotObject(shotCoordinateInString,getStubbedGridObject());
	}
	
	@Test(expected=InvalidInputException.class)
	public void GIVEN_ShipPositionInputXcoordinateOutOfMaxIntRange_THEN_InvalidInputException() {
		String shipCoordinatesAndOrientation = "(52134567898765435678987654356789,2,N)";
		converter.convertShipPositionStringInputToPositionObject(shipCoordinatesAndOrientation,getStubbedGridObject());
	}
	
	private Grid getStubbedGridObject() {
		return new Grid(10000,10000);
	}

	@Test(expected=InvalidInputException.class)
	public void GIVEN_ShipPositionInputYcoordinateOutOfMaxIntRange_THEN_InvalidInputException() {
		String shipCoordinatesAndOrientation = "(2,52134567898765435678987654356789,N)";
		converter.convertShipPositionStringInputToPositionObject(shipCoordinatesAndOrientation,getStubbedGridObject());
	}

	@Test(expected=InvalidInputException.class)
	public void GIVEN_ShipMovementInputYcoordinateOutOfMaxIntRange_THEN_InvalidInputException() {
		String shipMovementInString = "(1,2,52134567898765435678987654356789,LLMM)";
		converter.convertShipMovementInStringToShipsListOfMove(shipMovementInString,getStubbedGridObject(),getStubbedLifeStatus());
	}
	
	@Test(expected=InvalidInputException.class)
	public void GIVEN_validGridInput_And_ShipPositionCoordinateGreaterThanGrid_THEN_InvalidInputException() {
		String gridCoordinateInString = "(51,789)";
		String shipCoordinatesAndOrientation = "(78,2,E)";
		converter.convertShipPositionStringInputToPositionObject(shipCoordinatesAndOrientation,
				converter.convertGridStringInputToGridObject(gridCoordinateInString));
	}
	
	@Test(expected=InvalidInputException.class)
	public void GIVEN_validGridInput_And_ShipPositionCoordinateGreaterThanGrid_THEN_InvalidInputException_2() {
		String gridCoordinateInString = "(510,789)";
		String shipCoordinatesAndOrientation = "(178,1112,E)";
		converter.convertShipPositionStringInputToPositionObject(shipCoordinatesAndOrientation,
				converter.convertGridStringInputToGridObject(gridCoordinateInString));
	}
	
	@Test(expected=InvalidInputException.class)
	public void GIVEN_validGridInput_And_ShipShotCoordinateGreaterThanGrid_THEN_InvalidInputException() {
		String gridCoordinateInString = "(510,789)";
		String shotCoordinateInString = "(511,789)";
		converter.convertShotInStringToShotObject(shotCoordinateInString,converter.convertGridStringInputToGridObject(gridCoordinateInString));
		
	}
	
	@Test(expected=InvalidInputException.class)
	public void GIVEN_validGridInput_And_ShipShotCoordinateGreaterThanGrid_THEN_InvalidInputException_2() {
		String gridCoordinateInString = "(510,789)";
		String shotCoordinateInString = "(51,7890)";
		converter.convertShotInStringToShotObject(shotCoordinateInString,converter.convertGridStringInputToGridObject(gridCoordinateInString));
		
	}
	
	@Test(expected=InvalidInputException.class)
	public void GIVEN_validShipPositionsOnGridAndInvalidShipMovementCoordinates_THEN_InavlidInputException() {
		Grid g = createGridWithShipCoordinatesAndOrientation("(78,2,E) (1,45,W) (23,67,S)"); 
		String shipMovementInString = "(1,2,LMMRRLM)";
		converter.convertShipMovementInStringToShipsListOfMove(shipMovementInString,g,getStubbedLifeStatus());
	}
	
	@Test(expected=InvalidInputException.class)
	public void GIVEN_validShipPositionsOnGridAndInvalidShipMovementCoordinates_THEN_InavlidInputException_2() {
		Grid g = createGridWithShipCoordinatesAndOrientation("(78,2,E) (1,45,W) (23,67,S)"); 
		String shipMovementInString = "(14,23,LMMRRLM)";
		converter.convertShipMovementInStringToShipsListOfMove(shipMovementInString,g,getStubbedLifeStatus());
	}
	
	@Test(expected=InvalidMoveSequenceException.class)
	public void GIVEN_ShipOrientationNorth_And_MoveAheadIsOutOfGrid_THEN_InvalidMovesException() {
		Grid g = new Grid(5,5);
		Ship ship = new Ship(new Position(5,3,Orientation.E),nextMoveSequence(M,M,R),null,1);
		ship.move(g.getMaxCoOrdinates());
	}
	
	@Test(expected=InvalidMoveSequenceException.class)
	public void GIVEN_ShipOrientationSouth_And_MoveAheadIsOutOfGrid_THEN_InvalidMovesException() {
		Grid g = new Grid(5,5);
		Ship ship = new Ship(new Position(5,0,Orientation.S),nextMoveSequence(M,M,R),null,1);
		ship.move(g.getMaxCoOrdinates());
	}
	
	@Test(expected=InvalidMoveSequenceException.class)
	public void GIVEN_ShipOrientationWest_And_MoveAheadIsOutOfGrid_THEN_InvalidMovesException() {
		Grid g = new Grid(5,5);
		Ship ship = new Ship(new Position(0,2,Orientation.W),nextMoveSequence(M,M,R),null,1);
		ship.move(g.getMaxCoOrdinates());
	}
	
	@Test(expected=InvalidMoveSequenceException.class)
	public void GIVEN_ShipOrientationEast_And_MoveAheadIsOutOfGrid_THEN_InvalidMovesException() {
		Grid g = new Grid(5,5);
		Ship ship = new Ship(new Position(5,2,Orientation.E),nextMoveSequence(M,M,R),null,1);
		ship.move(g.getMaxCoOrdinates());
	}
	
	@Test(expected=InvalidInputException.class)
	public void GIVEN_BothZeroCoordinatesOfGrid_THEN_InvalidInputException() {
		String gridCoordinateInString = "(0,0)";
		converter.convertGridStringInputToGridObject(gridCoordinateInString);
	}
	
	private static List<Move> nextMoveSequence(Move...actions) {
		return Arrays.asList(actions);
	}
	
	
	private Grid createGridWithShipCoordinatesAndOrientation(String shipCoordinatesAndOrientation) {
		Grid g = new Grid(125,128);
		List<Position> posList = converter.convertShipPositionStringInputToPositionObject(shipCoordinatesAndOrientation,g);
		List<Ship> ships = new ArrayList<Ship>();
		int id =0;
		for (Position pos : posList) {
			ships.add(new Ship(pos,null,null,id++));
		}
		g.setShipsOnGrid(ships);
		return g;
	}
}
