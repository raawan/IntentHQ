package com.intentq.battlefield.main;

import java.util.List;

public class Grid {
	
	private final Coordinate maxCoOrdinates;
	private List<Ship> shipsOnGrid;
	
	public Grid(int i, int j) {
		maxCoOrdinates = new Coordinate(i, j);
	}

	public List<Ship> getShipsOnGrid() {
		return shipsOnGrid;
	}

	public void setShipsOnGrid(List<Ship> shipsOnGrid) {
		this.shipsOnGrid = shipsOnGrid;
	}

	public Coordinate getMaxCoOrdinates() {
		return maxCoOrdinates;
	}

}
