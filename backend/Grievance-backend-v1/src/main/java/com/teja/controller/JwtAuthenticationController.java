package com.teja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.teja.model.CustomResponse;
import com.teja.model.JwtRequest;
import com.teja.model.UserDetailsModel;
import com.teja.service.JwtAuthenticationService;

@RestController
@RequestMapping("/authenticate")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class JwtAuthenticationController {

	@Autowired
	private JwtAuthenticationService jwtAuthenticationService;

	@PostMapping("/get_token")
	public CustomResponse createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) {
		return jwtAuthenticationService.createAuthenticationToken(authenticationRequest);
	}
	
	@PostMapping("/register")
	public CustomResponse registerUser(@RequestBody UserDetailsModel userDetailsModel){
		return jwtAuthenticationService.registerUser(userDetailsModel);
	}
	
	@GetMapping("/check_user_email_exists")
	public CustomResponse checkUserEmail(@RequestParam ("userName") String userName) {
		return jwtAuthenticationService.checkUserEmail(userName);
	}

}
