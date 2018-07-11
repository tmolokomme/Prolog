package com.wealth.staticdata.client;

import java.io.Serializable;

public class DeliveryBranch implements Serializable {

    private static final long serialVersionUID = 1L;

    private String branchNumber;
    private String branchName;
    
    public DeliveryBranch() {}

    public String getBranchNumber() {
        return branchNumber;
    }

    public void setBranchNumber(String branchNumber) {
        this.branchNumber = branchNumber;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }
    
}
