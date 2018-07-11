package com.wealth.client;

import java.io.InputStream;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

public class Log4jInitializer {

	private Log4jInitializer() {}

	public static void initializeLogger() {
		initializeLogger(Log4jInitializer.class);
	}

	public static void initializeLogger(Class c) {
		InputStream input = c.getClassLoader().getResourceAsStream("log4j.xml");
		new DOMConfigurator().doConfigure(input, LogManager.getLoggerRepository());
	}

	private static void test() {
		initializeLogger();
		Logger log = Logger.getLogger("Hallo");
		log.info("Test");
	}

	public static void main(String[] args) {
		Log4jInitializer.test();
	}
}
