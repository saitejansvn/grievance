package com.teja.service;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teja.constants.GrievanceConstants;
import com.teja.constants.GrievanceStatus;
import com.teja.entity.CleanAndGreenDetails;
import com.teja.entity.GrievanceTypeEntity;
import com.teja.entity.HealthDetails;
import com.teja.model.CustomResponse;
import com.teja.model.GrievanceTypeDetailsModel;
import com.teja.repo.ICleanAndGreenRepository;
import com.teja.repo.IGrivenanceTypeRepository;
import com.teja.repo.IHealthDetailsTypeRepository;

@Service
@Transactional
public class GrievanceService {

	@Autowired
	private IHealthDetailsTypeRepository healthDetailsTypeRepository;

	@Autowired
	private IGrivenanceTypeRepository grivenanceTypeRepository;
	
	@Autowired
	private ICleanAndGreenRepository cleanAndGreenRepository;

	/* Service Method for Submitting Grievance Based on Type */
	public CustomResponse submitGrievance(GrievanceTypeDetailsModel grievanceTypeDetailsModel) {
		CustomResponse customResponse = new CustomResponse();
		int grievanceId = 0;

		try {
			if (grievanceTypeDetailsModel != null) {
				// Check GrievanceTypes
				if (grievanceTypeDetailsModel.getGrievanceType()
						.equalsIgnoreCase(GrievanceConstants.HEALTH.toString())) {
					HealthDetails healthDetails = new HealthDetails();
					BeanUtils.copyProperties(grievanceTypeDetailsModel.getHealthModel(), healthDetails);
					if(grievanceTypeDetailsModel.getGrievanceSubType().equalsIgnoreCase("First Aid")) {
						healthDetails.setComplaintDescription(grievanceTypeDetailsModel.getComplaintDescription());						
					}
					healthDetails.setHealthSubType(grievanceTypeDetailsModel.getGrievanceSubType());
					healthDetails.setCreatedDateTimeStamp(LocalDateTime.now());
					healthDetails.setModifiedDateTimeStamp(LocalDateTime.now());
					HealthDetails healthDetails2 = healthDetailsTypeRepository.save(healthDetails);
					// Now creating a record in the griveanveType Details Table
					GrievanceTypeEntity grievanceTypeEntity = buildGriveanceTypeDetails(grievanceTypeDetailsModel);
					grievanceTypeEntity.setHealthDetails(healthDetails2);
					grievanceId = grivenanceTypeRepository.save(grievanceTypeEntity).getGrievanceId();
				}else if (grievanceTypeDetailsModel.getGrievanceType().equalsIgnoreCase(GrievanceConstants.CLEANANDGREEN.getDisplayName())) {
				  	CleanAndGreenDetails cleanAndGreenDetails=new CleanAndGreenDetails();
				  	BeanUtils.copyProperties(grievanceTypeDetailsModel.getCleanAndGreenModel(),cleanAndGreenDetails);
				  	cleanAndGreenDetails.setCreatedTimeStamp(LocalDateTime.now());
				  	cleanAndGreenDetails.setModifiedTimeStamp(LocalDateTime.now());
				  	cleanAndGreenDetails.setCleanAndGreenSubType(grievanceTypeDetailsModel.getGrievanceSubType());
				  	if(grievanceTypeDetailsModel.getGrievanceSubType().equalsIgnoreCase("Sanitary")) {
				  		cleanAndGreenDetails.setRestRoomNumber(grievanceTypeDetailsModel.getCleanAndGreenModel().getRestRoomNumber());
				  	}
				  	cleanAndGreenDetails.setComplaintDescription(grievanceTypeDetailsModel.getComplaintDescription());
				  	CleanAndGreenDetails cleanAndGreenDetails2=cleanAndGreenRepository.save(cleanAndGreenDetails);
				  	GrievanceTypeEntity grievanceTypeEntity = buildGriveanceTypeDetails(grievanceTypeDetailsModel);
				  	grievanceTypeEntity.setCleanAndGreenDetails(cleanAndGreenDetails2);
				  	grivenanceTypeRepository.save(grievanceTypeEntity);		
				}
				customResponse = new CustomResponse(grievanceTypeDetailsModel.getGrievanceType()+" Griveance Type Created Successfully: " + grievanceId,
						HttpStatus.CREATED.toString(), HttpStatus.CREATED.value());
			}

		} catch (Exception e) {
			e.printStackTrace();
			customResponse = new CustomResponse(null, HttpStatus.INTERNAL_SERVER_ERROR.toString(),
					HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
		return customResponse;
	}

	private GrievanceTypeEntity buildGriveanceTypeDetails(GrievanceTypeDetailsModel grievanceTypeDetailsModel) {
		GrievanceTypeEntity grievanceTypeEntity=null;
		try {
			grievanceTypeEntity = new GrievanceTypeEntity();
			grievanceTypeEntity.setGrievanceStatus(GrievanceStatus.OPEN.toString());
			grievanceTypeEntity.setGrievanceType(grievanceTypeDetailsModel.getGrievanceType());
			grievanceTypeEntity.setCreatedDateTimeStamp(LocalDateTime.now());
			grievanceTypeEntity.setModifiedDateTimeStamp(LocalDateTime.now());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return grievanceTypeEntity;
	}

}
