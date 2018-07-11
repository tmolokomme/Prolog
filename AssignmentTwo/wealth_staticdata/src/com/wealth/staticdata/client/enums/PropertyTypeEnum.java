package com.wealth.staticdata.client.enums;

public enum PropertyTypeEnum {
	//Flat, House, Farm, SectionalTitle;
	
	Cluster(1,"Cluster"),
	Farm(2,"Farm > 8,56ha"),
	House(3,"House"),
	House_LHold(4,"House(LHold)"),
	Industrial(5,"Industrial"),
	Offices(6,"Offices"),		
	ST_Duet_House(7,"S/T Duet House"),
	ST_Duplex(8,"S/T Duplex"),
	ST_Flat(9,"S/T Flat"),
	ST_Industrial(10,"S/T Industrial"),
	ST_Offices(11,"S/T Offices"),
	ST_Simplex(12,"S/T Simplex"),
	ST_Shops(13,"S/T Shops"),	
	SHolding(14,"SHolding < 8,56ha"),	
	Shops(15,"Shops"),
	VacantLand(16,"Vacant Land"),	
	Guest_House(17, "Guest House"),
	School(18, "School"),
	Hotel(19, "Hotel"),
	Filling_Station(20, "Filling/Petrol Station"),
	Hospital(21, "Hospital"),
	Clinic(22, "Clinic"),
	Rental_Stock(23, "Rental Stock"),
	Township_Development(24, "Township Development"),
	Flats(25, "Flats"),
	Church(26, "Church");
	
	private final int index;
	private final String displayName;
	
	private PropertyTypeEnum(int index, String displayName) {
		this.index = index;
		this.displayName = displayName;
	}

	public int getIndex() {
		return index;
	}

	public String getDisplayName() {
		return displayName;
	}
}
