package com.intentq.battlefield.main;

import com.intentq.battlefield.dto.Coordinate;

public interface IOrientationOfShipState {
	public void moveLeft();
	public void moveRight();
	public void moveAhead(Coordinate maxCoordinateLimit);
}
