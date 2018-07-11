package com.wealth.staticdata.producthouse;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.wealth.client.ServerException;
import com.wealth.staticdata.producthouse.ProductHouseTranslator;
import com.wealth.staticdata.client.transferobjects.ProductHouseTO;

import com.wealth.staticdata.domain.ProductHouse;

public class ProductHouseTransactions {
	
	
	private ProductHouseTO[] convertToTOArray(List<ProductHouse> types) {
		ProductHouseTO[] typesTO = new ProductHouseTO[types.size()];
		int i = 0;
		for (ProductHouse cl : types) {
			typesTO[i] = ProductHouseTranslator.copyProductHouseTOFromProductHouse(cl);
			i++;
		}
		return typesTO;
	}

	
	
	public ProductHouseTO[] getProductHouseDetails(Session session) throws ServerException {		
		try {			
			Query query = session.createQuery("from ProductHouse where active = 1 order by description asc");
			List<ProductHouse> types = query.list();
			ProductHouseTO[] typesTO = convertToTOArray(types);
			return typesTO;
		} catch (HibernateException e) {
			throw new ServerException(e);
		}
	}
	
	
	public ProductHouseTO getProductHouseDetailsById(Session session, Integer id) throws ServerException {
		try {
			ProductHouse c = (	ProductHouse) session.get(	ProductHouse.class, id);
			if (c == null)
				return null;

			ProductHouseTO productHouseTO = ProductHouseTranslator.copyProductHouseTOFromProductHouse(c);
			return productHouseTO;
		} catch (HibernateException e) {
			throw new ServerException(e);
		}
	}

}
