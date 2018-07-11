package org.mockejb;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.naming.Context;
import javax.naming.NamingException;

import org.mockejb.MDBDescriptor;
import org.mockejb.MockContainer;
import org.mockejb.jms.MockQueue;
import org.mockejb.jms.MockTopic;
import org.mockejb.jms.QueueConnectionFactoryImpl;
import org.mockejb.jms.TopicConnectionFactoryImpl;

public class WealthMockContainer extends MockContainer {
    
    protected Context context = null;
    private String jmsUsername = null;
    private String jmsPassword = null;

    public WealthMockContainer(Context context) {
        super(context);
        this.context = context;
    }
    
    public void setJMSUsername(String username) {
        this.jmsUsername = username;
    }
    
    public void setJMSPassword(String password) {
        this.jmsPassword = password;
    }

    public void deploy(MDBDescriptor descriptor) throws NamingException, JMSException {

        // Create connection factory and destination using JMS 1.0 way
        MessageConsumer consumer;
        Connection connection;
        
        if ( descriptor.isTopic() ){
            
            TopicConnectionFactory topicConnectionFactory = (TopicConnectionFactory) createJMSObject( 
                    descriptor.getConnectionFactoryJndiName(), descriptor.isAlreadyBound(), 
                    new TopicConnectionFactoryImpl()); 
            
            Topic topic = (Topic) createJMSObject(descriptor.getDestinationJndiName(), descriptor.isAlreadyBound(),
                    new MockTopic(descriptor.getDestinationJndiName())); 
            
            TopicConnection topicConnection =  topicConnectionFactory.createTopicConnection(this.jmsUsername, this.jmsPassword);
            connection = topicConnection;
            // TODO: implement transactions and acknowledgements
            TopicSession topicSession = topicConnection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
            consumer = topicSession.createSubscriber(topic);
            
        }
        else {

            QueueConnectionFactory queueConnectionFactory = (QueueConnectionFactory) createJMSObject( 
                    descriptor.getConnectionFactoryJndiName(), descriptor.isAlreadyBound(), 
                    new QueueConnectionFactoryImpl()); 
            
            Queue queue = (Queue) createJMSObject(descriptor.getDestinationJndiName(), descriptor.isAlreadyBound(),
                    new MockQueue(descriptor.getDestinationJndiName())); 
           
            QueueConnection queueConnection =  queueConnectionFactory.createQueueConnection(this.jmsUsername, this.jmsPassword);
            connection = queueConnection;
            QueueSession queueSession = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            consumer = queueSession.createReceiver( queue );
        }
        
        // Same routine as for session bean
        MDBHome home = new MDBHome( descriptor );
        MDBHomeIface mdbHome = (MDBHomeIface) home.createProxy();
        
        MessageListener messageListener = mdbHome.create();
        
        consumer.setMessageListener( messageListener );
        connection.start();
    }
    
    /**
     * Helper method to create connection factory or destination based on the settings
     * 
     * @param jndiName
     * @param isAlreadyBound if true, get one from JNDI
     * @param defaultImpl
     * @return created object
     */
    private Object createJMSObject(String jndiName, boolean isAlreadyBound, Object defaultImpl)
            throws NamingException {
        
        Object obj = defaultImpl;
        if (isAlreadyBound) {
            obj = context.lookup( jndiName );
        } else {
            context.rebind( jndiName, defaultImpl );
        }
        
        return obj;        
    }
}