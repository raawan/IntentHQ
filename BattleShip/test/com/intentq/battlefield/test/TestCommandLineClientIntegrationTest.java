package com.intentq.battlefield.test;

import static org.junit.Assert.*;
import static com.intentq.battlefield.constants.Move.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import com.intentq.battlefield.client.CommandLineClient;
import com.intentq.battlefield.constants.LifeStatus;
import com.intentq.battlefield.constants.Move;
import com.intentq.battlefield.constants.Orientation;
import com.intentq.battlefield.dto.Coordinate;
import com.intentq.battlefield.dto.Grid;
import com.intentq.battlefield.dto.Position;
import com.intentq.battlefield.dto.Ship;
import com.intentq.battlefield.main.Converter;
import com.intentq.battlefield.main.PlayGame;
import com.intentq.battlefield.validator.Validator;

@RunWith(MockitoJUnitRunner.class)
public class TestCommandLineClientIntegrationTest {/*
	
	@Mock
	private BufferedReader readerMock;
	
	private CommandLineClient cmd;
	@Before
	public void setUp() {
		cmd = new CommandLineClient(readerMock,new Converter(new Validator()),new PlayGame(new Grid(10,10),new ArrayList<Ship>()));
	}
	
	@Test
	public void GIVEN_GridInputFromCommandLine_THEN_GetValidatedGridObjectFromConverter() throws IOException {
		
		String gridCoOrdinateFromCmdLine = "(5,5)";
		when(readerMock.readLine()).thenReturn(gridCoOrdinateFromCmdLine);
		Grid grid = cmd.createGridObject();
		verify(readerMock).readLine();
		assertNotNull(grid);
		assertEquals(5, grid.getMaxCoOrdinates().getX());
		assertEquals(5, grid.getMaxCoOrdinates().getY());
	}
	
	@Test
	public void GIVEN_GridInputFromCommandLine_OnceInValidAndOnceValidTHEN_GetValidatedGridObjectFromConverter() throws IOException {
		
		String gridCoOrdinateInvalidFormatFromCmdLine = "5,5)";
		String gridCoOrdinateValidFormatFromCmdLine = "(5,5)";
		when(readerMock.readLine()).thenReturn(gridCoOrdinateInvalidFormatFromCmdLine,gridCoOrdinateValidFormatFromCmdLine);
		Grid grid = cmd.createGridObject();
		verify(readerMock,times(2)).readLine();
		assertNotNull(grid);
		assertEquals(5, grid.getMaxCoOrdinates().getX());
		assertEquals(5, grid.getMaxCoOrdinates().getY());
	}
	
	@Test
	public void GIVEN_ShipPositionFromCmdLine_THEN_GetValidatedPositionListObject() throws IOException {
		
		String shipPositionFrmCmdLine = "(1,2,N) (3,4,W)";
		when(readerMock.readLine()).thenReturn(shipPositionFrmCmdLine);
		List<Position> positions = cmd.createShipsPositionOnGrid(new Grid(5,5));
		assertNotNull(positions);
	}
	
	@Test
	public void GIVEN_ShipPositionFromCmdLine_THEN_GetValidatedPositionListObject_2() throws IOException {
		
		String shipPositionFrmCmdLineInvalidInput = "(1,2,N) (3,4,W)";
		String shipPositionFrmCmdLineValidInput = "(1,2,N) (2,4,W)";
		when(readerMock.readLine()).thenReturn(shipPositionFrmCmdLineInvalidInput,shipPositionFrmCmdLineValidInput);
		List<Position> positions = cmd.createShipsPositionOnGrid(new Grid(2,5));
		verify(readerMock,times(2)).readLine();
		assertNotNull(positions);
	}
	
	@Test
	public void GIVEN_ShipPositionFromCmdLine_THEN_GetValidatedPositionListObject_3() throws IOException {
		
		String shipPositionFrmCmdLineInvalidInput = "(1,2,N) (-2,4,W)";
		String shipPositionFrmCmdLineValidInput = "(1,2,N) (2,4,W)";
		when(readerMock.readLine()).thenReturn(shipPositionFrmCmdLineInvalidInput,shipPositionFrmCmdLineValidInput);
		List<Position> positions = cmd.createShipsPositionOnGrid(new Grid(2,5));
		verify(readerMock,times(2)).readLine();
		assertNotNull(positions);
	}
	
	@Test
	public void GIVEN_ShipPositionFromCmdLine_THEN_GetValidatedPositionListObject_4() throws IOException {
		
		String shipPositionFrmCmdLineInvalidInput = "(1,2,X) (2,4,W)";
		String shipPositionFrmCmdLineValidInput = "(1,2,N) (2,4,W)";
		when(readerMock.readLine()).thenReturn(shipPositionFrmCmdLineInvalidInput,shipPositionFrmCmdLineValidInput);
		List<Position> positions = cmd.createShipsPositionOnGrid(new Grid(2,5));
		verify(readerMock,times(2)).readLine();
		assertNotNull(positions);
	}
	
	@Test
	public void GIVEN_ShipPositionFromCmdLine_THEN_GetValidatedPositionListObject_5() throws IOException {
		
		String shipPositionFrmCmdLineInvalidInput = "(1,2,X) (22345678987654345678976543245,4,W)";
		String shipPositionFrmCmdLineValidInput = "(1,2,N) (2,4,W)";
		when(readerMock.readLine()).thenReturn(shipPositionFrmCmdLineInvalidInput,shipPositionFrmCmdLineValidInput);
		List<Position> positions = cmd.createShipsPositionOnGrid(new Grid(2,5));
		verify(readerMock,times(2)).readLine();
		assertNotNull(positions);
	}
	
	@Test
	public void GIVEN_ShipPositionFromCmdLine_THEN_GetValidatedPositionListObject_6() throws IOException {
		
		String shipPositionFrmCmdLineInvalidInput = "((1,2,N) (2,4,W)";
		String shipPositionFrmCmdLineValidInput = "(1,2,N) (2,4,W)";
		when(readerMock.readLine()).thenReturn(shipPositionFrmCmdLineInvalidInput,shipPositionFrmCmdLineValidInput);
		List<Position> positions = cmd.createShipsPositionOnGrid(new Grid(2,5));
		verify(readerMock,times(2)).readLine();
		assertNotNull(positions);
	}
	
	@Test
	public void GIVEN_shipMovesFromCommandLine_THEN_GetValidatedM0veListObject() throws IOException {
		
		List<Ship> ships = new ArrayList<Ship>();
		Ship ship=new Ship(new Position(3, 5, Orientation.N), null, null, 1);
		ships.add(ship);
		Grid grid = new Grid(10,10);
		grid.setShipsOnGrid(ships);
		
		String shipMovesFrmCmdLine = "(3,5,LRM)";
		when(readerMock.readLine()).thenReturn(shipMovesFrmCmdLine);
		cmd.updateShipWithItsNextSetOfMovements(ship, grid);
		verify(readerMock,times(1)).readLine();
		assertEquals(Move.L,ship.getAction().getNextMoves().get(0));
		assertEquals(Move.R,ship.getAction().getNextMoves().get(1));
		assertEquals(Move.M,ship.getAction().getNextMoves().get(2));
	}
	
	@Test
	public void GIVEN_shipMovesFromCommandLine_THEN_GetValidatedM0veListObject_2() throws IOException {
		
		List<Ship> ships = new ArrayList<Ship>();
		Ship ship=new Ship(new Position(3, 5, Orientation.N), null, null, 1);
		ships.add(ship);
		Grid grid = new Grid(10,10);
		grid.setShipsOnGrid(ships);
		
		String shipMovesFrmCmdLineInvalid1 = "(-3,5,LRLRMMLR)";
		String shipMovesFrmCmdLineInvalid2 = "(3,599999999999999999999999,LRLRMMLR)";
		String shipMovesFrmCmdLineInvalid3 = "(3,5,ZRLRMMLR)";
		String shipMovesFrmCmdLineInvalid4 = "3,5,LRLRMMLR)))";
		String shipMovesFrmCmdLineInvalid5 = "(3,2,LRLRMMLR)";
		String shipMovesFrmCmdLine = "(3,5,LRLRMMLR)";
		
		when(readerMock.readLine()).thenReturn(shipMovesFrmCmdLineInvalid1,shipMovesFrmCmdLineInvalid2,shipMovesFrmCmdLineInvalid3,shipMovesFrmCmdLineInvalid4,
				shipMovesFrmCmdLineInvalid5,shipMovesFrmCmdLine);
		cmd.updateShipWithItsNextSetOfMovements(ship, grid);
		verify(readerMock,times(6)).readLine();
		assertEquals(Move.L,ship.getAction().getNextMoves().get(0));
	}
	
	@Test
	public void GIVEN_AllValidValuesToPlayAGamesIteration_THEN_GameCanBePlayed() {
		Grid grid = new Grid(10,10);
		List<Ship> ships = new ArrayList<Ship>();
		List<Move> nextMoveSequence = nextMoveSequence(L,M,L,M,L,M,L,M,M);
		Coordinate shot = new Coordinate(2, 3);
		Ship ship=new Ship(new Position(1, 2, Orientation.N), nextMoveSequence, shot, 1);
		ships.add(ship);

		nextMoveSequence = nextMoveSequence(M,M,R,M,M,R,M,R,R,M);
		shot = new Coordinate(1, 3);
		ship=new Ship(new Position(3, 3, Orientation.E), nextMoveSequence, shot, 2);
		ships.add(ship);
		
		grid.setShipsOnGrid(ships);
		ships = cmd.playAnIteration(new PlayGame(grid, ships), grid);
		assertEquals(LifeStatus.SUNK,ships.get(0).getLifeStatus());
		assertEquals(new Position(1, 3, Orientation.N),ships.get(0).getCurrentPosition());
		assertEquals(new Position(5, 1, Orientation.E),ships.get(1).getCurrentPosition());
	}
	
	private static List<Move> nextMoveSequence(Move...actions) {
		return Arrays.asList(actions);
	}
*/}
