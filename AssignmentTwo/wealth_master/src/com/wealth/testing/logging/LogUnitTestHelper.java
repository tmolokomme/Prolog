package com.wealth.testing.logging;

import java.util.Properties;

import org.apache.log4j.Category;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public final class LogUnitTestHelper {
    
    /** Flag - set when log4j is found in the classpath. */
    private static boolean isLog4jInitialized = false;
    
    
    /** Flag - set when log4j is found in the classpath. */
    private static boolean isLog4jInClasspath = false;
    
    /** Logging category. */
    private static Category category = null;
    
    /** Logging properties. */
    private static Properties properties = null;

    private LogUnitTestHelper() {}

    public static Category init(String className)  {
        
        LogUnitTestHelper.isLog4jInClasspath = true;
        
        if (!LogUnitTestHelper.isLog4jInitialized) {            
        
            try {
                Class aClass = Class.forName("org.apache.log4j.Category");
    
                category = Category.getInstance(className);
                properties = new Properties();
    
                // ############################################################
                // #
                // # Log4J settings. For information about Log4J visit
                // # http://jakarta.apache.org/log4j/index.html
                // #
                // ############################################################
    
                // # Set root category priority to DEBUG and its only appender to
                // console.
                //properties.put("log4j.rootCategory", "DEBUG, A1, testfile");
                properties.put("log4j.category.com.rmbprivatebank", "DEBUG, A1, testfile");
                properties.put("log4j.category.com.rmbpb", "DEBUG, A1, testfile");
                properties.put("log4j.category.com.rmbpb.test", "INFO, A1, testfile");
                properties.put("log4j.category.org.hibernate", "ERROR, A1, testfile");
                properties.put("log4j.category.net.sf", "FATAL, A1, testfile");
    
                // # console is set to be a ConsoleAppender.
                properties.put("log4j.appender.A1", "org.apache.log4j.ConsoleAppender");
    
                // # console uses PatternLayout.
                properties.put("log4j.appender.A1.layout", "org.apache.log4j.PatternLayout");
                properties.put("log4j.appender.A1.layout.ConversionPattern", "%5p %d{dd MMM yyyy HH:mm:ss,SSS} [%c] - %m%n");
    
                // # file is set to be a RollingFileAppender.
                properties.put("log4j.appender.testfile", "org.apache.log4j.RollingFileAppender");
                properties.put("log4j.appender.testfile.File", "junit-tests.log");
    
                properties.put("log4j.appender.testfile.MaxFileSize", "100KB");
                // # Keep one backup file
                properties.put("log4j.appender.testfile.MaxBackupIndex", "100");
    
                // # file uses PatternLayout.
                properties.put("log4j.appender.testfile.layout", "org.apache.log4j.PatternLayout");
                properties.put("log4j.appender.testfile.layout.ConversionPattern", "%5p %d{dd MMM yyyy HH:mm:ss,SSS} [%c] - %m%n");
    
                PropertyConfigurator.configure(properties);
                
                Logger L = Logger.getLogger(LogUnitTestHelper.class);
                L.debug("Log4J successfully instantiated.");
                return category;
            }
            catch (ClassNotFoundException cnfe) {
                LogUnitTestHelper.isLog4jInClasspath = false;
                LogUnitTestHelper.isLog4jInitialized = false;
                System.out.println("Log4J instantiation failed. Using System.out.println.");
                return null;
            }
        } else {
            return category;
        }
    }    
}