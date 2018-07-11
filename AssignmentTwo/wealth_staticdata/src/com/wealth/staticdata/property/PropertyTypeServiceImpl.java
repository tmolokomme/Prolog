package com.wealth.staticdata.property;

import javax.ejb.Stateless;
import javax.jws.WebService;

import org.hibernate.Session;

import com.wealth.client.ServerException;
import com.wealth.persistence.factory.HibernateContext;
import com.wealth.staticdata.StaticDataConstants;
import com.wealth.staticdata.client.interfaces.ejb.PropertyTypeLocal;
import com.wealth.staticdata.client.interfaces.ejb.PropertyTypeRemote;
import com.wealth.staticdata.client.transferobjects.PropertyTypeTO;

@Stateless
@WebService
public class PropertyTypeServiceImpl implements PropertyTypeLocal, PropertyTypeRemote {
    
	private PropertyTypeTransactions typesTxs = new PropertyTypeTransactions();
		
	public PropertyTypeTO[] getPropertyTypes() throws ServerException {
	    final Session session = HibernateContext.getSession(StaticDataConstants.SESSION_FACTORY_NAME);
		return typesTxs.getPropertyTypes(session);
	}

	public PropertyTypeTO[] getActivePropertyTypes() throws ServerException {
	    final Session session = HibernateContext.getSession(StaticDataConstants.SESSION_FACTORY_NAME);
		return typesTxs.getActivePropertyTypes(session);
	}

	public void createPropertyType(PropertyTypeTO propertyTypeTO) throws ServerException {
	    final Session session = HibernateContext.getSession(StaticDataConstants.SESSION_FACTORY_NAME);
		typesTxs.createPropertyType(session, propertyTypeTO);
	}

	public PropertyTypeTO getPropertyTypeById(int id) throws ServerException {
	    final Session session = HibernateContext.getSession(StaticDataConstants.SESSION_FACTORY_NAME);
		return typesTxs.getPropertyTypeById(session, new Integer(id));
	}
}