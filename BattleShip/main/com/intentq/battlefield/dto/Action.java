package com.intentq.battlefield.dto;

import java.util.List;

import com.intentq.battlefield.constants.Move;

public class Action {
	
	private List<Move> nextMoves;
	private Coordinate shot;
	
	public Action(List<Move> nextMoves, Coordinate shot) {
		this.nextMoves = nextMoves;
		this.shot = shot;
	}
	
	public List<Move> getNextMoves() {
		return nextMoves;
	}
	public void setNextMoves(List<Move> nextMoves) {
		this.nextMoves = nextMoves;
	}
	public Coordinate getShot() {
		return shot;
	}
	public void setShot(Coordinate shot) {
		this.shot = shot;
	}
	
}
