package com.intentq.battlefield.api.dto;

import com.intentq.battlefield.api.constants.Orientation;

public class Position {
	
	private Coordinate currentCoordinates;
	private Orientation orientation;
	
	public Position(int i, int j, Orientation n) {
		this.orientation = n;
		this.setCurrentCoordinates(new Coordinate(i, j));
	}
	

	public Orientation getOrientation() {
		return orientation;
	}

	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}


	public Coordinate getCurrentCoordinates() {
		return currentCoordinates;
	}


	public void setCurrentCoordinates(Coordinate currentCoordinates) {
		this.currentCoordinates = currentCoordinates;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) 
			return false;
		if(obj == this) 
			return true;
		if(!(obj instanceof Position))
			return false;
		
		Position pos = (Position) obj;
		
		if(this.getCurrentCoordinates().equals(pos.getCurrentCoordinates()) &&
			this.getOrientation().equals(pos.getOrientation())) {
				return true;
			}
		return false;	
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + this.getCurrentCoordinates().hashCode();
		result = prime * result + this.getOrientation().ordinal();
		return result;
	}
	
	@Override
	public String toString() {
		return "(" + this.getCurrentCoordinates().getX() + "," + this.getCurrentCoordinates().getY() + "," + this.getOrientation() + ")"; 
	}
}
