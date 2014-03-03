package com.intentq.battlefield.main;

import com.intentq.battlefield.dto.Coordinate;

public class OrientationOfShipStateContext implements IOrientationOfShipState {
	
	public OrientationOfShipStateContext(
			IOrientationOfShipState orientationOfShipState) {
		this.orientationOfShipState = orientationOfShipState;
	}

	private final IOrientationOfShipState orientationOfShipState;
	
	public IOrientationOfShipState getOrientationOfShipState() {
		return orientationOfShipState;
	}

	@Override
	public void moveLeft() {
		orientationOfShipState.moveLeft();
	}

	@Override
	public void moveRight() {
		orientationOfShipState.moveRight();
	}

	@Override
	public void moveAhead(Coordinate moveLimit) {
		orientationOfShipState.moveAhead(moveLimit);
	}
}
