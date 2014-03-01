package com.intentq.battlefield.test;

import static org.junit.Assert.*;
import static  com.intentq.battlefield.main.Move.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.intentq.battlefield.main.Grid;
import com.intentq.battlefield.main.LifeStatus;
import com.intentq.battlefield.main.Move;
import com.intentq.battlefield.main.Orientation;
import com.intentq.battlefield.main.PlayGame;
import com.intentq.battlefield.main.Position;
import com.intentq.battlefield.main.Ship;
import com.intentq.battlefield.main.ShotCoordinate;

public class TestBattleShipShots {

	private List<Move> nextMoveSequence(Move...actions) {
		return Arrays.asList(actions);
	}
	
	@Test
	public void GIVEN_TwoShips_WithMoveSequenceAndShots_The2ndShipShotHits1st_THEN_FirstShipSank() {
		
		List<Ship> ships = new ArrayList<Ship>();
		ships.add(new Ship(new Position(1,2,Orientation.N),nextMoveSequence(L,M,L,M,L,M,L,M,M),new ShotCoordinate(2,3),1));
		ships.add(new Ship(new Position(3,3,Orientation.E),nextMoveSequence(M,M,R,M,M,R,M,R,R,M),new ShotCoordinate(2,3),2));
		PlayGame game = new PlayGame(new Grid(5,5),ships);
		game.play();
		assertEquals(LifeStatus.SUNK,ships.get(0).getStatus());
		assertEquals(LifeStatus.ALIVE,ships.get(1).getStatus());
		
	}

}
