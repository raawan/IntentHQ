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
		ship.getCurrentPositionObject().setOrientation(Orientation.W);
		
	}

	@Override
	public void moveRight() {
		ship.getCurrentPositionObject().setOrientation(Orientation.E);
	}
	
	@Override
	public void moveAhead() {
		ship.getCurrentPositionObject().getCurrentCoordinates().setY(ship.getCurrentPositionObject().getCurrentCoordinates().getY()+1);
	}
}
