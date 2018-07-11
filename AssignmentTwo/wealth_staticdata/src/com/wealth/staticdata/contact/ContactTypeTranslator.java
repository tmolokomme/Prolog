package com.wealth.staticdata.contact;

import com.wealth.staticdata.client.transferobjects.ContactTypeTO;
import com.wealth.staticdata.domain.ContactType;

public class ContactTypeTranslator {
    
	public static ContactTypeTO copyContactTypeTOFromContactType(ContactType p) {
		ContactTypeTO to = new ContactTypeTO();
		to.setActive(p.isActive());
		to.setTypes(p.getTypes());
		to.setId(p.getId());
		return to;
	}
	public static ContactType copyContactTypeFromContactTypeTO(ContactTypeTO p) {
		ContactType to = new ContactType();
		to.setActive(p.isActive());
		to.setTypes(p.getTypes());
		to.setId(p.getId());
		return to;
	}
}
