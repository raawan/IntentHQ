package com.intentq.battlefield.main;

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
	public void moveLeft(Action action) {
		orientationOfShipState.moveLeft(action);
	}

	@Override
	public void moveRight(Action action) {
		orientationOfShipState.moveRight(action);
	}

	@Override
	public void moveAhead() {
		orientationOfShipState.moveAhead();
	}
}
