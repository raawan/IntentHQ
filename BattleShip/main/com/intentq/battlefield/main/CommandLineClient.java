package com.intentq.battlefield.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import com.intentq.battlefield.dto.Grid;
import com.intentq.battlefield.dto.Position;
import com.intentq.battlefield.dto.Ship;
import com.intentq.battlefield.exception.InvalidInputException;

public class CommandLineClient {
	
	private BufferedReader cmdLineReader;
	private IConverter inputConverter;
	
	public CommandLineClient(BufferedReader cmdLineReader,
			IConverter inputConverter) {
		this.cmdLineReader = cmdLineReader;
		this.inputConverter = inputConverter;
	}

	public  Grid createGridObject() throws IOException {
		boolean input = false;
		String  gridCoordinate;
		Grid grid = null;
		while(!input) {
			try {
				gridCoordinate = getNextInputFromCmdLine();
				grid	 = inputConverter.convertGridStringInputToGridObject(gridCoordinate);
				input = true;
			} catch(InvalidInputException iie) {
				System.out.println(iie.getMessage());
				System.out.println("Please enter GRID input again");
			}
		}
		return grid;
	}
	
	public  List<Position> createShipsPositionOnGrid(Grid g) throws IOException {
		boolean input =false;
		String shipCoordinates;
		List<Position> positions=null;
		while(!input) {
			try {
				shipCoordinates = getNextInputFromCmdLine();
				positions	 = inputConverter.convertShipPositionStringInputToPositionObject(shipCoordinates,g);
				input = true;
			} catch(InvalidInputException iie) {
				System.out.println(iie.getMessage());
				System.out.println("Please enter Ships position input again");
				
			}
		}
		return positions;
	}
	
	public  void updateShipWithItsNextSetOfMovements(Ship s,Grid g) throws IOException {
		boolean input =false;
		String shipMovements;
		while(!input) {
			try {
				shipMovements = getNextInputFromCmdLine();
				s.getAction().setNextMoves(inputConverter.convertShipMovementInStringToShipsListOfMove(shipMovements,g));
				input = true;
			} catch(InvalidInputException iie) {
				System.out.println(iie.getMessage());
				System.out.println("Please enter Ships movement input again");
			}
		}		
	}

	
	public BufferedReader getCmdLineReader() {
		return cmdLineReader;
	}

	public void setCmdLineReader(BufferedReader cmdLineReader) {
		this.cmdLineReader = cmdLineReader;
	}
	
	public IConverter getInputConverter() {
		return inputConverter;
	}

	public void setInputConverter(IConverter inputConverter) {
		this.inputConverter = inputConverter;
	}
	
	private String getNextInputFromCmdLine() throws IOException {
		//cmdLineReader= new BufferedReader(new InputStreamReader(System.in));
		return cmdLineReader.readLine();
	}
}
