package com.intentq.battlefield.main;

import java.util.List;

public class Grid {
	
	private final int maxX;
	private final int maxY;
	private List<Ship> shipsOnGrid;
	
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

	public List<Ship> getShipsOnGrid() {
		return shipsOnGrid;
	}

	public void setShipsOnGrid(List<Ship> shipsOnGrid) {
		this.shipsOnGrid = shipsOnGrid;
	}

}
