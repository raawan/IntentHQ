package com.intentq.battlefield.api.dto;

import java.util.List;

import com.intentq.battlefield.api.constants.LifeStatus;
import com.intentq.battlefield.api.constants.Move;
import com.intentq.battlefield.api.constants.Orientation;
import com.intentq.battlefield.api.logic.IOrientationOfShipState;
import com.intentq.battlefield.api.logic.OrientationOfShipStateContext;
import com.intentq.battlefield.api.logic.OrientationOfShipStateFactory;

//ToDo: Check if Builder is useful?
public class Ship {
	
	private int id;
	private Position currentPosition;
	private LifeStatus lifeStatus = LifeStatus.ALIVE;
	private Action action;

	public Ship(Position currentPosition, List<Move> nextMoveSequence, Coordinate shot, int id) {
		this.currentPosition = currentPosition;
		action = new Action(nextMoveSequence,shot);
		this.setId(id);
	}

	public void move(Coordinate maxCoordinateLimit) {
		
		for(Move nextMove : getAction().getNextMoves()) {
			playMove(getCurrentState(),nextMove,maxCoordinateLimit);
		}
	}
	
	private IOrientationOfShipState getCurrentState() {
		return new OrientationOfShipStateContext(OrientationOfShipStateFactory.getState(this));
	}

	private void playMove(IOrientationOfShipState orientationOfShipState,Move nextMove, Coordinate maxCoordinateLimit) {
		switch(nextMove) {
			case L:
				orientationOfShipState.moveLeft();
				break;
			case M:
				orientationOfShipState.moveAhead(maxCoordinateLimit);
				break;
			case R:
				orientationOfShipState.moveRight();
				break;
		}		
	}

	public Position getCurrentPosition() {
		return currentPosition;
	}

	public void setCurrentPosition(Position currentPositionObject) {
		this.currentPosition = currentPositionObject;
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
	
	public Orientation getCurrentOrientation() {
		return this.getCurrentPosition().getOrientation();
	}
	
	public Coordinate getCurrentCoordinate() {
		return this.getCurrentPosition().getCurrentCoordinates();
	}
	
	public boolean isAlive() {
		return this.getLifeStatus().equals(LifeStatus.ALIVE);
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

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}
	
	@Override
	public String toString() {
		if(getLifeStatus().equals(LifeStatus.SUNK)) {
			return this.getCurrentPosition().toString()+" " + getLifeStatus();
		}
		return this.getCurrentPosition().toString(); 
	}
}
