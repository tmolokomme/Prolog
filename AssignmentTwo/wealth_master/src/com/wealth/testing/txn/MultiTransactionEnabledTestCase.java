package com.wealth.testing.txn;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.wealth.persistence.factory.HibernateContext;
import com.wealth.persistence.factory.HibernateSessionFactory;
import com.wealth.testing.hibernate.HibernateConfig;
import com.wealth.testing.hibernate.MultiHibernateEnabledTestCase;

public class MultiTransactionEnabledTestCase extends MultiHibernateEnabledTestCase {
    
    protected List<HibernateConfig> hibConfigs = new ArrayList<HibernateConfig>(0);
    
    public MultiTransactionEnabledTestCase(String name, String applicationName,
    		String[] sessionFactoryJNDINames, String[] sessionFactoryJNDINameExtensions) {
        super(name, applicationName, sessionFactoryJNDINames, sessionFactoryJNDINameExtensions);        
        this.hibConfigs.addAll(super.getHibernateConfigurations());
    }

    public MultiTransactionEnabledTestCase(String name, List<HibernateConfig> hibConfigs) {
        super(name, hibConfigs);        
        this.hibConfigs.addAll(hibConfigs);
    }

    protected void setUp() throws Exception {
        super.setUp();
        if ((this.hibConfigs != null) && (this.hibConfigs.size() > 0)) {
            for (int i=0; i<this.hibConfigs.size(); i++) {
                HibernateConfig conf = this.hibConfigs.get(i);
                conf.setSession(HibernateSessionFactory.getSession(conf.getHibSessionFactoryJNDIName()));
                conf.setTransaction(conf.getSession().beginTransaction());
//                super.setSession(this.session);
//                super.setTransaction(this.transaction);
            }
        }        
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        HibernateContext.nullifyOpenSession();
    }

    public List<HibernateConfig> getHibConfigs() {
        return hibConfigs;
    }

    public void setHibConfigs(List<HibernateConfig> hibConfigs) {
        this.hibConfigs = hibConfigs;
    }
    
    public Session getSession(String hibSessionFactoryJNDIName) {
        if ((this.hibConfigs != null) && (this.hibConfigs.size() > 0)) {
            for (int i=0; i<this.hibConfigs.size(); i++) {
                HibernateConfig conf = this.hibConfigs.get(i);
                if (conf.getHibSessionFactoryJNDIName().equals(hibSessionFactoryJNDIName)){
                    return conf.getSession();
                }
            }
            return null;
        } else {
            return null;
        }
    }

    public Transaction getTransaction(String hibSessionFactoryJNDIName) {
        if ((this.hibConfigs != null) && (this.hibConfigs.size() > 0)) {
            for (int i=0; i<this.hibConfigs.size(); i++) {
                HibernateConfig conf = this.hibConfigs.get(i);
                if (conf.getHibSessionFactoryJNDIName().equals(hibSessionFactoryJNDIName)){
                    return conf.getTransaction();
                }
            }
            return null;
        } else {
            return null;
        }
    }
}