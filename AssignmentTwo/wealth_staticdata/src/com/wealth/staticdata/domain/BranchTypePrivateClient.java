package com.wealth.staticdata.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.wealth.domain.BaseDomainEntity;

@Entity
@Table(name="FNBPC_Branches")
@Embeddable
public class BranchTypePrivateClient extends BaseDomainEntity{

	private static final long serialVersionUID = 1L;
	@Column(name="FNB_BRANCH_SHORTNAME")
    private String branchCode;
	
	@Column(name="BRANCH_NO")
    private String branchNumber;
    
	@Column(name="BRANCH_NAME")  
	private String branchName;
	
	@Column(name="FNB_HOGAN_BRANCHNUMBER")
	private String hoganBranchNumber;

	@Column(name="ADDRESS_LINE1")
    private String addressLine1;

	@Column(name="ADDRESS_LINE2")
	private String addressLine2;
	
	@Column(name="ADDRESS_LINE3")
    private String addressLine3;
	
	@Column(name="CITY")
    private String city;
	
	public BranchTypePrivateClient() {
	}

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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}


}
