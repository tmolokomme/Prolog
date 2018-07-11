package com.wealth.testing.hibernate;

import java.io.File;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import com.wealth.client.ApplicationInitializer;
import com.wealth.testing.jdbc.DataSourceEnabledTestCase;
import com.wealth.testing.jdbc.DataSourceUnitTestHelper;

public class HibernateEnabledTestCase extends DataSourceEnabledTestCase {
    
    private static String TEST_HIB_CONFIG = "hib.cfg.test.xml";

    private String hibSessionFactoryJNDIName = "hibSessionFactory";
    private SessionFactory sessionFactory = null;
    private boolean testAnnotationConfiguration = false;
    private String applicationName = null;
    private Session session = null;
    private Transaction transaction = null;
    
    public HibernateEnabledTestCase(String testname) {
        super(testname, DataSourceUnitTestHelper.LOCAL_ENV);
        this.hibSessionFactoryJNDIName = HibernateUnitTestHelper.DEFAULT_HIB_SESSION_JNDI_NAME;
    }
    
    public HibernateEnabledTestCase(String testname, String hibSessionFactoryJNDIName) {
        super(testname, DataSourceUnitTestHelper.LOCAL_ENV);
        this.hibSessionFactoryJNDIName = hibSessionFactoryJNDIName;
    }
    
    public HibernateEnabledTestCase(String testname, String hibSessionFactoryJNDIName, SessionFactory sessionFactory) {
        super(testname, DataSourceUnitTestHelper.LOCAL_ENV);
        this.hibSessionFactoryJNDIName = hibSessionFactoryJNDIName;
        this.sessionFactory = sessionFactory;
    }

    public HibernateEnabledTestCase(String testname, int environment, String hibSessionFactoryJNDIName) {
        super(testname, environment);
        this.hibSessionFactoryJNDIName = hibSessionFactoryJNDIName;
    }

    protected void setUp() throws Exception {
        super.setUp();
        if (this.testAnnotationConfiguration) {
            File f = new File(ApplicationInitializer.getConfigDir() + "/" + applicationName + "_" + HibernateEnabledTestCase.TEST_HIB_CONFIG);
            AnnotationConfiguration cfg = new AnnotationConfiguration();
            cfg.configure(f);
            this.sessionFactory = cfg.buildSessionFactory();
            HibernateUnitTestHelper.setupHibSessionFactory(this.hibSessionFactoryJNDIName, this.sessionFactory);
        } else {
            HibernateUnitTestHelper.setupHibSessionFactory(this.hibSessionFactoryJNDIName);
        }        
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        if (this.transaction != null) {
            this.transaction.commit();
            info("COMMITTING Hibernate [Transaction]");
            System.out.println("COMMITTING Hibernate [Transaction]");
        } else {
        	System.out.println("HibernateEnabledTestCase:tearDown(): Transaction = null");
        }
        
        if (this.session != null) {
            this.session.close();
            info("CLOSING Hibernate [Session]");
            System.out.println("CLOSING Hibernate [Session]");
        } else {
        	System.out.println("HibernateEnabledTestCase:tearDown(): Session = null");
        }
        HibernateUnitTestHelper.destroyHibSessionFactory(this.hibSessionFactoryJNDIName);
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public boolean isTestAnnotationConfiguration() {
        return testAnnotationConfiguration;
    }

    public void setTestAnnotationConfiguration(boolean testAnnotationConfiguration, String applicationName) {
        this.testAnnotationConfiguration = testAnnotationConfiguration;
        this.applicationName = applicationName;
    }

    public String getApplicationName() {
        return applicationName;
    }
}
