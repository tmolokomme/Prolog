package com.wealth.persistence.factory;

//import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

import com.wealth.testing.hibernate.HibernateConfig;

import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Maintains and exposes, for app usage, the current context bound Hibernate Session.
 * Application code need only deal with the {@link #getSession(java.lang.String)}
 * as the means to retreive the {@link org.hibernate.Session} associated with
 * the current context.
 */
public class HibernateContext {
	
	private static final Logger logger = Logger.getLogger(HibernateContext.class);	
	
	private static List<HibernateConfig> OPEN_TEST_CONFIGS = null;
	
   /**
    * Retreives an "unmanaged" session against the same underlying jdbc connnection as the session
    * currently bound to the current context for the given JNDI name.  This is simply a convenience
    * method for SessionFactory.openSession({@link #getSession}.connection()).  Unmanaged here means that
    * the returned session is not controlled by the code managing the actually bound session; callers
    * are required to cleanup these sessions manually using {@link #releaseUnmanagedSession}.
    *
    * @param name The "name" of the {@link org.hibernate.SessionFactory}
    *       for which an unmanaged session is requested.
    * @return An unmanaged session.
    * @throws HibernateException If an error occurs opening the new Session.
    * @throws IllegalStateException If unable to locate a managed Session for the current context. 
    */
   public static Session getUnmanagedSession(String name) throws HibernateException, IllegalStateException
   {
      SessionFactory sf = locateSessionFactory( name );
      return sf.openSession(); //(sf.getCurrentSession().connection());
   }

   /**
    * Method to release a previously obtained unmanaged session.
    *
    * @param unmanagedSession The unmanaged Session to release.
    * @throws HibernateException If an error occurs releasing the unmanaged Session.
    */
   public static void releaseUnmanagedSession(Session unmanagedSession) throws HibernateException
   {
      unmanagedSession.close();
   }
   
   private static boolean containsOpenTestSession(String name) {
	   if (HibernateContext.OPEN_TEST_CONFIGS != null) {
		   for (int i=0; i<HibernateContext.OPEN_TEST_CONFIGS.size(); i++) {
 			  HibernateConfig conf = HibernateContext.OPEN_TEST_CONFIGS.get(i);
 			  if (name.equals(conf.getHibSessionFactoryJNDIName())) {
 				  return true;
 			  }
		   }
	   }
	   return false;
   }

   /**
    * Retreives the session currently bound to the current context.
    *
    * @param name The "name" of the {@link org.hibernate.SessionFactory}
    *       for which a session is requested.
    * @return The current session.
    * 
    * @see org.hibernate.SessionFactory#getCurrentSession()
    */
   public static Session getSession(String name) {
      Session session = null;
      try {
          session = locateSessionFactory(name).getCurrentSession();
          HibernateContext.OPEN_TEST_CONFIGS = null;
      } catch (HibernateException he) {
    	  logger.error("HibernateContext::getSession()::HibernateException=["+he.getMessage()+"]");
    	  if ( (HibernateContext.OPEN_TEST_CONFIGS == null) ||
    		   (!HibernateContext.containsOpenTestSession(name)) ) {
    		  System.err.println("Getting HIBERNATE Stateful Session straight from the HibernateSessionFactory["+name+"]");
    		  session = locateSessionFactory(name).openSession();
    		  HibernateContext.OPEN_TEST_CONFIGS = new ArrayList<HibernateConfig>(1);
    		  HibernateConfig newConfig = new HibernateConfig();
    		  newConfig.setHibSessionFactoryJNDIName(name);
    		  newConfig.setSession(session);
    		  OPEN_TEST_CONFIGS.add(newConfig);
    	  } else {
    		  for (int i=0; i<HibernateContext.OPEN_TEST_CONFIGS.size(); i++) {
    			  HibernateConfig conf = HibernateContext.OPEN_TEST_CONFIGS.get(i);
    			  if (name.equals(conf.getHibSessionFactoryJNDIName())) {
    				  session = conf.getSession();
    				  logger.error("Returning (existing) OPEN SESSION for SessionFactory ["+ name +"], with Session=["+ session +"]");
    				  break;
    			  }
    		  }    		  
    	  }
      }
      return session;
   }

   private static SessionFactory locateSessionFactory(String name) throws HibernateException
   {
      InitialContext context = null;
      try
      {
    	  
         context = new InitialContext();         
         final SessionFactory factory = (SessionFactory) context.lookup(name);
         return factory;
      }
      catch(NamingException e)
      {
    	  logger.error("locateSessionFactory()::NamingException=["+e.getMessage()+"]");
    	  throw new HibernateException("Unable to locate SessionFactory in JNDI under name [" + name + "]", e);
    	  /* try {
    		  int pos = name.indexOf("/");
    		  String applicationame = name.substring(0, pos); 
    		  new Hibernate().createJndiSessionObject(applicationame, name);
    		  final SessionFactory factory = (SessionFactory) context.lookup(name);
    	      return factory;
    	  } catch(Exception ex) {
    		  */
    		//System.err.println("locateSessionFactory()::NamingException=["+e.getMessage()+"]");
        	  //throw new HibernateException("Unable to locate SessionFactory in JNDI under name [" + name + "]", e);
    	  //}
    	  
      }
      finally
      {
         release(context);
      }
   }

   private static void release(InitialContext ctx)
   {
      if (ctx != null)
      {
         try
         {
            ctx.close();
         }
         catch(Throwable ignore)
         {
            // ignore
         }
      }
   }
   
   public static void nullifyOpenSession() {
	   if (HibernateContext.OPEN_TEST_CONFIGS != null) {
		   for (int i=0; i<HibernateContext.OPEN_TEST_CONFIGS.size(); i++) {
 			  HibernateConfig conf = HibernateContext.OPEN_TEST_CONFIGS.get(i);
 			  Session theSessionToNullify = conf.getSession();
 			  theSessionToNullify = null;
		   }
		   HibernateContext.OPEN_TEST_CONFIGS.clear();
	   }
	   HibernateContext.OPEN_TEST_CONFIGS = null;
   }
}