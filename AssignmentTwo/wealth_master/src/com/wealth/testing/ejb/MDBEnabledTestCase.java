package com.wealth.testing.ejb;

import com.wealth.testing.mq.MQHibEnabledTestCase;

public class MDBEnabledTestCase extends MQHibEnabledTestCase {
    
    public MDBEnabledTestCase(String testname) {
        super(testname);
    }
    
    public MDBEnabledTestCase(String testname, String hibSessionFactoryJNDIName) {
        super(testname, hibSessionFactoryJNDIName);
    }
    
    public MDBEnabledTestCase(String testname, String hibSessionFactoryJNDIName, String queueConnFactoryJNDIName) {
        super(testname, hibSessionFactoryJNDIName, queueConnFactoryJNDIName);
    }    

    public MDBEnabledTestCase(String testname, int environment, String hibSessionFactoryJNDIName, String queueConnFactoryJNDIName) {
        super(testname, environment, hibSessionFactoryJNDIName, queueConnFactoryJNDIName);
    }

    protected void setUp() throws Exception {
        super.setUp();
        MDBUnitTestHelper.setupMockContainer();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        MDBUnitTestHelper.destroyMockContainer();
    }
}