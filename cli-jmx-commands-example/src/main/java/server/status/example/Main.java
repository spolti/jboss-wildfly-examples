package server.status.example;

import java.io.IOException;
import java.util.HashMap;

public class Main {
	private static HashMap<String, String> commandResult;

	public static void main(String args[]) throws IOException, InterruptedException {

		System.out.println("Running through CLI...");
		ServerStatusCLI cli = new ServerStatusCLI();
		commandResult = cli.runCli();
		
		
		//printing the result
		for (int i = 0; i < commandResult.size(); i++){
			System.out.println("SUCCESS: " + commandResult.get("SUCCESS"));
			System.out.println("FAIL: " + commandResult.get("FAIL"));
		}
//		System.out.println("Running through JMX...");
//		ServerStatusJMX jmx = new ServerStatusJMX();
//		jmx.runJmx();
	}
}