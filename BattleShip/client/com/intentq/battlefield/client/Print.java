package com.intentq.battlefield.client;


import com.intentq.battlefield.api.dto.Ship;

public class Print {
	
	private Print() {}
	
	public static void print(String message) {
		System.out.println(message);
	}
	
	public static void print(Ship ship) {
		System.out.println(ship);
	}
	
	public static void printInputFormatHelp() {
		print("GRID input format==> (X,Y)");
		print("Ship position==> (X1,Y1,O1)<SPACE>(X1,Y1,O1)<SPACE>.........<SPACE>(Xn,Yn,On)");
		print("Ship Movement==> (X,Y,LLRRMMR)");
		print("SHot coordinates==>(X,Y)");
	}
}
