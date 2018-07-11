package com.wealth.client;

import java.util.Hashtable;
import java.util.Properties;

public class PropertyManager {
    
	private static Hashtable<String, PropertyManager> instances = null;
	
	private String applicationName = null;
	private Properties properties = null;

	public synchronized static PropertyManager getInstance(String applicationName) throws ServerException {
		if (instances == null) {
		    instances = new Hashtable<String, PropertyManager>();
		}
	    if (!instances.containsKey(applicationName)) {
			PropertyManager propMan = new PropertyManager(applicationName);
			instances.put(applicationName, propMan);
		}
		return instances.get(applicationName);
	}

	private PropertyManager(String applicationName) throws ServerException {
		this.properties = ApplicationPropertyInitializer.initialize(applicationName);
	}

	public String getVersionNumber() throws ServerException {
		String version = properties.getProperty("app.version");
		if (version == null)
			return "Development Version";
		return version;
	}
	
	public String getHibernateConfigFile() throws ServerException {
		return getExtrapolatedProperty("hibernate.config");
	}
	
	public String getLog4jConfigFile() throws ServerException {
		return getExtrapolatedProperty("log4j.config");
	}
	
	public String getInitialContextFactory() {
		return properties.getProperty("client.initial.context.factory");
	}
	
	public String getProviderUrl() {
		return properties.getProperty("client.provider.url");
	}

	public String getUrlPackagePrefixes() {
		return properties.getProperty("client.url.package.prefixes");
	}
	
	public Properties getProperties() {
		return properties;
	}
	
	public static void main(String[] args) throws ServerException {
		System.out.println(PropertyManager.getInstance("wara").getVersionNumber());
		System.out.println(PropertyManager.getInstance("wara").getHibernateConfigFile());
		System.out.println(PropertyManager.getInstance("wara").getLog4jConfigFile());
	}
	
	private String getExtrapolatedProperty(String key) throws ServerException {
		String value = properties.getProperty(key);
		if (value == null) return null;
		value = value.replace("$configdir", ApplicationInitializer.getConfigDir());
		value = value.replace('\\', '/');
		return value;		
	}

    public String getApplicationName() {
        return applicationName;
    }
}