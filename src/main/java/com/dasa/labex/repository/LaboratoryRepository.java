package com.dasa.labex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dasa.labex.entity.LaboratoryEntity;

@Repository
public interface LaboratoryRepository extends JpaRepository<LaboratoryEntity, Long>{

}