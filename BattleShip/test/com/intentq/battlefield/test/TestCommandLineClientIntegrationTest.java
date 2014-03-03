package com.intentq.battlefield.test;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import com.intentq.battlefield.dto.Grid;
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
}
