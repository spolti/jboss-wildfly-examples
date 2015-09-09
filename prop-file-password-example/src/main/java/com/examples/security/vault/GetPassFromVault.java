package com.examples.security.vault;

import org.jboss.security.vault.*;

public class GetPassFromVault {

	
	public static String getPwd(String vaultExpression) throws SecurityVaultException{

		return SecurityVaultUtil.getValueAsString(vaultExpression);
	}
}