package com.FileHandling;

import java.io.IOException;

public class ExecutingCommands 
{
	public static void main(String[] args) throws IOException {
		var runner=Runtime.getRuntime();
		//runner.exec("notepad");
		String cmdArray[]= {"cmd", "/c", "dir", "D:\\Original\\"};
//		new ProcessBuilder(cmdArray)
//		.start();
		runner.exec(cmdArray);
		runner.exec("LevelsofTesting.pdf");
		System.out.println("opened");
		runner.gc();
	}
}
