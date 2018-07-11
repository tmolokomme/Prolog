package com.wealth.staticdata.account;

import javax.ejb.Stateless;
import javax.jws.WebService;

import org.hibernate.Session;

import com.wealth.client.ServerException;
import com.wealth.persistence.factory.HibernateContext;
import com.wealth.staticdata.StaticDataConstants;
import com.wealth.staticdata.client.interfaces.ejb.AccountTypeLocal;
import com.wealth.staticdata.client.interfaces.ejb.AccountTypeRemote;
import com.wealth.staticdata.client.transferobjects.AccountTypeTO;

@Stateless
@WebService
public class AccountTypeServiceImpl implements AccountTypeLocal, AccountTypeRemote {
    
	private AccountTypeTransactions typesTxs = new AccountTypeTransactions();
		
	public AccountTypeTO[] getAccountTypes() throws ServerException {
	    final Session session = HibernateContext.getSession(StaticDataConstants.SESSION_FACTORY_NAME);
		return typesTxs.getAccountTypes(session);
	}

	public AccountTypeTO[] getActiveAccountTypes() throws ServerException {
	    final Session session = HibernateContext.getSession(StaticDataConstants.SESSION_FACTORY_NAME);
		return typesTxs.getActiveAccountTypes(session);
	}

	public void createAccountTypes(AccountTypeTO accountTypesTO) throws ServerException {
	    final Session session = HibernateContext.getSession(StaticDataConstants.SESSION_FACTORY_NAME);
		typesTxs.createAccountTypes(session, accountTypesTO);
	}

	public AccountTypeTO getAccountTypesById(int id) throws ServerException {
	    final Session session = HibernateContext.getSession(StaticDataConstants.SESSION_FACTORY_NAME);
		return typesTxs.getAccountTypesById(session, new Integer(id));
	}
	
}