package com.devjan.wifiqrcodegenerator;

import java.util.concurrent.Callable;

import picocli.CommandLine;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

public class Main implements Callable<Void> {
	@Option(names = { "-v", "--verbose" }, description = "More verbose output")
	private boolean verbose = false;

	@Parameters(index = "0", paramLabel = "OUTPUT-FILE", description = "Output file")
	private String outputFile;

	public static void main(String[] args) {
		// use picocli to handle CLI args and stuff
		CommandLine.call(new Main(), System.err, args);
	}

	@Override
	public Void call() throws Exception {
		System.out.println("Hello world :)");
		return null;
	}

}
