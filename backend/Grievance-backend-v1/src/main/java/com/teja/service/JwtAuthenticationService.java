package com.teja.service;

import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.teja.config.JwtTokenUtil;
import com.teja.entity.UserDet;
import com.teja.model.CustomResponse;
import com.teja.model.JwtRequest;
import com.teja.model.JwtResponse;
import com.teja.model.UserDetailsModel;
import com.teja.repo.IUserRepository;

@Service
public class JwtAuthenticationService {

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService jwtInMemoryUserDetailsService;

	@Autowired 
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	 

	public CustomResponse createAuthenticationToken(JwtRequest authenticationRequest) {

		CustomResponse customResponse=new CustomResponse();
		JwtResponse jwtResponse = new JwtResponse();
		try {

			if (authenticationRequest != null) {
				UserDet userDet = userRepository.findByUserName(authenticationRequest.getUsername());
				if (userDet != null) {
					
					
					//boolean isValid = bCryptPasswordEncoder.matches(authenticationRequest.getPassword().trim(), userDet.getPassword());
					boolean isValid=authenticationRequest.getPassword().trim().equalsIgnoreCase(userDet.getPassword());					
					if (isValid) {

					//	authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

						final UserDetails userDetails = jwtInMemoryUserDetailsService
								.loadUserByUsername(authenticationRequest.getUsername());

						final String token = jwtTokenUtil.generateToken(userDetails);		
						jwtResponse.setJwttoken(token);
						jwtResponse.setUserName(userDetails.getUsername());
						jwtResponse.setRole(userDet.getRole());
						jwtResponse.setValid(true);
						jwtResponse.setStatusCode(HttpStatus.OK.value());
						customResponse=new CustomResponse(jwtResponse, HttpStatus.OK.toString(), HttpStatus.OK.value());

					} else {	
						jwtResponse.setValid(false);
						jwtResponse.setStatusCode(HttpStatus.OK.value());
						customResponse=new CustomResponse
								(jwtResponse, HttpStatus.OK.toString(),HttpStatus.OK.value());
					}

				} else {
					customResponse=new CustomResponse
							("User Not Found", HttpStatus.OK.toString(),HttpStatus.OK.value());
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			customResponse=new CustomResponse(e, HttpStatus.INTERNAL_SERVER_ERROR.toString(),
					HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
		return customResponse;
	}

	private void authenticate(String username, String password) throws Exception {
		Objects.requireNonNull(username);
		Objects.requireNonNull(password);

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

	public CustomResponse registerUser(UserDetailsModel userDetailsModel) {
		CustomResponse customResponse = new CustomResponse();
		try {
			if (userDetailsModel != null) {
				UserDet userDet = new UserDet();
				BeanUtils.copyProperties(userDetailsModel, userDet);
				//userDet.setPassword(bCryptPasswordEncoder.encode(userDetailsModel.getPassword().trim()));
				userDet.setPassword(userDetailsModel.getPassword().trim());
				int userId = userRepository.save(userDet).getUserId();
				customResponse=new CustomResponse("User Id Created Successfully: " + userId, HttpStatus.OK.toString(),HttpStatus.OK.value());
			}
		} catch (Exception e) {
			return new CustomResponse
					(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.toString(), HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
		return customResponse;
	}

	public CustomResponse checkUserEmail(String username) {
		CustomResponse customResponse=new CustomResponse();
		try {
			if(username!=null) {
				UserDet userDet=userRepository.findByUserName(username);
				if(userDet!=null) {
					return new CustomResponse("UserName Already Exists",HttpStatus.FOUND.toString(), HttpStatus.FOUND.value());
				}else {
					return new CustomResponse("UserName Not Exists",HttpStatus.NOT_FOUND.toString(), HttpStatus.NOT_FOUND.value());
				}
				
			}
		}catch (Exception e) {
			return new CustomResponse
					(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.toString(), HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
		return customResponse;
	}

}
