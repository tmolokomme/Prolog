package com.wealth.testing.jndi;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.spi.InitialContextFactory;

public class SimpleContextFactory implements InitialContextFactory {

    private static SimpleContext instance = null;

    public Context getInitialContext(Hashtable environment) throws NamingException {
        if (instance == null) {
            // instance = new SimpleContext(environment);
            instance = new SimpleContext();
        }
        return instance;
    }
}
