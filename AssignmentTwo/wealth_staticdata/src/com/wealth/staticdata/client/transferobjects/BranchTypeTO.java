package com.wealth.staticdata.client.transferobjects;

import java.io.Serializable;

public class BranchTypeTO implements Serializable {

	private static final long serialVersionUID = 1L;

    private String branchCode;
    private String branchNumber;
    private String hoganBranchNumber;
    private String branchName;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String city;
    private String displayName;
    private Boolean pvtClientBranch;
    
	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

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

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getAddressLine3() {
		return addressLine3;
	}

	public void setAddressLine3(String addressLine3) {
		this.addressLine3 = addressLine3;
	}

	public String getHoganBranchNumber() {
		return hoganBranchNumber;
	}

	public void setHoganBranchNumber(String hoganBranchNumber) {
		this.hoganBranchNumber = hoganBranchNumber;
	}

	@Override
	public String toString() {
		return " name:"+branchName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDisplayName() {
		return displayName;
	}

	public Boolean getPvtClientBranch() {
		return pvtClientBranch;
	}

	public void setPvtClientBranch(Boolean pvtClientBranch) {
		this.pvtClientBranch = pvtClientBranch;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
}
