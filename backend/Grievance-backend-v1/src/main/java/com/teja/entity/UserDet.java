package com.teja.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "user_det")
@Data
public class UserDet {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;

	@Column(name = "f_name")
	private String firstName;

	@Column(name = "l_name")
	private String lastName;

	@Column(name = "mob_number")
	private String mobileNumber;

	@Column(name = "user_name")
	private String userName;

	@JsonIgnore
	@Column(name = "password")
	private String password;

	@Column(name = "role_type")
	private String role;

}
