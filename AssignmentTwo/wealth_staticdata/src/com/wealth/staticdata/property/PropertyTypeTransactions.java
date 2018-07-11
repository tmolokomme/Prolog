package com.wealth.staticdata.property;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.wealth.client.ServerException;
import com.wealth.staticdata.client.transferobjects.PropertyTypeTO;
import com.wealth.staticdata.domain.PropertyType;

public class PropertyTypeTransactions {

	private PropertyTypeTO[] convertToTOArray(List<PropertyType> types) {
		PropertyTypeTO[] typesTO = new PropertyTypeTO[types.size()];
		int i = 0;
		for (PropertyType cl : types) {
			typesTO[i] = PropertyTypeTranslator.copyPropertyTypeTOFromPropertyType(cl);
			i++;
		}
		return typesTO;
	}

	public PropertyTypeTO getPropertyTypeById(Session session, Integer id) throws ServerException {
		try {
			PropertyType c = (PropertyType) session.get(PropertyType.class, id);
			if (c == null)
				return null;

			PropertyTypeTO propertyTypeTO = PropertyTypeTranslator.copyPropertyTypeTOFromPropertyType(c);
			return propertyTypeTO;
		} catch (HibernateException e) {
			throw new ServerException(e);
		}
	}

	public PropertyTypeTO[] getPropertyTypes(Session session) throws ServerException {
		try {
			Query query = session.createQuery("from PropertyType order by types asc");
			List<PropertyType> types = query.list();
			PropertyTypeTO[] typesTO = convertToTOArray(types);
			return typesTO;
		} catch (HibernateException e) {
			throw new ServerException(e);
		}
	}

	public PropertyTypeTO[] getActivePropertyTypes(Session session) throws ServerException {
		try {
			Query query = session.createQuery("from PropertyType propertyType where active = 1 order by propertyType asc");
			List<PropertyType> types = query.list();
			PropertyTypeTO[] typesTO = convertToTOArray(types);
			return typesTO;
		} catch (HibernateException e) {
			throw new ServerException(e);
		}
	}

	public void createPropertyType(Session session, final PropertyTypeTO propertyTypeTO) throws ServerException {
		if (propertyTypeTO == null)
			throw new ServerException("PropertyType cannot be null");

		final PropertyType propertyType = PropertyTypeTranslator.copyPropertyTypeFromPropertyTypeTO(propertyTypeTO);
		if (propertyType == null)
			throw new ServerException("Server propertyType null unexpectedly");

		try {
			session.save(propertyType);
			propertyTypeTO.setId(propertyType.getId());
		} catch (HibernateException e) {
			throw new ServerException(e);
		}
	}

}
