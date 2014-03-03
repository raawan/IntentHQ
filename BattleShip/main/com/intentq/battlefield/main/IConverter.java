package com.intentq.battlefield.main;

import java.util.List;

import com.intentq.battlefield.constants.LifeStatus;
import com.intentq.battlefield.constants.Move;
import com.intentq.battlefield.dto.Coordinate;
import com.intentq.battlefield.dto.Grid;
import com.intentq.battlefield.dto.Position;

public interface IConverter {

	Grid convertGridStringInputToGridObject(String gridCoordinateInString);

	List<Position> convertShipPositionStringInputToPositionObject(
			String shipCoordinatesAndOrientation, Grid grid);

	Coordinate convertShotInStringToShotObject(String shotCoordinateInString,
			Grid grid);

	List<Move> convertShipMovementInStringToShipsListOfMove(
			String shipMovementInString, Grid grid,LifeStatus lifeStatus);

}
