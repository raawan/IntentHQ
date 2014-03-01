package com.intentq.battlefield.main;

import java.util.List;

public class PlayGame {
	
	private final Grid grid;
	private final List<Ship> ships;
	
	public PlayGame(Grid grid, List<Ship> ships) {
		this.grid=grid;
		this.ships=ships;
	}

	public Grid getGrid() {
		return grid;
	}

	public List<Ship> getShips() {
		return ships;
	}

	public void play() {
		// TODO Auto-generated method stub
		
	}
	
}
