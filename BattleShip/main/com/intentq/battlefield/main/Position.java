package com.intentq.battlefield.main;

public class Position {
	
	private int x;
	private int y;
	private Orientation orientation;
	
	public Position(int i, int j, Orientation n) {
		this.x = i;
		this.y = j;
		this.orientation = n;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Orientation getOrientation() {
		return orientation;
	}

	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}

}
