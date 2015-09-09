package com.examples;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import org.jboss.security.vault.SecurityVaultException;

import com.examples.properties.ReadPropertyFile;
import com.examples.security.Blowsifh;
import com.examples.security.vault.GetPassFromVault;

@SuppressWarnings("restriction")
@Singleton
@Startup
public class ExampleLoadPasswordEJB {

	Logger log = Logger.getLogger(ExampleLoadPasswordEJB.class.getCanonicalName());
    
	@Inject
	private ReadPropertyFile prop;
	
	@PostConstruct
	public void start() throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException, SecurityVaultException {
		
		log.info("Getting password password1: " + String.valueOf(Blowsifh.decode(prop.loadProps().getProperty("password1"))));
		log.info("Getting password password2: " + String.valueOf(Blowsifh.decode(prop.loadProps().getProperty("password2"))));
		
		log.info("Getting password password-vault1: " + String.valueOf(GetPassFromVault.getPwd(prop.loadProps().getProperty("password-vault1"))));
		log.info("Getting password password-vault2: " + String.valueOf(GetPassFromVault.getPwd(prop.loadProps().getProperty("password-vault2"))));
	}

	@PreDestroy
    public void stop() {
    	log.info("Shutting down.");
    }

}