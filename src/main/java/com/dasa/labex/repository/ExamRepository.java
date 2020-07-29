package com.dasa.labex.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dasa.labex.entity.ExamEntity;

@Repository
public interface ExamRepository extends JpaRepository<ExamEntity, Long> {
	
	List<ExamEntity> findByNameContains(String name);
	List<ExamEntity> findByNameContainsAndStatus(String name, char status);
	List<ExamEntity> findByStatus(char status);

}