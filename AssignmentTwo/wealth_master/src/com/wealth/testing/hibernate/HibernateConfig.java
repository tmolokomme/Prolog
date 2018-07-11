package com.wealth.testing.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class HibernateConfig {
    
    private String applicationName = null;
    private String hibSessionFactoryJNDIName = null;
    private String hibSessionFactoryJNDINameExtension = null;
    private boolean testAnnotationConfiguration = false;
    private SessionFactory sessionFactory = null;
    private Session session = null;
    private Transaction transaction = null;
    
    public HibernateConfig() {}
    
    public String getApplicationName() {
        return applicationName;
    }
    
    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }
    
    public String getHibSessionFactoryJNDIName() {
        return hibSessionFactoryJNDIName;
    }
    
    public void setHibSessionFactoryJNDIName(String hibSessionFactoryJNDIName) {
        this.hibSessionFactoryJNDIName = hibSessionFactoryJNDIName;
    }
    
    public boolean isTestAnnotationConfiguration() {
        return testAnnotationConfiguration;
    }
    
    public void setTestAnnotationConfiguration(boolean testAnnotationConfiguration) {
        this.testAnnotationConfiguration = testAnnotationConfiguration;
    }
    
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    public Session getSession() {
        return session;
    }
    
    public void setSession(Session session) {
        this.session = session;
    }
    
    public Transaction getTransaction() {
        return transaction;
    }
    
    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public String getHibSessionFactoryJNDINameExtension() {
        return hibSessionFactoryJNDINameExtension;
    }

    public void setHibSessionFactoryJNDINameExtension(String hibSessionFactoryJNDINameExtension) {
        this.hibSessionFactoryJNDINameExtension = hibSessionFactoryJNDINameExtension;
    }
}