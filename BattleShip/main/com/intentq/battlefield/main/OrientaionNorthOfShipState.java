package com.intentq.battlefield.main;

import com.intentq.battlefield.constants.Orientation;
import com.intentq.battlefield.dto.Ship;

public class OrientaionNorthOfShipState implements IOrientationOfShipState {

	public OrientaionNorthOfShipState(Ship ship) {
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
		ship.getCurrentPosition().setOrientation(Orientation.W);
		
	}

	@Override
	public void moveRight() {
		ship.getCurrentPosition().setOrientation(Orientation.E);
	}
	
	@Override
	public void moveAhead() {
		ship.getCurrentPosition().getCurrentCoordinates().setY(ship.getCurrentPosition().getCurrentCoordinates().getY()+1);
	}
}
