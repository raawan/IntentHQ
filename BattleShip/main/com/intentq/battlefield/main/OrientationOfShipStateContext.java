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
	public void moveLeft(Move move) {
		orientationOfShipState.moveLeft(move);
	}

	@Override
	public void moveRight(Move move) {
		orientationOfShipState.moveRight(move);
	}

	@Override
	public void moveAhead() {
		orientationOfShipState.moveAhead();
	}
}
