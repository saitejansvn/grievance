package com.teja.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teja.entity.CleanAndGreenDetails;

public interface ICleanAndGreenRepository  extends JpaRepository<CleanAndGreenDetails,Integer>{

}
