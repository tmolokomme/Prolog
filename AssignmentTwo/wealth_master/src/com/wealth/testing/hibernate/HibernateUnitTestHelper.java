package com.wealth.testing.hibernate;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUnitTestHelper {
    
    public static final String DEFAULT_HIB_SESSION_JNDI_NAME = "rmbpb_killingspree_app/hibernate/KillingspreeHibSessionFactory";

    private static SessionFactory sf;

    public static void setupHibSessionFactory(String jndiName) throws NamingException {
        sf = (new Configuration()).configure().buildSessionFactory();
        Context ctx = new InitialContext();
        ctx.bind(jndiName, sf);
    }
    
    public static void setupHibSessionFactory(String jndiName, SessionFactory sessionFactory) throws NamingException {        
        sf = sessionFactory;
        Context ctx = new InitialContext();
        ctx.bind(jndiName, sf);
    }

    public static void destroyHibSessionFactory(String jndiName) throws NamingException {
        sf.close();
        sf = null;
        System.gc();
    }
    
    public static void destroyHibSessionFactory(String jndiName, SessionFactory sessionFactory) throws NamingException {
    	sessionFactory.close();
    	sessionFactory = null;
    	if (sf != null) {
    	    sf.close();
    	}
        sf = null;
        System.gc();
    }
}