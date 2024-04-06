package com.teja.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teja.entity.HealthDetails;

public interface IHealthDetailsTypeRepository extends JpaRepository<HealthDetails, Integer> {

}
