package com.dasa.labex.service;

import java.util.List;

import com.dasa.labex.model.Exam;

public interface ExamService {
	
	public Exam exam(Long id);
	public List<Exam> exams(String name, String status);
	public Exam save(Exam exam);
	public void delete(Long id);

}