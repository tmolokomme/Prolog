package com.wealth.staticdata.producthouse;
import javax.ejb.Stateless;
import javax.jws.WebService;

import org.hibernate.Session;

import com.wealth.client.ServerException;
import com.wealth.persistence.factory.HibernateContext;
import com.wealth.staticdata.StaticDataConstants;
import com.wealth.staticdata.client.interfaces.ejb.ProductHouseLocal;
import com.wealth.staticdata.client.interfaces.ejb.ProductHouseRemote;
import com.wealth.staticdata.client.transferobjects.ContactTypeTO;
import com.wealth.staticdata.client.transferobjects.ProductHouseTO;

@Stateless
@WebService

public class ProductHouseServiceImpl implements ProductHouseLocal,ProductHouseRemote {
	
	private ProductHouseTransactions typesTxs = new ProductHouseTransactions();
	
	
	public ProductHouseTO[] getProductHouseDetails() throws ServerException {
	    final Session session = HibernateContext.getSession(StaticDataConstants.SESSION_FACTORY_NAME);
		return typesTxs.getProductHouseDetails(session);
	}
	
	public ProductHouseTO getProductHouseDetailsById(int id) throws ServerException {
	    final Session session = HibernateContext.getSession(StaticDataConstants.SESSION_FACTORY_NAME);
		return typesTxs.getProductHouseDetailsById(session, new Integer(id));
	}

}
