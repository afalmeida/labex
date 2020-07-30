package com.dasa.labex.mapper;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import com.dasa.labex.controller.LaboratoryAddressController;
import com.dasa.labex.controller.LaboratoryController;
import com.dasa.labex.entity.LaboratoryEntity;
import com.dasa.labex.model.Laboratory;
import com.dasa.labex.model.LaboratoryUpload;
import com.dasa.labex.model.StatusEnum;

@Component
public class LaboratoryMapper {
	
	private LaboratoryMapper() {}
	
	@Autowired
	private AddressMapper addressMapper;
	
	public Laboratory buildLaboratory(LaboratoryEntity laboratoryEntity) {
		Link linkSelf = linkTo(methodOn(LaboratoryController.class).laboratory(laboratoryEntity.getId())).withSelfRel();
		Link linkLabAddress= linkTo(methodOn(LaboratoryAddressController.class).address(laboratoryEntity.getId())).withRel("address");

		return Laboratory.builder()
				.id(laboratoryEntity.getId())
				.name(laboratoryEntity.getName())
				.status(StatusEnum.status(laboratoryEntity.getStatus()))
				.build()
				.add(linkSelf)
				.add(linkLabAddress);
	}
	
	public Laboratory buildLaboratory(LaboratoryUpload laboratoryUpload) {
		return Laboratory.builder()
				.name(laboratoryUpload.getName())
				.status(StatusEnum.status(laboratoryUpload.getStatus()))
				.address(addressMapper.buildAddress(laboratoryUpload))
				.build();
	}
	
	public LaboratoryEntity buildLaboratory(Long laboratoryId) {
		return LaboratoryEntity.builder()
				.id(laboratoryId)
				.build();
	}
	
	public LaboratoryEntity buildLaboratory(Laboratory laboratory) {
		return LaboratoryEntity.builder()
				.id(laboratory.getId())
				.name(laboratory.getName())
				.status(laboratory.getStatus().getStatus().charAt(0))
				.build();
	}
	
	
	public List<Laboratory> buildLaboratories(List<LaboratoryEntity> llaboratoriesEntity) {
		List<Laboratory> laboratories = new ArrayList<Laboratory>();
		
		llaboratoriesEntity.forEach(laboratoryEntity -> {
			laboratories.add(this.buildLaboratory(laboratoryEntity));
		});
		
		return laboratories;
	}
}