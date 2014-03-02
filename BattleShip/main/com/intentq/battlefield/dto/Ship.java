package com.intentq.battlefield.dto;

import java.util.List;

import com.intentq.battlefield.constants.LifeStatus;
import com.intentq.battlefield.constants.Move;
import com.intentq.battlefield.constants.Orientation;
import com.intentq.battlefield.main.IOrientationOfShipState;
import com.intentq.battlefield.main.OrientationOfShipStateContext;
import com.intentq.battlefield.main.OrientationOfShipStateFactory;

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


	public void move() {
		
		IOrientationOfShipState orientationOfShipState;
		for(Move nextMove : getAction().getNextMoves()) {
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
}
