package com.dasa.labex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dasa.labex.entity.ExamEntity;

@Repository
public interface ExamRepository extends JpaRepository<ExamEntity, Long>{

}