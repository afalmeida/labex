package com.dasa.labex.factory;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.test.util.ReflectionTestUtils;

import com.dasa.labex.entity.ExamEntity;
import com.dasa.labex.mapper.ExamMapper;
import com.dasa.labex.model.Exam;
import com.dasa.labex.model.StatusEnum;
import com.dasa.labex.model.TypeEnum;
import com.dasa.labex.repository.ExamRepository;

public class ExamFactoryTest {

	@InjectMocks
	private ExamFactory examFactory;

	@Mock
	private ExamRepository examRepository;
	
	@Spy
	private ExamMapper examMapper;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
        ReflectionTestUtils.setField(examFactory, "examMapper", examMapper);

	}

	@Test
	public void examsNameAtivo() {
		Mockito.when(examRepository.findByNameContainsAndStatus("STRING",'A')).thenReturn(examsAtivoEntityMock());

		List<Exam> exams = examFactory.exams("STRING", "A");
		assertNotNull(exams);
		assertTrue(exams.size()==2);
		assertTrue(exams.get(0).getType().equals(TypeEnum.ANC));
		assertTrue(exams.get(1).getType().equals(TypeEnum.IMG));
		assertTrue(exams.get(0).getStatus().equals(StatusEnum.ATIVO));
		assertTrue(exams.get(1).getStatus().equals(StatusEnum.ATIVO));
		
	}
	
	@Test
	public void examsNameInativo() {
		Mockito.when(examRepository.findByNameContainsAndStatus("STRING",'A')).thenReturn(examsInativoEntityMockEmpty());

		List<Exam> exams = examFactory.exams("STRING", "I");
		assertNotNull(exams);
		assertTrue(exams.isEmpty());
		
	}
	
	@Test
	public void examsAll() {
		Mockito.when(examRepository.findAll()).thenReturn(examsEntityMock());

		List<Exam> exams = examFactory.exams(null, "ALL");
		assertNotNull(exams);
		assertTrue(exams.size()==2);
		assertTrue(exams.get(0).getType().equals(TypeEnum.ANC));
		assertTrue(exams.get(1).getType().equals(TypeEnum.IMG));
		assertTrue(exams.get(0).getStatus().equals(StatusEnum.ATIVO));
		assertTrue(exams.get(1).getStatus().equals(StatusEnum.INATIVO));
		
	}
	
	@Test
	public void examsAtivoAll() {
		Mockito.when(examRepository.findByStatus('A')).thenReturn(examsAtivoEntityMock());

		List<Exam> exams = examFactory.exams(null, "A");
		assertNotNull(exams);
		assertTrue(exams.size()==2);
		assertTrue(exams.get(0).getType().equals(TypeEnum.ANC));
		assertTrue(exams.get(1).getType().equals(TypeEnum.IMG));
		assertTrue(exams.get(0).getStatus().equals(StatusEnum.ATIVO));
		assertTrue(exams.get(1).getStatus().equals(StatusEnum.ATIVO));
		
	}
	
	@Test
	public void examsInativoAll() {
		Mockito.when(examRepository.findByStatus('I')).thenReturn(examsInativoEntityMock());

		List<Exam> exams = examFactory.exams(null, "I");
		assertNotNull(exams);
		assertTrue(exams.size()==2);
		assertTrue(exams.get(0).getType().equals(TypeEnum.ANC));
		assertTrue(exams.get(1).getType().equals(TypeEnum.IMG));
		assertTrue(exams.get(0).getStatus().equals(StatusEnum.INATIVO));
		assertTrue(exams.get(1).getStatus().equals(StatusEnum.INATIVO));
		
	}
	

	private List<ExamEntity> examsEntityMock() {
		List<ExamEntity> examsEntity = new ArrayList<ExamEntity>();

		examsEntity.add(ExamEntity.builder().id(1l).name("EXAME A").type("ANC").status('A').build());
		examsEntity.add(ExamEntity.builder().id(2l).name("EXAME B").type("IMG").status('I').build());

		return examsEntity;
	}
	
	private List<ExamEntity> examsAtivoEntityMock() {
		List<ExamEntity> examsEntity = new ArrayList<ExamEntity>();

		examsEntity.add(ExamEntity.builder().id(1l).name("EXAME A").type("ANC").status('A').build());
		examsEntity.add(ExamEntity.builder().id(2l).name("EXAME B").type("IMG").status('A').build());

		return examsEntity;
	}
	
	private List<ExamEntity> examsInativoEntityMock() {
		List<ExamEntity> examsEntity = new ArrayList<ExamEntity>();

		examsEntity.add(ExamEntity.builder().id(1l).name("EXAME A").type("ANC").status('I').build());
		examsEntity.add(ExamEntity.builder().id(2l).name("EXAME B").type("IMG").status('I').build());

		return examsEntity;
	}
	
	private List<ExamEntity> examsInativoEntityMockEmpty() {
		List<ExamEntity> examsEntity = new ArrayList<ExamEntity>();


		return examsEntity;
	}
}