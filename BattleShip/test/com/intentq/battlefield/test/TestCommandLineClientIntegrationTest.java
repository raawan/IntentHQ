package com.intentq.battlefield.test;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import com.intentq.battlefield.dto.Grid;
import com.intentq.battlefield.dto.Position;
import com.intentq.battlefield.main.CommandLineClient;
import com.intentq.battlefield.main.Converter;
import com.intentq.battlefield.util.Validator;

@RunWith(MockitoJUnitRunner.class)
public class TestCommandLineClientIntegrationTest {
	
	@Mock
	private BufferedReader readerMock;
	
	@Test
	public void GIVEN_GridInputFromCommandLine_THEN_GetValidatedGridObjectFromConverter() throws IOException {
		
		CommandLineClient cmd = new CommandLineClient(readerMock,new Converter(new Validator()));
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
		
		CommandLineClient cmd = new CommandLineClient(readerMock,new Converter(new Validator()));
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
		CommandLineClient cmd = new CommandLineClient(readerMock,new Converter(new Validator()));
		String shipPositionFrmCmdLine = "(1,2,N) (3,4,W)";
		when(readerMock.readLine()).thenReturn(shipPositionFrmCmdLine);
		List<Position> positions = cmd.createShipsPositionOnGrid(new Grid(5,5));
		assertNotNull(positions);
	}
	
	@Test
	public void GIVEN_ShipPositionFromCmdLine_THEN_GetValidatedPositionListObject_2() throws IOException {
		CommandLineClient cmd = new CommandLineClient(readerMock,new Converter(new Validator()));
		String shipPositionFrmCmdLineInvalidInput = "(1,2,N) (3,4,W)";
		String shipPositionFrmCmdLineValidInput = "(1,2,N) (2,4,W)";
		when(readerMock.readLine()).thenReturn(shipPositionFrmCmdLineInvalidInput,shipPositionFrmCmdLineValidInput);
		List<Position> positions = cmd.createShipsPositionOnGrid(new Grid(2,5));
		verify(readerMock,times(2)).readLine();
		assertNotNull(positions);
	}
	
	@Test
	public void GIVEN_ShipPositionFromCmdLine_THEN_GetValidatedPositionListObject_3() throws IOException {
		CommandLineClient cmd = new CommandLineClient(readerMock,new Converter(new Validator()));
		String shipPositionFrmCmdLineInvalidInput = "(1,2,N) (-2,4,W)";
		String shipPositionFrmCmdLineValidInput = "(1,2,N) (2,4,W)";
		when(readerMock.readLine()).thenReturn(shipPositionFrmCmdLineInvalidInput,shipPositionFrmCmdLineValidInput);
		List<Position> positions = cmd.createShipsPositionOnGrid(new Grid(2,5));
		verify(readerMock,times(2)).readLine();
		assertNotNull(positions);
	}
	
	@Test
	public void GIVEN_ShipPositionFromCmdLine_THEN_GetValidatedPositionListObject_4() throws IOException {
		CommandLineClient cmd = new CommandLineClient(readerMock,new Converter(new Validator()));
		String shipPositionFrmCmdLineInvalidInput = "(1,2,X) (2,4,W)";
		String shipPositionFrmCmdLineValidInput = "(1,2,N) (2,4,W)";
		when(readerMock.readLine()).thenReturn(shipPositionFrmCmdLineInvalidInput,shipPositionFrmCmdLineValidInput);
		List<Position> positions = cmd.createShipsPositionOnGrid(new Grid(2,5));
		verify(readerMock,times(2)).readLine();
		assertNotNull(positions);
	}
	
	@Test
	public void GIVEN_ShipPositionFromCmdLine_THEN_GetValidatedPositionListObject_5() throws IOException {
		CommandLineClient cmd = new CommandLineClient(readerMock,new Converter(new Validator()));
		String shipPositionFrmCmdLineInvalidInput = "(1,2,X) (22345678987654345678976543245,4,W)";
		String shipPositionFrmCmdLineValidInput = "(1,2,N) (2,4,W)";
		when(readerMock.readLine()).thenReturn(shipPositionFrmCmdLineInvalidInput,shipPositionFrmCmdLineValidInput);
		List<Position> positions = cmd.createShipsPositionOnGrid(new Grid(2,5));
		verify(readerMock,times(2)).readLine();
		assertNotNull(positions);
	}
	
	@Test
	public void GIVEN_ShipPositionFromCmdLine_THEN_GetValidatedPositionListObject_6() throws IOException {
		CommandLineClient cmd = new CommandLineClient(readerMock,new Converter(new Validator()));
		String shipPositionFrmCmdLineInvalidInput = "((1,2,N) (2,4,W)";
		String shipPositionFrmCmdLineValidInput = "(1,2,N) (2,4,W)";
		when(readerMock.readLine()).thenReturn(shipPositionFrmCmdLineInvalidInput,shipPositionFrmCmdLineValidInput);
		List<Position> positions = cmd.createShipsPositionOnGrid(new Grid(2,5));
		verify(readerMock,times(2)).readLine();
		assertNotNull(positions);
	}
}
