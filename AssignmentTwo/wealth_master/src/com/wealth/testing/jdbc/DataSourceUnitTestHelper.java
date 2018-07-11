package com.wealth.testing.jdbc;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public final class DataSourceUnitTestHelper {

    private static boolean initialized = false;
    
    public static final int LOCAL_ENV = 0;
    public static final int DEV_ENV = 0;
    public static final int TEST_ENV = 1;
    
    private static final String SCV_DS_JNDI_NAME = "rmbpb/ds/SCV_DS";
    private static final String CP_DS_JNDI_NAME = "rmbpb/ds/CP_DS";
    private static final String DMT_DS_JNDI_NAME = "rmbpb/ds/DMT_DS";
    private static final String AMR_DS_JNDI_NAME = "wealth/ds/AMR_DS";
    private static final String NOTIFICATION_DS_JNDI_NAME = "wealth/ds/NOTIFICATION_DS";
    private static final String CLIENT_INTERACTION_DS_JNDI_NAME = "wealth/ds/CLIENT_INTERACTION_DS";
    private static final String CSCOPS_DS_JNDI_NAME = "wealth/ds/CSCOPS_DS";
    private static final String WORKFLOW_DS_JNDI_NAME = "wealth/ds/Workflow_DS";
    private static final String MASTER_DS_JNDI_NAME = "wealth/ds/MASTER_DS";
    private static final String LOCAL_DS_JNDI_NAME = "wealth/ds/LOCAL_DS";
    private static final String STATIC_DATA_DATASOURCE_NAME = "wealth/ds/StaticData_DS";
    private static final String PRICING_ENGINE_DATASOURCE_NAME = "rmbpb/ds/PRICING_ENGINE_DS";
    private static final String DMS_DATASOURCE_NAME = "wealth/ds/DMS_DS";
    private static final String PAYMENTS_DATASOURCE_NAME = "wealth/ds/PAYMENTS_DOA_DS";
    private static final String PAYMENTS_BATCH_DATASOURCE_NAME = "wealth/ds/PAYMENTS_DOA_BATCH_DS";
    private static final String VALUATIONS_DS_JNDI_NAME = "wealth/ds/VALUATIONS_DS";

    private DataSourceUnitTestHelper() {}

    public static void init(int environment) throws NamingException {
        if (!initialized) {
            Context ctx = new InitialContext();
            
            switch (environment) {
                
                case 0 : {
                    SimpleDataSource scvDS = new SimpleDataSource();            
                    scvDS.dbDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
                    scvDS.dbServer = "jdbc:sqlserver://svjsqldev01:1433;databaseName=wealth_scv_qa;"; 
                    scvDS.dbLogin = "appadmin"; 
                    scvDS.dbPassword = "Password01";            
                    ctx.bind(DataSourceUnitTestHelper.SCV_DS_JNDI_NAME, scvDS);
                    
                    SimpleDataSource cpDS = new SimpleDataSource();            
                    cpDS.dbDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
                    cpDS.dbServer = "jdbc:sqlserver://svjsqldev01:1433;databaseName=wealth_cp_qa;"; 
                    cpDS.dbLogin = "appadmin"; 
                    cpDS.dbPassword = "Password01";
                    ctx.bind(DataSourceUnitTestHelper.CP_DS_JNDI_NAME, cpDS);
                    
                    SimpleDataSource dmtDS = new SimpleDataSource();            
                    dmtDS.dbDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
                    dmtDS.dbServer = "jdbc:sqlserver://svjsqldev01:1433;databaseName=DMT_LIVE;"; 
                    dmtDS.dbLogin = "appadmin"; 
                    dmtDS.dbPassword = "Password01";
                    ctx.bind(DataSourceUnitTestHelper.DMT_DS_JNDI_NAME, dmtDS);
                    
                    SimpleDataSource amrDS = new SimpleDataSource();            
                    amrDS.dbDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
                    amrDS.dbServer = "jdbc:sqlserver://svjsqldev01:1433;databaseName=wealth_amr_qa;"; 
                    amrDS.dbLogin = "appadmin"; 
                    amrDS.dbPassword = "Password01";
                    ctx.bind(DataSourceUnitTestHelper.AMR_DS_JNDI_NAME, amrDS);
                    
                    SimpleDataSource cscOpsDS = new SimpleDataSource();            
                    cscOpsDS.dbDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
                    cscOpsDS.dbServer = "jdbc:sqlserver://svjsqldev01:1433;databaseName=wealth_csc_ops;"; 
                    cscOpsDS.dbLogin = "appadmin"; 
                    cscOpsDS.dbPassword = "Password01";
                    ctx.bind(DataSourceUnitTestHelper.CSCOPS_DS_JNDI_NAME, cscOpsDS);
                    
                    SimpleDataSource masterDS = new SimpleDataSource();            
                    masterDS.dbDriver = "com.mysql.jdbc.Driver";
                    masterDS.dbServer = "jdbc:mysql://freakentwig:3306/wealth_testing"; 
                    masterDS.dbLogin = "testing"; 
                    masterDS.dbPassword = "testing";
                    ctx.bind(DataSourceUnitTestHelper.MASTER_DS_JNDI_NAME, masterDS);
                    
                    SimpleDataSource localDS = new SimpleDataSource();            
                    localDS.dbDriver = "com.mysql.jdbc.Driver";
                    localDS.dbServer = "jdbc:mysql://localhost:3306/wealth_testing"; 
                    localDS.dbLogin = "testing"; 
                    localDS.dbPassword = "testing";
                    ctx.bind(DataSourceUnitTestHelper.LOCAL_DS_JNDI_NAME , localDS);
                    
                    SimpleDataSource wfDS = new SimpleDataSource();            
                    wfDS.dbDriver = "com.mysql.jdbc.Driver";
                    wfDS.dbServer = "jdbc:mysql://freakentwig:3306/workflow_integration"; 
                    wfDS.dbLogin = "testing"; 
                    wfDS.dbPassword = "testing";
                    ctx.bind(DataSourceUnitTestHelper.WORKFLOW_DS_JNDI_NAME, wfDS);
                    
                    SimpleDataSource sdDS = new SimpleDataSource();
                    sdDS.dbDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
                    sdDS.dbServer = "jdbc:sqlserver://svjsqldev01:1433;DatabaseName=wealth_static";
                    sdDS.dbLogin = "appadmin";
                    sdDS.dbPassword = "Password01";
                    ctx.bind(DataSourceUnitTestHelper.STATIC_DATA_DATASOURCE_NAME, sdDS);
                    
                    SimpleDataSource ciDS = new SimpleDataSource();            
                    ciDS.dbDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
                    ciDS.dbServer = "jdbc:sqlserver://svjsqldev01:1433;SelectMethod=cursor;AutoCommit=true;DatabaseName=wealth_client_interaction"; 
                    ciDS.dbLogin = "appadmin"; 
                    ciDS.dbPassword = "Password01";
                    ctx.bind(DataSourceUnitTestHelper.CLIENT_INTERACTION_DS_JNDI_NAME, ciDS);
                    
                    SimpleDataSource notDS = new SimpleDataSource();            
                    notDS.dbDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
                    notDS.dbServer = "jdbc:sqlserver://svjsqldev01:1433;SelectMethod=cursor;AutoCommit=true;DatabaseName=wealth_notification"; 
                    notDS.dbLogin = "appadmin"; 
                    notDS.dbPassword = "Password01";
                    ctx.bind(DataSourceUnitTestHelper.NOTIFICATION_DS_JNDI_NAME, notDS);
                    
                    SimpleDataSource pricingEngineDS = new SimpleDataSource();            
                    pricingEngineDS.dbDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
                    pricingEngineDS.dbServer = "jdbc:sqlserver://svjsqldev01:1433;DatabaseName=rmbpb_PricingEngine"; 
                    pricingEngineDS.dbLogin = "appdevadmin"; 
                    pricingEngineDS.dbPassword = "Password01";
                    ctx.bind(DataSourceUnitTestHelper.PRICING_ENGINE_DATASOURCE_NAME, pricingEngineDS);
                    
                    SimpleDataSource dmsDS = new SimpleDataSource();            
                    dmsDS.dbDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
                    dmsDS.dbServer = "jdbc:sqlserver://svjsqldev01:1433;databaseName=wealth_dms;"; 
                    dmsDS.dbLogin = "appadmin"; 
                    dmsDS.dbPassword = "Password01";
                    ctx.bind(DataSourceUnitTestHelper.DMS_DATASOURCE_NAME, dmsDS);
                    
                    SimpleDataSource paymentsDS = new SimpleDataSource();            
                    paymentsDS.dbDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
                    paymentsDS.dbServer = "jdbc:sqlserver://svjsqldev01:1433;databaseName=wealth_payments;"; 
                    paymentsDS.dbLogin = "appadmin"; 
                    paymentsDS.dbPassword = "Password01";
                    ctx.bind(DataSourceUnitTestHelper.PAYMENTS_DATASOURCE_NAME, paymentsDS);
                    
                    SimpleDataSource paymentsBatchDS = new SimpleDataSource();            
                    paymentsBatchDS.dbDriver = "com.sybase.jdbc3.jdbc.SybDriver";
                    paymentsBatchDS.dbServer = "jdbc:sybase:Tds:SVJADS01:5000/phoenix_view"; 
                    paymentsBatchDS.dbLogin = "debitorder"; 
                    paymentsBatchDS.dbPassword = "debitorder";
                    ctx.bind(DataSourceUnitTestHelper.PAYMENTS_BATCH_DATASOURCE_NAME, paymentsBatchDS);
                    
                    SimpleDataSource valuationsDS = new SimpleDataSource();            
                    valuationsDS.dbDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
                    valuationsDS.dbServer = "jdbc:sqlserver://svjsqldev01:1433;databaseName=wealth_valuations;"; 
                    valuationsDS.dbLogin = "appadmin"; 
                    valuationsDS.dbPassword = "Password01";
                    ctx.bind(DataSourceUnitTestHelper.VALUATIONS_DS_JNDI_NAME, valuationsDS);
                    
                    initialized = true;
                    break;
                    }
                
                case 1 : {
                    SimpleDataSource scvDS = new SimpleDataSource();            
                    scvDS.dbDriver = "com.ibm.db2.jcc.DB2Driver";
                    scvDS.dbServer = "jdbc:db2://svjdb202:50001/SCV_TEST"; 
                    scvDS.dbLogin = "db2admin"; 
                    scvDS.dbPassword = "tg1nhn";            
                    ctx.bind(DataSourceUnitTestHelper.SCV_DS_JNDI_NAME, scvDS);
                    
                    SimpleDataSource cpDS = new SimpleDataSource();            
                    cpDS.dbDriver = "com.ibm.db2.jcc.DB2Driver";
                    cpDS.dbServer = "jdbc:db2://svjdb202:50001/CP_TEST"; 
                    cpDS.dbLogin = "db2admin"; 
                    cpDS.dbPassword = "tg1nhn";
                    ctx.bind(DataSourceUnitTestHelper.CP_DS_JNDI_NAME, cpDS);
                    
                    SimpleDataSource dmtDS = new SimpleDataSource();            
                    dmtDS.dbDriver = "com.ibm.db2.jcc.DB2Driver";
                    dmtDS.dbServer = "jdbc:db2://svjdb202:50001/DMT_TEST"; 
                    dmtDS.dbLogin = "db2admin"; 
                    dmtDS.dbPassword = "tg1nhn";
                    ctx.bind(DataSourceUnitTestHelper.DMT_DS_JNDI_NAME, dmtDS);
                    
                    initialized = true;
                    break;
                    }
                
                default : throw new NamingException("Initialization not supported for "+
                        "the given environment["+environment+"]. [Please use class attributes in "+
                        "DataSourceUnitTestHelper to set environment for the testcase!]");                
            }
        }
    }

    public static boolean isInitialized() {
        return initialized;
    }

    public static void shutdown() throws NamingException {
        Context ctx = new InitialContext();
        ctx.unbind(DataSourceUnitTestHelper.SCV_DS_JNDI_NAME);
        ctx.unbind(DataSourceUnitTestHelper.CP_DS_JNDI_NAME);
        ctx.unbind(DataSourceUnitTestHelper.DMT_DS_JNDI_NAME);
        ctx.unbind(DataSourceUnitTestHelper.AMR_DS_JNDI_NAME);
        ctx.unbind(DataSourceUnitTestHelper.NOTIFICATION_DS_JNDI_NAME);
        ctx.unbind(DataSourceUnitTestHelper.CLIENT_INTERACTION_DS_JNDI_NAME);
        ctx.unbind(DataSourceUnitTestHelper.CSCOPS_DS_JNDI_NAME);
        ctx.unbind(DataSourceUnitTestHelper.WORKFLOW_DS_JNDI_NAME);
        ctx.unbind(DataSourceUnitTestHelper.LOCAL_DS_JNDI_NAME);
        ctx.unbind(DataSourceUnitTestHelper.STATIC_DATA_DATASOURCE_NAME);
        ctx.unbind(DataSourceUnitTestHelper.PRICING_ENGINE_DATASOURCE_NAME);
        ctx.unbind(DataSourceUnitTestHelper.DMS_DATASOURCE_NAME);
        ctx.unbind(DataSourceUnitTestHelper.PAYMENTS_DATASOURCE_NAME);
        ctx.unbind(DataSourceUnitTestHelper.PAYMENTS_BATCH_DATASOURCE_NAME);
        ctx.unbind(DataSourceUnitTestHelper.VALUATIONS_DS_JNDI_NAME);
        
        initialized = false;
    }
}
