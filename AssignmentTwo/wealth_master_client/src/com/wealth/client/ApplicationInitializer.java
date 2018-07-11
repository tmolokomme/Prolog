package com.wealth.client;

public class ApplicationInitializer {	

	public static String getConfigDir() throws ServerException {
		String configDir = System.getenv().get("WEALTH_CONFIG_DIR");
		if (configDir == null) {
			throw new ServerException("WEALTH_CONFIG_DIR not set! FATAL");
		}
		return configDir;
	}	

	public static void main(String[] args) throws ServerException {
		System.out.println(ApplicationInitializer.getConfigDir());
	}
}
