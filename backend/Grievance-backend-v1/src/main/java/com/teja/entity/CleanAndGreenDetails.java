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
@Table(name = "clean_and_green_details")
@Data
public class CleanAndGreenDetails {

	@Id
	@Column(name = "clean_green_details_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cleanGreenDetailsId;

	@Column(name = "sub_type")
	private String cleanAndGreenSubType;

	@Column(name = "description")
	private String complaintDescription;
	
	@Column(name="rest_room_number")
	private String restRoomNumber;

	@Column(name="created_time_stamp")
	private LocalDateTime createdTimeStamp;
	
	@Column(name="modified_time_stamp")
	private LocalDateTime modifiedTimeStamp;
	
}
