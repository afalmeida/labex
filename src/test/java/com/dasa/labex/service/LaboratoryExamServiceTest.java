package com.dasa.labex.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.test.util.ReflectionTestUtils;

import com.dasa.labex.entity.ExamEntity;
import com.dasa.labex.entity.LaboratoryEntity;
import com.dasa.labex.entity.LaboratoryExamEntity;
import com.dasa.labex.exception.NotFoundException;
import com.dasa.labex.exception.UnprocessableEntityException;
import com.dasa.labex.mapper.ExamMapper;
import com.dasa.labex.mapper.LaboratoryExamMapper;
import com.dasa.labex.mapper.LaboratoryMapper;
import com.dasa.labex.model.Exam;
import com.dasa.labex.model.Laboratory;
import com.dasa.labex.model.LaboratoryExam;
import com.dasa.labex.model.StatusEnum;
import com.dasa.labex.model.TypeEnum;
import com.dasa.labex.repository.LaboratoryExamRepository;

public class LaboratoryExamServiceTest {
	
	@InjectMocks
	private LaboratoryExamServiceImpl laboratoryExamService;
	
	@Spy
	private LaboratoryMapper laboratoryMapper;
	
	@Spy
	private LaboratoryExamMapper laboratoryExamMapper;
	
	@Spy
	private ExamMapper examMapper;
	
	
	@Mock
	private LaboratoryExamRepository laboratoryExamRepository;
	
	@Mock
	private ExamService examService;
	
	@Mock
	private LaboratoryService laboratoryService;
	
	private final Long LABORATORY_ID = 1l;
	private final Long EXAM_ID = 1l;
	
	
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
        ReflectionTestUtils.setField(laboratoryExamService, "laboratoryMapper", laboratoryMapper);
        ReflectionTestUtils.setField(laboratoryExamService, "laboratoryExamMapper", laboratoryExamMapper);
        
        ReflectionTestUtils.setField(laboratoryExamMapper, "examMapper", examMapper);
        ReflectionTestUtils.setField(laboratoryExamMapper, "laboratoryMapper", laboratoryMapper);
	}
	
	@Test
	public void examsByLaboratory() {
		when(laboratoryExamRepository.findByLaboratory(laboratoryMapper.buildLaboratory(LABORATORY_ID))).thenReturn(laboratoryExamsEntityMock());
		
		List<Exam> exams = laboratoryExamService.examsByLaboratory(LABORATORY_ID);
		
		assertNotNull(exams);
		assertTrue(exams.size()==2);
		
	}
	
	@Test
	public void examsByLaboratoryNotFound() {
		when(laboratoryExamRepository.findByLaboratory(laboratoryMapper.buildLaboratory(LABORATORY_ID))).thenReturn((new ArrayList<LaboratoryExamEntity>()));
		
		assertThrows(NotFoundException.class, () -> {
			laboratoryExamService.examsByLaboratory(LABORATORY_ID);
		});
		
	}
	
	@Test
	public void save() {
		when(examService.exam(EXAM_ID)).thenReturn(examMock());
		when(laboratoryService.laboratory(LABORATORY_ID)).thenReturn(laboratoryMock());
		
		laboratoryExamService.save(laboratoryExamMock());
		
	}
	
	@Test
	public void saveExamStatusInativo() {
		when(examService.exam(EXAM_ID)).thenReturn(examInativoMock());
		when(laboratoryService.laboratory(LABORATORY_ID)).thenReturn(laboratoryMock());
		
		try {
			laboratoryExamService.save(laboratoryExamMock());
		} catch (Exception ex) {        
			if (ex instanceof UnprocessableEntityException) {
				UnprocessableEntityException httpClientErrorException = (UnprocessableEntityException) ex;
				
	            assertNotNull(httpClientErrorException);
	            assertTrue(httpClientErrorException.getValidationErrors().size()==1);
	            assertEquals("Existem erros de validacao no request enviado", ex.getMessage());
	            assertEquals("Não foi possivel associar um exame, laboratorio ou exame status INATIVO", httpClientErrorException.getValidationErrors().get(0).getError());
	            assertSame(UnprocessableEntityException.class, ex.getClass());
			}
		}
	}
	
	@Test
	public void saveLaboratoryStatusInativo() {
		when(examService.exam(EXAM_ID)).thenReturn(examMock());
		when(laboratoryService.laboratory(LABORATORY_ID)).thenReturn(laboratoryInativoMock());
		
		try {
			laboratoryExamService.save(laboratoryExamMock());
		} catch (Exception ex) {        
			if (ex instanceof UnprocessableEntityException) {
				UnprocessableEntityException httpClientErrorException = (UnprocessableEntityException) ex;
				
	            assertNotNull(httpClientErrorException);
	            assertTrue(httpClientErrorException.getValidationErrors().size()==1);
	            assertEquals("Existem erros de validacao no request enviado", ex.getMessage());
	            assertEquals("Não foi possivel associar um exame, laboratorio ou exame status INATIVO", httpClientErrorException.getValidationErrors().get(0).getError());
	            assertSame(UnprocessableEntityException.class, ex.getClass());
			}
		}
	}
	
	@Test
	public void delete() {
		when(examService.exam(EXAM_ID)).thenReturn(examMock());
		when(laboratoryService.laboratory(LABORATORY_ID)).thenReturn(laboratoryMock());
		
		laboratoryExamService.delete(LABORATORY_ID,EXAM_ID);
		
	}
	
	@Test
	public void deleteExamStatusInativo() {
		when(examService.exam(EXAM_ID)).thenReturn(examInativoMock());
		when(laboratoryService.laboratory(LABORATORY_ID)).thenReturn(laboratoryMock());
		
		try {
			laboratoryExamService.delete(LABORATORY_ID,EXAM_ID);
			
		} catch (Exception ex) {        
			if (ex instanceof UnprocessableEntityException) {
				UnprocessableEntityException httpClientErrorException = (UnprocessableEntityException) ex;
				
	            assertNotNull(httpClientErrorException);
	            assertTrue(httpClientErrorException.getValidationErrors().size()==1);
	            assertEquals("Existem erros de validacao no request enviado", ex.getMessage());
	            assertEquals("Não foi possivel desassociar um exame, laboratorio ou exame status INATIVO", httpClientErrorException.getValidationErrors().get(0).getError());
	            assertSame(UnprocessableEntityException.class, ex.getClass());
			}
		}
	}
	
	@Test
	public void deleteLaboratoryStatusInativo() {
		when(examService.exam(EXAM_ID)).thenReturn(examMock());
		when(laboratoryService.laboratory(LABORATORY_ID)).thenReturn(laboratoryInativoMock());
		
		try {
			laboratoryExamService.delete(LABORATORY_ID,EXAM_ID);
			
		} catch (Exception ex) {        
			if (ex instanceof UnprocessableEntityException) {
				UnprocessableEntityException httpClientErrorException = (UnprocessableEntityException) ex;
				
	            assertNotNull(httpClientErrorException);
	            assertTrue(httpClientErrorException.getValidationErrors().size()==1);
	            assertEquals("Existem erros de validacao no request enviado", ex.getMessage());
	            assertEquals("Não foi possivel desassociar um exame, laboratorio ou exame status INATIVO", httpClientErrorException.getValidationErrors().get(0).getError());
	            assertSame(UnprocessableEntityException.class, ex.getClass());
			}
		}
	}
	
	private List<LaboratoryExamEntity> laboratoryExamsEntityMock(){
		ExamEntity examA = ExamEntity.builder().id(1l).name("EXAME A").type("ANC").status('A').build();
		ExamEntity examB = ExamEntity.builder().id(1l).name("EXAME B").type("ANC").status('A').build();
		LaboratoryEntity laboratoryA = LaboratoryEntity.builder().id(1l).name("LAB A").status('A').build();
		
		List<LaboratoryExamEntity> laboratoryExamsEntity = new ArrayList<LaboratoryExamEntity>();

		laboratoryExamsEntity.add(LaboratoryExamEntity.builder().exam(examA).laboratory(laboratoryA).build());
		laboratoryExamsEntity.add(LaboratoryExamEntity.builder().exam(examB).laboratory(laboratoryA).build());
		
		return laboratoryExamsEntity;
	}
	
	private Exam examMock() {
		return Exam.builder().id(1l).name("EXAME A").type(TypeEnum.ANC).status(StatusEnum.ATIVO).build();
	}
	
	private Exam examInativoMock() {
		return Exam.builder().id(1l).name("EXAME A").type(TypeEnum.ANC).status(StatusEnum.INATIVO).build();
	}
	
	private Laboratory laboratoryMock() {
		return Laboratory.builder().id(1l).name("LAB A").status(StatusEnum.ATIVO).build();
	}
	
	private Laboratory laboratoryInativoMock() {
		return Laboratory.builder().id(1l).name("LAB A").status(StatusEnum.INATIVO).build();
	}
	
	private LaboratoryExam laboratoryExamMock() {
		return LaboratoryExam.builder().laboratoryId(LABORATORY_ID).examId(EXAM_ID).build();
	}

}