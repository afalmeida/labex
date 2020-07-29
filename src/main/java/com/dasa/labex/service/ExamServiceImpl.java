package com.dasa.labex.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dasa.labex.entity.ExamEntity;
import com.dasa.labex.exception.FieldError;
import com.dasa.labex.exception.InternalServerException;
import com.dasa.labex.exception.NotFoundException;
import com.dasa.labex.exception.UnprocessableEntityException;
import com.dasa.labex.factory.ExamFactory;
import com.dasa.labex.mapper.ExamMapper;
import com.dasa.labex.model.Exam;
import com.dasa.labex.model.StatusEnum;
import com.dasa.labex.repository.ExamRepository;

@Service
public class ExamServiceImpl implements ExamService {
	
	@Autowired
	private ExamRepository examRepository;
	
	@Autowired
	private ExamMapper examMapper;
	
	@Autowired
	private ExamFactory examFactory;

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
	public List<Exam> exams(String name, String status) {
		List<Exam> exams = examFactory.exams(name, status);
		
		if(exams.isEmpty()) {
			throw new NotFoundException();
		}
		
		return exams;
	}

	@Override
	public Exam save(Exam exam) {
		try {
			ExamEntity examEntity = examRepository.save(examMapper.buildExam(exam));
			
			return examMapper.buildExam(examEntity);
			
		} catch (Exception e) {
			throw new InternalServerException(e);
		}
	}

	@Override
	public void delete(Long id) {
		try {
			Exam exam = this.exam(id);
			
			if (exam.getStatus().equals(StatusEnum.ATIVO)) {
				examRepository.deleteById(id);
			
			} else {
				List<FieldError> validationErrors = new ArrayList<FieldError>();
				validationErrors.add(FieldError.builder()
						.name("status")
						.error("Impossivel remover um exame com status INATIVO")
						.build());
				throw new UnprocessableEntityException(validationErrors);
			}
			
		} catch (NotFoundException e) {
			throw e;
			
		} catch (UnprocessableEntityException e) {
			throw e;
		
		} catch (Exception e) {
			throw new InternalServerException(e);
		}
	}
}