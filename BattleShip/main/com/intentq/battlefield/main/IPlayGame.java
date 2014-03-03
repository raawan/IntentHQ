package com.intentq.battlefield.main;

import java.util.List;

import com.intentq.battlefield.dto.Grid;
import com.intentq.battlefield.dto.Ship;

public interface IPlayGame {

	void play();

	void setGrid(Grid grid);

	List<Ship> getShips();

}
