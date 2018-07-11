package com.wealth.staticdata.contact;

import javax.ejb.Stateless;
import javax.jws.WebService;

import org.hibernate.Session;

import com.wealth.client.ServerException;
import com.wealth.persistence.factory.HibernateContext;
import com.wealth.staticdata.StaticDataConstants;
import com.wealth.staticdata.client.interfaces.ejb.ContactTypeLocal;
import com.wealth.staticdata.client.interfaces.ejb.ContactTypeRemote;
import com.wealth.staticdata.client.transferobjects.ContactTypeTO;

@Stateless
@WebService
public class ContactTypeServiceImpl implements ContactTypeLocal, ContactTypeRemote {
    
	private ContactTypeTransactions typesTxs = new ContactTypeTransactions();
		
	public ContactTypeTO[] getContactTypes() throws ServerException {
	    final Session session = HibernateContext.getSession(StaticDataConstants.SESSION_FACTORY_NAME);
		return typesTxs.getContactType(session);
	}

	public ContactTypeTO[] getActiveContactTypes() throws ServerException {
	    final Session session = HibernateContext.getSession(StaticDataConstants.SESSION_FACTORY_NAME);
		return typesTxs.getActiveContactType(session);
	}

	public void createContactType(ContactTypeTO contactTypeTO) throws ServerException {
	    final Session session = HibernateContext.getSession(StaticDataConstants.SESSION_FACTORY_NAME);
		typesTxs.createContactType(session, contactTypeTO);
	}

	public ContactTypeTO getContactTypeById(int id) throws ServerException {
	    final Session session = HibernateContext.getSession(StaticDataConstants.SESSION_FACTORY_NAME);
		return typesTxs.getContactTypeById(session, new Integer(id));
	}
}