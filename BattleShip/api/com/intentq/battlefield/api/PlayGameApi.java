package com.intentq.battlefield.api;

import java.util.List;

import com.intentq.battlefield.api.constants.LifeStatus;
import com.intentq.battlefield.api.constants.Orientation;
import com.intentq.battlefield.api.dto.Coordinate;
import com.intentq.battlefield.api.dto.Grid;
import com.intentq.battlefield.api.dto.Position;
import com.intentq.battlefield.api.dto.Ship;
import com.intentq.battlefield.api.exception.InvalidMoveSequenceException;
import com.intentq.battlefield.api.util.ExceptionMessage;
import com.intentq.battlefield.api.validator.Validator;

public class PlayGameApi implements IPlayGameApi {
	
	private Grid grid;
	
	public PlayGameApi(Grid grid, List<Ship> ships) {
		this.grid=grid;
		if(grid!=null) {
			grid.setShipsOnGrid(ships);
		}
	}
	
	@Override
	public void setGrid(Grid grid) {
		this.grid = grid;
	}

	@Override
	public List<Ship> getShips() {
		return grid.getShipsOnGrid();
	}
	
	public Grid getGrid() {
		return grid;
	}

	/*
	 * Assumption: Every ship will move and shot in that ORDER during that ship's iteration
	 * 
	 */
	@Override
	public void play() {
		for(Ship thisShip : grid.getShipsOnGrid()) {
			try {
				moveShip(thisShip);
			} catch (InvalidMoveSequenceException ime) {
				continue;
			}
			takeShot(thisShip);
		}
	}
	
	private void takeShot(Ship thisShip) {
		if(checkIfShipWantsToShot(thisShip)) {
			updateShotShipStatus(thisShip);
		}		
	}

	private void moveShip(Ship thisShip) {
		if(checkIfShipWantsToMove(thisShip)) {
			validateAndMove(thisShip);
		}		
	}

	/*
	 * Throws InvalidMoveSequenceException - if ship moved to already occupied position
	 */
	private void validateAndMove(Ship thisShip) {
		Coordinate start = new Coordinate(thisShip.getCurrentCoordinate().getX(), 
				thisShip.getCurrentCoordinate().getY());
		Orientation startOrientation = thisShip.getCurrentOrientation();
		thisShip.move(this.getGrid().getMaxCoOrdinates());
		Coordinate end = thisShip.getCurrentCoordinate();
		if(checkIfThisShipCouldCollideToAnyExistingShipOnGrid(thisShip,start,startOrientation,end)) {
			resetShipPosition(thisShip,start,startOrientation);
			ExceptionMessage.throwPositionALreadyOccupiedException(end);
		}
	}

	private boolean checkIfThisShipCouldCollideToAnyExistingShipOnGrid(Ship thisShip, Coordinate start, Orientation startOrientation, Coordinate end) {
		for(Ship ship : grid.getShipsOnGrid()) {
			if(isShipCanMoveToExistingLocation(thisShip,ship)) {
				return true;
			} 
		}
		return false;
	}

	private void resetShipPosition(Ship thisShip, Coordinate start,
			Orientation startOrientation) {
		thisShip.setCurrentPosition(new Position(start.getX(), start.getY(), startOrientation));		
	}

	private boolean isShipCanMoveToExistingLocation(Ship thisShip, Ship ship) {
		return ship.getId()!= thisShip.getId() && ship.getCurrentCoordinate().equals(thisShip.getCurrentCoordinate()) && ship.isAlive();
	}

	private boolean checkIfShipWantsToShot(Ship thisShip) {
		return (thisShip.getAction().getShot()!=null);
	}

	private boolean checkIfShipWantsToMove(Ship thisShip) {
		return (thisShip.getAction().getNextMoves()!=null);
	}

	private void updateShotShipStatus(Ship thisShip) {
		for(Ship occupiedShipOnGrid : grid.getShipsOnGrid()) {
			if(checkIfShipsStatusToBeUpdatedToSunk(occupiedShipOnGrid,thisShip)) {
				updateLifeStatus(occupiedShipOnGrid);
			}
		}		
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

	private boolean checkIfShotCoordinatesMatchesWithThisOccupiedShipCoordinates(Ship occupiedShipOnGrid, Ship thisShip) {
		return (occupiedShipOnGrid.getCurrentCoordinate().equals(thisShip.getAction().getShot()));
	}

	private boolean checkIfThisOccupiedShipIsAlive(Ship occupiedShipOnGrid) {
		return new Validator().validateShipAlive(occupiedShipOnGrid);
	}
	
	/*
	 * A ship dont want to shot itself
	 */
	private boolean checkIfThisOccupiedShipIsTheOneWhoAlsoShot(
			Ship occupiedShipOnGrid, Ship thisShip) {
		return (occupiedShipOnGrid.getId()==thisShip.getId());
	}

}
