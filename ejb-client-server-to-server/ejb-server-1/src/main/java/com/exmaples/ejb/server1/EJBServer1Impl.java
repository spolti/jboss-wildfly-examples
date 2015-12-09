package com.exmaples.ejb.server1;

import java.util.logging.Logger;

import javax.ejb.Remote;
import javax.ejb.Stateless;

@Stateless
@Remote(EJBServer1.class)
public class EJBServer1Impl implements EJBServer1 {

	private Logger log = Logger.getLogger(EJBServer1Impl.class.getCanonicalName());
	
	public String executeMethod() throws InterruptedException {
		// TODO Auto-generated method stub
		log.info("EJBServer1 called.");
		Thread.sleep(10000);
		return "Server 1 Executed.";
	}
}