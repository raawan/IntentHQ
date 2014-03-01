package com.intentq.battlefield.main;

public class Grid {
	
	private final int maxX;
	private final int maxY;
	
	public Grid(int i, int j) {
		this.maxX=i;
		this.maxY=j;
	}

	public int getMaxX() {
		return maxX;
	}

	public int getMaxY() {
		return maxY;
	}

}
