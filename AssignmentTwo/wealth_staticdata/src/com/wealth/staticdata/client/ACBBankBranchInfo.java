package com.wealth.staticdata.client;

import java.io.Serializable;

public class ACBBankBranchInfo
	implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String bankName;
    
    private String branchCode;
    
    private String branchName;
    
    public ACBBankBranchInfo() {
    }
    
    public ACBBankBranchInfo(String bankName, String branchCode, String branchName) {
		this.bankName = bankName;
		this.branchCode = branchCode;
		this.branchName = branchName;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
}




