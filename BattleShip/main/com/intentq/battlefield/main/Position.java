package com.intentq.battlefield.main;

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

}
