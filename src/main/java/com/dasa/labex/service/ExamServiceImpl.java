package com.dasa.labex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dasa.labex.entity.ExamEntity;
import com.dasa.labex.exception.InternalServerException;
import com.dasa.labex.exception.NotFoundException;
import com.dasa.labex.mapper.ExamMapper;
import com.dasa.labex.model.Exam;
import com.dasa.labex.repository.ExamRepository;

@Service
public class ExamServiceImpl implements ExamService {
	
	@Autowired
	private ExamRepository examRepository;
	
	@Autowired
	private ExamMapper examMapper;

	@Override
	public Exam exam(Long id) {
		try {
			examRepository.findById(id)
					.orElseThrow(() -> new NotFoundException());
			
			return examMapper.buildExam(examRepository.findById(id).get());
			
		} catch (NotFoundException e) {
			throw e;
			
		} catch (Exception e) {
			throw new InternalServerException(e);
		}
		
	}

	@Override
	public List<Exam> exams() {
		List<Exam> exams = examMapper.buildExams(examRepository.findAll());
		
		if(exams.isEmpty()) {
			throw new NotFoundException();
		}
		
		return exams;
	}

	@Override
	public List<Exam> exams(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exam save(Exam exam) {
		ExamEntity examEntity = examRepository.save(examMapper.buildExam(exam));
		
		return examMapper.buildExam(examEntity);
	}

}
