package com.intentq.battlefield.main;

import com.intentq.battlefield.constants.Orientation;
import com.intentq.battlefield.dto.Coordinate;
import com.intentq.battlefield.dto.Ship;
import com.intentq.battlefield.exception.InvalidMoveSequenceException;

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
			throw new InvalidMoveSequenceException("A move from a moveSequence is going out of grid");
		}
	}
	
	private int getNextLocation() {
		return ship.getCurrentPosition().getCurrentCoordinates().getX()-1;
	}
}
