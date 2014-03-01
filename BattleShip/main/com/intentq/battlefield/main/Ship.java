package com.intentq.battlefield.main;

import java.util.List;


public class Ship {
	
	private Position currentPosition;
	private List<Move> nextMoves;
	
	
	public Ship(Position currentPositionObject, List<Move> moves) {
		this.currentPosition = currentPositionObject;
		this.setNextMoves(moves);
	}

	public void move() {
		
		IOrientationOfShipState orientationOfShipState;
		
		for(Move nextMove : nextMoves) {
			orientationOfShipState = new OrientationOfShipStateContext(OrientationOfShipStateFactory.getState(this));
			if(nextMove.equals(Move.L)) {
				orientationOfShipState.moveLeft();
			} else if (nextMove.equals(Move.R)) {
				orientationOfShipState.moveRight();
			} else {
				orientationOfShipState.moveAhead();
			}
		}
	}
	

	public Position getCurrentPositionObject() {
		return currentPosition;
	}


	public void setCurrentPositionObject(Position currentPositionObject) {
		this.currentPosition = currentPositionObject;
	}

	public List<Move> getNextMoves() {
		return nextMoves;
	}

	public void setNextMoves(List<Move> nextMoves) {
		this.nextMoves = nextMoves;
	}

}
