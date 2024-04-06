package com.teja.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	
	private String jwttoken;
	
	public String userName;
	
	public String role;
	
	private boolean isValid;
	
	private int statusCode;
	

	
}