package com.teja.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "grivenace_type")
@Data
public class GrievanceTypeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "grievance_id")
	private Integer grievanceId;
	
	@Column(name = "grievance_status")
	private String grievanceStatus;

	@Column(name = "grivenace_type")
	private String grievanceType;

	/*Invoking other grievanceType Details*/
	@ManyToOne
	@JoinColumn(name = "health_details")
	private HealthDetails healthDetails;

	@ManyToOne
	@JoinColumn(name="clean_and_green")
	private CleanAndGreenDetails cleanAndGreenDetails;
	
	
	@Column(name = "created_date_time_stamp")
	private LocalDateTime createdDateTimeStamp;

	@Column(name = "modified_date_time_stamp")
	private LocalDateTime modifiedDateTimeStamp;


}
