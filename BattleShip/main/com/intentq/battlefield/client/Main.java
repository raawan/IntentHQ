package com.intentq.battlefield.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.intentq.battlefield.main.Converter;
import com.intentq.battlefield.main.IPlayGame;
import com.intentq.battlefield.main.PlayGame;
import com.intentq.battlefield.validator.Validator;

public class Main {

	public static void main(String[] args) {
		CommandLineClient commandLineClient = new CommandLineClient(getConsoleInputReader(),new Converter(new Validator()), initialiseGame());
		try {
			printInputFormatHelp();
			commandLineClient.run();
		} catch (Exception e) {
			System.out.println("system is unavailable!");
		}
	}
	
	private static IPlayGame initialiseGame() {
		return new PlayGame(null, null);
	}
	
	private static  BufferedReader getConsoleInputReader() {
		return new BufferedReader(new InputStreamReader(System.in));
	}
	
	private static void printInputFormatHelp() {
		System.out.println("GRID input format==> (X,Y)");
		System.out.println("Ship position==> (X1,Y1,O1)<SPACE>(X1,Y1,O1)<SPACE>.........<SPACE>(Xn,Yn,On)");
		System.out.println("Ship Movement==> (X,Y,LLRRMMR)");
		System.out.println("SHot coordinates==>(X,Y)");
	}
}
