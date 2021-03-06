package com.exmaples.ejb.server;

import java.util.logging.Logger;

import javax.ejb.Remote;
import javax.ejb.Stateless;

@Stateless
@Remote(EJBServer.class)
public class EJBServerImpl implements EJBServer {

	private Logger log = Logger.getLogger(EJBServerImpl.class.getCanonicalName());
	
	public String execute() throws InterruptedException {
		// TODO Auto-generated method stub
		log.info("EJBServer called.");
		Thread.sleep(10000);
		return "Server Executed.";
	}
}