package com.wealth.staticdata.branch;

import com.wealth.staticdata.client.transferobjects.BranchTypeTO;
import com.wealth.staticdata.domain.BranchType;
import com.wealth.staticdata.domain.BranchTypePrivateClient;

public class FNBBranchTranslator {
	public static BranchTypeTO copyBranchTypesTOFromBranchTypes(BranchType p) {
		BranchTypeTO to = new BranchTypeTO();
		to.setAddressLine1(p.getAddressLine1());
		to.setAddressLine2(p.getAddressLine2());
		to.setAddressLine3(p.getAddressLine3());
		to.setBranchCode(p.getBranchCode());
		to.setBranchName(p.getBranchName());
		to.setBranchNumber(p.getBranchNumber());
		to.setCity(p.getCity());
		to.setHoganBranchNumber(p.getHoganBranchNumber());
		to.setDisplayName(p.getHoganBranchNumber()+" - "+p.getBranchName());
		to.setPvtClientBranch(new Boolean(false));
		return to;
	}
	public static BranchType copyBranchTypesFromBranchTypesTO(BranchTypeTO p) {
		BranchType to = new BranchType();
		to.setAddressLine1(p.getAddressLine1());
		to.setAddressLine2(p.getAddressLine2());
		to.setAddressLine3(p.getAddressLine3());
		to.setBranchCode(p.getBranchCode());
		to.setBranchName(p.getBranchName());
		to.setBranchNumber(p.getBranchNumber());
		to.setHoganBranchNumber(p.getHoganBranchNumber());
		to.setCity(p.getCity());
		return to;
	}
	public static BranchTypeTO copyBranchTypesTOFromBranchTypes(BranchTypePrivateClient p) {
		BranchTypeTO to = new BranchTypeTO();
		to.setAddressLine1(p.getAddressLine1());
		to.setAddressLine2(p.getAddressLine2());
		to.setAddressLine3(p.getAddressLine3());
		to.setBranchCode(p.getBranchCode());
		to.setBranchName(p.getBranchName());
		to.setHoganBranchNumber(p.getHoganBranchNumber());
		to.setBranchNumber(p.getBranchNumber());
		to.setCity(p.getCity());
		to.setDisplayName(p.getHoganBranchNumber()+" - "+p.getBranchName());
		to.setPvtClientBranch(new Boolean(true));
		return to;
	}
	public static BranchTypePrivateClient copyBranchTypesPCFromBranchTypesTO(BranchTypeTO p) {
		BranchTypePrivateClient to = new BranchTypePrivateClient();
		to.setAddressLine1(p.getAddressLine1());
		to.setAddressLine2(p.getAddressLine2());
		to.setAddressLine3(p.getAddressLine3());
		to.setBranchCode(p.getBranchCode());
		to.setBranchName(p.getBranchName());
		to.setHoganBranchNumber(p.getHoganBranchNumber());
		to.setBranchNumber(p.getBranchNumber());
		to.setCity(p.getCity());
		return to;
	}

}
