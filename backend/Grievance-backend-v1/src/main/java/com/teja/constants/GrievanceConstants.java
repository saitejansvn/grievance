package com.teja.constants;

public enum GrievanceConstants {
	HEALTH("Health"),
	CLEANANDGREEN("Clean And Green"),
	LOCKERS,
	TICKETS,
	PARKING,
	QUEUE,
	FOOD;
	
	private final String displayName;

    // Constructor for constants with custom display names
    GrievanceConstants(String displayName) {
        this.displayName = displayName;
    }

    // Default constructor for constants without custom display names
    GrievanceConstants() {
        this.displayName = name(); // Use the default name as the display name
    }

    public String getDisplayName() {
        return displayName;
    }
}
