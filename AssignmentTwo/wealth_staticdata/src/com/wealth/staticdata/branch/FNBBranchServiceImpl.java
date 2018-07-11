package com.wealth.staticdata.branch;

import javax.ejb.Stateless;
import javax.jws.WebService;

import org.hibernate.Session;

import com.wealth.client.ServerException;
import com.wealth.persistence.factory.HibernateContext;
import com.wealth.staticdata.StaticDataConstants;
import com.wealth.staticdata.client.interfaces.ejb.FNBBranchLocal;
import com.wealth.staticdata.client.interfaces.ejb.FNBBranchRemote;
import com.wealth.staticdata.client.transferobjects.BranchTypeTO;

@Stateless
@WebService
public class FNBBranchServiceImpl implements FNBBranchLocal, FNBBranchRemote {
    
	private FNBBranchTransactions branchesTxs = new FNBBranchTransactions();
		
	public BranchTypeTO[] fetchAllFNBBranches() throws ServerException {
		final Session session = HibernateContext.getSession(StaticDataConstants.SESSION_FACTORY_NAME);
		return branchesTxs.fetchAllFNBBranches(session);
	}

	public BranchTypeTO[] fetchAllFNBPCBranches() throws ServerException {
		final Session session = HibernateContext.getSession(StaticDataConstants.SESSION_FACTORY_NAME);
		return branchesTxs.fetchAllFNBPCBranches(session);
	}

}