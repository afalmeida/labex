package com.dasa.labex.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

import com.dasa.labex.entity.LaboratoryEntity;
import com.dasa.labex.exception.NotFoundException;
import com.dasa.labex.exception.UnprocessableEntityException;
import com.dasa.labex.factory.LaboratoryFactory;
import com.dasa.labex.mapper.LaboratoryMapper;
import com.dasa.labex.model.Laboratory;
import com.dasa.labex.model.StatusEnum;
import com.dasa.labex.repository.LaboratoryRepository;

public class LaboratoryServiceTest {
	
	@InjectMocks
	private LaboratoryServiceImpl laboratoryService;
	
	@Mock
	private LaboratoryFactory laboratoryFactory;
	
	@Spy
	private LaboratoryMapper laboratoryMapper;
	
	@Mock
	private AddressService addressService;
	
	@Mock
	private LaboratoryRepository laboratoryRepository;
	
	private final Long LABORATORY_ID = 1l;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
        ReflectionTestUtils.setField(laboratoryService, "laboratoryMapper", laboratoryMapper);

	}
	
	@Test
	public void laboratory() {
		when(laboratoryRepository.findById(LABORATORY_ID)).thenReturn(laboratoriesEntityMock());

		Laboratory laboratory = laboratoryService.laboratory(LABORATORY_ID);
		
		assertNotNull(laboratory);
		assertTrue(laboratory.getStatus().equals(StatusEnum.ATIVO));
		assertTrue(laboratory.getLinks().hasSize(2));

	}
	
	@Test
	public void laboratoryNotFound() {
		when(laboratoryRepository.findById(LABORATORY_ID)).thenThrow(NotFoundException.class);

		assertThrows(NotFoundException.class, () -> {
			laboratoryService.laboratory(LABORATORY_ID);
		});
	}
	
	@Test
	public void laboratories() {
		when(laboratoryFactory.laboratories(null,"ALL")).thenReturn(laboratoriesMock());

		List<Laboratory> laboratories = laboratoryService.laboratories(null, "ALL");
		assertNotNull(laboratories);
		assertTrue(laboratories.size()==2);
		assertTrue(laboratories.get(0).getStatus().equals(StatusEnum.ATIVO));
		assertTrue(laboratories.get(1).getStatus().equals(StatusEnum.INATIVO));
		
	}
	
	@Test
	public void laboratorysNotFound() {
		when(laboratoryFactory.laboratories(null,"ALL")).thenReturn(new ArrayList<Laboratory>());

		assertThrows(NotFoundException.class, () -> {
			laboratoryService.laboratories(null, "ALL");
		});
		
	}
	
	@Test
	public void laboratorysStatusAtivo() {
		when(laboratoryFactory.laboratories(null,"A")).thenReturn(laboratoriesStatusAtivoMock());

		List<Laboratory> laboratorys = laboratoryService.laboratories(null,"A");
		assertNotNull(laboratorys);
		assertTrue(laboratorys.size()==2);
		assertFalse(laboratorys.stream()
				.filter(laboratoryFilter-> laboratoryFilter.getStatus().equals(StatusEnum.INATIVO))
				.findAny().isPresent());
		
	}
	
	@Test
	public void save() {
		Laboratory laboratoryInit = Laboratory.builder().name("LAB A").status(StatusEnum.ATIVO).build();
		LaboratoryEntity laboratoryEntity = LaboratoryEntity.builder().name("LAB A").status('A').build();
		
		when(laboratoryRepository.save(laboratoryEntity)).thenReturn(entityMock());
		
		Laboratory laboratory = laboratoryService.save(laboratoryInit);
		
		assertNotNull(laboratory);
		assertTrue(laboratory.getId().equals(110l));
	}
	
	@Test
	public void delete() {
		
		when(laboratoryRepository.findById(LABORATORY_ID)).thenReturn(laboratoriesEntityMock());
		
		laboratoryService.delete(LABORATORY_ID);

	}
	
	@Test()
	public void deleteStatusInativo() {
		when(laboratoryRepository.findById(LABORATORY_ID)).thenReturn(laboratoriesInativoEntityMock());
		
		try {
			 laboratoryService.delete(LABORATORY_ID);
			 
		} catch (Exception ex) {        
			if (ex instanceof UnprocessableEntityException) {
				UnprocessableEntityException httpClientErrorException = (UnprocessableEntityException) ex;
				
	            assertNotNull(httpClientErrorException);
	            assertTrue(httpClientErrorException.getValidationErrors().size()==1);
	            assertEquals("Existem erros de validacao no request enviado", ex.getMessage());
	            assertEquals("Impossivel remover um laboratorye com status INATIVO", httpClientErrorException.getValidationErrors().get(0).getError());
	            assertSame(UnprocessableEntityException.class, ex.getClass());
			}
    	}
	}
	

	private Optional<LaboratoryEntity> laboratoriesEntityMock() {
		return Optional.of(LaboratoryEntity.builder().id(1l).name("LAB A").status('A').build());

	}
	
	private Optional<LaboratoryEntity> laboratoriesInativoEntityMock() {
		return Optional.of(LaboratoryEntity.builder().id(1l).name("LAB A").status('I').build());

	}
	
	
	private List<Laboratory> laboratoriesMock() {
		List<Laboratory> laboratories = new ArrayList<Laboratory>();

		laboratories.add(Laboratory.builder().id(1l).name("LAB A").status(StatusEnum.ATIVO).build());
		laboratories.add(Laboratory.builder().id(1l).name("LAB B").status(StatusEnum.INATIVO).build());

		return laboratories;
	}
	
	private List<Laboratory> laboratoriesStatusAtivoMock() {
		List<Laboratory> laboratories = new ArrayList<Laboratory>();

		laboratories.add(Laboratory.builder().id(1l).name("LAB A").status(StatusEnum.ATIVO).build());
		laboratories.add(Laboratory.builder().id(1l).name("LAB B").status(StatusEnum.ATIVO).build());

		return laboratories;
	}
	
	private LaboratoryEntity entityMock() {
		return LaboratoryEntity.builder().id(110l).name("LAB A").status('A').build();

	}
}