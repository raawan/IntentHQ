package com.intentq.battlefield.main;

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
		ship.getCurrentPositionObject().setOrientation(Orientation.N);
		
	}

	@Override
	public void moveRight() {
		ship.getCurrentPositionObject().setOrientation(Orientation.S);
	}
	
	@Override
	public void moveAhead() {
		ship.getCurrentPositionObject().getCurrentCoordinates().setX(ship.getCurrentPositionObject().getCurrentCoordinates().getX()+1);
	}
}
