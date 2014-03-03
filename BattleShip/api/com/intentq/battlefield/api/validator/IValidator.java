package com.intentq.battlefield.api.validator;

import java.util.List;

import com.intentq.battlefield.api.constants.LifeStatus;
import com.intentq.battlefield.api.dto.Coordinate;
import com.intentq.battlefield.api.dto.Grid;
import com.intentq.battlefield.api.dto.Ship;
import com.intentq.battlefield.api.validator.Validator.ThreeValuedObject;

public interface IValidator {

	Coordinate validateGridCoordinate(String gridCoordinateInString);

	List<ThreeValuedObject> validateShipPositions(
			String shipCoordinatesAndOrientation, Grid grid);

	Coordinate validateShotCoordinates(String shotCoordinateInString, Grid grid);

	ThreeValuedObject validateShipMovement(String shipMovementInString,	Grid grid, LifeStatus lifeStatus);

	boolean validateShipAlive(Ship s);

}
