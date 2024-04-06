package com.teja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teja.model.CustomResponse;
import com.teja.model.GrievanceTypeDetailsModel;
import com.teja.service.GrievanceService;

/***
 * @author Saiteja Nimmagadda
 */
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/grievance")
public class GrievanceController {

	@Autowired
	private GrievanceService grievanceService;
	
	@PostMapping("/submit_grievance")
	public CustomResponse submitGrievance(@RequestBody GrievanceTypeDetailsModel grievanceTypeDetailsModel) {
		return grievanceService.submitGrievance(grievanceTypeDetailsModel);
	}
	

}
