package server.status.example;

import java.io.IOException;
import java.util.HashMap;

import org.jboss.as.cli.CliInitializationException;
import org.jboss.as.cli.CommandContext;
import org.jboss.as.cli.CommandContextFactory;
import org.jboss.as.cli.CommandLineException;
import org.jboss.as.controller.client.ModelControllerClient;
import org.jboss.dmr.ModelNode;

public class ServerStatusCLI {

	private String host = "127.0.0.1";
	private int port = 9999;
	private String user = "admin";
	private String password = "password";

	private HashMap<String, String> commandResult = new HashMap<String, String>();
	private CommandContext ctx;
	
	public HashMap<String, String> runCli() throws IOException {

		try {
			
			ctx = CommandContextFactory.getInstance().newCommandContext(user, password.toCharArray());
			ctx.connectController(host, port);
			
			
		} catch (CliInitializationException e) {			
			commandResult.put("command1Fail", "Failed to initialize CLI context" + e);
			return commandResult;
			
		} catch (CommandLineException e) {
			commandResult.put("command1Fail","Failed to execute CLI command" + e);
			return commandResult;
		}
		
		//command1
		try {
			ModelNode request = ctx.buildRequest(":read-attribute(name=server-state)");
			ModelControllerClient client = ctx.getModelControllerClient();
			String result = client.execute(request).get("result").toString();
			System.out.println(result);
			commandResult.put("command1Sucess", result);
			//return commandResult;
			
		}  catch (CommandLineException e) {
			commandResult.put("command1Fail","Failed to execute CLI command" + e);
			return commandResult;
		}

		//command2
		try {
			ModelNode request = ctx.buildRequest(":read-attribute(name=server-state)");
			ModelControllerClient client = ctx.getModelControllerClient();
			String result = client.execute(request).get("result").toString();
			System.out.println(result);
			commandResult.put("command2Sucess", result);
			//return commandResult;
			
		}  catch (CommandLineException e) {
			commandResult.put("command2Fail","Failed to execute CLI command" + e);
			return commandResult;
		}
		
		return commandResult;
	}
	


	
	public static void main(String args[]) throws IOException, InterruptedException {

		HashMap<String, String> result = new HashMap<String, String>();
		
		System.out.println("Running through CLI...");
		ServerStatusCLI cli = new ServerStatusCLI();
		result = cli.runCli();
		
		Object[] valueMap = result.keySet().toArray();		
		
		//printing the result
		for (int i = 0; i < result.size(); i++){
			if (result.get(valueMap[i]).contains("Fail")) {
				System.out.println("Fail: " + result.get(valueMap[i]));	
				
			} else {
				System.out.println("Sucess: " + result.get(valueMap[i]));	
			}
			
		}
	}
}
