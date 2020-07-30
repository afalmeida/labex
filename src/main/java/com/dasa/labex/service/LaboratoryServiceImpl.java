package com.dasa.labex.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dasa.labex.entity.LaboratoryEntity;
import com.dasa.labex.exception.FieldError;
import com.dasa.labex.exception.InternalServerException;
import com.dasa.labex.exception.NotFoundException;
import com.dasa.labex.exception.UnprocessableEntityException;
import com.dasa.labex.factory.LaboratoryFactory;
import com.dasa.labex.mapper.LaboratoryMapper;
import com.dasa.labex.model.Laboratory;
import com.dasa.labex.model.StatusEnum;
import com.dasa.labex.repository.LaboratoryRepository;

@Service
public class LaboratoryServiceImpl implements LaboratoryService {
	
	@Autowired
	private LaboratoryRepository laboratoryRepository;
	
	@Autowired
	private AddressService addressService;
	
	@Autowired
	private LaboratoryMapper laboratoryMapper;
	
	@Autowired
	private LaboratoryFactory laboratoryFactory;

	@Override
	public Laboratory laboratory(Long id) {
		try {
			laboratoryRepository.findById(id)
					.orElseThrow(() -> new NotFoundException());
			
			return laboratoryMapper.buildLaboratory(laboratoryRepository.findById(id).get());
			
		} catch (NotFoundException e) {
			throw e;
			
		} catch (Exception e) {
			throw new InternalServerException(e);
		}
	}

	@Override
	public List<Laboratory> laboratories(String name, String status) {
		List<Laboratory> laboratorys = laboratoryFactory.laboratories(name, status);
		
		if(laboratorys.isEmpty()) {
			throw new NotFoundException();
		}
		
		return laboratorys;
	}

	@Override
	public Laboratory save(Laboratory laboratory) {
		try {
			LaboratoryEntity laboratoryEntity = laboratoryRepository.save(
					laboratoryMapper.buildLaboratory(
							laboratory));
			
			addressService.saveByLaboratory(laboratoryEntity.getId(), laboratory.getAddress());
			
			return laboratoryMapper.buildLaboratory(laboratoryEntity);
			
		} catch (Exception e) {
			throw new InternalServerException(e);
		}
	}
	
	@Override
	public void update(Laboratory laboratory) {
		try {
			this.laboratory(laboratory.getId());
			LaboratoryEntity laboratoryEntity = laboratoryRepository.save(
					laboratoryMapper.buildLaboratory(
							laboratory));
			
			addressService.updateByLaboratory(laboratoryEntity.getId(), laboratory.getAddress());
			
			laboratoryMapper.buildLaboratory(laboratoryEntity);
			
		} catch (NotFoundException e) {
			throw e;
			
		} catch (Exception e) {
			throw new InternalServerException(e);
		}
	}

	@Override
	public void delete(Long id) {
		try {
			Laboratory laboratory = this.laboratory(id);
			
			if (laboratory.getStatus().equals(StatusEnum.ATIVO)) {
				addressService.deleteByLaboratory(id);
				laboratoryRepository.deleteById(id);
				
			} else {
				List<FieldError> validationErrors = new ArrayList<FieldError>();
				validationErrors.add(FieldError.builder()
						.name("status")
						.error("Impossivel remover um laboratorio com status INATIVO")
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