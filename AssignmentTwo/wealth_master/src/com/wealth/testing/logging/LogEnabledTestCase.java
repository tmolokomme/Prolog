/*
 * @(#) LogEnabledTestCase.java
 * Copyright (c) 2007
 * Property of RMB Private Bank.
 */
package com.wealth.testing.logging;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import junit.framework.TestCase;

import org.apache.log4j.Category;

/**
 * This abstract class provides a test case with Log4J logging enabled.
 * 
 * @author $Author: hvandermerwe $
 */

public abstract class LogEnabledTestCase extends TestCase {

    /** The log4j category. */
    private Category category;    

    /**
     * Yet another initialisation utility constructor.
     * 
     * @param name
     *            The name of the test case as a <code>String</code>.
     * @param propertyFile
     *            The location of the log4j properties file as a
     *            <code>String</code>.
     */

    public LogEnabledTestCase(String testname) {
        super(testname);
    }

    /**
     * Returns the log4j properties.
     * 
     * @return Properties The properties for the log4j.
     */
    /*public Properties getProperties() {
        return this.properties;
    }*/

    /**
     * Sets the log4j properties.
     * 
     * @param properties
     *            The log4j properties.
     */
    /*public void setProperties(Properties properties) {
        this.properties = properties;
    }*/

    /**
     * The setup method of the test case.
     * 
     * @throws Exception
     *             Thrown if problems are encountered during set up.
     */
    protected void setUp() throws Exception {
        super.setUp();
        this.category = LogUnitTestHelper.init(this.getClass().getName());        
        info("Set up started.");
    }

    /**
     * The tear down method of the test case.
     * 
     * @throws Exception
     *             Thrown if problems are encountered during the tear down
     *             process.
     */
    protected void tearDown() throws Exception {
        super.tearDown();        
        info("Tear down finished.");
    }

    /**
     * A method for debugging.
     * 
     * @param message
     *            The message to log as an <code>Object</code>.
     */
    public void debug(Object message) {
        if (this.category != null) {
            this.category.debug(message);
        }
        else {
            System.out.println("DEBUG (" + getClass().getName() + ") " + getFormattedDateTime() + " - " + message);
        }
    }

    /**
     * A method for debugging.
     * 
     * @param message
     *            The message to log as an <code>Object</code>.
     * @param t
     *            The exception to log as a <code>Throwable</code>.
     */
    public void debug(Object message, Throwable t) {
        if (this.category != null) {
            this.category.debug(message);
            this.category.debug(getFormattedThrowable(t));
        }
        else {
            System.out.println("DEBUG (" + getClass().getName() + ") " + getFormattedDateTime() + " - " + message);
            System.out.println("DEBUG (" + getClass().getName() + ") " + getFormattedDateTime() + " - " + getFormattedThrowable(t));
        }
    }

    /**
     * A method for error logging.
     * 
     * @param message
     *            The message to log as an <code>Object</code>.
     */
    public void error(Object message) {
        if (this.category != null) {
            this.category.error(message);
        }
        else {
            System.out.println("ERROR (" + getClass().getName() + ") " + getFormattedDateTime() + " - " + message);
        }
    }

    /**
     * A method for error logging.
     * 
     * @param message
     *            The message to log as an <code>Object</code>.
     * @param t
     *            The exception to log as a <code>Throwable</code>.
     */
    public void error(Object message, Throwable t) {
        if (this.category != null) {
            this.category.error(message);
            this.category.error(getFormattedThrowable(t));
        }
        else {
            System.out.println("ERROR (" + getClass().getName() + ") " + getFormattedDateTime() + " - " + message);
            System.out.println("ERROR (" + getClass().getName() + ") " + getFormattedDateTime() + " - " + getFormattedThrowable(t));
        }
    }

    /**
     * A method for logging fatal information.
     * 
     * @param message
     *            The message to log as an <code>Object</code>.
     */
    public void fatal(Object message) {
        if (this.category != null) {
            this.category.fatal(message);
        }
        else {
            System.out.println("FATAL (" + getClass().getName() + ") " + getFormattedDateTime() + " - " + message);
        }
    }

    /**
     * A method for logging fatal information.
     * 
     * @param message
     *            The message to log as an <code>Object</code>.
     * @param t
     *            The exception to log as a <code>Throwable</code>.
     */
    public void fatal(Object message, Throwable t) {
        if (this.category != null) {
            this.category.fatal(message);
            this.category.fatal(getFormattedThrowable(t));
        }
        else {
            System.out.println("FATAL (" + getClass().getName() + ") " + getFormattedDateTime() + " - " + message);
            System.out.println("FATAL (" + getClass().getName() + ") " + getFormattedDateTime() + " - " + getFormattedThrowable(t));
        }
    }

    /**
     * A method for information logging.
     * 
     * @param message
     *            The message to log as an <code>Object</code>.
     */
    public void info(Object message) {
        if (this.category != null) {
            this.category.info(message);
        }
        else {
            System.out.println(" INFO (" + getClass().getName() + ") " + getFormattedDateTime() + " - " + message);
        }
    }

    /**
     * A method for information logging.
     * 
     * @param message
     *            The message to log as an <code>Object</code>.
     * @param t
     *            The exception to log as a <code>Throwable</code>.
     */
    public void info(Object message, Throwable t) {
        if (this.category != null) {
            this.category.info(message);
            this.category.info(getFormattedThrowable(t));
        }
        else {
            System.out.println(" INFO (" + getClass().getName() + ") " + getFormattedDateTime() + " - " + message);
            System.out.println(" INFO (" + getClass().getName() + ") " + getFormattedDateTime() + " - " + getFormattedThrowable(t));
        }
    }

    /**
     * A method for warning logging.
     * 
     * @param message
     *            The message to log as an <code>Object</code>.
     */
    public void warn(Object message) {
        if (this.category != null) {
            this.category.warn(message);
        }
        else {
            System.out.println(" WARN (" + getClass().getName() + ") " + getFormattedDateTime() + " - " + message);
        }
    }

    /**
     * A method for warning logging.
     * 
     * @param message
     *            The message to log as an <code>Object</code>.
     * @param t
     *            The exception to log as a <code>Throwable</code>.
     */
    public void warn(Object message, Throwable t) {
        if (this.category != null) {
            this.category.warn(message);
            this.category.warn(getFormattedThrowable(t));
        }
        else {
            System.out.println(" WARN (" + getClass().getName() + ") " + getFormattedDateTime() + " - " + message);
            System.out.println(" WARN (" + getClass().getName() + ") " + getFormattedDateTime() + " - " + getFormattedThrowable(t));
        }
    }

    /**
     * A format a throwable object for logging.
     * 
     * @param t
     *            The exception to format as a <code>Throwable</code>.
     * @return String The formatted throwable object.
     */
    private String getFormattedThrowable(Throwable t) {
        StringWriter sw = new StringWriter();
        t.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }

    /**
     * A method for building a formatted date and time string.
     * 
     * @return String The formatted date and time.
     */
    private String getFormattedDateTime() {
        String sDate = "*none*";
        Calendar cal = Calendar.getInstance(Locale.getDefault());
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy HH:mm:ss,SSS");
        sDate = sdf.format(cal.getTime());
        return sDate;
    }
}
