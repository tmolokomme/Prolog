package com.wealth.testing.jndi;

import javax.naming.Context;
import javax.naming.NamingException;

public final class JNDIUnitTestHelper {

    private static boolean initialized = false;

    private JNDIUnitTestHelper() {}

    public static void init() throws NamingException {
        System.setProperty(Context.INITIAL_CONTEXT_FACTORY, "com.wealth.testing.jndi.SimpleContextFactory");
        initialized = true;
    }

    public static boolean isInitialized() {
        return initialized;
    }

    public static void shutdown() throws NamingException {
        System.setProperty(Context.INITIAL_CONTEXT_FACTORY, "");
        initialized = false;
    }
}
