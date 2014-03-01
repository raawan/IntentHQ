package com.intentq.battlefield.test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.intentq.battlefield.main.Move;
import com.intentq.battlefield.main.Orientation;
import com.intentq.battlefield.main.Position;
import com.intentq.battlefield.main.Ship;
import static  com.intentq.battlefield.main.Move.*;

public class TestShipMovements {


	@Test
	public void GIVEN_validShipCoordinates_and_LeftMove_THEN_shipRotatesLeft() {
		Ship ship = new Ship(new Position(1,2,Orientation.N),nextMoveSequence(L));
		ship.move();
		assertEquals(Orientation.W,ship.getCurrentPositionObject().getOrientation());
	}
	
	@Test
	public void GIVEN_validShipCoordinates_and_RightMove_THEN_shipRotatesRight() {
		Ship ship = new Ship(new Position(1,2,Orientation.N),nextMoveSequence(R));
		ship.move();
		assertEquals(Orientation.E,ship.getCurrentPositionObject().getOrientation());
	}
	

	@Test
	public void GIVEN_ShipOrientationSouth_and_RightMove_THEN_shipOrientationWest() {
		Ship ship = new Ship(new Position(1,2,Orientation.S),nextMoveSequence(R));
		ship.move();
		assertEquals(Orientation.W,ship.getCurrentPositionObject().getOrientation());
	}
	
	@Test
	public void GIVEN_ShipOrientationSouth_and_LeftMove_THEN_shipOrientationEast() {
		Ship ship = new Ship(new Position(1,2,Orientation.S),nextMoveSequence(L));
		ship.move();
		assertEquals(Orientation.E,ship.getCurrentPositionObject().getOrientation());
	}
	
	@Test
	public void GIVEN_ShipOrientationWest_and_LeftMove_THEN_shipOrientationSouth() {
		Ship ship = new Ship(new Position(1,2,Orientation.W),nextMoveSequence(L));
		ship.move();
		assertEquals(Orientation.S,ship.getCurrentPositionObject().getOrientation());
	}
	
	@Test
	public void GIVEN_ShipOrientationWest_and_RightMove_THEN_shipOrientationNorth() {
		Ship ship = new Ship(new Position(1,2,Orientation.W),nextMoveSequence(R));
		ship.move();
		assertEquals(Orientation.N,ship.getCurrentPositionObject().getOrientation());
	}
	

	@Test
	public void GIVEN_ShipOrientationEast_and_LeftMove_THEN_shipOrientationNorth() {
		Ship ship = new Ship(new Position(1,2,Orientation.W),nextMoveSequence(L));
		ship.move();
		assertEquals(Orientation.S,ship.getCurrentPositionObject().getOrientation());
	}
	
	@Test
	public void GIVEN_ShipOrientationEast_and_RightMove_THEN_shipOrientationSouth() {
		Ship ship = new Ship(new Position(1,2,Orientation.W),nextMoveSequence(R));
		ship.move();
		assertEquals(Orientation.N,ship.getCurrentPositionObject().getOrientation());
	}
	
	@Test
	public void GIVEN_ShipOrientationNorth_and_MoveAhead_THEN_yCoordinateIncrementByOne() {
		Ship ship = new Ship(new Position(1,2,Orientation.N),nextMoveSequence(M));
		ship.move();
		assertEquals(3,ship.getCurrentPositionObject().getY());
		assertEquals(1,ship.getCurrentPositionObject().getX());
		assertEquals(Orientation.N,ship.getCurrentPositionObject().getOrientation());
	}
	
	@Test
	public void GIVEN_ShipOrientationSouth_and_MoveAhead_THEN_yCoordinateDecrementByOne() {
		Ship ship = new Ship(new Position(1,2,Orientation.S),nextMoveSequence(M));
		ship.move();
		assertEquals(1,ship.getCurrentPositionObject().getY());
		assertEquals(1,ship.getCurrentPositionObject().getX());
		assertEquals(Orientation.S,ship.getCurrentPositionObject().getOrientation());
	}
	
	@Test
	public void GIVEN_ShipOrientationEast_and_MoveAhead_THEN_xCoordinateInrementByOne() {
		Ship ship = new Ship(new Position(1,2,Orientation.E),nextMoveSequence(M));
		ship.move();
		assertEquals(2,ship.getCurrentPositionObject().getX());
		assertEquals(2,ship.getCurrentPositionObject().getY());
		assertEquals(Orientation.E,ship.getCurrentPositionObject().getOrientation());
	}
	
	public void GIVEN_ShipOrientation_12N_Movement_LMLMLMLMM_THEN_ResultingShipOrientation_13N() {
		
		Ship ship = new Ship(new Position(1,2,Orientation.N),nextMoveSequence(L,M,L,M,L,M,L,M,M));
		ship.move();
		assertEquals(Orientation.N,ship.getCurrentPositionObject().getOrientation());
		assertEquals(2,ship.getCurrentPositionObject().getY());
		assertEquals(1,ship.getCurrentPositionObject().getX());
	}
	
	private List<Move> nextMoveSequence(Move...actions) {
		return Arrays.asList(actions);
		
	}
	
}	
