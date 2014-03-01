package com.intentq.battlefield.main;

public class Ship {
	
	private String currentPosition;
	private String nextMove;
	
	public Ship(String currentPosition, String nextMoves) {
		this.currentPosition=currentPosition;
		this.nextMove=nextMoves;
	}

	public void move() {
		StringBuilder currPos =  new StringBuilder(currentPosition);
		switch(new StringBuilder(currentPosition).charAt(currentPosition.length()-1)) {
			case 'N' : if(nextMove.equalsIgnoreCase("L")) {
				currPos.setCharAt(currentPosition.length()-1, 'W');
			} else {
				currPos.setCharAt(currentPosition.length()-1, 'E');
			} 
			
			break;
			case 'S' : if(nextMove.equalsIgnoreCase("L")) {
				currPos.setCharAt(currentPosition.length()-1, 'E');
			} else {
				currPos.setCharAt(currentPosition.length()-1, 'W');
			} 
			break;
			case 'E' : if(nextMove.equalsIgnoreCase("L")) {
				currPos.setCharAt(currentPosition.length()-1, 'N');
			} else {
				currPos.setCharAt(currentPosition.length()-1, 'S');
			} 
			break;
			case 'W' : if(nextMove.equalsIgnoreCase("L")) {
				currPos.setCharAt(currentPosition.length()-1, 'S');
			} else {
				currPos.setCharAt(currentPosition.length()-1, 'N');
			} 
			break;
		}
		this.setCurrentPosition(currPos.toString());
	}

	public String getNextMove() {
		return nextMove;
	}

	public void setNextMove(String nextMove) {
		this.nextMove = nextMove;
	}

	public String getCurrentPosition() {
		return currentPosition;
	}

	public void setCurrentPosition(String currentPosition) {
		this.currentPosition = currentPosition;
	}

}
