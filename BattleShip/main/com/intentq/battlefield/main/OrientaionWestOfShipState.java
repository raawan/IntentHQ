package com.intentq.battlefield.main;

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
	public void moveLeft(Move move) {
		ship.getCurrentPositionObject().setOrientation(Orientation.S);
		
	}

	@Override
	public void moveRight(Move move) {
		ship.getCurrentPositionObject().setOrientation(Orientation.N);
	}
	
	@Override
	public void moveAhead() {
		ship.getCurrentPositionObject().setX(ship.getCurrentPositionObject().getX()-1);
	}
}
