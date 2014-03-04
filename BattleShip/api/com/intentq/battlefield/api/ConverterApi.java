package com.intentq.battlefield.api;

import java.util.ArrayList;
import java.util.List;

import com.intentq.battlefield.api.constants.LifeStatus;
import com.intentq.battlefield.api.constants.Move;
import com.intentq.battlefield.api.constants.Orientation;
import com.intentq.battlefield.api.dto.Coordinate;
import com.intentq.battlefield.api.dto.Grid;
import com.intentq.battlefield.api.dto.Position;
import com.intentq.battlefield.api.validator.Validator;
import com.intentq.battlefield.api.validator.Validator.ThreeValuedObject;

public class ConverterApi implements IConverterApi {
	
	//toDo: validator should not be visible to clients
	private Validator validator;
	
	public ConverterApi(Validator validator) {
		this.validator = validator;
	}

	/*
	 * Grid coordinate input format = (x,y)
	 */
	@Override
	public Grid convertGridStringInputToGridObject(String gridCoordinateInString) {
		
		Coordinate obj = validator.validateGridCoordinate(gridCoordinateInString);
		return new Grid(obj.getX(),obj.getY());	
	}

	/*
	 * Ship position input format
	 * (X1,Y1,O1)<SPACE>(X1,Y1,O1)<SPACE>.........<SPACE>(Xn,Yn,On)<SPACE>
	 */
	@Override
	public List<Position> convertShipPositionStringInputToPositionObject(String shipCoordinatesAndOrientation, Grid grid) {
		
		List<ThreeValuedObject> objs = validator.validateShipPositions(shipCoordinatesAndOrientation,grid);
		return createPositionList(objs);
	}
	
	private List<Position> createPositionList(List<ThreeValuedObject> objs) {
		List<Position> positions = new ArrayList<Position>();
		for(ThreeValuedObject obj : objs) {
			positions.add(new Position(obj.getX(),obj.getY(),Orientation.valueOf(obj.getValue())));
		}
		return positions;
	}
	
	/*
	 * Shot coordinate input format = (x,y)
	 */
	@Override
	public Coordinate convertShotInStringToShotObject(String shotCoordinateInString, Grid grid) {
		
		return validator.validateShotCoordinates(shotCoordinateInString,grid);
	}

	/*
	 * Ship movements input format (x,y,LMMRRMMLL)
	 */
	@Override
	public List<Move> convertShipMovementInStringToShipsListOfMove(String shipMovementInString, Grid grid,LifeStatus lifeStatus) {
		
		ThreeValuedObject obj= validator.validateShipMovement(shipMovementInString,grid,lifeStatus);
		return convertToMoveListObject(obj);
	}
	
	private List<Move> convertToMoveListObject(ThreeValuedObject obj) {
		if(obj!=null) {
			StringBuilder moves = new StringBuilder(obj.getValue());
			int index=0;
			List<Move> movesList = new ArrayList<Move>();
			while(index<moves.length()) {
				movesList.add(Move.valueOf(moves.charAt(index)+""));
				index++;
			}
			return movesList;
		}
		return new ArrayList<Move>();
	}
	
}
