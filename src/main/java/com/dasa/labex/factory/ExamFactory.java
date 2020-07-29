package com.dasa.labex.factory;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dasa.labex.entity.ExamEntity;
import com.dasa.labex.mapper.ExamMapper;
import com.dasa.labex.model.Exam;
import com.dasa.labex.model.StatusEnum;
import com.dasa.labex.repository.ExamRepository;

@Component
public class ExamFactory {
	
	private ExamFactory () {}
	
	@Autowired
	private ExamRepository examRepository;
	
	@Autowired
	private ExamMapper examMapper;
	
	public List<Exam> exams(String name, String status) {
		List<ExamEntity> exams = null;
		StatusEnum statusEnum = StatusEnum.status(status);
		
		if(StringUtils.isNotBlank(name)) {
			if (statusEnum.equals(StatusEnum.ALL)){
				exams = examRepository.findByNameContains(name);
			
			} else {
				exams = examRepository.findByNameContainsAndStatus(name,statusEnum.getStatus().charAt(0));
			}

		} else {
			if (statusEnum.equals(StatusEnum.ALL)){
				exams = examRepository.findAll();
			
			} else {
				exams = examRepository.findByStatus(statusEnum.getStatus().charAt(0));
			}
		}
		
		return examMapper.buildExams(exams);
	}
}