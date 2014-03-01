package com.intentq.battlefield.main;


public class Ship {
	
	private Position currentPosition;
	private Action nextMove;
	
	
	public Ship(Position currentPositionObject, Action nextMoveObject) {
		this.currentPosition = currentPositionObject;
		this.nextMove = nextMoveObject;
	}

	public void move() {
		
		IOrientationOfShipState orientationOfShipState = new OrientationOfShipStateContext(OrientationOfShipStateFactory.getState(this));
		if(nextMove.equals(Action.L)) {
			orientationOfShipState.moveLeft(getNextMoveObject());
		} else if (nextMove.equals(Action.R)) {
			orientationOfShipState.moveRight(getNextMoveObject());
		} else {
			orientationOfShipState.moveAhead();
		}
	}
	

	public Position getCurrentPositionObject() {
		return currentPosition;
	}


	public void setCurrentPositionObject(Position currentPositionObject) {
		this.currentPosition = currentPositionObject;
	}


	public Action getNextMoveObject() {
		return nextMove;
	}


	public void setNextMoveObject(Action nextMoveObject) {
		this.nextMove = nextMoveObject;
	}



}
