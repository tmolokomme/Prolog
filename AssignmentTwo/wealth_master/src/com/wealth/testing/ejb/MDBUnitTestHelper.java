package com.wealth.testing.ejb;

import javax.jms.JMSException;
import javax.jms.MessageListener;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.mockejb.MDBDescriptor;
import org.mockejb.WealthMockContainer;
import org.mockejb.interceptor.AspectSystem;
import org.mockejb.interceptor.AspectSystemFactory;
import org.mockejb.interceptor.ClassPointcut;
import org.mockejb.interceptor.InvocationRecorder;
import org.mockejb.jms.MockQueue;
import org.mockejb.jndi.MockContextFactory;

public class MDBUnitTestHelper {
    
    private static boolean isInitialized = false;
    
    private static WealthMockContainer mockContainer = null;
    private static InvocationRecorder recorder = null;
    private static AspectSystem aspectSystem = null;
    
    public static void setupMockContainer() throws NamingException {
        if (!isInitialized) {
            
            Context ctx = new InitialContext();            
            MockContextFactory.setDelegateContext(ctx);
            mockContainer = new WealthMockContainer(ctx);
            mockContainer.setJMSUsername("scvtest");
            mockContainer.setJMSPassword("scvtest");
            
            recorder = new InvocationRecorder();
            aspectSystem = AspectSystemFactory.getAspectSystem();
            isInitialized = true;
        }
    }
    
    public static void setupMDB(String queueConnFactoryJNDIName, String queueJNDIName, MessageListener messageBean)
            throws NamingException, JMSException {
        
        if (isInitialized) {
            
            MockQueue testQueue = new MockQueue(queueJNDIName);
            testQueue.addMessageListener(messageBean);
        
            //context.rebind( "AnotherSampleQueueConnectionFactory", new org.mockejb.jms.QueueConnectionFactoryImpl() );
            //context.rebind( "AnotherSampleQueue", new org.mockejb.jms.MockQueue( "AnotherSampleQueue" ) );
            MDBDescriptor mdbDescriptor = new MDBDescriptor(queueConnFactoryJNDIName, queueJNDIName, messageBean);            
            
            //Then method tell MockEJB not to create queues/factories
            mdbDescriptor.setIsAlreadyBound(true);            
            mockContainer.deploy(mdbDescriptor);        
                    
            // Intercept MDB
            aspectSystem.add(new ClassPointcut(MessageListener.class, false), recorder);
            
        } else {
            throw new NamingException("MockContainer not setup yet or setup failed!");
        }
    }
    
    public static void destroyMockContainer() {        
        aspectSystem = null;
        recorder = null;
        mockContainer = null;
    }
}