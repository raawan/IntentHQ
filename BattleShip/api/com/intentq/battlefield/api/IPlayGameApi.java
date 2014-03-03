package com.intentq.battlefield.api;

import java.util.List;

import com.intentq.battlefield.api.dto.Grid;
import com.intentq.battlefield.api.dto.Ship;

public interface IPlayGameApi {

	void play();

	void setGrid(Grid grid);

	List<Ship> getShips();

}
