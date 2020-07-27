package com.dasa.labex.mapper;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import com.dasa.labex.controller.ExamController;
import com.dasa.labex.entity.ExamEntity;
import com.dasa.labex.model.Exam;
import com.dasa.labex.model.StatusEnum;
import com.dasa.labex.model.TypeEnum;

@Component
public class ExamMapper {
	
	private ExamMapper() {}
	
	
	public Exam buildExam(ExamEntity examEntity) {
		Link linkSelf = linkTo(methodOn(ExamController.class).exam(examEntity.getId())).withSelfRel();

		return Exam.builder()
				.id(examEntity.getId())
				.name(examEntity.getName())
				.type(TypeEnum.valueOf(examEntity.getType()))
				.status(StatusEnum.status(examEntity.getStatus()))
				.build()
				.add(linkSelf);
	}
	
	public ExamEntity buildExam(Exam exam) {
		return ExamEntity.builder()
				.name(exam.getName())
				.type(exam.getType().name())
				.status(exam.getStatus().getStatus().charAt(0))
				.build();
	}
	
	public List<Exam> buildExams(List<ExamEntity> examsEntity) {
		List<Exam> exams = new ArrayList<Exam>();
		
		examsEntity.forEach(examEntity -> {
			exams.add(this.buildExam(examEntity));
		});
		
		return exams;
	}
}