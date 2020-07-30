package com.dasa.labex.service;

import java.util.List;

import com.dasa.labex.model.Exam;
import com.dasa.labex.model.LaboratoryExam;

public interface LaboratoryExamService {
	
	public List<Exam> examsByLaboratory(Long laboratoryId);
	public void save(LaboratoryExam laboratoryExam);
	public void delete(Long laboratoryId, Long examId);

}
