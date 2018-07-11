package com.wealth.staticdata.domain;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.wealth.domain.BaseDomainEntity;
import com.wealth.staticdata.client.enums.AccountTypesEnum;

@Entity
@Embeddable
public class AccountType extends BaseDomainEntity {

	private static final long serialVersionUID = 1L;

	private boolean active;
	@Enumerated(EnumType.STRING)
	private AccountTypesEnum types;

	public AccountType() {
	}

	public AccountType(boolean active, AccountTypesEnum types) {
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

	public AccountTypesEnum getTypes() {
		return types;
	}

	public void setTypes(AccountTypesEnum types) {
		this.types = types;
	}

}
