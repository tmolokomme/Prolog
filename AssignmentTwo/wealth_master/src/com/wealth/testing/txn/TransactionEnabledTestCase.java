package com.wealth.testing.txn;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.wealth.persistence.factory.HibernateContext;
import com.wealth.persistence.factory.HibernateSessionFactory;
import com.wealth.testing.hibernate.HibernateEnabledTestCase;

public class TransactionEnabledTestCase extends HibernateEnabledTestCase {
    
    protected String hibSessionFactoryJNDIName = null;
    protected Session session = null;
    protected Transaction transaction = null;

    public TransactionEnabledTestCase(String name, String hibSessionFactoryJNDIName, String applicationName) {
        super(name, hibSessionFactoryJNDIName);
        super.setTestAnnotationConfiguration(true, applicationName);
        this.hibSessionFactoryJNDIName = hibSessionFactoryJNDIName;
    }

    protected void setUp() throws Exception {
        super.setUp();
        this.session = HibernateSessionFactory.getSession(this.hibSessionFactoryJNDIName);
        this.transaction = this.session.beginTransaction();
        super.setSession(this.session);
        super.setTransaction(this.transaction);
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        HibernateContext.nullifyOpenSession();
    }

    public Session getSession() {
        return session;
    }

    public Transaction getTransaction() {
        return transaction;
    }
}