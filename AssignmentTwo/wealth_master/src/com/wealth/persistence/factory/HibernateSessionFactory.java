package com.wealth.persistence.factory;

import java.io.File;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistryBuilder;

import com.wealth.client.ApplicationInitializer;

public class HibernateSessionFactory {    
    
    private static String HIBERNATE_CONFIG_FILE = "hib.cfg.xml";

    public static SessionFactory getSessionFactory(String applicationName) {
        try {
            File f = new File(ApplicationInitializer.getConfigDir() + "/" + applicationName + "_" + HIBERNATE_CONFIG_FILE);
            
            /*
            AnnotationConfiguration cfg = new AnnotationConfiguration();
            cfg.configure(f);            
            return cfg.buildSessionFactory();   
            */
            
            Configuration cfg = new Configuration();
			cfg.configure(f);
			//return cfg.buildSessionFactory();  
			
			//HIB 4 - NEW WAY	
            ServiceRegistryBuilder ssrb = new ServiceRegistryBuilder().applySettings(cfg.getProperties());
            return cfg.buildSessionFactory(ssrb.buildServiceRegistry()); //ssrb.build());
            
            
            
        }       
        catch (HibernateException e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError(e);
        }
    }
    
    public static SessionFactory getSessionFactory(String applicationName, String sessionFactoryNameExtension) {
        if ((sessionFactoryNameExtension == null) || ("".equals(sessionFactoryNameExtension))) {
            return HibernateSessionFactory.getSessionFactory(applicationName);
        }
        try {
            File f = new File(ApplicationInitializer.getConfigDir() + "/" + applicationName + "_" +
                    sessionFactoryNameExtension + "_" + HIBERNATE_CONFIG_FILE);
            /*AnnotationConfiguration cfg = new AnnotationConfiguration();
            cfg.configure(f);            
            return cfg.buildSessionFactory();  
            */
            Configuration cfg = new Configuration();
			cfg.configure(f);
			//return cfg.buildSessionFactory(); 
			
			//HIB 4 - NEW WAY	
            ServiceRegistryBuilder ssrb = new ServiceRegistryBuilder().applySettings(cfg.getProperties());
            return cfg.buildSessionFactory(ssrb.buildServiceRegistry()); //ssrb.build());
        }       
        catch (HibernateException e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError(e);
        }
    }
    
    public static SessionFactory getSessionFactory(String applicationName, Properties properties) {
        try {
            File f = new File(ApplicationInitializer.getConfigDir() + "/" + applicationName + "_" + HIBERNATE_CONFIG_FILE);
            /*
            AnnotationConfiguration cfg = new AnnotationConfiguration();
            cfg.addProperties(properties);
            cfg.configure(f);            
            return cfg.buildSessionFactory();  
            */
            Configuration cfg = new Configuration();
			cfg.configure(f);
			//return cfg.buildSessionFactory();  
			
			//HIB 4 - NEW WAY	
            ServiceRegistryBuilder ssrb = new ServiceRegistryBuilder().applySettings(cfg.getProperties());
            return cfg.buildSessionFactory(ssrb.buildServiceRegistry()); //ssrb.build());
        }       
        catch (HibernateException e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError(e);
        }
    }
    
    public static SessionFactory getSessionFactory(String applicationName, String sessionFactoryNameExtension, Properties properties) {
    	System.out.println("sessionFactoryNameExtension=["+sessionFactoryNameExtension+"]");
    	if ((sessionFactoryNameExtension == null) || ("".equals(sessionFactoryNameExtension))) {        	
            return HibernateSessionFactory.getSessionFactory(applicationName, properties);
        }
        try {
            File f = new File(ApplicationInitializer.getConfigDir() + "/" + applicationName + "_" +
                    sessionFactoryNameExtension + "_" + HIBERNATE_CONFIG_FILE);
            /*
            AnnotationConfiguration cfg = new AnnotationConfiguration();
            cfg.addProperties(properties);
            cfg.configure(f);            
            return cfg.buildSessionFactory(); 
            */
            Configuration cfg = new Configuration();
			cfg.configure(f);
			//return cfg.buildSessionFactory();  
			
			//HIB 4 - NEW WAY	
            ServiceRegistryBuilder ssrb = new ServiceRegistryBuilder().applySettings(cfg.getProperties());
            return cfg.buildSessionFactory(ssrb.buildServiceRegistry()); //ssrb.build());
        }       
        catch (HibernateException e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError(e);
        }
    }

    public static Session getSession(String sessionFactoryJNDIName) {
        return HibernateContext.getSession(sessionFactoryJNDIName);        
    }    
}