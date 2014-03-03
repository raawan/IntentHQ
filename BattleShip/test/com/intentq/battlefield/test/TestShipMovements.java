package com.intentq.battlefield.test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.intentq.battlefield.api.constants.Move;
import com.intentq.battlefield.api.constants.Orientation;
import com.intentq.battlefield.api.dto.Coordinate;
import com.intentq.battlefield.api.dto.Position;
import com.intentq.battlefield.api.dto.Ship;

import static com.intentq.battlefield.api.constants.Move.*;

public class TestShipMovements {


	@Test
	public void GIVEN_validShipCoordinates_and_LeftMove_THEN_shipRotatesLeft() {
		Ship ship = new Ship(new Position(1,2,Orientation.N),nextMoveSequence(L),getStubbedShotCoordinates(),1);
		ship.move(getStubbedMaxLimitCoordinates());
		assertEquals(Orientation.W,ship.getCurrentPosition().getOrientation());
	}
	
	private Coordinate getStubbedMaxLimitCoordinates() {
		return new Coordinate(1000,1000);
	}

	private Coordinate getStubbedShotCoordinates() {
		// TODO Auto-generated method stub
		return new Coordinate(0, 0);
	}

	@Test
	public void GIVEN_validShipCoordinates_and_RightMove_THEN_shipRotatesRight() {
		Ship ship = new Ship(new Position(1,2,Orientation.N),nextMoveSequence(R),getStubbedShotCoordinates(),1);
		ship.move(getStubbedMaxLimitCoordinates());
		assertEquals(Orientation.E,ship.getCurrentPosition().getOrientation());
	}
	

	@Test
	public void GIVEN_ShipOrientationSouth_and_RightMove_THEN_shipOrientationWest() {
		Ship ship = new Ship(new Position(1,2,Orientation.S),nextMoveSequence(R),getStubbedShotCoordinates(),1);
		ship.move(getStubbedMaxLimitCoordinates());
		assertEquals(Orientation.W,ship.getCurrentPosition().getOrientation());
	}
	
	@Test
	public void GIVEN_ShipOrientationSouth_and_LeftMove_THEN_shipOrientationEast() {
		Ship ship = new Ship(new Position(1,2,Orientation.S),nextMoveSequence(L),getStubbedShotCoordinates(),1);
		ship.move(getStubbedMaxLimitCoordinates());
		assertEquals(Orientation.E,ship.getCurrentPosition().getOrientation());
	}
	
	@Test
	public void GIVEN_ShipOrientationWest_and_LeftMove_THEN_shipOrientationSouth() {
		Ship ship = new Ship(new Position(1,2,Orientation.W),nextMoveSequence(L),getStubbedShotCoordinates(),1);
		ship.move(getStubbedMaxLimitCoordinates());
		assertEquals(Orientation.S,ship.getCurrentPosition().getOrientation());
	}
	
	@Test
	public void GIVEN_ShipOrientationWest_and_RightMove_THEN_shipOrientationNorth() {
		Ship ship = new Ship(new Position(1,2,Orientation.W),nextMoveSequence(R),getStubbedShotCoordinates(),1);
		ship.move(getStubbedMaxLimitCoordinates());
		assertEquals(Orientation.N,ship.getCurrentPosition().getOrientation());
	}
	

	@Test
	public void GIVEN_ShipOrientationEast_and_LeftMove_THEN_shipOrientationNorth() {
		Ship ship = new Ship(new Position(1,2,Orientation.W),nextMoveSequence(L),getStubbedShotCoordinates(),1);
		ship.move(getStubbedMaxLimitCoordinates());
		assertEquals(Orientation.S,ship.getCurrentPosition().getOrientation());
	}
	
	@Test
	public void GIVEN_ShipOrientationEast_and_RightMove_THEN_shipOrientationSouth() {
		Ship ship = new Ship(new Position(1,2,Orientation.W),nextMoveSequence(R),getStubbedShotCoordinates(),1);
		ship.move(getStubbedMaxLimitCoordinates());
		assertEquals(Orientation.N,ship.getCurrentPosition().getOrientation());
	}
	
	@Test
	public void GIVEN_ShipOrientationNorth_and_MoveAhead_THEN_yCoordinateIncrementByOne() {
		Ship ship = new Ship(new Position(1,2,Orientation.N),nextMoveSequence(M),getStubbedShotCoordinates(),1);
		ship.move(getStubbedMaxLimitCoordinates());
		assertEquals(3,ship.getCurrentPosition().getCurrentCoordinates().getY());
		assertEquals(1,ship.getCurrentPosition().getCurrentCoordinates().getX());
		assertEquals(Orientation.N,ship.getCurrentPosition().getOrientation());
	}
	
	@Test
	public void GIVEN_ShipOrientationSouth_and_MoveAhead_THEN_yCoordinateDecrementByOne() {
		Ship ship = new Ship(new Position(1,2,Orientation.S),nextMoveSequence(M),getStubbedShotCoordinates(),1);
		ship.move(getStubbedMaxLimitCoordinates());
		assertEquals(1,ship.getCurrentPosition().getCurrentCoordinates().getY());
		assertEquals(1,ship.getCurrentPosition().getCurrentCoordinates().getX());
		assertEquals(Orientation.S,ship.getCurrentPosition().getOrientation());
	}
	
	@Test
	public void GIVEN_ShipOrientationEast_and_MoveAhead_THEN_xCoordinateInrementByOne() {
		Ship ship = new Ship(new Position(1,2,Orientation.E),nextMoveSequence(M),getStubbedShotCoordinates(),1);
		ship.move(getStubbedMaxLimitCoordinates());
		assertEquals(2,ship.getCurrentPosition().getCurrentCoordinates().getX());
		assertEquals(2,ship.getCurrentPosition().getCurrentCoordinates().getY());
		assertEquals(Orientation.E,ship.getCurrentPosition().getOrientation());
	}
	
	public void GIVEN_ShipOrientation_12N_Movement_LMLMLMLMM_THEN_ResultingShipOrientation_13N() {
		
		Ship ship = new Ship(new Position(1,2,Orientation.N),nextMoveSequence(L,M,L,M,L,M,L,M,M),getStubbedShotCoordinates(),1);
		ship.move(getStubbedMaxLimitCoordinates());
		assertEquals(Orientation.N,ship.getCurrentPosition().getOrientation());
		assertEquals(2,ship.getCurrentPosition().getCurrentCoordinates().getY());
		assertEquals(1,ship.getCurrentPosition().getCurrentCoordinates().getX());
	}
	
	public void GIVEN_ShipOrientation_33E_Movement_MMRMMRMRRM_THEN_ResultingShipOrientation_51E() {
		
		Ship ship = new Ship(new Position(3,3,Orientation.E),nextMoveSequence(M,M,R,M,M,R,M,R,R,M),getStubbedShotCoordinates(),1);
		ship.move(getStubbedMaxLimitCoordinates());
		assertEquals(Orientation.E,ship.getCurrentPosition().getOrientation());
		assertEquals(1,ship.getCurrentPosition().getCurrentCoordinates().getY());
		assertEquals(5,ship.getCurrentPosition().getCurrentCoordinates().getX());
	}

	private List<Move> nextMoveSequence(Move...actions) {
		return Arrays.asList(actions);
	}
	
}	
