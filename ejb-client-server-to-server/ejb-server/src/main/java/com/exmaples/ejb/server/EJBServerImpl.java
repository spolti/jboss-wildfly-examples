package com.exmaples.ejb.server;

import java.util.Hashtable;
import java.util.logging.Logger;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;

import com.exmaples.ejb.server1.EJBServer1;

@Stateless
@Remote(EJBServer.class)
public class EJBServerImpl implements EJBServer {

	private Logger log = Logger.getLogger(EJBServerImpl.class.getCanonicalName());
	
	public String execute() throws InterruptedException {
		//calling server1 remote EJB
		try {
			
//			InetAddress dns_address = InetAddress.getByName("server-1");
//			log.info("Remote Address " + dns_address);
			
			log.info("EJBServerImpl called, calling EJBServer");
			Hashtable<String,String> props = new Hashtable<String,String>();
            props.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
            InitialContext context = new javax.naming.InitialContext(props);
			
	        EJBServer1 ejb = (EJBServer1) context.lookup("ejb:/ejb-server1/EJBServer1Impl!com.exmaples.ejb.server1.EJBServer1");
	        return ejb.executeMethod();

		} catch (Exception e) {
			e.printStackTrace();
			return "Remote execution failed.";
		}		
	}
}