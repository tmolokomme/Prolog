package com.wealth.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ApplicationPropertyInitializer {

	public static Properties initialize(String applicationName) throws ServerException {
		try {
			String configFileName = ApplicationInitializer.getConfigDir() + "/"+applicationName+".properties";
			File appConfig = new File(configFileName);
			if (!appConfig.exists()) {
				throw new ServerException("Config file does not exist: "
						+ configFileName);
			}
			InputStream input = new FileInputStream(appConfig);
			/*DEAD CODE
			if (input == null) {
				throw new RuntimeException("Failed to load inputStream : "
						+ configFileName);
			} */
			Properties p = new Properties();
			p.load(input);
			return p;
		} catch (IOException e) {
			throw new ServerException(e);
		}
	}	

	public static void main(String[] args) throws ServerException {
		Properties p = ApplicationPropertyInitializer.initialize("wara");
		System.out.println(p);
	}
}
