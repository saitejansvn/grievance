package com.teja.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teja.entity.UserDet;

public interface IUserRepository extends JpaRepository<UserDet,Integer>{

	UserDet findByUserName(String userName);
	
}
