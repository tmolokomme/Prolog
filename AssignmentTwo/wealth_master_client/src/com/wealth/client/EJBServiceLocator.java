package com.wealth.client;

import java.util.Hashtable;

import javax.naming.InitialContext;

public class EJBServiceLocator {

	public static Object getEJB(Hashtable properties, String jndiName) throws ServerException {
		
		Object object = null;
		try {
			InitialContext ctx = new InitialContext(properties);
			object = ctx.lookup(jndiName);
		} catch (Exception e) {
			throw new ServerException(e);
		}
		return object;
	}
}
