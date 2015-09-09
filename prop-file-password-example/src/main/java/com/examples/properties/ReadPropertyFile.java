package com.examples.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

public class ReadPropertyFile {
	
	Logger log = Logger.getLogger(ReadPropertyFile.class.getCanonicalName());
	
	private Properties prop = new Properties();
	private InputStream input = null;

	private String filename = "passwords.properties";

	public Properties loadProps() {

		input = ReadPropertyFile.class.getClassLoader().getResourceAsStream(filename);
		if (input == null) {
			log.info("Sorry, unable to find " + filename);
			return prop;
		}

		try {
			log.info("loading the property file...");
			prop.load(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
	}
}