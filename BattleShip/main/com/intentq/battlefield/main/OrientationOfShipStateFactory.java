package com.intentq.battlefield.main;

import com.intentq.battlefield.dto.Ship;

public class OrientationOfShipStateFactory {
	
	/*
	 * A DI container would be a better option
	 */
	public static IOrientationOfShipState getState(Ship ship)
	{
		switch(ship.getCurrentPositionObject().getOrientation()) {
			case N : return new OrientaionNorthOfShipState(ship);
			case S : return new OrientaionSouthOfShipState(ship);
			case E : return new OrientaionEastOfShipState(ship);
			case W : return new OrientaionWestOfShipState(ship);
		}
		return null;
	}
}
