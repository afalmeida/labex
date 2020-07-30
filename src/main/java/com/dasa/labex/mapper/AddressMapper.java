package com.dasa.labex.mapper;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import com.dasa.labex.controller.LaboratoryAddressController;
import com.dasa.labex.controller.LaboratoryController;
import com.dasa.labex.entity.AddressEntity;
import com.dasa.labex.entity.LaboratoryAddressEntity;
import com.dasa.labex.entity.LaboratoryEntity;
import com.dasa.labex.model.Address;
import com.dasa.labex.model.LaboratoryUpload;

@Component
public class AddressMapper {
	
	private AddressMapper() {}
	
	public Address buildAddress(LaboratoryUpload laboratoryUpload) {
		return Address.builder()
				.street(laboratoryUpload.getStreet())
				.number(laboratoryUpload.getNumber())
				.neighborhood(laboratoryUpload.getNeighborhood())
				.additionalInfo(laboratoryUpload.getAdditionalInfo())
				.zipCode(laboratoryUpload.getZipCode())
				.city(laboratoryUpload.getCity())
				.state(laboratoryUpload.getState())
				.build();
	}
	public AddressEntity buildAddressEntity (Address address) {		
		return AddressEntity.builder()
				.id(address.getId())
				.street(address.getStreet())
				.number(address.getNumber())
				.neighborhood(address.getNeighborhood())
				.additionalInfo(address.getAdditionalInfo())
				.zipCode(address.getZipCode())
				.city(address.getCity())
				.state(address.getState())
				.build();
	}
	
	public AddressEntity buildAddressEntity (Address address, Long id) {		
		return AddressEntity.builder()
				.id(id)
				.street(address.getStreet())
				.number(address.getNumber())
				.neighborhood(address.getNeighborhood())
				.additionalInfo(address.getAdditionalInfo())
				.zipCode(address.getZipCode())
				.city(address.getCity())
				.state(address.getState())
				.build();
	}
	
	public LaboratoryAddressEntity buildLaboratoryAddressEntity (AddressEntity addressEntity, LaboratoryEntity laboratoryEntity ) {		
		return LaboratoryAddressEntity.builder()
				.address(addressEntity)
				.laboratory(laboratoryEntity)
				.build();
	}

	public Address buildAddress (AddressEntity addressEntity, Long laboratoryId) {
		Link linkSelf = linkTo(methodOn(LaboratoryAddressController.class).address(laboratoryId)).withSelfRel();
		Link linkLaboratory= linkTo(methodOn(LaboratoryController.class).laboratory(laboratoryId)).withRel("laboratories");

		return Address.builder()
				.id(addressEntity.getId())
				.street(addressEntity.getStreet())
				.number(addressEntity.getNumber())
				.neighborhood(addressEntity.getNeighborhood())
				.additionalInfo(addressEntity.getAdditionalInfo())
				.zipCode(addressEntity.getZipCode())
				.city(addressEntity.getCity())
				.state(addressEntity.getState())
				.build()
				.add(linkSelf)
				.add(linkLaboratory);
	}
}