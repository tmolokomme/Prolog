package com.wealth.staticdata.domain;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.wealth.domain.BaseDomainEntity;
import com.wealth.staticdata.client.enums.ContactTypeEnum;

@Entity
@Embeddable
public class ContactType extends BaseDomainEntity {

	private static final long serialVersionUID = 1L;

	private boolean active;
	@Enumerated(EnumType.STRING)
	private ContactTypeEnum types;

	public ContactType() {
	}

	public ContactType(boolean active, ContactTypeEnum types) {
		super();
		this.active = active;
		this.types = types;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public ContactTypeEnum getTypes() {
		return types;
	}

	public void setTypes(ContactTypeEnum types) {
		this.types = types;
	}

}
