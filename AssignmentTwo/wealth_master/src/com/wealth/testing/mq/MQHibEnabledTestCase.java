package com.wealth.testing.mq;

import javax.jms.JMSException;
import javax.naming.NamingException;
//import javax.resource.spi.InvalidPropertyException;

import com.wealth.testing.hibernate.HibernateEnabledTestCase;

public class MQHibEnabledTestCase extends HibernateEnabledTestCase {

    private String queueConnFactoryJNDIName = "qcf";
    
    public MQHibEnabledTestCase(String testname) {
        super(testname);
        this.queueConnFactoryJNDIName = MQUnitTestHelper.DEFAULT_MQ_CONN_FACTORY_JNDI_NAME;
    }
    
    public MQHibEnabledTestCase(String testname, String queueConnFactoryJNDIName) {
        super(testname);
        this.queueConnFactoryJNDIName = queueConnFactoryJNDIName;
    }
    
    public MQHibEnabledTestCase(String testname, String hibSessionFactoryJNDIName, String queueConnFactoryJNDIName) {
        super(testname, hibSessionFactoryJNDIName);
        this.queueConnFactoryJNDIName = queueConnFactoryJNDIName;
    }

    public MQHibEnabledTestCase(String testname, int environment, String hibSessionFactoryJNDIName, String queueConnFactoryJNDIName) {
        super(testname, environment, hibSessionFactoryJNDIName);
        this.queueConnFactoryJNDIName = queueConnFactoryJNDIName;
    }

    protected void setUp() throws Exception {
        super.setUp();
        
        /*try {
            MQUnitTestHelper.setupQueueConnectionFactory(this.queueConnFactoryJNDIName);
        }
        catch (InvalidPropertyException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
        catch (NamingException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
        catch (JMSException e) {
            e.printStackTrace();
            Exception le = e.getLinkedException();
            if (le != null) le.printStackTrace();
            fail(e.getMessage());
        } */
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        MQUnitTestHelper.destroyQueueConnectionFactory(this.queueConnFactoryJNDIName);
    }
}