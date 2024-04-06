package com.teja.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class GrievanceTypeDetailsModel implements Serializable {
	private static final long serialVersionUID = 1L;
		
	private String grievanceType;
	
	private String  grievanceSubType;
	
	private String complaintDescription;
	
	/**Invoking models*/
	private HealthModel healthModel;
	
	private CleanAndGreenModel cleanAndGreenModel;
	
	
	

}
