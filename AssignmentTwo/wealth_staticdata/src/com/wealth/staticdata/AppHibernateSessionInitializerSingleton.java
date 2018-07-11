package com.wealth.staticdata;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Local;
import javax.ejb.Singleton;
import javax.ejb.Startup;

//import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.wealth.persistence.jmx.Hibernate;
import com.wealth.staticdata.client.StaticDataServiceLocator;
import com.wealth.staticdata.client.interfaces.ejb.ApplicationHibSessionInitLocal;

@Local
//@LocalBean(jndiBinding = "SingletonBean")
@Startup
@Singleton
//@Stateless
public class AppHibernateSessionInitializerSingleton implements ApplicationHibSessionInitLocal {
	
	private static Log log = LogFactory.getLog(AppHibernateSessionInitializerSingleton.class);
	
	private final Hibernate hibernateObject = new Hibernate();;
	@PostConstruct
	private void startup() throws Exception {
		 InitialContext context = null;
	      try
	      {    
	    	 log.info("startup().check exist-jndi:: " + StaticDataConstants.SESSION_FACTORY_NAME);
	         context = new InitialContext();         
	         context.lookup(StaticDataConstants.SESSION_FACTORY_NAME);
	      }
	      catch(NamingException e)
	      {
	    	  log.info("create-jndi, startup()::NamingException=["+StaticDataConstants.SESSION_FACTORY_NAME+"]");
	    	  hibernateObject.createJndiSessionObject(StaticDataServiceLocator.APPLICATION_NAME, StaticDataConstants.SESSION_FACTORY_NAME);
	    	  
	      }
	      finally
	      {
	    	  if (context != null) {
	    		  context.close();
	    	  }
	      }		
	}

	@PreDestroy
	private void cleanUp() throws Exception {
		log.info("remove-jndi, cleanUp()::NamingException=["+ StaticDataConstants.SESSION_FACTORY_NAME +"]");
		if (hibernateObject != null) {
			hibernateObject.stopService();
		}
		
	} 
}
