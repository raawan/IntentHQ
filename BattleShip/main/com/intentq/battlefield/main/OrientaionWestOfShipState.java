package com.intentq.battlefield.main;

import com.intentq.battlefield.constants.Orientation;
import com.intentq.battlefield.dto.Ship;

public class OrientaionWestOfShipState implements IOrientationOfShipState {

	public OrientaionWestOfShipState(Ship ship) {
		this.ship = ship;
	}

	private Ship ship;
	
	public Ship getShip() {
		return ship;
	}

	public void setShip(Ship ship) {
		this.ship = ship;
	}

	@Override
	public void moveLeft() {
		ship.getCurrentPosition().setOrientation(Orientation.S);
		
	}

	@Override
	public void moveRight() {
		ship.getCurrentPosition().setOrientation(Orientation.N);
	}
	
	@Override
	public void moveAhead() {
		ship.getCurrentPosition().getCurrentCoordinates().setX(ship.getCurrentPosition().getCurrentCoordinates().getX()-1);
	}
}
