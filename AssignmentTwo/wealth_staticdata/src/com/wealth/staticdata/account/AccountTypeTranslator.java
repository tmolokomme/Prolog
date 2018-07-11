package com.wealth.staticdata.account;

import com.wealth.staticdata.client.transferobjects.AccountTypeTO;
import com.wealth.staticdata.domain.AccountType;

public class AccountTypeTranslator {
	public static AccountTypeTO copyAccountTypesTOFromAccountTypes(AccountType p) {
		AccountTypeTO to = new AccountTypeTO();
		to.setActive(p.isActive());
		to.setTypes(p.getTypes());
		to.setId(p.getId());
		return to;
	}
	public static AccountType copyAccountTypesFromAccountTypesTO(AccountTypeTO p) {
		AccountType to = new AccountType();
		to.setActive(p.isActive());
		to.setTypes(p.getTypes());
		to.setId(p.getId());
		return to;
	}
}
