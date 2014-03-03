package com.intentq.battlefield.test;

import static org.junit.Assert.*;
import static com.intentq.battlefield.api.constants.Move.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.intentq.battlefield.api.PlayGameApi;
import com.intentq.battlefield.api.constants.LifeStatus;
import com.intentq.battlefield.api.constants.Move;
import com.intentq.battlefield.api.constants.Orientation;
import com.intentq.battlefield.api.dto.Coordinate;
import com.intentq.battlefield.api.dto.Grid;
import com.intentq.battlefield.api.dto.Position;
import com.intentq.battlefield.api.dto.Ship;

public class TestPlayGame {

	private List<Move> nextMoveSequence(Move...actions) {
		return Arrays.asList(actions);
	}
	
	@Test
	public void GIVEN_TwoShips_WithMoveSequenceAndShots_The2ndShipShotHits1st_THEN_FirstShipSank() throws ClassNotFoundException {
		
		List<Ship> ships = new ArrayList<Ship>();
		ships.add(new Ship(new Position(1,2,Orientation.N),nextMoveSequence(L,M,L,M,L,M,L,M,M),new Coordinate(2,3),1));
		ships.add(new Ship(new Position(3,3,Orientation.E),nextMoveSequence(M,M,R,M,M,R,M,R,R,M),new Coordinate(1,3),2));
		PlayGameApi game = new PlayGameApi(new Grid(5,5),ships);
		game.play();
		Grid g = game.getGrid();
		List<Ship> newShips = new ArrayList<Ship>(g.getShipsOnGrid()); 
		Ship ship0 = newShips.get(0);
		Ship ship1 = newShips.get(1);
		assertEquals(LifeStatus.SUNK,ship0.getLifeStatus());
		assertEquals(LifeStatus.ALIVE,ship1.getLifeStatus());
		assertEquals(new Position(1, 3, Orientation.N),ship0.getCurrentPosition());
		assertEquals(new Position(5, 1, Orientation.E),ship1.getCurrentPosition());
	}
	
	@Test
	public void GIVEN_TwoShips_WithMoveSequenceAndShots_The2ndShipSHotButDidntHitTheFirstOne_THEN_BothShipsAreAlive() {
		
		List<Ship> ships = new ArrayList<Ship>();
		
		ships.add(new Ship(new Position(1,2,Orientation.N),nextMoveSequence(L,M,L,M,L,M,L,M,M),new Coordinate(2,3),1));
		ships.add(new Ship(new Position(3,3,Orientation.E),nextMoveSequence(M,M,R,M,M,R,M,R,R,M),new Coordinate(2,3),2));
		PlayGameApi game = new PlayGameApi(new Grid(5,5),ships);
		game.play();
		assertEquals(LifeStatus.ALIVE,ships.get(0).getLifeStatus());
		assertEquals(LifeStatus.ALIVE,ships.get(1).getLifeStatus());
		assertEquals(new Position(1, 3, Orientation.N),ships.get(0).getCurrentPosition());
		assertEquals(new Position(5, 1, Orientation.E),ships.get(1).getCurrentPosition());
	}
	
	@Test
	public void GIVEN_TwoShips_WithMoveSequenceAndShots_AndAnotherSequenceAndSHots_THEN_GameCanBePlayed() {
		
		List<Ship> ships = new ArrayList<Ship>();
		
		ships.add(new Ship(new Position(1,2,Orientation.N),nextMoveSequence(L,M,L,M,L,M,L,M,M),new Coordinate(2,3),1));
		ships.add(new Ship(new Position(3,3,Orientation.E),nextMoveSequence(M,M,R,M,M,R,M,R,R,M),new Coordinate(2,3),2));
		PlayGameApi game = new PlayGameApi(new Grid(5,5),ships);
		game.play();
		assertEquals(LifeStatus.ALIVE,ships.get(0).getLifeStatus());
		assertEquals(LifeStatus.ALIVE,ships.get(1).getLifeStatus());
		
		ships.get(0).getAction().setNextMoves(nextMoveSequence(L,M));
		ships.get(0).getAction().setShot(new Coordinate(3,3));
		ships.get(1).getAction().setNextMoves(nextMoveSequence(R,M));
		ships.get(1).getAction().setShot(new Coordinate(0,3));
		game.play();
		assertEquals(new Position(0, 3, Orientation.W),ships.get(0).getCurrentPosition());
		assertEquals(LifeStatus.SUNK,ships.get(0).getLifeStatus());
		assertEquals(LifeStatus.ALIVE,ships.get(1).getLifeStatus());
		
	}
	
	@Test
	public void GIVEN_MoreThanTwoShips_THEN_GameCanBePlayed() {
		
		List<Ship> ships = new ArrayList<Ship>();
        ships.add(new Ship(new Position(1,2,Orientation.N),nextMoveSequence(L,M,R,M,M,R,M,M),new Coordinate(2,4),1));
        ships.add(new Ship(new Position(3,3,Orientation.E),nextMoveSequence(M,M,R,R,M,M,R,R),new Coordinate(2,5),2));
        ships.add(new Ship(new Position(2,3,Orientation.S),nextMoveSequence(R,R,M,M,R,L),new Coordinate(3,3),3));
        PlayGameApi game = new PlayGameApi(new Grid(10,10),ships);
        game.play();
        assertEquals(LifeStatus.ALIVE,ships.get(0).getLifeStatus());
        assertEquals(LifeStatus.SUNK,ships.get(1).getLifeStatus());
        assertEquals(LifeStatus.ALIVE,ships.get(2).getLifeStatus());
        assertEquals(new Position(2, 4, Orientation.E),ships.get(0).getCurrentPosition());
        assertEquals(new Position(3, 3, Orientation.E),ships.get(1).getCurrentPosition());
        assertEquals(new Position(2, 5, Orientation.N),ships.get(2).getCurrentPosition());
	}
	
	@Test
	public void GIVEN_AsunkShip_THEN_NotAllowedToMoveAndThePositionIsUnchanged() {

		List<Ship> ships = new ArrayList<Ship>();
		ships.add(new Ship(new Position(1,2,Orientation.N),nextMoveSequence(L,M,R,M,M,R,M,M),new Coordinate(3,3),1));
		ships.add(new Ship(new Position(3,3,Orientation.E),nextMoveSequence(M,M,R,R,M,M,R,R),new Coordinate(2,5),2));
		ships.add(new Ship(new Position(2,3,Orientation.S),nextMoveSequence(M,M,M),new Coordinate(4,3),3));
		PlayGameApi game = new PlayGameApi(new Grid(10,10),ships);
		game.play();
		assertEquals(LifeStatus.ALIVE,ships.get(0).getLifeStatus());
		assertEquals(LifeStatus.SUNK,ships.get(1).getLifeStatus());
		assertEquals(LifeStatus.ALIVE,ships.get(2).getLifeStatus());
		assertEquals(new Position(2, 4, Orientation.E),ships.get(0).getCurrentPosition());
		assertEquals(new Position(3, 3, Orientation.E),ships.get(1).getCurrentPosition());
		assertEquals(new Position(2, 0, Orientation.S),ships.get(2).getCurrentPosition());

		ships = new ArrayList<Ship>();
		ships.add(new Ship(new Position(2,4,Orientation.E),nextMoveSequence(M,M),new Coordinate(3,3),1));
		ships.add(new Ship(new Position(3,3,Orientation.E),nextMoveSequence(L,M,R,M),new Coordinate(2,5),2));
		ships.add(new Ship(new Position(2,0,Orientation.S),nextMoveSequence(L,M,M),new Coordinate(4,3),3));
		assertEquals(new Position(3, 3, Orientation.E),ships.get(1).getCurrentPosition());
	}
	
	@Test
	public void GIVEN_GridPositionOccupiedByShip_And_AnotherShipWantsToMoveToSameLoction_THEN_ThePositionOfThatShipIsReset() {
		List<Ship> ships = new ArrayList<Ship>();
        ships.add(new Ship(new Position(1,2,Orientation.N),nextMoveSequence(L,M,R,M,M,R,M,M),new Coordinate(1,4),1));
        ships.add(new Ship(new Position(2,3,Orientation.N),nextMoveSequence(M),new Coordinate(2,5),2));
        PlayGameApi game = new PlayGameApi(new Grid(10,10),ships);
        game.play();
        assertEquals(new Position(2, 3, Orientation.N),ships.get(1).getCurrentPosition());
	}
	
	@Test
	public void GIVEN_GridPositionOccupiedBySunkedShip_And_AnotherShipWantsToMoveToSameLoction_THEN_NoInvalidMoveException() {
		List<Ship> ships = new ArrayList<Ship>();
        ships.add(new Ship(new Position(1,2,Orientation.N),nextMoveSequence(L,M,R,M,M,R,M,M),new Coordinate(1,4),1));
        ships.add(new Ship(new Position(2,3,Orientation.N),nextMoveSequence(M),new Coordinate(2,5),2));
        ships.get(0).setLifeStatus(LifeStatus.SUNK);
        PlayGameApi game = new PlayGameApi(new Grid(10,10),ships);
        game.play();
	}
}
