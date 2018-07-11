package com.wealth.staticdata.client.interfaces;

import com.wealth.client.ServerException;
import com.wealth.staticdata.client.transferobjects.BranchTypeTO;

public interface FNBBranchService {
	public BranchTypeTO[] fetchAllFNBBranches() throws ServerException;
	public BranchTypeTO[] fetchAllFNBPCBranches() throws ServerException;
}
