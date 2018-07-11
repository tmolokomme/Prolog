package com.wealth.staticdata.client;

import java.util.Hashtable;

import javax.naming.InitialContext;

import com.wealth.client.EJBServiceLocator;
import com.wealth.client.PropertyManager;
import com.wealth.client.ServerException;
import com.wealth.staticdata.client.interfaces.AccountTypeService;
import com.wealth.staticdata.client.interfaces.ContactTypeService;
import com.wealth.staticdata.client.interfaces.ProductHouseService;
import com.wealth.staticdata.client.interfaces.PropertyTypeService;

public class StaticDataServiceLocator {
    
    public static final String APPLICATION_NAME = "wealth_staticdata";
    private static final  String appName = APPLICATION_NAME;
    private static final String moduleName = APPLICATION_NAME + "_ejb";
    private static final String distinctName = ""; //default
    private static final String jndiStyle = "ejb"; //default
    
	public static StaticDataLookupSession getStaticDataLookupSession() throws ServerException {
		Hashtable<String, String> properties = new Hashtable<String, String>();
		properties.put(InitialContext.INITIAL_CONTEXT_FACTORY, PropertyManager.getInstance(StaticDataServiceLocator.APPLICATION_NAME).getInitialContextFactory());
		properties.put(InitialContext.PROVIDER_URL, PropertyManager.getInstance(StaticDataServiceLocator.APPLICATION_NAME).getProviderUrl());	
		properties.put(InitialContext.URL_PKG_PREFIXES, PropertyManager.getInstance(StaticDataServiceLocator.APPLICATION_NAME).getUrlPackagePrefixes());
		//return (StaticDataLookupSession) EJBServiceLocator.getEJB(properties, StaticDataServiceLocator.APPLICATION_NAME + "/StaticDataLookupSessionBean/remote");
		
		String beanName = "StaticDataLookupSessionBean";
		String remoteIfaceClassName = StaticDataLookupSession.class.getName();
		String jndiName = jndiStyle + appName + "/" + moduleName + "/" + distinctName + "/"+ beanName + "!" + remoteIfaceClassName;
		
		//String jndiName = "ejb:/jboss-as-ejb-remote-app/StaticDataLookupSessionBean!"+ StaticDataLookupSession.class.getName();
		return (StaticDataLookupSession) EJBServiceLocator.getEJB(properties, jndiName);
		
	}
	
	public static ContactTypeService getContactTypeService() throws ServerException {
        Hashtable<String, String> properties = new Hashtable<String, String>();
        properties.put(InitialContext.INITIAL_CONTEXT_FACTORY, PropertyManager.getInstance(StaticDataServiceLocator.APPLICATION_NAME).getInitialContextFactory());
        properties.put(InitialContext.PROVIDER_URL, PropertyManager.getInstance(StaticDataServiceLocator.APPLICATION_NAME).getProviderUrl());   
        properties.put(InitialContext.URL_PKG_PREFIXES, PropertyManager.getInstance(StaticDataServiceLocator.APPLICATION_NAME).getUrlPackagePrefixes());
        //return (ContactTypeService) EJBServiceLocator.getEJB(properties, StaticDataServiceLocator.APPLICATION_NAME + "/ContactTypeServiceImpl/remote");
        
        String beanName = "ContactTypeServiceImpl"; 
		String remoteIfaceClassName = ContactTypeService.class.getName();
		String jndiName = jndiStyle + appName + "/" + moduleName + "/" + distinctName + "/"+ beanName + "!" + remoteIfaceClassName;
        
        return (ContactTypeService) EJBServiceLocator.getEJB(properties, jndiName);
    }
	
	public static AccountTypeService getAccountTypeService() throws ServerException {
        Hashtable<String, String> properties = new Hashtable<String, String>();
        properties.put(InitialContext.INITIAL_CONTEXT_FACTORY, PropertyManager.getInstance(StaticDataServiceLocator.APPLICATION_NAME).getInitialContextFactory());
        properties.put(InitialContext.PROVIDER_URL, PropertyManager.getInstance(StaticDataServiceLocator.APPLICATION_NAME).getProviderUrl());   
        properties.put(InitialContext.URL_PKG_PREFIXES, PropertyManager.getInstance(StaticDataServiceLocator.APPLICATION_NAME).getUrlPackagePrefixes());
        //return (AccountTypeService) EJBServiceLocator.getEJB(properties, StaticDataServiceLocator.APPLICATION_NAME + "/AccountTypeServiceImpl/remote");
        
        String beanName = "AccountTypeServiceImpl"; 
		String remoteIfaceClassName = AccountTypeService.class.getName();
		String jndiName = jndiStyle + appName + "/" + moduleName + "/" + distinctName + "/"+ beanName + "!" + remoteIfaceClassName;
		
        return (AccountTypeService) EJBServiceLocator.getEJB(properties, jndiName);
    }
	
	public static PropertyTypeService getPropertyTypeService() throws ServerException {
        Hashtable<String, String> properties = new Hashtable<String, String>();
        properties.put(InitialContext.INITIAL_CONTEXT_FACTORY, PropertyManager.getInstance(StaticDataServiceLocator.APPLICATION_NAME).getInitialContextFactory());
        properties.put(InitialContext.PROVIDER_URL, PropertyManager.getInstance(StaticDataServiceLocator.APPLICATION_NAME).getProviderUrl());   
        properties.put(InitialContext.URL_PKG_PREFIXES, PropertyManager.getInstance(StaticDataServiceLocator.APPLICATION_NAME).getUrlPackagePrefixes());
        //return (PropertyTypeService) EJBServiceLocator.getEJB(properties, StaticDataServiceLocator.APPLICATION_NAME + "/PropertyTypeServiceImpl/remote");
        
        String beanName = "PropertyTypeServiceImpl"; 
   		String remoteIfaceClassName = PropertyTypeService.class.getName();
   		String jndiName = jndiStyle + appName + "/" + moduleName + "/" + distinctName + "/"+ beanName + "!" + remoteIfaceClassName;
        return (PropertyTypeService) EJBServiceLocator.getEJB(properties, jndiName);
    }
	
	public static ProductHouseService getPoductHouseService() throws ServerException {
        Hashtable<String, String> properties = new Hashtable<String, String>();
        properties.put(InitialContext.INITIAL_CONTEXT_FACTORY, PropertyManager.getInstance(StaticDataServiceLocator.APPLICATION_NAME).getInitialContextFactory());
        properties.put(InitialContext.PROVIDER_URL, PropertyManager.getInstance(StaticDataServiceLocator.APPLICATION_NAME).getProviderUrl());   
        properties.put(InitialContext.URL_PKG_PREFIXES, PropertyManager.getInstance(StaticDataServiceLocator.APPLICATION_NAME).getUrlPackagePrefixes());
        //return (ProductHouseService) EJBServiceLocator.getEJB(properties, StaticDataServiceLocator.APPLICATION_NAME + "/ProductHouseServiceImpl/remote");
        
        String beanName = "ProductHouseServiceImpl"; 
   		String remoteIfaceClassName = ProductHouseService.class.getName();
   		String jndiName = jndiStyle + appName + "/" + moduleName + "/" + distinctName + "/"+ beanName + "!" + remoteIfaceClassName;
        return (ProductHouseService) EJBServiceLocator.getEJB(properties, jndiName);
    }
	
	

}
