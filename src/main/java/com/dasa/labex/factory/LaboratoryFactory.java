package com.dasa.labex.factory;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dasa.labex.entity.LaboratoryEntity;
import com.dasa.labex.mapper.LaboratoryMapper;
import com.dasa.labex.model.Laboratory;
import com.dasa.labex.model.StatusEnum;
import com.dasa.labex.repository.LaboratoryRepository;

@Component
public class LaboratoryFactory {
	
	private LaboratoryFactory () {}
	
	@Autowired
	private LaboratoryRepository laboratoryRepository;
	
	@Autowired
	private LaboratoryMapper laboratoryMapper;
	
	public List<Laboratory> laboratories(String name, String status) {
		List<LaboratoryEntity> exams = null;
		StatusEnum statusEnum = StatusEnum.status(status);
		
		if(StringUtils.isNotBlank(name)) {
			if (statusEnum.equals(StatusEnum.ALL)){
				exams = laboratoryRepository.findByNameContains(name);
			
			} else {
				exams = laboratoryRepository.findByNameContainsAndStatus(name,statusEnum.getStatus().charAt(0));
			}

		} else {
			if (statusEnum.equals(StatusEnum.ALL)){
				exams = laboratoryRepository.findAll();
			
			} else {
				exams = laboratoryRepository.findByStatus(statusEnum.getStatus().charAt(0));
			}
		}
		
		return laboratoryMapper.buildLaboratories(exams);
	}
}