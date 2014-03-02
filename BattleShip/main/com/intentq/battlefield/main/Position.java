package com.intentq.battlefield.main;

public class Position {
	
	private ShotCoordinate currentCoordinates;
	private Orientation orientation;
	
	public Position(int i, int j, Orientation n) {
		this.orientation = n;
		this.setCurrentCoordinates(new ShotCoordinate(i, j));
	}
	

	public Orientation getOrientation() {
		return orientation;
	}

	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}


	public ShotCoordinate getCurrentCoordinates() {
		return currentCoordinates;
	}


	public void setCurrentCoordinates(ShotCoordinate currentCoordinates) {
		this.currentCoordinates = currentCoordinates;
	}

}
