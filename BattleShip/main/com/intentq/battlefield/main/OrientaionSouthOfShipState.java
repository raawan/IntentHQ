package com.intentq.battlefield.main;

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
	public void moveLeft(Action action) {
		ship.getCurrentPositionObject().setOrientation(Orientation.E);
		
	}

	@Override
	public void moveRight(Action action) {
		ship.getCurrentPositionObject().setOrientation(Orientation.W);
	}

}
