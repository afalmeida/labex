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

import com.dasa.labex.entity.LaboratoryEntity;
import com.dasa.labex.mapper.LaboratoryMapper;
import com.dasa.labex.model.Laboratory;
import com.dasa.labex.model.StatusEnum;
import com.dasa.labex.repository.LaboratoryRepository;

public class LaboratoryFactoryTest {

	@InjectMocks
	private LaboratoryFactory laboratoryFactory;

	@Mock
	private LaboratoryRepository laboratoryRepository;
	
	@Spy
	private LaboratoryMapper laboratoryMapper;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
        ReflectionTestUtils.setField(laboratoryFactory, "laboratoryMapper", laboratoryMapper);

	}

	@Test
	public void laboratoriesNameAtivo() {
		Mockito.when(laboratoryRepository.findByNameContainsAndStatus("STRING",'A')).thenReturn(laboratoriesAtivoEntityMock());

		List<Laboratory> laboratories = laboratoryFactory.laboratories("STRING", "A", null);
		assertNotNull(laboratories);
		assertTrue(laboratories.size()==2);
		assertTrue(laboratories.get(0).getStatus().equals(StatusEnum.ATIVO));
		assertTrue(laboratories.get(1).getStatus().equals(StatusEnum.ATIVO));
		
	}
	
	@Test
	public void laboratoriesNameInativo() {
		Mockito.when(laboratoryRepository.findByNameContainsAndStatus("STRING",'A')).thenReturn(laboratoriesInativoEntityMockEmpty());

		List<Laboratory> laboratories = laboratoryFactory.laboratories("STRING", "I", null);
		assertNotNull(laboratories);
		assertTrue(laboratories.isEmpty());
		
	}
	
	@Test
	public void laboratoriesAll() {
		Mockito.when(laboratoryRepository.findAll()).thenReturn(laboratoriesEntityMock());

		List<Laboratory> laboratories = laboratoryFactory.laboratories(null, "ALL", null);
		assertNotNull(laboratories);
		assertTrue(laboratories.size()==2);
		assertTrue(laboratories.get(0).getStatus().equals(StatusEnum.ATIVO));
		assertTrue(laboratories.get(1).getStatus().equals(StatusEnum.INATIVO));
		
	}
	
	@Test
	public void laboratoriesAtivoAll() {
		Mockito.when(laboratoryRepository.findByStatus('A')).thenReturn(laboratoriesAtivoEntityMock());

		List<Laboratory> laboratories = laboratoryFactory.laboratories(null, "A", null);
		assertNotNull(laboratories);
		assertTrue(laboratories.size()==2);
		assertTrue(laboratories.get(0).getStatus().equals(StatusEnum.ATIVO));
		assertTrue(laboratories.get(1).getStatus().equals(StatusEnum.ATIVO));
		
	}
	
	@Test
	public void laboratoriesInativoAll() {
		Mockito.when(laboratoryRepository.findByStatus('I')).thenReturn(laboratoriesInativoEntityMock());

		List<Laboratory> laboratories = laboratoryFactory.laboratories(null, "I", null);
		assertNotNull(laboratories);
		assertTrue(laboratories.size()==2);
		assertTrue(laboratories.get(0).getStatus().equals(StatusEnum.INATIVO));
		assertTrue(laboratories.get(1).getStatus().equals(StatusEnum.INATIVO));
		
	}
	

	private List<LaboratoryEntity> laboratoriesEntityMock() {
		List<LaboratoryEntity> laboratoriesEntity = new ArrayList<LaboratoryEntity>();

		laboratoriesEntity.add(LaboratoryEntity.builder().id(1l).name("LAB A").status('A').build());
		laboratoriesEntity.add(LaboratoryEntity.builder().id(2l).name("LAB B").status('I').build());

		return laboratoriesEntity;
	}
	
	private List<LaboratoryEntity> laboratoriesAtivoEntityMock() {
		List<LaboratoryEntity> laboratoriesEntity = new ArrayList<LaboratoryEntity>();

		laboratoriesEntity.add(LaboratoryEntity.builder().id(1l).name("LAB A").status('A').build());
		laboratoriesEntity.add(LaboratoryEntity.builder().id(2l).name("LAB B").status('A').build());

		return laboratoriesEntity;
	}
	
	private List<LaboratoryEntity> laboratoriesInativoEntityMock() {
		List<LaboratoryEntity> laboratoriesEntity = new ArrayList<LaboratoryEntity>();

		laboratoriesEntity.add(LaboratoryEntity.builder().id(1l).name("LAB A").status('I').build());
		laboratoriesEntity.add(LaboratoryEntity.builder().id(2l).name("LAB B").status('I').build());

		return laboratoriesEntity;
	}
	
	private List<LaboratoryEntity> laboratoriesInativoEntityMockEmpty() {
		List<LaboratoryEntity> laboratoriesEntity = new ArrayList<LaboratoryEntity>();


		return laboratoriesEntity;
	}
}