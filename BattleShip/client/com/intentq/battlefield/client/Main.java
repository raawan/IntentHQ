package com.intentq.battlefield.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.intentq.battlefield.api.ConverterApi;
import com.intentq.battlefield.api.IPlayGameApi;
import com.intentq.battlefield.api.PlayGameApi;
import com.intentq.battlefield.api.validator.Validator;
import static com.intentq.battlefield.client.Print.*;

public class Main {

	public static void main(String[] args) {
		CommandLineClient commandLineClient = new CommandLineClient(getConsoleInputReader(),new ConverterApi(new Validator()), initialiseGame());
		try {
			printInputFormatHelp();
			commandLineClient.run();
		} catch (Exception e) {
			print("system is unavailable!");
		}
	}
	
	private static IPlayGameApi initialiseGame() {
		return new PlayGameApi(null, null);
	}
	
	private static  BufferedReader getConsoleInputReader() {
		return new BufferedReader(new InputStreamReader(System.in));
	}
	
}
