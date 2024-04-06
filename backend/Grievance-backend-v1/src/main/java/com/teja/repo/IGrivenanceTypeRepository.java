package com.teja.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teja.entity.GrievanceTypeEntity;

public interface IGrivenanceTypeRepository  extends JpaRepository<GrievanceTypeEntity, Integer>{

}
