package com.wealth.testing.mq;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
//import javax.resource.spi.InvalidPropertyException;

import org.apache.log4j.Logger;

import com.ibm.mq.jms.JMSC;
import com.ibm.mq.jms.MQQueueConnectionFactory;

public class MQUnitTestHelper {
    
    /**
     * Standard Logger.
     */
    private static final Logger L = Logger.getLogger(MQUnitTestHelper.class);
    
    public static final String DEFAULT_MQ_CONN_FACTORY_JNDI_NAME = "rmbpb_scv/jms/WASMQ_CONN_FACTORY";
    
    private static MQQueueConnectionFactory mqqcf;
    
    public static void setupQueueConnectionFactory(String jndiName)
            throws NamingException, JMSException { //InvalidPropertyException
        
        // Setup the MQ Connection Factory
        mqqcf = new MQQueueConnectionFactory();
        mqqcf.setTransportType(JMSC.MQJMS_TP_CLIENT_MQ_TCPIP);
        //mqqcf.setTransportType(JMSC.MQJMS_CLIENT_JMS_COMPLIANT);
        mqqcf.setChannel("SYSTEM.DEF.SVRCONN");        
        mqqcf.setHostName("svjscv02");
        mqqcf.setQueueManager("PRIVATEBANK.VODS.DEV.QMGR");
        mqqcf.setPort(1414);
        L.debug("mqqcf=["+mqqcf+"]");
                
        // Bind the MQ Connection Factory to the JNDI context.
        Context ctx = new InitialContext();
        ctx.bind(jndiName, mqqcf);
        
        // Create a Queue Connection
        QueueConnection qConn = mqqcf.createQueueConnection("RMBPBTST", "RMBTEST1");
        L.debug("qConn=["+qConn+"]");
        
        // Create a Queue Session
        QueueSession qSess = qConn.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
        L.debug("qSess=["+qSess+"]");
        
        // ---------- CREATE ALL THE QUEUEs TO BE IN THE JNDI CONTEXT ----------------        
        // Setup the VodsPortal Request Queue
        Queue requestVodsPortalQ = qSess.createQueue("VODSPORTAL.REQUEST.Q");
        L.debug("requestVodsPortalQ=["+requestVodsPortalQ+"]");
        ctx.bind("rmbpb_scv/jms/request_vodsportal", requestVodsPortalQ);
        // Setup the VodsPortal Reply Queue
        Queue replyVodsPortalQ = qSess.createQueue("VODSPORTAL.REPLY.Q");
        L.debug("replyVodsPortalQ=["+replyVodsPortalQ+"]");
        ctx.bind("rmbpb_scv/jms/reply_vodsportal", replyVodsPortalQ);
        // Setup the VodsPortal Send Queue
        Queue sendVodsPortalQ = qSess.createQueue("VODSPORTAL.PREPROD.SEND.Q");
        L.debug("sendVodsPortalQ=["+sendVodsPortalQ+"]");
        ctx.bind("rmbpb_scv/jms/send_vodsportal", sendVodsPortalQ);
        // Setup the VodsPortal Receive Queue
        Queue receiveVodsPortalQ = qSess.createQueue("PRIVATEBANK.VODS.REPLY.Q");
        L.debug("receiveVodsPortalQ=["+receiveVodsPortalQ+"]");
        ctx.bind("rmbpb_scv/jms/receive_vodsportal", receiveVodsPortalQ);
        
        // Setup the Phoenix Request Queue
        Queue requestPhoenixQ = qSess.createQueue("PHOENIX.REQUEST.Q");
        L.debug("requestPhoenixQ=["+requestPhoenixQ+"]");        
        ctx.bind("rmbpb_scv/jms/request_phoenix", requestPhoenixQ);        
        // Setup the Phoenix Reply Queue
        Queue replyPhoenixQ = qSess.createQueue("PHOENIX.REPLY.Q");
        L.debug("replyPhoenixQ=["+replyPhoenixQ+"]");        
        ctx.bind("rmbpb_scv/jms/reply_phoenix", replyPhoenixQ);        
                
        // Setup the Aims Request Queue
        Queue requestAimsQ = qSess.createQueue("AIMS.REQUEST.Q");
        L.debug("requestAimsQ=["+requestAimsQ+"]");
        ctx.bind("rmbpb_scv/jms/request_aims", requestAimsQ);
        // Setup the Aims Reply Queue
        Queue replyAimsQ = qSess.createQueue("AIMS.REPLY.Q");
        L.debug("replyAimsQ=["+replyAimsQ+"]");
        ctx.bind("rmbpb_scv/jms/reply_aims", replyAimsQ);
        
        // Setup the DomDoc Request Queue
        Queue requestDomDocQ = qSess.createQueue("DOMDOC.REQUEST.Q");
        L.debug("requestDomDocQ=["+requestDomDocQ+"]");
        ctx.bind("rmbpb_scv/jms/request_domdoc", requestDomDocQ);
        // Setup the DomDoc Reply Queue
        Queue replyDomDocQ = qSess.createQueue("DOMDOC.REPLY.Q");
        L.debug("replyDomDocQ=["+replyDomDocQ+"]");
        ctx.bind("rmbpb_scv/jms/reply_domdoc", replyDomDocQ);
        
        // Setup the Command Inquiry Request Queue
        Queue requestCommandInquiryQ = qSess.createQueue("COMMAND.INQUIRY.REQUEST.Q");
        L.debug("requestCommandInquiryQ=["+requestCommandInquiryQ+"]");
        ctx.bind("rmbpb_scv/jms/request_command_inquiries", requestCommandInquiryQ);
        // Setup the Command Update Request Queue
        Queue requestCommandUpdateQ = qSess.createQueue("COMMAND.UPDATE.REQUEST.Q");
        L.debug("requestCommandUpdateQ=["+requestCommandUpdateQ+"]");
        ctx.bind("rmbpb_scv/jms/request_command_updates", requestCommandUpdateQ);
        
        // Setup the Autoload Reply Queue
        Queue replyAutoloadQ = qSess.createQueue("AUTOLOAD.REPLY.Q");
        L.debug("replyAutoloadQ=["+replyAutoloadQ+"]");
        ctx.bind("rmbpb_scv/jms/reply_commands", replyAutoloadQ);
        
        // Setup the SCV Dmt Push (Request) Queue
        Queue pushScvDmtQ = qSess.createQueue("SCV.DMT.PUSH.Q");
        L.debug("pushScvDmtQ=["+pushScvDmtQ+"]");
        ctx.bind("rmbpb_scv/jms/push_dmt_updates", pushScvDmtQ);
    }
    
    public static void destroyQueueConnectionFactory(String jndiName) throws NamingException {        
        mqqcf = null;
        System.gc();        
    }
}