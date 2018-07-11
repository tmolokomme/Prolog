package com.wealth.persistence.jmx;

import org.apache.log4j.Logger;
import org.hibernate.ConnectionReleaseMode;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Environment;
//>>import org.hibernate.transaction.JBossTransactionManagerLookup;
//>>import org.hibernate.transaction.JTATransactionFactory;

//import org.jboss.system.ServiceMBeanSupport;
//import org.jboss.logging.Logger;
//import org.jboss.util.naming.Util;

import com.wealth.persistence.factory.HibernateSessionFactory;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import java.util.Properties;

public class Hibernate implements HibernateMBean { //extends ServiceMBeanSupport

    private static final Logger log = Logger.getLogger( Hibernate.class );

    /**
     * notification type produced when the session factory gets created
     */
    public static final String SESSION_FACTORY_CREATE = "hibernate.sessionfactory.create";
    
    /**
     * notification type produced when the session factory gets destroyed
     */
    public static final String SESSION_FACTORY_DESTROY = "hibernate.sessionfactory.destroy";

    // Configuration attributes "passed through" to Hibernate
    private String datasourceName;
    private String dialect;
    private String defaultSchema;
    private String defaultCatalog;
    private String hbm2ddlAuto;
    private String querySubstitutions;
    private String showSqlEnabled;
    private String username;
    private String password;
    private String cacheProviderClass;
//    private ObjectName deployedTreeCacheObjectName;
    private String cacheRegionPrefix;

    // Configuration attributes used strictly by the MBean
    private String sessionFactoryName;
    private String sessionFactoryNameExtension;
    private String applicationName;

    // Internal state
    private boolean dirty = false;
    private SessionFactory sessionFactory;
    
    
    public Hibernate() {
    	log.info("....$$$$$$$$$$$$ Constructed...." + Hibernate.class.getCanonicalName());
    }
    
    /**
     * (non-Javadoc)
     * @see com.wealth.persistence.jmx.HibernateMBean#getApplicationName()
     * @jmx.managed-attribute
     */
    public String getApplicationName() {        
        return applicationName;
    }
    
    /**
     * (non-Javadoc)
     * @see com.wealth.persistence.jmx.HibernateMBean#setApplicationName(java.lang.String)
     * @jmx.managed-attribute
     */
    public void setApplicationName(String applicationName) {        
        this.applicationName = applicationName;
    }
    
    /**
     * (non-Javadoc)
     * @see com.wealth.persistence.jmx.HibernateMBean#getSessionFactoryNameExtension()
     * @jmx.managed-attribute
     */
    public String getSessionFactoryNameExtension() {        
        return sessionFactoryNameExtension;
    }
    
    /**
     * (non-Javadoc)
     * @see com.wealth.persistence.jmx.HibernateMBean#setSessionFactoryNameExtension(java.lang.String)
     * @jmx.managed-attribute
     */
    public void setSessionFactoryNameExtension(String sessionFactoryNameExtension) {        
        this.sessionFactoryNameExtension = sessionFactoryNameExtension;
    }

    /**
     * Get the <tt>SessionFactory</tt> JNDI name.
     * @jmx.managed-attribute
     */
    public String getSessionFactoryName()
    {
       return sessionFactoryName;
    }

    /**
     * Set the <tt>SessionFactory</tt> JNDI name.
     * @jmx.managed-attribute
     */
    public void setSessionFactoryName(String sessionFactoryName)
    {
       this.sessionFactoryName = sessionFactoryName;
       dirty = true;
    }

    /**
     * Get the JNDI datasource name.
     * @jmx.managed-attribute
     */
    public String getDatasourceName()
    {
       return datasourceName;
    }

    /**
     * Set the JNDI datasource name.
     * @jmx.managed-attribute
     */
    public void setDatasourceName(String datasourceName)
    {
       this.datasourceName = datasourceName;
       dirty = true;
    }

    /**
     * Get the default database schema.
     * @jmx.managed-attribute
     */
    public String getDefaultSchema()
    {
       return defaultSchema;
    }

    /**
     * Set the default database schema.
     * @jmx.managed-attribute
     */
    public void setDefaultSchema(String defaultSchema)
    {
       this.defaultSchema = defaultSchema;
       dirty = true;
    }

    /**
     * Get the default database catalog.
     * @jmx.managed-attribute
     */
    public String getDefaultCatalog()
    {
       return defaultCatalog;
    }

    /**
     * Set the default database catalog.
     * @jmx.managed-attribute
     */
    public void setDefaultCatalog(String defaultCatalog)
    {
       this.defaultCatalog = defaultCatalog;
    }

    /**
     * @jmx.managed-attribute
     */
    public String getHbm2ddlAuto()
    {
       return hbm2ddlAuto;
    }

    /**
     * @jmx.managed-attribute
     */
    public void setHbm2ddlAuto(String hbm2ddlAuto)
    {
       this.hbm2ddlAuto = hbm2ddlAuto;
       dirty = true;
    }

    /**
     * Get the Hibernate SQL dialect.
     * @jmx.managed-attribute
     */
    public String getDialect()
    {
       return dialect;
    }

    /**
     * Set the Hibernate SQL dialect.
     * @jmx.managed-attribute
     */
    public void setDialect(String dialect)
    {
       this.dialect = dialect;
       dirty = true;
    }    

    /**
     * Get the query substitutions.
     * @jmx.managed-attribute
     */
    public String getQuerySubstitutions()
    {
       return querySubstitutions;
    }

    /**
     * Set the query substitutions.
     * @jmx.managed-attribute
     */
    public void setQuerySubstitutions(String querySubstitutions)
    {
       this.querySubstitutions = querySubstitutions;
       dirty = true;
    }

    /**
     * Get the cache provider class.
     * @jmx.managed-attribute
     */
    public String getCacheProviderClass()
    {
       return cacheProviderClass;
    }

    /**
     * Set the cache provider class.
     * @jmx.managed-attribute
     */
    public void setCacheProviderClass(String cacheProviderClass)
    {
       this.cacheProviderClass = cacheProviderClass;
       dirty = true;
    }

    /**
     * @jmx.managed-attribute
     */
    public String getCacheRegionPrefix()
    {
       return cacheRegionPrefix;
    }

    /**
     * @jmx.managed-attribute
     */
    public void setCacheRegionPrefix(String cacheRegionPrefix)
    {
       this.cacheRegionPrefix = cacheRegionPrefix;
       dirty = true;
    }

    /**
     * Is SQL being logged to the console?
     * @jmx.managed-attribute
     */
    public String getShowSqlEnabled()
    {
       return showSqlEnabled;
    }

    /**
     * @jmx.managed-attribute
     */
    public void setShowSqlEnabled(String showSqlEnabled)
    {
       this.showSqlEnabled = showSqlEnabled;
       dirty = true;
    }

    /**
     * Get the database username.
     * @jmx.managed-attribute
     */
    public String getUsername()
    {
       return username;
    }

    /**
     * Set the database username.
     * @jmx.managed-attribute
     */
    public void setUsername(String username)
    {
       this.username = username;
       dirty = true;
    }

    /**
     * Get the database password.
     * @jmx.managed-attribute
     */
    public String getPassword()
    {
       return password;
    }

    /**
     * Set the database password.
     * @jmx.managed-attribute
     */
    public void setPassword(String password)
    {
       this.password = password;
       dirty = true;
    }

    /**
     * @jmx.managed-attribute
     */
//    public ObjectName getDeployedTreeCacheObjectName()
//    {
//       return deployedTreeCacheObjectName;
//    }

    /**
     * @jmx.managed-attribute
     */
//    public void setDeployedTreeCacheObjectName(ObjectName deployedTreeCacheObjectName)
//    {
//       this.deployedTreeCacheObjectName = deployedTreeCacheObjectName;
//    }

    /**
     * @jmx.managed-attribute
     */
    public boolean isDirty()
    {
       return dirty;
    }

    /**
     * @jmx.managed-attribute
     */
    public boolean isSessionFactoryRunning()
    {
       return sessionFactory != null;
    }

    /**
     * @jmx.managed-operation
     */
    public void rebuildSessionFactory() throws Exception
    {
       destroySessionFactory();
       buildSessionFactory();
    }

    /**
     * Configure Hibernate and bind the <tt>SessionFactory</tt> to JNDI.
     */    
    //This code replaces 
    public void createJndiSessionObject(String appName, String bindingName) throws Exception { 	
    	applicationName = appName;
    	sessionFactoryName = bindingName;
    	log.debug( "Hibernate MBean starting; " + this );
    	buildSessionFactory();
    }
    
    public void startService() throws Exception
    {
       log.debug( "Hibernate MBean starting; " + this );
       buildSessionFactory();
    }

    /**
     * Close the <tt>SessionFactory</tt>.
     */
    public void stopService() throws Exception
    {
       destroySessionFactory();
    }

    private void buildSessionFactory() throws Exception {
       log.debug( "Building SessionFactory; " + this );
       
//       sessionFactory = HibernateSessionFactory.getSessionFactory(this.applicationName, this.getProperties());
       sessionFactory = HibernateSessionFactory.getSessionFactory(this.applicationName, this.sessionFactoryNameExtension, this.getProperties());

       try {
          bind();
       }
       catch( HibernateException e ) {
          forceCleanup();
          throw e;
       }

       dirty = false;

       /*sendNotification(
          new Notification(SESSION_FACTORY_CREATE, getServiceName(), getNextNotificationSequenceNumber())
       ); */

       log.info("SessionFactory successfully built and bound into JNDI [" + sessionFactoryName + "]");
    }

    private void destroySessionFactory() throws Exception
    {
       if (sessionFactory != null)
       {
          // TODO : need to determine the exact situations where we need to clear the 2nd-lvl cache
          //    to allow clean release of the classloaders.  Most likely, if structured entries are
          //    used; anything else?
          unbind();
          sessionFactory.close();
          sessionFactory = null;

          /*sendNotification(
             new Notification(SESSION_FACTORY_DESTROY, getServiceName(), getNextNotificationSequenceNumber())
          ); */
       }
    }

    private Properties getProperties()
    {
       Properties props = new Properties();

       setUnlessNull(props, Environment.DATASOURCE, datasourceName);
       setUnlessNull(props, Environment.DIALECT, dialect);
       // TODO : needed until we decide what to do about "no cache provider specified" internally within Hibernate
       if ( cacheProviderClass == null )
       {
          // Hibernate defaults to always instantiating an EHCacheProvider, which
          // introduces a huge potential for an unecessary dependency
          cacheProviderClass = "org.hibernate.cache.HashtableCacheProvider";
       }
       //>>setUnlessNull(props, Environment.CACHE_PROVIDER, cacheProviderClass);
       setUnlessNull(props, Environment.CACHE_REGION_PREFIX, cacheRegionPrefix);
//       setUnlessNull(props, Environment.USE_MINIMAL_PUTS, minimalPutsEnabled);
       setUnlessNull(props, Environment.HBM2DDL_AUTO, hbm2ddlAuto);
       setUnlessNull(props, Environment.DEFAULT_SCHEMA, defaultSchema);
//       setUnlessNull(props, Environment.STATEMENT_BATCH_SIZE, jdbcBatchSize);

//       log.info( "Using JDBC batch size : " + jdbcBatchSize );

//       setUnlessNull(props, Environment.STATEMENT_FETCH_SIZE, jdbcFetchSize);
//       setUnlessNull(props, Environment.USE_SCROLLABLE_RESULTSET, jdbcScrollableResultSetEnabled);
//       setUnlessNull(props, Environment.USE_QUERY_CACHE, queryCacheEnabled);
       setUnlessNull(props, Environment.QUERY_SUBSTITUTIONS, querySubstitutions);
//       setUnlessNull(props, Environment.MAX_FETCH_DEPTH, maxFetchDepth);
       setUnlessNull(props, Environment.SHOW_SQL, showSqlEnabled);
//       setUnlessNull(props, Environment.USE_GET_GENERATED_KEYS, getGeneratedKeysEnabled);
       setUnlessNull(props, Environment.USER, username);
       setUnlessNull(props, Environment.PASS, password);
//       setUnlessNull(props, Environment.BATCH_VERSIONED_DATA, batchVersionedDataEnabled);
//       setUnlessNull(props, Environment.USE_STREAMS_FOR_BINARY, streamsForBinaryEnabled);
//       setUnlessNull(props, Environment.USE_REFLECTION_OPTIMIZER, reflectionOptimizationEnabled);

       //>>setUnlessNull(props, Environment.TRANSACTION_MANAGER_STRATEGY, JBossTransactionManagerLookup.class.getName());
       //>>setUnlessNull(props, Environment.TRANSACTION_STRATEGY, JTATransactionFactory.class.getName());

//       if ( deployedTreeCacheObjectName != null )
//       {
//          String objNameString = deployedTreeCacheObjectName.toString();
//          if ( objNameString != null && !"".equals( objNameString ) )
//          {
//             props.setProperty( DeployedTreeCacheProvider.OBJECT_NAME_PROP, objNameString );
//          }
//       }

       props.setProperty( Environment.FLUSH_BEFORE_COMPLETION, "true" );
       props.setProperty( Environment.AUTO_CLOSE_SESSION, "true" );
//       props.setProperty( Environment.RELEASE_CONNECTIONS, "false" );
       props.setProperty( Environment.RELEASE_CONNECTIONS, ConnectionReleaseMode.ON_CLOSE.toString() );

       return props;
    }

    private void setUnlessNull(Properties props, String key, String value)
    {
       if(value != null)
       {
          props.setProperty(key, value);
       }
    }   

    private void bind() throws HibernateException {
       InitialContext ctx = null;
       try
       {
          ctx = new InitialContext();
          
          //--
          try {
              Object obj = ctx.lookup(sessionFactoryName);
              log.info("found " + sessionFactoryName + " : " + obj);
          } catch (NamingException ne) {
        	  ctx.rebind( sessionFactoryName, sessionFactory );
        	  log.info("not found: " + sessionFactoryName + ", rebind: " + sessionFactory);
          }

       }
       catch( NamingException e )
       {
          throw new HibernateException( "Unable to bind SessionFactory into JNDI", e );
       }
       finally
       {
          if ( ctx != null )
          {
             try
             {
                ctx.close();
             }
             catch( Throwable ignore )
             {
                // ignore
             }
          }
       }
    }

    private void unbind() throws HibernateException {
       InitialContext ctx = null;
       try
       {
          ctx = new InitialContext();
          ctx.unbind(sessionFactoryName );
       }
       catch( NamingException e )
       {
          throw new HibernateException( "Unable to unbind SessionFactory from JNDI", e );
       }
       finally {
          if ( ctx != null )
          {
             try
             {
                ctx.close();
             }
             catch( Throwable ignore )
             {
                // ignore
             }
          }
       }
    }

    private void forceCleanup()
    {
       try
       {
          sessionFactory.close();
          sessionFactory = null;
       }
       catch( Throwable ignore )
       {
          // ignore
       }
    }

    public String toString()
    {
       return super.toString() + " [ServiceName=" + this.applicationName + ", JNDI=" + sessionFactoryName + "]";
    }
}