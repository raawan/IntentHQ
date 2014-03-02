package com.intentq.battlefield.dto;

import java.util.List;

import com.intentq.battlefield.constants.LifeStatus;
import com.intentq.battlefield.constants.Move;
import com.intentq.battlefield.main.IOrientationOfShipState;
import com.intentq.battlefield.main.OrientationOfShipStateContext;
import com.intentq.battlefield.main.OrientationOfShipStateFactory;

//ToDo: Check if Builder is useful?

public class Ship {
	
	private int id;
	private Position currentPosition;
	private List<Move> nextMoves;
	private LifeStatus lifeStatus = LifeStatus.ALIVE;
	private Coordinate shot;
	
	public Ship(Position currentPositionObject, List<Move> nextMoves, int id) {
		this.currentPosition = currentPositionObject;
		this.setNextMoves(nextMoves);
		this.setId(id);
	}

	public Ship(Position currentPosition, List<Move> nextMoveSequence, Coordinate shot, int id) {
		this.currentPosition = currentPosition;
		this.setNextMoves(nextMoveSequence);
		this.setShot(shot);
		this.setId(id);
	}

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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LifeStatus getLifeStatus() {
		return lifeStatus;
	}

	public void setLifeStatus(LifeStatus lifeStatus) {
		this.lifeStatus = lifeStatus;
	}

	public Coordinate getShot() {
		return shot;
	}

	public void setShot(Coordinate shot) {
		this.shot = shot;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) 
			return false;
		if(obj == this) 
			return true;
		if(!(obj instanceof Position))
			return false;
		
		Ship ship = (Ship) obj;
		
		if(this.getId()==ship.getId()) {
				return true;
			}
		return false;	
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + this.getId();
		return result;
	}
}
