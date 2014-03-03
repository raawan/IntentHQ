package com.intentq.battlefield.api.logic;

import com.intentq.battlefield.api.dto.Coordinate;

public interface IOrientationOfShipState {
	public void moveLeft();
	public void moveRight();
	public void moveAhead(Coordinate maxCoordinateLimit);
}
