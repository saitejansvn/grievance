
package com.teja.model;

import java.io.Serializable;

import lombok.Data;
@Data
public class UserDetailsModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private String firstName;
	private String lastName;
	private String mobileNumber;
	private String role;
	private String userName;
	private String password;
}
