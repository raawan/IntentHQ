package com.intentq.battlefield.main;

import java.util.List;

public class PlayGame {
	
	private final Grid grid;
	
	public PlayGame(Grid grid, List<Ship> ships) {
		this.grid=grid;
		if(grid!=null) {
			grid.setShipsOnGrid(ships);
		}
	}

	public Grid getGrid() {
		return grid;
	}


	public void play() {
		for(Ship thisShip : grid.getShipsOnGrid()) 
		{
			if(thisShip.getNextMoves()!=null) {
				//ToDo: validate any move not falling off the grid
				//May be this is not the right place to validate, ship could be?
				thisShip.move();
			}
			if(thisShip.getShot()!=null) {
				for(Ship occupiedShipOnGrid : grid.getShipsOnGrid())
				{
					if(occupiedShipOnGrid.getId()!=thisShip.getId() &&
							occupiedShipOnGrid.getLifeStatus().equals(LifeStatus.ALIVE) &&
							occupiedShipOnGrid.getCurrentPositionObject().getX() == thisShip.getShot().getX() &&
							occupiedShipOnGrid.getCurrentPositionObject().getY() == thisShip.getShot().getY()) {
						occupiedShipOnGrid.setLifeStatus(LifeStatus.SUNK);
					}
				}
			}
		}
		
	}
	
}
