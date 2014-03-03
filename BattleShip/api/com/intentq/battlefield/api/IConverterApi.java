package com.intentq.battlefield.api;

import java.util.List;

import com.intentq.battlefield.api.constants.LifeStatus;
import com.intentq.battlefield.api.constants.Move;
import com.intentq.battlefield.api.dto.Coordinate;
import com.intentq.battlefield.api.dto.Grid;
import com.intentq.battlefield.api.dto.Position;

public interface IConverterApi {

	Grid convertGridStringInputToGridObject(String gridCoordinateInString);

	List<Position> convertShipPositionStringInputToPositionObject(
			String shipCoordinatesAndOrientation, Grid grid);

	Coordinate convertShotInStringToShotObject(String shotCoordinateInString,
			Grid grid);

	List<Move> convertShipMovementInStringToShipsListOfMove(
			String shipMovementInString, Grid grid,LifeStatus lifeStatus);

}
