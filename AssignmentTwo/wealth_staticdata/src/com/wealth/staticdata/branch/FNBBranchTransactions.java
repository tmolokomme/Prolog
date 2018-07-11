package com.wealth.staticdata.branch;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.wealth.client.ServerException;
import com.wealth.staticdata.client.transferobjects.BranchTypeTO;
import com.wealth.staticdata.domain.BranchType;
import com.wealth.staticdata.domain.BranchTypePrivateClient;

public class FNBBranchTransactions {

	private BranchTypeTO[] convertToTOArray(List<BranchType> types) {
		BranchTypeTO[] typesTO = new BranchTypeTO[types.size()];
		int i = 0;
		for (BranchType cl : types) {
			typesTO[i] = FNBBranchTranslator.copyBranchTypesTOFromBranchTypes(cl);
			i++;
		}
		return typesTO;
	}

	private BranchTypeTO[] convertPCToTOArray(List<BranchTypePrivateClient> types) {
		BranchTypeTO[] typesTO = new BranchTypeTO[types.size()];
		int i = 0;
		for (BranchTypePrivateClient cl : types) {
			typesTO[i] = FNBBranchTranslator.copyBranchTypesTOFromBranchTypes(cl);
			i++;
		}
		return typesTO;
	}

	public BranchTypeTO[] fetchAllFNBBranches(Session session) {
		try {			
			Query query = session.createQuery("from BranchType order by hoganBranchNumber asc");
			List<BranchType> types = query.list();
			BranchTypeTO[] typesTO = convertToTOArray(types);
			return typesTO;
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ServerException(e);
		}
	}

	public BranchTypeTO[] fetchAllFNBPCBranches(Session session) {
		try {			
			Query query = session.createQuery("from BranchTypePrivateClient order by hoganBranchNumber asc");
			List<BranchTypePrivateClient> types = query.list();
			BranchTypeTO[] typesTO = convertPCToTOArray(types);
			return typesTO;
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ServerException(e);
		}
	}

}
