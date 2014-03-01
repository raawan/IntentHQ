package com.intentq.battlefield.main;


public class Ship {
	
	private Position currentPosition;
	private Action nextMove;
	
	
	public Ship(Position currentPositionObject, Action nextMoveObject) {
		this.currentPosition = currentPositionObject;
		this.nextMove = nextMoveObject;
	}

	public void move() {
		switch(currentPosition.getOrientation()) {
		case E:
			if(nextMove.equals(Action.L)) {
				currentPosition.setOrientation(Orientation.N);
			} else {
				currentPosition.setOrientation(Orientation.S);
			}
			break;
		case N:
			if(nextMove.equals(Action.L)) {
				currentPosition.setOrientation(Orientation.W);
			} else {
				currentPosition.setOrientation(Orientation.E);
			}
			break;
		case S:
			if(nextMove.equals(Action.L)) {
				currentPosition.setOrientation(Orientation.E);
			} else {
				currentPosition.setOrientation(Orientation.W);
			}
			break;
		case W:
			if(nextMove.equals(Action.L)) {
				currentPosition.setOrientation(Orientation.S);
			} else {
				currentPosition.setOrientation(Orientation.N);
			}
			break;
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
