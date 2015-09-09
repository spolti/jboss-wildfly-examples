package com.examples.rest;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.examples.security.Blowsifh;

@Path("/encrypt")
public class Encrypt {
	
	@GET
	@Path("{password}")
	@Produces("application/json; charset=utf8")
	public String encrypt(@PathParam("password") String password) throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException{
		
		return Blowsifh.encode(password);
	}
}
