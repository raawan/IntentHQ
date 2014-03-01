package com.intentq.battlefield.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.intentq.battlefield.main.Action;
import com.intentq.battlefield.main.Orientation;
import com.intentq.battlefield.main.Position;
import com.intentq.battlefield.main.Ship;

public class TestShipMovements {


	@Test
	public void GIVEN_validShipCoordinates_and_leftMove_THEN_shipRotatesLeft() {
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
	public void GIVEN_ShipOrientationSouth_and_LEftMove_THEN_shipOrientationEast() {
		Ship ship = new Ship(new Position(1,2,Orientation.S),Action.L);
		ship.move();
		assertEquals(Orientation.E,ship.getCurrentPositionObject().getOrientation());
	}
	
}
