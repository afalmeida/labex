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
	
	public List<Laboratory> laboratories(String name, String status, String examName) {
		List<LaboratoryEntity> laboratories = null;
		StatusEnum statusEnum = StatusEnum.status(status);
		
		if(StringUtils.isNotBlank(examName)) {
			laboratories = laboratoryRepository.findByExamName(examName);
		} else if(StringUtils.isNotBlank(name)) {
			if (statusEnum.equals(StatusEnum.ALL)){
				laboratories = laboratoryRepository.findByNameContains(name);
			
			} else {
				laboratories = laboratoryRepository.findByNameContainsAndStatus(name,statusEnum.getStatus().charAt(0));
			}

		} else {
			if (statusEnum.equals(StatusEnum.ALL)){
				laboratories = laboratoryRepository.findAll();
			
			} else {
				laboratories = laboratoryRepository.findByStatus(statusEnum.getStatus().charAt(0));
			}
		}
		
		return laboratoryMapper.buildLaboratories(laboratories);
	}
}