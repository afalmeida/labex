package com.dasa.labex.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.test.util.ReflectionTestUtils;

import com.dasa.labex.entity.ExamEntity;
import com.dasa.labex.factory.ExamFactory;
import com.dasa.labex.mapper.ExamMapper;
import com.dasa.labex.model.Exam;
import com.dasa.labex.model.StatusEnum;
import com.dasa.labex.model.TypeEnum;
import com.dasa.labex.repository.ExamRepository;

public class ExamServiceTest {
	
	@InjectMocks
	private ExamServiceImpl examService;
	
	@Mock
	private ExamFactory examFactory;
	
	@Spy
	private ExamMapper examMapper;
	
	@Mock
	private ExamRepository examRepository;
	
	private final Long EXAM_ID = 1l;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
        ReflectionTestUtils.setField(examService, "examMapper", examMapper);

	}
	
	@Test
	public void exam() {
		when(examRepository.findById(EXAM_ID)).thenReturn(examsEntityMock());

		Exam exam = examService.exam(EXAM_ID);
		
		assertNotNull(exam);
		assertTrue(exam.getType().equals(TypeEnum.ANC));
		assertTrue(exam.getStatus().equals(StatusEnum.ATIVO));
		assertTrue(exam.getLinks().hasSize(1));
		assertEquals(exam.getLinks().getLink("self").get().getHref(), "/labex/exams/1");;

	}
	
	@Test
	public void exams() {
		when(examFactory.exams(null,"ALL")).thenReturn(examsMock());

		List<Exam> exams = examService.exams(null, "ALL");
		assertNotNull(exams);
		assertTrue(exams.size()==2);
		assertTrue(exams.get(0).getType().equals(TypeEnum.ANC));
		assertTrue(exams.get(1).getType().equals(TypeEnum.IMG));
		assertTrue(exams.get(0).getStatus().equals(StatusEnum.ATIVO));
		assertTrue(exams.get(1).getStatus().equals(StatusEnum.INATIVO));
		
	}
	
	@Test
	public void examsStatusAtivo() {
		when(examFactory.exams(null,"A")).thenReturn(examsStatusAtivoMock());

		List<Exam> exams = examService.exams(null,"A");
		assertNotNull(exams);
		assertTrue(exams.size()==2);
		assertFalse(exams.stream()
				.filter(examFilter-> examFilter.getStatus().equals(StatusEnum.INATIVO))
				.findAny().isPresent());
		
	}
	
	@Test
	public void save() {
		Exam examInit = Exam.builder().name("EXAME A").type(TypeEnum.ANC).status(StatusEnum.ATIVO).build();
		ExamEntity examEntity = ExamEntity.builder().name("EXAME A").type("ANC").status('A').build();
		
		when(examRepository.save(examEntity)).thenReturn(entityMock());
		
		Exam exax = examService.save(examInit);
		
		assertNotNull(exax);
		assertTrue(exax.getId().equals(110l));
	}
	
	@Test
	public void delete() {

		
		assertNotNull(null);


	}
	

	private Optional<ExamEntity> examsEntityMock() {
		return Optional.of(ExamEntity.builder().id(1l).name("EXAME A").type("ANC").status('A').build());

	}
	
	private List<Exam> examsMock() {
		List<Exam> exams = new ArrayList<Exam>();

		exams.add(Exam.builder().id(1l).name("EXAME A").type(TypeEnum.ANC).status(StatusEnum.ATIVO).build());
		exams.add(Exam.builder().id(1l).name("EXAME B").type(TypeEnum.IMG).status(StatusEnum.INATIVO).build());

		return exams;
	}
	
	private List<Exam> examsStatusAtivoMock() {
		List<Exam> exams = new ArrayList<Exam>();

		exams.add(Exam.builder().id(1l).name("EXAME A").type(TypeEnum.ANC).status(StatusEnum.ATIVO).build());
		exams.add(Exam.builder().id(1l).name("EXAME B").type(TypeEnum.IMG).status(StatusEnum.ATIVO).build());

		return exams;
	}
	
	private ExamEntity entityMock() {
		return ExamEntity.builder().id(110l).name("EXAME A").type("ANC").status('A').build();

	}
}