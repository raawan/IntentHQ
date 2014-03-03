package com.intentq.battlefield.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.intentq.battlefield.api.ConverterApi;
import com.intentq.battlefield.api.IPlayGameApi;
import com.intentq.battlefield.api.PlayGameApi;
import com.intentq.battlefield.api.validator.Validator;

public class Main {

	public static void main(String[] args) {
		CommandLineClient commandLineClient = new CommandLineClient(getConsoleInputReader(),new ConverterApi(new Validator()), initialiseGame());
		try {
			printInputFormatHelp();
			commandLineClient.run();
		} catch (Exception e) {
			System.out.println("system is unavailable!");
		}
	}
	
	private static IPlayGameApi initialiseGame() {
		return new PlayGameApi(null, null);
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
