package com.intentq.battlefield.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.intentq.battlefield.main.Action;
import com.intentq.battlefield.main.Orientation;
import com.intentq.battlefield.main.Position;
import com.intentq.battlefield.main.Ship;

public class TestShipMovements {


	@Test
	public void GIVEN_validShipCoordinates_and_LeftMove_THEN_shipRotatesLeft() {
		Ship ship = new Ship(new Position(1,2,Orientation.N),Action.L);
		ship.move();
		assertEquals(Orientation.W,ship.getCurrentPositionObject().getOrientation());
	}
	
	@Test
	public void GIVEN_validShipCoordinates_and_RightMove_THEN_shipRotatesRight() {
		Ship ship = new Ship(new Position(1,2,Orientation.N),Action.R);
		ship.move();
		assertEquals(Orientation.E,ship.getCurrentPositionObject().getOrientation());
	}
	

	@Test
	public void GIVEN_ShipOrientationSouth_and_RightMove_THEN_shipOrientationWest() {
		Ship ship = new Ship(new Position(1,2,Orientation.S),Action.R);
		ship.move();
		assertEquals(Orientation.W,ship.getCurrentPositionObject().getOrientation());
	}
	
	@Test
	public void GIVEN_ShipOrientationSouth_and_LeftMove_THEN_shipOrientationEast() {
		Ship ship = new Ship(new Position(1,2,Orientation.S),Action.L);
		ship.move();
		assertEquals(Orientation.E,ship.getCurrentPositionObject().getOrientation());
	}
	
	@Test
	public void GIVEN_ShipOrientationWest_and_LeftMove_THEN_shipOrientationSouth() {
		Ship ship = new Ship(new Position(1,2,Orientation.W),Action.L);
		ship.move();
		assertEquals(Orientation.S,ship.getCurrentPositionObject().getOrientation());
	}
	
	@Test
	public void GIVEN_ShipOrientationWest_and_RightMove_THEN_shipOrientationNorth() {
		Ship ship = new Ship(new Position(1,2,Orientation.W),Action.R);
		ship.move();
		assertEquals(Orientation.N,ship.getCurrentPositionObject().getOrientation());
	}
	

	@Test
	public void GIVEN_ShipOrientationEast_and_LeftMove_THEN_shipOrientationNorth() {
		Ship ship = new Ship(new Position(1,2,Orientation.W),Action.L);
		ship.move();
		assertEquals(Orientation.S,ship.getCurrentPositionObject().getOrientation());
	}
	
	@Test
	public void GIVEN_ShipOrientationEast_and_RightMove_THEN_shipOrientationSouth() {
		Ship ship = new Ship(new Position(1,2,Orientation.W),Action.R);
		ship.move();
		assertEquals(Orientation.N,ship.getCurrentPositionObject().getOrientation());
	}
	
	@Test
	public void GIVEN_ShipOrientationNorth_and_MoveAhead_THEN_xCoordinateIncrementByOne() {
		Ship ship = new Ship(new Position(1,2,Orientation.N),Action.M);
		ship.move();
		assertEquals(2,ship.getCurrentPositionObject().getX());
	}
}
