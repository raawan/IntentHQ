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
	public void GIVEN_TwoShips_WithMoveSequenceAndShots_The2ndShipShotHits1st_THEN_FirstShipSank() throws ClassNotFoundException {
		
		List<Ship> ships = new ArrayList<Ship>();
		ships.add(new Ship(new Position(1,2,Orientation.N),nextMoveSequence(L,M,L,M,L,M,L,M,M),new ShotCoordinate(2,3),1));
		ships.add(new Ship(new Position(3,3,Orientation.E),nextMoveSequence(M,M,R,M,M,R,M,R,R,M),new ShotCoordinate(1,3),2));
		PlayGame game = new PlayGame(new Grid(5,5),ships);
		game.play();
		Grid g = game.getGrid();
		List<Ship> newShips = new ArrayList<Ship>(g.getShipsOnGrid()); 
		Ship ship0 = newShips.get(0);
		Ship ship1 = newShips.get(1);
		assertEquals(LifeStatus.SUNK,ship0.getLifeStatus());
		assertEquals(LifeStatus.ALIVE,ship1.getLifeStatus());
		assertEquals(Orientation.N,ship0.getCurrentPositionObject().getOrientation());
		assertEquals(1,ship0.getCurrentPositionObject().getCurrentCoordinates().getX());
		assertEquals(3,ship0.getCurrentPositionObject().getCurrentCoordinates().getY());
		assertEquals(Orientation.E,ship1.getCurrentPositionObject().getOrientation());
		assertEquals(5,ship1.getCurrentPositionObject().getCurrentCoordinates().getX());
		assertEquals(1,ship1.getCurrentPositionObject().getCurrentCoordinates().getY());
	}
	
	@Test
	public void GIVEN_TwoShips_WithMoveSequenceAndShots_The2ndShipSHotButDidntHitTheFirstOne_THEN_BothShipsAreAlive() {
		
		List<Ship> ships = new ArrayList<Ship>();
		
		ships.add(new Ship(new Position(1,2,Orientation.N),nextMoveSequence(L,M,L,M,L,M,L,M,M),new ShotCoordinate(2,3),1));
		ships.add(new Ship(new Position(3,3,Orientation.E),nextMoveSequence(M,M,R,M,M,R,M,R,R,M),new ShotCoordinate(2,3),2));
		PlayGame game = new PlayGame(new Grid(5,5),ships);
		game.play();
		assertEquals(LifeStatus.ALIVE,ships.get(0).getLifeStatus());
		assertEquals(1,ships.get(0).getCurrentPositionObject().getCurrentCoordinates().getX());
		assertEquals(3,ships.get(0).getCurrentPositionObject().getCurrentCoordinates().getY());
		assertEquals(LifeStatus.ALIVE,ships.get(1).getLifeStatus());
		assertEquals(5,ships.get(1).getCurrentPositionObject().getCurrentCoordinates().getX());
		assertEquals(1,ships.get(1).getCurrentPositionObject().getCurrentCoordinates().getY());
	}
	
	@Test
	public void GIVEN_TwoShips_WithMoveSequenceAndShots_AndAnotherSequenceAndSHots_THEN_GameCanBePlayed() {
		
		List<Ship> ships = new ArrayList<Ship>();
		
		ships.add(new Ship(new Position(1,2,Orientation.N),nextMoveSequence(L,M,L,M,L,M,L,M,M),new ShotCoordinate(2,3),1));
		ships.add(new Ship(new Position(3,3,Orientation.E),nextMoveSequence(M,M,R,M,M,R,M,R,R,M),new ShotCoordinate(2,3),2));
		PlayGame game = new PlayGame(new Grid(5,5),ships);
		game.play();
		assertEquals(LifeStatus.ALIVE,ships.get(0).getLifeStatus());
		assertEquals(LifeStatus.ALIVE,ships.get(1).getLifeStatus());
		
		ships.get(0).setNextMoves(nextMoveSequence(L,M));
		ships.get(0).setShot(new ShotCoordinate(3,3));
		ships.get(0).setNextMoves(nextMoveSequence(R,M));
		ships.get(1).setShot(new ShotCoordinate(2,3));
		game.play();
		assertEquals(2,ships.get(0).getCurrentPositionObject().getCurrentCoordinates().getX());
		assertEquals(3,ships.get(0).getCurrentPositionObject().getCurrentCoordinates().getY());
		assertEquals(LifeStatus.SUNK,ships.get(0).getLifeStatus());
		assertEquals(LifeStatus.ALIVE,ships.get(1).getLifeStatus());
		
	}
	
	@Test
	public void GIVEN_MoreThanTwoShips_THEN_GameCanBePlayed() {
		
		List<Ship> ships = new ArrayList<Ship>();
        ships.add(new Ship(new Position(1,2,Orientation.N),nextMoveSequence(L,M,R,M,M,R,M,M),new ShotCoordinate(2,4),1));
        ships.add(new Ship(new Position(3,3,Orientation.E),nextMoveSequence(M,M,R,R,M,M,R,R),new ShotCoordinate(2,5),2));
        ships.add(new Ship(new Position(2,3,Orientation.S),nextMoveSequence(R,R,M,M,R,L),new ShotCoordinate(3,3),3));
        PlayGame game = new PlayGame(new Grid(10,10),ships);
        game.play();
        assertEquals(LifeStatus.ALIVE,ships.get(0).getLifeStatus());
        assertEquals(LifeStatus.SUNK,ships.get(1).getLifeStatus());
        assertEquals(LifeStatus.ALIVE,ships.get(2).getLifeStatus());
    	assertEquals(2,ships.get(0).getCurrentPositionObject().getCurrentCoordinates().getX());
		assertEquals(4,ships.get(0).getCurrentPositionObject().getCurrentCoordinates().getY());
		assertEquals(3,ships.get(1).getCurrentPositionObject().getCurrentCoordinates().getX());
		assertEquals(3,ships.get(1).getCurrentPositionObject().getCurrentCoordinates().getY());
		assertEquals(2,ships.get(2).getCurrentPositionObject().getCurrentCoordinates().getX());
		assertEquals(5,ships.get(2).getCurrentPositionObject().getCurrentCoordinates().getY());
	}
	
	@Test
	public void GIVEN_AsunkShip_THEN_NotAllowedToMoveAndThePositionIsUnchanged() {
		
		List<Ship> ships = new ArrayList<Ship>();
        ships.add(new Ship(new Position(1,2,Orientation.N),nextMoveSequence(L,M,R,M,M,R,M,M),new ShotCoordinate(3,3),1));
        ships.add(new Ship(new Position(3,3,Orientation.E),nextMoveSequence(M,M,R,R,M,M,R,R),new ShotCoordinate(2,5),2));
        ships.add(new Ship(new Position(2,3,Orientation.S),nextMoveSequence(M,M,M),new ShotCoordinate(4,3),3));
        PlayGame game = new PlayGame(new Grid(10,10),ships);
        game.play();
        assertEquals(LifeStatus.ALIVE,ships.get(0).getLifeStatus());
        assertEquals(LifeStatus.SUNK,ships.get(1).getLifeStatus());
        assertEquals(LifeStatus.ALIVE,ships.get(2).getLifeStatus());
    	assertEquals(2,ships.get(0).getCurrentPositionObject().getCurrentCoordinates().getX());
		assertEquals(4,ships.get(0).getCurrentPositionObject().getCurrentCoordinates().getY());
		assertEquals(3,ships.get(1).getCurrentPositionObject().getCurrentCoordinates().getX());
		assertEquals(3,ships.get(1).getCurrentPositionObject().getCurrentCoordinates().getY());
		assertEquals(2,ships.get(2).getCurrentPositionObject().getCurrentCoordinates().getX());
		assertEquals(0,ships.get(2).getCurrentPositionObject().getCurrentCoordinates().getY());
		
		ships = new ArrayList<Ship>();
		 ships.add(new Ship(new Position(2,4,Orientation.E),nextMoveSequence(M,M),new ShotCoordinate(3,3),1));
	        ships.add(new Ship(new Position(3,3,Orientation.E),nextMoveSequence(L,M,R,M),new ShotCoordinate(2,5),2));
	        ships.add(new Ship(new Position(2,0,Orientation.S),nextMoveSequence(L,M,M),new ShotCoordinate(4,3),3));
	        assertEquals(3,ships.get(1).getCurrentPositionObject().getCurrentCoordinates().getX());
			assertEquals(3,ships.get(1).getCurrentPositionObject().getCurrentCoordinates().getY());
	}
}
