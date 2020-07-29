package com.dasa.labex.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dasa.labex.entity.LaboratoryEntity;

@Repository
public interface LaboratoryRepository extends JpaRepository<LaboratoryEntity, Long> {
	
	List<LaboratoryEntity> findByNameContains(String name);
	List<LaboratoryEntity> findByNameContainsAndStatus(String name, char status);
	List<LaboratoryEntity> findByStatus(char status);

}