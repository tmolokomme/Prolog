package com.wealth.staticdata.contact;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.wealth.client.ServerException;
import com.wealth.staticdata.client.transferobjects.ContactTypeTO;
import com.wealth.staticdata.domain.ContactType;

public class ContactTypeTransactions {

	private ContactTypeTO[] convertToTOArray(List<ContactType> types) {
		ContactTypeTO[] typesTO = new ContactTypeTO[types.size()];
		int i = 0;
		for (ContactType cl : types) {
			typesTO[i] = ContactTypeTranslator.copyContactTypeTOFromContactType(cl);
			i++;
		}
		return typesTO;
	}

	public ContactTypeTO getContactTypeById(Session session, Integer id) throws ServerException {
		try {
			ContactType c = (ContactType) session.get(ContactType.class, id);
			if (c == null)
				return null;

			ContactTypeTO contactTypeTO = ContactTypeTranslator.copyContactTypeTOFromContactType(c);
			return contactTypeTO;
		} catch (HibernateException e) {
			throw new ServerException(e);
		}
	}

	public ContactTypeTO[] getContactType(Session session) throws ServerException {
		try {
			Query query = session.createQuery("from ContactType order by types asc");
			List<ContactType> types = query.list();
			ContactTypeTO[] typesTO = convertToTOArray(types);
			return typesTO;
		} catch (HibernateException e) {
			throw new ServerException(e);
		}
	}

	public ContactTypeTO[] getActiveContactType(Session session) throws ServerException {
		try {
			Query query = session.createQuery("from ContactType contactType where active = 1 order by contactType asc");
			List<ContactType> types = query.list();
			ContactTypeTO[] typesTO = convertToTOArray(types);
			return typesTO;
		} catch (HibernateException e) {
			throw new ServerException(e);
		}
	}

	public void createContactType(Session session, final ContactTypeTO contactTypeTO) throws ServerException {
		if (contactTypeTO == null)
			throw new ServerException("ContactType cannot be null");

		final ContactType contactType = ContactTypeTranslator.copyContactTypeFromContactTypeTO(contactTypeTO);
		if (contactType == null)
			throw new ServerException("Server contactType null unexpectedly");

		try {
			session.save(contactType);
			contactTypeTO.setId(contactType.getId());
		} catch (HibernateException e) {
			throw new ServerException(e);
		}
	}

}
