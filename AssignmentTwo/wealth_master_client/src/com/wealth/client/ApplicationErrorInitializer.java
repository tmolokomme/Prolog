package com.wealth.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.PropertyResourceBundle;


public class ApplicationErrorInitializer {		

	public static PropertyResourceBundle initialize(String applicationName) throws ServerException {
		try {
			String configFileName = ApplicationInitializer.getConfigDir() + "/"+applicationName+"_error.properties";
			File appConfig = new File(configFileName);
			if (!appConfig.exists()) {
				throw new ServerException("Config file does not exist: "+ configFileName);
			}
			InputStream input = new FileInputStream(appConfig);
			if (input == null) {
				throw new RuntimeException("Failed to load inputStream : "+ configFileName);
			}
			PropertyResourceBundle p = new PropertyResourceBundle(input);
			//p.getBundle(applicationName+"_error");
			return p;
		} catch (IOException e) {
			throw new ServerException(e);
		}
	}

	public static void main(String[] args) throws ServerException {
	    PropertyResourceBundle prb = ApplicationErrorInitializer.initialize("wara");
	    System.out.println(prb);       
        System.out.println(prb.getString("ejb.ServerException"));       
        System.out.println(prb.getString("ui.1111"));
	}
}
