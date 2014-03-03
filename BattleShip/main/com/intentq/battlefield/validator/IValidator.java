package com.intentq.battlefield.validator;

import java.util.List;

import com.intentq.battlefield.constants.LifeStatus;
import com.intentq.battlefield.dto.Coordinate;
import com.intentq.battlefield.dto.Grid;
import com.intentq.battlefield.dto.Ship;
import com.intentq.battlefield.validator.Validator.ThreeValuedObject;

public interface IValidator {

	Coordinate validateGridCoordinate(String gridCoordinateInString);

	List<ThreeValuedObject> validateShipPositions(
			String shipCoordinatesAndOrientation, Grid grid);

	Coordinate validateShotCoordinates(String shotCoordinateInString, Grid grid);

	ThreeValuedObject validateShipMovement(String shipMovementInString,	Grid grid, LifeStatus lifeStatus);

	boolean validateShipAlive(Ship s);

}
