package com.dasa.labex.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dasa.labex.entity.ExamEntity;
import com.dasa.labex.entity.LaboratoryEntity;
import com.dasa.labex.entity.LaboratoryExamEntity;
import com.dasa.labex.exception.FieldError;
import com.dasa.labex.exception.InternalServerException;
import com.dasa.labex.exception.NotFoundException;
import com.dasa.labex.exception.UnprocessableEntityException;
import com.dasa.labex.mapper.ExamMapper;
import com.dasa.labex.mapper.LaboratoryExamMapper;
import com.dasa.labex.mapper.LaboratoryMapper;
import com.dasa.labex.model.Exam;
import com.dasa.labex.model.Laboratory;
import com.dasa.labex.model.LaboratoryExam;
import com.dasa.labex.model.StatusEnum;
import com.dasa.labex.repository.LaboratoryExamRepository;

@Service
public class LaboratoryExamServiceImpl implements LaboratoryExamService {
	
	@Autowired
	private LaboratoryExamMapper laboratoryExamMapper;
	
	@Autowired
	private LaboratoryExamRepository laboratoryExamRepository;
	
	@Autowired
	private ExamMapper examMapper;
	
	@Autowired
	private LaboratoryMapper laboratoryMapper;
	
	@Autowired
	private ExamService examService;
	
	@Autowired
	private LaboratoryService laboratoryService;

	@Override
	public List<Exam> examsByLaboratory(Long laboratoryId) {
		List<LaboratoryExamEntity> laboratoryExamsEntity = laboratoryExamRepository.findByLaboratory(laboratoryMapper.buildLaboratory(laboratoryId));
		
		if(laboratoryExamsEntity.isEmpty()) {
			throw new NotFoundException();
		}
		
		return laboratoryExamMapper.buildExamsByLaboratory(laboratoryExamsEntity);
	}
	
	@Override
	@Transactional
	public void save(LaboratoryExam laboratoryExam) {
		
		try {
			Exam exam = examService.exam(laboratoryExam.getExamId());
			Laboratory laboratory = laboratoryService.laboratory(laboratoryExam.getLaboratoryId());
			
			if (exam.getStatus().equals(StatusEnum.ATIVO) && laboratory.getStatus().equals(StatusEnum.ATIVO)) {
				laboratoryExamRepository.save(laboratoryExamMapper.buildLaboratoryExamEntity(laboratoryExam));
			
			} else {
				List<FieldError> validationErrors = new ArrayList<FieldError>();
				validationErrors.add(FieldError.builder()
						.name("status EXAME/LABORATORIO")
						.error("Não foi possivel associar um exame, laboratorio ou exame status INATIVO")
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

	@Override
	@Transactional
	public void delete(Long laboratoryId, Long examId) {
		try {
			Exam exam = examService.exam(examId);
			Laboratory laboratory = laboratoryService.laboratory(laboratoryId);
			
			if (exam.getStatus().equals(StatusEnum.ATIVO) && laboratory.getStatus().equals(StatusEnum.ATIVO)) {
				ExamEntity examEntity = examMapper.buildExam(examId);
				LaboratoryEntity laboratoryEntity = laboratoryMapper.buildLaboratory(laboratoryId);
				
				laboratoryExamRepository.deleteByLaboratoryAndExam(laboratoryEntity, examEntity);
			
			} else {
				List<FieldError> validationErrors = new ArrayList<FieldError>();
				validationErrors.add(FieldError.builder()
						.name("status EXAME/LABORATORIO")
						.error("Não foi possivel desassociar um exame, laboratorio ou exame status INATIVO")
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