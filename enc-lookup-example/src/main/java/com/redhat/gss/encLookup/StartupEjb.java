package com.redhat.gss.encLookup;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

@SuppressWarnings("restriction")
@Singleton
@Startup
public class StartupEjb {

	Logger log = Logger.getLogger(StartupEjb.class.getCanonicalName());

	DataSource ds = null;

	Context ctx = null;

	@PostConstruct
	public void start() throws InterruptedException {

		try {

			String strDSName = "java:comp/env/jdbc/mim_datasource";
			ctx = new InitialContext();
			ds = (javax.sql.DataSource) ctx.lookup(strDSName);
			log.info("Getting Connection " + ds.getConnection());
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	@PreDestroy
	public void stop() throws NamingException {
		log.info("Closing Context.");
		ctx.close();
	}
}