package com.dasa.labex.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dasa.labex.entity.ExamEntity;
import com.dasa.labex.entity.LaboratoryEntity;
import com.dasa.labex.entity.LaboratoryExamEntity;

@Repository
public interface LaboratoryExamRepository extends JpaRepository<LaboratoryExamEntity, Long> {
	
	public List<LaboratoryExamEntity> findByLaboratory(LaboratoryEntity laboratory);
	void deleteByLaboratoryAndExam(LaboratoryEntity laboratory, ExamEntity exam);

}