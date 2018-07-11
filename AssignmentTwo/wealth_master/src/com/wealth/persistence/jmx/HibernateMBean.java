package com.wealth.persistence.jmx;

//import org.jboss.system.ServiceMBean;
//import org.jboss.deployment.DeploymentInfo;

import javax.management.JMException;
import javax.management.MBeanRegistration;

/**
 * Describes a Hibernate service MBean. Configures a
 * {@link org.hibernate.SessionFactory} instance and exposes it through JNDI.
 * The SessionFactory is built through<ul>
 * <li>annotation-configuration : where the configuration is loaded from the
 * WEALTH_CONFIG_DIR system variable by application name. * 
 * </ul>
 *
 * @version <tt>$Revision: 1.2 $</tt>
 * @author <a HREF="mailto:alex@jboss.org">Alexey Loubyansky</a>
 * @author <a HREF="mailto:gavin@hibernate.org">Gavin King</a>
 * @author <a HREF="mailto:steve@hibernate.org">Steve Ebersole</a>
 * @author <a HREF="mailto:dimitris@jboss.org">Dimitris Andreadis</a>
 */
public interface HibernateMBean { //extends ServiceMBean, MBeanRegistration {

    /**
     * Retrieve MBean's current deployment info.
     *
     * @return the deployment info.
     */
    //public DeploymentInfo getDeploymentInfo() throws JMException;

    /**
     * The Application name for which the AnnotationConfiguration should be loaded.
     *
     * @return The current setting value.
     */
    public String getApplicationName();

    /**
     * The Application name for which the AnnotationConfiguration should be loaded.
     *
     * @param applicationName The new Application name to use.
     */
    public void setApplicationName(String applicationName);
    
    /**
     * The SessionFactory Name EXTENSION to support multiple AnnotationConfigurations for a single application.
     *
     * @return The current setting value.
     */
    public String getSessionFactoryNameExtension();

    /**
     * The SessionFactory Name EXTENSION to support multiple AnnotationConfigurations for a single application.
     *
     * @param applicationName The new Application name to use.
     */
    public void setSessionFactoryNameExtension(String sessionFactoryNameExtension);

    /**
     * The JNDI namespace where the managed {@link org.hibernate.SessionFactory} is to be bound.
     *
     * @return The current setting value.
     */
    public String getSessionFactoryName();

    /**
     * The JNDI namespace where the managed {@link org.hibernate.SessionFactory} is to be bound.
     *
     * @param sessionFactoryName The new JNDI namespace to use.
     */
    public void setSessionFactoryName(String sessionFactoryName);

    /**
     * The JMX name of a {@link org.jboss.cache.TreeCache} MBean to be used as the second level cache.
     * <p/>
     * Note : only used when {@link #getCacheProviderClass} == {@link org.jboss.hibernate.cache.DeployedTreeCacheProvider}
     *
     * @return The current setting
     */
//    public ObjectName getDeployedTreeCacheObjectName();

    /**
     * The JMX name of a {@link org.jboss.cache.TreeCache} MBean to be used as the second level cache.
     *
     * @param deployedTreeCacheObjectName The new mbean object name.
     */
//    public void setDeployedTreeCacheObjectName(ObjectName deployedTreeCacheObjectName);

    /**
     * Retreive the service name of the managed stats mbean.
     * <p/>
     * When statistics are enabled on the managed session factory, the mbean
     * automatically manages a stats mbean for stats exposure via jmx. This
     * returns the name under which that stats mbean is available from the jmx
     * server.
     *
     * @return The service name of the stats mbean, or null if stats not enabled.
     */
//    public ObjectName getStatisticsServiceName();

    /**
     * The name of the dialect class to use for communicating with the database.
     *
     * @return The current setting value.
     *
     * @see org.hibernate.cfg.Environment#DIALECT
     */
    public String getDialect();

    /**
     * The name of the dialect class to use for communicating with the database.
     *
     * @param dialect The new dialect class name to use.
     */
    public void setDialect(String dialect);

    /**
     * The JNDI namespace of the {@link javax.sql.DataSource} which should be used by the managed {@link
     * org.hibernate.SessionFactory}.
     *
     * @return The current setting value.
     *
     * @see org.hibernate.cfg.Environment#DATASOURCE
     */
    public String getDatasourceName();

    /**
     * The JNDI namespace of the {@link javax.sql.DataSource} which should be used by the managed {@link
     * org.hibernate.SessionFactory}.
     *
     * @param datasourceName The new DataSource JNDI name to use.
     */
    public void setDatasourceName(String datasourceName);

    /**
     * The username used to access the specified datasource.
     *
     * @return The current setting value.
     *
     * @see org.hibernate.cfg.Environment#USER
     */
    public String getUsername();

    /**
     * The username used to access the specified datasource.
     *
     * @param username The new username value.
     */
    public void setUsername(String username);

    /**
     * The password used to access the specified datasource.
     *
     * @param password The new password value.
     */
    public void setPassword(String password);

    /**
     * Should sql comments be used?
     *
     * @return
     *
     * @see org.hibernate.cfg.Environment#USE_SQL_COMMENTS
     */
//    public Boolean getSqlCommentsEnabled();

    /**
     * Should sql comments be used?
     *
     * @param commentsEnabled
     */
//    public void setSqlCommentsEnabled(Boolean commentsEnabled);

    /**
     * The default database schema to use within the database being mapped.
     * <p/>
     * Used for databases which support the concept of schemas instead of catalogs.
     *
     * @return The current setting value.
     *
     * @see #getDefaultCatalog
     * @see org.hibernate.cfg.Environment#DEFAULT_SCHEMA
     */
    public String getDefaultSchema();

    /**
     * The default database schema to use within the database being mapped.
     *
     * @param defaultSchema The new default schema name to use.
     */
    public void setDefaultSchema(String defaultSchema);

    /**
     * The default database catalog to use within the database being mapped.
     * <p/>
     * Used for databases which support the concept of catalogs instead of schemas.
     *
     * @return The current setting value.
     *
     * @see #getDefaultSchema
     * @see org.hibernate.cfg.Environment#DEFAULT_CATALOG
     */
    public String getDefaultCatalog();

    /**
     * The default database catalog to use within the database being mapped.
     *
     * @param defaultCatalog The new default catalog name.
     */
    public void setDefaultCatalog(String defaultCatalog); 

    /**
     * Query substitutions to use.
     *
     * @return The current setting value
     *
     * @see org.hibernate.cfg.Environment#QUERY_SUBSTITUTIONS
     */
    public String getQuerySubstitutions();

    /**
     * Query substitutions to use.
     *
     * @param querySubstitutions The new query substitutions to use
     */
    public void setQuerySubstitutions(String querySubstitutions);

    /**
     * The name of the {@link org.hibernate.cache.CacheProvider} implementation class to use for second level caching.
     *
     * @return The current setting value
     *
     * @see org.hibernate.cfg.Environment#CACHE_PROVIDER
     */
    public String getCacheProviderClass();

    /**
     * The name of the {@link org.hibernate.cache.CacheProvider} implementation class to use for second level caching.
     *
     * @param cacheProviderClass The new provider impl class name.
     */
    public void setCacheProviderClass(String cacheProviderClass);

    /**
     * The prefix to use for this session factory within the second level cache.
     *
     * @return The current setting value
     *
     * @see org.hibernate.cfg.Environment#CACHE_NAMESPACE
     */
    public String getCacheRegionPrefix();

    /**
     * The prefix to use for this session factory within the second level cache.
     *
     * @param cacheRegionPrefix The new prefix value.
     */
    public void setCacheRegionPrefix(String cacheRegionPrefix);

    /**
     * Should Hibernate use structured cache entries when putting stuff into the second level cache?
     * <p/>
     * Mainly useful if users wish to directly browse the second level caches as it is easier to see what the cache entries
     * actually represent.
     *
     * @return
     *
     * @see org.hibernate.cfg.Environment#USE_STRUCTURED_CACHE
     */
//    public Boolean getUseStructuredCacheEntriesEnabled();

    /**
     * Should Hibernate use structured cache entries when putting stuff into the second level cache?
     *
     * @param structuredEntriesEnabled
     */
//    public void setUseStructuredCacheEntriesEnabled(Boolean structuredEntriesEnabled);

//    public Boolean getSecondLevelCacheEnabled();

//    public void setSecondLevelCacheEnabled(Boolean secondLevelCacheEnabled);

    /**
     * Should all SQL be shown (dumped to console and logged)?
     *
     * @return The current setting value
     *
     * @see org.hibernate.cfg.Environment#SHOW_SQL
     */
    public String getShowSqlEnabled();

    /**
     * Should all SQL be shown (dumped to console and logged)?
     *
     * @param showSqlEnabled
     */
    public void setShowSqlEnabled(String showSqlEnabled);

    /**
     * Should generation and collection of Hibernate3 statistics be enabled?
     *
     * @return
     *
     * @see org.hibernate.cfg.Environment#GENERATE_STATISTICS
     */
//    public Boolean getStatGenerationEnabled();

    /**
     * Should generation and collection of Hibernate3 statistics be enabled?
     *
     * @param statGenerationEnabled
     */
//    public void setStatGenerationEnabled(Boolean statGenerationEnabled);

    /**
     * The {@link org.jboss.hibernate.ListenerInjector} implementor class to use.
     *
     * @return
     */
//    public String getListenerInjector();

    /**
     * The {@link org.jboss.hibernate.ListenerInjector} implementor class to use.
     *
     * @param listenerInjector
     */
//    public void setListenerInjector(String listenerInjector);

    /**
     * Is this MBean dirty? Meaning, have any changes been made to it that have not yet been propogated to the managed
     * {@link org.hibernate.SessionFactory}?
     * <p/>
     * Note : the only way to propogate these changes to the SF is by calling the {@link #rebuildSessionFactory()} managed
     * operation.
     *
     * @return
     */
    public boolean isDirty();

    /**
     * Does this MBean instance have a currently running managed {@link org.hibernate.SessionFactory}?
     *
     * @return
     */
    public boolean isSessionFactoryRunning();

    /**
     * The version Hibernate for the managed {@link org.hibernate.SessionFactory}.
     *
     * @return
     */
//    public String getVersion();

    /**
     * Exposes the internally managed session factory via a read-only JMX managed attribute.
     *
     * @return The managed session factory.
     */
//    public SessionFactory getInstance();

    /**
     * The date and time since which the currently managed {@link org.hibernate.SessionFactory} has been running.
     *
     * @return The date and time the current {@link org.hibernate.SessionFactory} was started.
     */
//    public Date getRunningSince();

    /**
     * A JMX managed operation to rebuild the managed {@link org.hibernate.SessionFactory} such that any setting changes
     * made can take effect.
     *
     * @throws Exception
     */
    public void rebuildSessionFactory() throws Exception;

}