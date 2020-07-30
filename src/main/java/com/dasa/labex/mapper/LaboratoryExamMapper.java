package com.dasa.labex.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dasa.labex.entity.ExamEntity;
import com.dasa.labex.entity.LaboratoryExamEntity;
import com.dasa.labex.model.Exam;
import com.dasa.labex.model.LaboratoryExam;

@Component
public class LaboratoryExamMapper {
	
	private LaboratoryExamMapper() {}
	
	@Autowired
	private LaboratoryMapper laboratoryMapper;
	
	@Autowired
	private ExamMapper examMapper;

	public LaboratoryExamEntity buildLaboratoryExamEntity(LaboratoryExam laboratoryExam) {
		return LaboratoryExamEntity
				.builder()
				.laboratory(laboratoryMapper.buildLaboratory(laboratoryExam.getLaboratoryId()))
				.exam(examMapper.buildExam(laboratoryExam.getExamId()))
				.build();
	}
	
	public List<Exam> buildExamsByLaboratory(List<LaboratoryExamEntity> laboratoryExamsEntity) {
		List<Exam> exams = new ArrayList<Exam>();
		
		laboratoryExamsEntity.forEach(laboratoryExam ->{
			ExamEntity examEntity = laboratoryExam.getExam();
			
			exams.add(examMapper.buildExam(
					examEntity.getId(),
					examEntity.getName()));
		});
		
		return exams;
	}
}