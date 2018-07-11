package com.wealth.staticdata.client;

import java.util.List;

import javax.ejb.Remote;

import com.wealth.client.ServerException;

@Remote
public interface StaticDataLookupSession {

	public List<DeliveryBranch> getFNBPCDeliveryBranches() throws ServerException;
    
	public DeliveryBranchDetails getFNBPCDeliveryBranchDetails(String branchNumber) throws ServerException;
    
	public List<DeliveryBranch> getRMBPBDeliveryBranches() throws ServerException;
    
	public DeliveryBranchDetails getRMBPBDeliveryBranchDetails(String branchNumber) throws ServerException;
    
	public List<Title> getTitles() throws ServerException;
	
	//payment automation additions
	//public ACBBankBranchInfo[] getBanks() throws ServerException;
	
	//public ACBBankBranchInfo[] getBranchesForBank(String bankName) throws ServerException;	

	public ACBBankBranchInfo getBranchInfo(String branchCode) throws ServerException;
	
}




