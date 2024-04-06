package com.teja.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="health_details")
@Data
public class HealthDetails {

	@Id
	@Column(name="health_details_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer HealthDetailsId;
	
	@Column(name="health_sub_type")
	private String healthSubType;
	
	@Column(name="queue_number")
	private String queueNumber;
	
	@Column(name="section_number")
	private String sectionNumber;
	
	@Column(name="gate_number")
	private String gateNumber;

	@Column(name="description")
	private String complaintDescription;

	@Column(name="created_date_time_stamp")
	private LocalDateTime createdDateTimeStamp;
	
	@Column(name="modified_date_time_stamp")
	private LocalDateTime modifiedDateTimeStamp;
	
	
	
}
