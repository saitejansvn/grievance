package com.teja.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class HealthModel implements Serializable {
	
	private static final long serialVersionUID = 1L; 
  
        private String healthSubType;
		
        private String queueNumber;
		
		private String sectionNumber;
		
		private String gateNumber;
		
		private String complaintDescription;
		
}
