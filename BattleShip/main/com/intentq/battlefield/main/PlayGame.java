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
		for(Ship thisShip : grid.getShipsOnGrid()) {
			if(checkIfShipWantsToMove(thisShip)) {
				//ToDo: validate any move not falling off the grid
				//May be this is not the right place to validate, ship could be?
				Coordinate start = new Coordinate(thisShip.getCurrentPositionObject().getCurrentCoordinates().getX(), 
													thisShip.getCurrentPositionObject().getCurrentCoordinates().getY());
				Orientation startOrientation = thisShip.getCurrentPositionObject().getOrientation();
				thisShip.move();
				Coordinate end = thisShip.getCurrentPositionObject().getCurrentCoordinates();
				for(Ship ship : grid.getShipsOnGrid()) {
					if(ship.getId()!= thisShip.getId() && ship.getCurrentPositionObject().getCurrentCoordinates().equals(end) && ship.getLifeStatus().equals(LifeStatus.ALIVE)) {
						thisShip.setCurrentPositionObject(new Position(start.getX(), start.getY(), startOrientation));
						throw new InvalidMoveSequenceException("x:"+end.getX()+"-y:"+end.getY()+"position already occupied");
					} 
				}
			}
			if(checkIfShipWantsToShot(thisShip)) {
				updateShotShipStatus(thisShip);
			}
		}
	}
	
	private boolean checkIfShipWantsToShot(Ship thisShip) {
		return (thisShip.getShot()!=null);
	}

	private boolean checkIfShipWantsToMove(Ship thisShip) {
		return (thisShip.getNextMoves()!=null);
	}

	private void updateShotShipStatus(Ship thisShip) {
		for(Ship occupiedShipOnGrid : grid.getShipsOnGrid()) {
			if(checkIfShipsStatusToBeUpdatedToSunk(occupiedShipOnGrid,thisShip)) {
				updateShipsStatusWhichSankAndGridStatusWithTheSankedShip(occupiedShipOnGrid);
				
			}
		}		
	}
	
	private void updateShipsStatusWhichSankAndGridStatusWithTheSankedShip(
			Ship occupiedShipOnGrid) {
		updateLifeStatus(occupiedShipOnGrid);
	}

	private boolean checkIfShipsStatusToBeUpdatedToSunk(
			Ship occupiedShipOnGrid, Ship thisShip) {
		if(!checkIfThisOccupiedShipIsTheOneWhoAlsoShot(occupiedShipOnGrid,thisShip) &&
				checkIfThisOccupiedShipIsAlive(occupiedShipOnGrid) &&
				checkIfShotCoordinatesMatchesWithThisOccupiedShipCoordinates(occupiedShipOnGrid,thisShip)) {
			return true;
		}
		return false;
	}
	
	/*
	 * The ship is shot
	 */
	private void updateLifeStatus(Ship ship) {
		ship.setLifeStatus(LifeStatus.SUNK);
	}

	private boolean checkIfShotCoordinatesMatchesWithThisOccupiedShipCoordinates(
			Ship occupiedShipOnGrid, Ship thisShip) {
		return (occupiedShipOnGrid.getCurrentPositionObject().getCurrentCoordinates().getX() == thisShip.getShot().getX() &&
				occupiedShipOnGrid.getCurrentPositionObject().getCurrentCoordinates().getY() == thisShip.getShot().getY());
	}

	private boolean checkIfThisOccupiedShipIsAlive(Ship occupiedShipOnGrid) {
		return (occupiedShipOnGrid.getLifeStatus().equals(LifeStatus.ALIVE));
	}
	
	/*
	 * A ship dont want to shot itself
	 */
	private boolean checkIfThisOccupiedShipIsTheOneWhoAlsoShot(
			Ship occupiedShipOnGrid, Ship thisShip) {
		return (occupiedShipOnGrid.getId()==thisShip.getId());
	}
	
}
