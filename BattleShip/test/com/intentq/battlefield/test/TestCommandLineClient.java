package com.intentq.battlefield.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.intentq.battlefield.dto.Grid;
import com.intentq.battlefield.main.CommandLineClient;
import com.intentq.battlefield.main.Converter;
import com.intentq.battlefield.main.IConverter;
import com.intentq.battlefield.util.Validator;

public class TestCommandLineClient {
	
	@Test
	public void GIVEN_GridInputFromCommandLine_THEN_GetValidatedGridObjectFromConverter() {
		CommandLineClient cmd = new CommandLineClient();
		String gridFrmCommandLine = cmd.getGridCoordinateFromCmdLine();
		IConverter converter = new Converter(new Validator());
		Grid grid = converter.convertGridStringInputToGridObject(gridFrmCommandLine);
		assertNotNull(grid);
	}
}
