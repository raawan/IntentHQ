package com.intentq.battlefield.api.logic;

import com.intentq.battlefield.api.constants.Orientation;
import com.intentq.battlefield.api.dto.Coordinate;
import com.intentq.battlefield.api.dto.Ship;
import com.intentq.battlefield.api.util.ExceptionMessage;

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
	public void moveAhead(Coordinate moveLimit) {
		int nextLocation = getNextLocation();
		if(nextLocation>=0) {
			ship.getCurrentPosition().getCurrentCoordinates().setX(nextLocation);
		} else {
			ExceptionMessage.throwMoveGoingOutOfGridException();
		}
	}
	
	private int getNextLocation() {
		return ship.getCurrentPosition().getCurrentCoordinates().getX()-1;
	}
}
