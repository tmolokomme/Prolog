package com.wealth.testing.jndi;

import com.wealth.testing.logging.LogEnabledTestCase;

public class JNDIEnabledTestCase extends LogEnabledTestCase {
    
    public JNDIEnabledTestCase(String name) {
        super(name);
    }

    protected void setUp() throws Exception {
        super.setUp();
        if (!JNDIUnitTestHelper.isInitialized()) {
            JNDIUnitTestHelper.init();
        }
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        // JNDIUnitTestHelper.shutdown();
    }    
}