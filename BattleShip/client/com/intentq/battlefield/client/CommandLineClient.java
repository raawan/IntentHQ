package com.intentq.battlefield.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.intentq.battlefield.api.IConverterApi;
import com.intentq.battlefield.api.IPlayGameApi;
import com.intentq.battlefield.api.constants.LifeStatus;
import com.intentq.battlefield.api.dto.Grid;
import com.intentq.battlefield.api.dto.Position;
import com.intentq.battlefield.api.dto.Ship;
import com.intentq.battlefield.api.exception.InvalidInputException;

public class CommandLineClient {
	
	private BufferedReader cmdLineReader;
	private IConverterApi inputConverter;
	private IPlayGameApi playGame;
	
	public CommandLineClient(BufferedReader cmdLineReader,
			IConverterApi inputConverter,IPlayGameApi playGame) {
		this.setCmdLineReader(cmdLineReader);
		this.setInputConverter(inputConverter);
		this.setPlayGame(playGame);
	}

	private  Grid createGridObject() throws IOException {
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
	
	private  List<Position> createShipsPositionOnGrid(Grid g) throws IOException {
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
	
	private  void updateShipWithItsNextSetOfMovements(Ship s,Grid g) throws IOException {
		boolean input =false;
		String shipMovements;
		while(!input) {
			try {
				shipMovements = getNextInputFromCmdLine();
				s.getAction().setNextMoves(inputConverter.convertShipMovementInStringToShipsListOfMove(shipMovements,g,s.getLifeStatus()));
				input = true;
			} catch(InvalidInputException iie) {
				System.out.println(iie.getMessage());
				System.out.println("Please enter Ships movement input again");
			}
		}		
	}

	private  void updateShipWithItsShot(Ship s, Grid g) throws IOException {
		boolean input =false;
		String shot;
		while(!input) {
			try {
				shot = getNextInputFromCmdLine();
				s.getAction().setShot(inputConverter.convertShotInStringToShotObject(shot,g));
				input = true;
			} catch(InvalidInputException iie) {
				System.out.println(iie.getMessage());
				System.out.println("Please enter Ships shot input again");
			}
		}		
	}
	
	private  List<Ship> playAnIteration(IPlayGameApi playGame, Grid grid) {
		playGame.setGrid(grid);
		playGame.play();
		return playGame.getShips();
	}
	
	public void run() throws IOException {
		try {
			Grid grid= initialiseGrid();
			playGame(grid,playGame);
	      } catch (IOException ioe) {
	         System.out.println("IO error trying to read your input!");
	         System.exit(1);
	      } finally {
	    	  if(cmdLineReader!=null) {
	    		  cmdLineReader.close();
	    	  }
	      }
	}
	
	private Grid initialiseGrid() throws IOException {
		Grid grid = createGridObject();
		List<Position> positions = createShipsPositionOnGrid(grid);
		List<Ship> ships = createShipObjects(positions);
		addShipsOnGrid(ships,grid);
		return grid;
	}
	
	private void addShipsOnGrid(List<Ship> ships, Grid grid) {
		grid.setShipsOnGrid(ships);
	}
	
	private  List<Ship> createShipObjects(List<Position> positions) {
		List<Ship> ships = new ArrayList<Ship>();
		Ship ship = null;
		int id = 0;
		for(Position pos : positions) {
			ship=new Ship(pos, null, null, ++id);
			ships.add(ship);
		}
		return ships;
	}
	
	private void playGame(Grid grid, IPlayGameApi playGame) throws IOException {
		do {
			updateShipWithShipMovementsAndShots(grid);
			printOutput(playAnIteration(playGame,grid));
		}while(continueGame(grid));
	}
	
	private  void updateShipWithShipMovementsAndShots(Grid g) throws IOException {
		for(Ship s : g.getShipsOnGrid()) {
			if(s.getLifeStatus().equals(LifeStatus.ALIVE)) {
				updateShipWithItsNextSetOfMovements(s,g);
				updateShipWithItsShot(s,g);
			}
		}
	}
	
	private boolean continueGame(Grid grid) throws IOException {
		//ToDO: If one ship - messgae game won
		System.out.println("press 1 to Continue game, any other key to exit");
		String input = getNextInputFromCmdLine();
		if(input.trim().equals("1")) {
			System.out.println("Press next set of movement");
			System.out.println("Ship NOT SANK are as follows:");
			for(Ship ship : grid.getShipsOnGrid()) {
				if(ship.getLifeStatus().equals(LifeStatus.ALIVE))
					System.out.println(ship.getCurrentPosition()+ " " + ship.getLifeStatus() );
			}
			return true;
		}
		return false;
	}
	
	private  void printOutput(List<Ship> finalShipStatusList) {
		for(Ship sh : finalShipStatusList) {
			System.out.println(sh);
		}		
	}
	
	public BufferedReader getCmdLineReader() {
		return cmdLineReader;
	}

	public void setCmdLineReader(BufferedReader cmdLineReader) {
		this.cmdLineReader = cmdLineReader;
	}
	
	public IConverterApi getInputConverter() {
		return inputConverter;
	}

	public void setInputConverter(IConverterApi inputConverter) {
		this.inputConverter = inputConverter;
	}
	
	private String getNextInputFromCmdLine() throws IOException {
		return cmdLineReader.readLine();
	}

	public IPlayGameApi getPlayGame() {
		return playGame;
	}

	public void setPlayGame(IPlayGameApi playGame) {
		this.playGame = playGame;
	}
}
