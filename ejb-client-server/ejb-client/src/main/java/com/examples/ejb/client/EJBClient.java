package com.examples.ejb.client;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.exmaples.ejb.server.EJBServer;

public class EJBClient {

	private static int SPAWN_THREADS = 100;	//number of the parallel EJB calls
	private static int EXECUTOR_THREADS = 100;
	
	public static void main(String args[]) throws NamingException, FileNotFoundException, IOException, InterruptedException, ExecutionException {

		System.out.println("EJB client-1");

		ExecutorService executorService = Executors.newFixedThreadPool(EXECUTOR_THREADS);
		Set<Callable<String>> callables = new HashSet<Callable<String>>();
		
		int i = 0;
		while(i++ < SPAWN_THREADS) {
		
			callables.add(new Callable<String>() {
			    public String call() throws Exception {
			    	System.out.println("Calling EJB thread " + Thread.currentThread().getName() + " ---> Resut: " + callEJBServer());
			    	return "OK";
			    }
			});

		}
		
		executorService.invokeAll(callables);
		executorService.shutdown();
	}

	public static String callEJBServer() throws NamingException, FileNotFoundException, IOException, InterruptedException {

        final Hashtable<String, String> jndiProperties = new Hashtable<String, String>();
        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        Context context = new InitialContext(jndiProperties);

        EJBServer ejb = (EJBServer) context.lookup("ejb:/ejb-server/EJBServerImpl!com.exmaples.ejb.server.EJBServer");
        return ejb.execute();

	}
}