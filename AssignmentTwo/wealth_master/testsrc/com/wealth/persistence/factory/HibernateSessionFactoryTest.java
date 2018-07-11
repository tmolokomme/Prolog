package com.wealth.persistence.factory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.wealth.testing.hibernate.HibernateEnabledTestCase;

public class HibernateSessionFactoryTest extends HibernateEnabledTestCase {
    
    private static final String SESSION_FACTORY_JNDI_NAME = "wealth_master/hibernate/MasterHibSessionFactory";
    
    public HibernateSessionFactoryTest() {        
        super("HibernateSessionFactoryTest", HibernateSessionFactoryTest.SESSION_FACTORY_JNDI_NAME);
        super.setTestAnnotationConfiguration(true, "wealth_master");
    }

    @Override
    protected void setUp() throws Exception {        
        super.setUp();        
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        HibernateContext.nullifyOpenSession();
    }
    
    public void testGetSessionFactory() {
        info(">>>>>>------[HibernateSessionFactoryTest].testGetSessionFactory()----------------[BEGIN]");
        SessionFactory sf = HibernateSessionFactory.getSessionFactory("wealth_master");
        assertNotNull(sf);
        sf.close();
        info(">>>>>>------[HibernateSessionFactoryTest].testGetSessionFactory()------------------[END]");
    }
    
    public void testGetSession() {
        info(">>>>>>------[HibernateSessionFactoryTest].testGetSession()----------------[BEGIN]");
        Session session = HibernateSessionFactory.getSession(HibernateSessionFactoryTest.SESSION_FACTORY_JNDI_NAME);        
        assertNotNull(session);
        session.close();
        info(">>>>>>------[HibernateSessionFactoryTest].testGetSession()------------------[END]");
    }
}