package com.intentq.battlefield.main;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestShipMovements {

	@Test
	public void GIVEN_validShipCoordinates_and_leftMove_THEN_shipRotatesLeft() {
		Ship ship = new Ship("12N","L");
		ship.move();
		assertEquals("12W",ship.getCurrentPosition());
	}
	
	@Test
	public void GIVEN_validShipCoordinates_and_RightMove_THEN_shipRotatesRight() {
		Ship ship = new Ship("12N","R");
		ship.move();
		assertEquals("12E",ship.getCurrentPosition());
	}
	
}
