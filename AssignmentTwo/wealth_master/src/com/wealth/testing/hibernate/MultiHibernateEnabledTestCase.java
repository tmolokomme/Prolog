package com.wealth.testing.hibernate;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import com.wealth.client.ApplicationInitializer;
import com.wealth.testing.jdbc.DataSourceEnabledTestCase;
import com.wealth.testing.jdbc.DataSourceUnitTestHelper;

public class MultiHibernateEnabledTestCase extends DataSourceEnabledTestCase {
    
    private static String TEST_HIB_CONFIG = "hib.cfg.test.xml";

    private List<HibernateConfig> hibConfigs = new ArrayList<HibernateConfig>(0);
    
    public MultiHibernateEnabledTestCase(String testname) {
        super(testname, DataSourceUnitTestHelper.LOCAL_ENV);
        HibernateConfig defaultConfig = new HibernateConfig();
        defaultConfig.setHibSessionFactoryJNDIName(HibernateUnitTestHelper.DEFAULT_HIB_SESSION_JNDI_NAME);
        this.hibConfigs.add(defaultConfig);
    }
    
    public MultiHibernateEnabledTestCase(String testname, List<HibernateConfig> hibernateConfigurations) {
        super(testname, DataSourceUnitTestHelper.LOCAL_ENV);        
        this.hibConfigs.addAll(hibernateConfigurations);
    }
    
    public MultiHibernateEnabledTestCase(String testname, String hibSessionFactoryJNDIName) {
        super(testname, DataSourceUnitTestHelper.LOCAL_ENV);
        HibernateConfig defaultConfig = new HibernateConfig();
        defaultConfig.setHibSessionFactoryJNDIName(hibSessionFactoryJNDIName);
        this.hibConfigs.add(defaultConfig);
    }
    
    public MultiHibernateEnabledTestCase(String testname, String applicationName, String[] hibSessionFactoryJNDINames,
    		String[] hibSessionFactoryJNDINameExtensions) {
        super(testname, DataSourceUnitTestHelper.LOCAL_ENV);
        List<HibernateConfig> configs = new ArrayList<HibernateConfig>(hibSessionFactoryJNDINames.length);
        for (int i=0; i<hibSessionFactoryJNDINames.length; i++) {
        	HibernateConfig config = new HibernateConfig();
        	config.setHibSessionFactoryJNDIName(hibSessionFactoryJNDINames[i]);
        	config.setHibSessionFactoryJNDINameExtension(hibSessionFactoryJNDINameExtensions[i]);
        	config.setTestAnnotationConfiguration(true);
        	config.setApplicationName(applicationName);
        	this.hibConfigs.add(config);
        }
    }
    
    public MultiHibernateEnabledTestCase(String testname, String hibSessionFactoryJNDIName, SessionFactory sessionFactory) {
        super(testname, DataSourceUnitTestHelper.LOCAL_ENV);
        HibernateConfig defaultConfig = new HibernateConfig();
        defaultConfig.setHibSessionFactoryJNDIName(hibSessionFactoryJNDIName);
        defaultConfig.setSessionFactory(sessionFactory);
        this.hibConfigs.add(defaultConfig);
    }

    public MultiHibernateEnabledTestCase(String testname, int environment, String hibSessionFactoryJNDIName) {
        super(testname, environment);
        HibernateConfig defaultConfig = new HibernateConfig();
        defaultConfig.setHibSessionFactoryJNDIName(hibSessionFactoryJNDIName);
        this.hibConfigs.add(defaultConfig);
    }
    
    public MultiHibernateEnabledTestCase(String testname, int environment,  List<HibernateConfig> hibernateConfigurations) {
        super(testname, environment);        
        this.hibConfigs.addAll(hibernateConfigurations);
    }
    
    public MultiHibernateEnabledTestCase(String testname, int environment, String applicationName, List<String> hibSessionFactoryJNDINames,
    		List<String> hibSessionFactoryJNDINameExtensions) {
        super(testname, environment);
        List<HibernateConfig> configs = new ArrayList<HibernateConfig>(hibSessionFactoryJNDINames.size());
        for (int i=0; i<hibSessionFactoryJNDINames.size(); i++) {
        	HibernateConfig config = new HibernateConfig();
        	config.setHibSessionFactoryJNDIName(hibSessionFactoryJNDINames.get(i));
        	config.setHibSessionFactoryJNDINameExtension(hibSessionFactoryJNDINameExtensions.get(i));
        	config.setTestAnnotationConfiguration(true);
        	config.setApplicationName(applicationName);
        	this.hibConfigs.add(config);
        }
    }

    protected void setUp() throws Exception {
        super.setUp();
        if ((this.hibConfigs != null) && (this.hibConfigs.size() > 0)) {
            for (int i=0; i<this.hibConfigs.size(); i++) {
                HibernateConfig conf = this.hibConfigs.get(i);
                if (conf.isTestAnnotationConfiguration()) {
                    String filepath = ApplicationInitializer.getConfigDir() + "/" + conf.getApplicationName() + "_" + MultiHibernateEnabledTestCase.TEST_HIB_CONFIG;
                    if ((conf.getHibSessionFactoryJNDINameExtension() != null) && (!"".equals(conf.getHibSessionFactoryJNDINameExtension()))) {
                        filepath = ApplicationInitializer.getConfigDir() + "/" + conf.getApplicationName() +
                            "_" + conf.getHibSessionFactoryJNDINameExtension() + "_" + MultiHibernateEnabledTestCase.TEST_HIB_CONFIG;
                    }
                    File f = new File(filepath);
                    AnnotationConfiguration cfg = new AnnotationConfiguration();
                    cfg.configure(f);
                    conf.setSessionFactory(cfg.buildSessionFactory());
                    HibernateUnitTestHelper.setupHibSessionFactory(conf.getHibSessionFactoryJNDIName(), conf.getSessionFactory());
                } else {
                    HibernateUnitTestHelper.setupHibSessionFactory(conf.getHibSessionFactoryJNDIName());
                }
            }
        }        
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        if ((this.hibConfigs != null) && (this.hibConfigs.size() > 0)) {
            for (int i=0; i<this.hibConfigs.size(); i++) {
                HibernateConfig conf = this.hibConfigs.get(i);
                if (conf.getTransaction() != null) {
                    conf.getTransaction().commit();
                    info("COMMITTING Hibernate [Transaction]");
                    System.out.println("COMMITTING Hibernate [Transaction]");
                } else {
                    System.out.println("HibernateEnabledTestCase:tearDown(): Transaction = null");
                }
                
                if (conf.getSession() != null) {
                    conf.getSession().close();
                    info("CLOSING Hibernate [Session]");
                    System.out.println("CLOSING Hibernate [Session]");
                } else {
                    System.out.println("HibernateEnabledTestCase:tearDown(): Session = null");
                }
                
                if (conf.isTestAnnotationConfiguration()) {
                	HibernateUnitTestHelper.destroyHibSessionFactory(conf.getHibSessionFactoryJNDIName(), conf.getSessionFactory());
                } else {
                	HibernateUnitTestHelper.destroyHibSessionFactory(conf.getHibSessionFactoryJNDIName());
                }
            }
        }        
    }

    public List<HibernateConfig> getHibernateConfigurations() {
        return hibConfigs;
    }

    public void setHibernateConfigurations(List<HibernateConfig> hibConfigs) {
        this.hibConfigs = hibConfigs;
    }    
}
