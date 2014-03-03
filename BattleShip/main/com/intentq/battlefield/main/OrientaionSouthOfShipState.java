package com.intentq.battlefield.main;

import com.intentq.battlefield.constants.Orientation;
import com.intentq.battlefield.dto.Coordinate;
import com.intentq.battlefield.dto.Ship;
import com.intentq.battlefield.exception.InvalidMoveSequenceException;

public class OrientaionSouthOfShipState implements IOrientationOfShipState {
	
	public OrientaionSouthOfShipState(Ship ship) {
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
		ship.getCurrentPosition().setOrientation(Orientation.E);
		
	}

	@Override
	public void moveRight() {
		ship.getCurrentPosition().setOrientation(Orientation.W);
	}
	
	@Override
	public void moveAhead(Coordinate moveLimit) {
		int nextLocation = getNextLocation();
		if(nextLocation >= 0) {
			ship.getCurrentPosition().getCurrentCoordinates().setY(nextLocation);
		} else {
			throw new InvalidMoveSequenceException("A move from a moveSequence is going out of grid");
		}
	}
	
	private int getNextLocation() {
		return ship.getCurrentPosition().getCurrentCoordinates().getY()-1;
	}
}
