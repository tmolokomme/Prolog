package com.wealth.staticdata.account;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.wealth.client.ServerException;
import com.wealth.staticdata.client.transferobjects.AccountTypeTO;
import com.wealth.staticdata.domain.AccountType;

public class AccountTypeTransactions {

	private AccountTypeTO[] convertToTOArray(List<AccountType> types) {
		AccountTypeTO[] typesTO = new AccountTypeTO[types.size()];
		int i = 0;
		for (AccountType cl : types) {
			typesTO[i] = AccountTypeTranslator.copyAccountTypesTOFromAccountTypes(cl);
			i++;
		}
		return typesTO;
	}

	public AccountTypeTO getAccountTypesById(Session session, Integer id) throws ServerException {		
		try {			
			AccountType c = (AccountType) session.get(AccountType.class, id);
			if (c == null)
				return null;

			AccountTypeTO accountTypesTO = AccountTypeTranslator.copyAccountTypesTOFromAccountTypes(c);
			return accountTypesTO;
		} catch (HibernateException e) {
			throw new ServerException(e);
		}
	}

	public AccountTypeTO[] getAccountTypes(Session session) throws ServerException {		
		try {			
			Query query = session.createQuery("from AccountType order by types asc");
			List<AccountType> types = query.list();
			AccountTypeTO[] typesTO = convertToTOArray(types);
			return typesTO;
		} catch (HibernateException e) {
			throw new ServerException(e);
		}
	}

	public AccountTypeTO[] getActiveAccountTypes(Session session) throws ServerException {
		try {
			Query query = session.createQuery("from AccountType accountType where active = 1 order by accountType asc");
			List<AccountType> types = query.list();
			AccountTypeTO[] typesTO = convertToTOArray(types);
			return typesTO;
		} catch (HibernateException e) {
			throw new ServerException(e);
		}
	}

	public void createAccountTypes(Session session, final AccountTypeTO accountTypesTO) throws ServerException {
		if (accountTypesTO == null)
			throw new ServerException("AccountType cannot be null");

		final AccountType accountTypes = AccountTypeTranslator.copyAccountTypesFromAccountTypesTO(accountTypesTO);
		if (accountTypes == null)
			throw new ServerException("Server accountType null unexpectedly");

		try {
		    session.save(accountTypes);
            accountTypesTO.setId(accountTypes.getId());
		} catch (HibernateException e) {
			throw new ServerException(e);
		}
	}

}
