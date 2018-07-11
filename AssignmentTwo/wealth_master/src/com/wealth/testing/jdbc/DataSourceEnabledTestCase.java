package com.wealth.testing.jdbc;

import com.wealth.testing.jndi.JNDIEnabledTestCase;

public class DataSourceEnabledTestCase extends JNDIEnabledTestCase {
    
    private int environment = DataSourceUnitTestHelper.LOCAL_ENV;
    
    public DataSourceEnabledTestCase(String testname) {
        super(testname);
        this.environment = DataSourceUnitTestHelper.LOCAL_ENV;
    }
    
    public DataSourceEnabledTestCase(String testname, int environment) {
        super(testname);
        this.environment = environment;
    }

    protected void setUp() throws Exception {        
        super.setUp();
        if (!DataSourceUnitTestHelper.isInitialized()) {
            DataSourceUnitTestHelper.init(this.environment);
        }
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        DataSourceUnitTestHelper.shutdown();
    }
}