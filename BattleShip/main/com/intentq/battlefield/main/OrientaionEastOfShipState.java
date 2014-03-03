package com.intentq.battlefield.main;

import com.intentq.battlefield.constants.Orientation;
import com.intentq.battlefield.dto.Coordinate;
import com.intentq.battlefield.dto.Ship;
import com.intentq.battlefield.exception.InvalidMoveSequenceException;
import com.intentq.battlefield.util.ExceptionMessage;

public class OrientaionEastOfShipState implements IOrientationOfShipState {

	public OrientaionEastOfShipState(Ship ship) {
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
		ship.getCurrentPosition().setOrientation(Orientation.N);
		
	}

	@Override
	public void moveRight() {
		ship.getCurrentPosition().setOrientation(Orientation.S);
	}
	
	@Override
	public void moveAhead(Coordinate moveLimit) {
		int nextLocation = getNextLocation();
		if(nextLocation<= moveLimit.getX()) {
			ship.getCurrentPosition().getCurrentCoordinates().setX(nextLocation);
		} else {
			ExceptionMessage.throwMoveGoingOutOfGridException();
		}
	}
	
	private int getNextLocation() {
		return ship.getCurrentPosition().getCurrentCoordinates().getX()+1;
	}
}
