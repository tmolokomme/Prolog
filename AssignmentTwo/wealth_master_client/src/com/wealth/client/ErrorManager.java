package com.wealth.client;

import java.util.Hashtable;
import java.util.PropertyResourceBundle;

public class ErrorManager {
    
	private static Hashtable<String, ErrorManager> instances = null;
	
	private String applicationName = null;
	private PropertyResourceBundle properties;

	public synchronized static ErrorManager getInstance(String applicationName) throws ServerException {
		if (instances == null) {
		    instances = new Hashtable<String,ErrorManager>();
		}
	    if (!instances.containsKey(applicationName)) {
			ErrorManager errorM = new ErrorManager(applicationName);
			instances.put(applicationName, errorM);
		}
	    return instances.get(applicationName);
	}

	private ErrorManager(String applicationName) throws ServerException {
	    this.applicationName = applicationName;
		this.properties = ApplicationErrorInitializer.initialize(this.applicationName);
	}

    public String getApplicationName() {
        return applicationName;
    }    

    public PropertyResourceBundle getProperties() {
        return properties;
    }
    
    public String getProperty(String key) {
        return this.properties.getString(key);
    }
    
    public static void main(String args[]) {
        String errorMessage = ErrorManager.getInstance("wara").getProperty("ui.1111");
        System.out.println(errorMessage);
    }
}
