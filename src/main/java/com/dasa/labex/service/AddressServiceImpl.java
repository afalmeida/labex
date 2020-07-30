package com.dasa.labex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dasa.labex.entity.AddressEntity;
import com.dasa.labex.entity.LaboratoryAddressEntity;
import com.dasa.labex.entity.LaboratoryEntity;
import com.dasa.labex.exception.InternalServerException;
import com.dasa.labex.exception.NotFoundException;
import com.dasa.labex.mapper.AddressMapper;
import com.dasa.labex.mapper.LaboratoryMapper;
import com.dasa.labex.model.Address;
import com.dasa.labex.repository.AddressRepository;
import com.dasa.labex.repository.LaboratoryAddressRepository;

@Service
public class AddressServiceImpl implements AddressService {
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private LaboratoryAddressRepository laboratoryAddressRepository;
	
	@Autowired
	private AddressMapper addressMapper;
	
	@Autowired
	private LaboratoryMapper laboratoryMapper;

	@Override
	public Address address(Long laboratoryId) {		
		LaboratoryAddressEntity laboratoryAddressEntity = laboratoryAddressRepository.findByLaboratory(
				laboratoryMapper.buildLaboratory(laboratoryId));
		
		if (laboratoryAddressEntity == null) {
			throw new NotFoundException();
		}
		
		return addressMapper.buildAddress(laboratoryAddressEntity.getAddress(), laboratoryId);
	}

	@Override
	public void saveByLaboratory(Long laboratoryId, Address address) {
		try {
			AddressEntity addressEntity = addressRepository.save(addressMapper.buildAddressEntity(address));
			LaboratoryEntity laboratoryEntity = laboratoryMapper.buildLaboratory(laboratoryId);
			
			laboratoryAddressRepository.save(addressMapper.buildLaboratoryAddressEntity(addressEntity, laboratoryEntity));
			
		} catch (Exception e) {
			throw new InternalServerException(e);
		}
	}
	
	@Override
	public void updateByLaboratory(Long laboratoryId, Address address) {
		try {
			LaboratoryAddressEntity laboratoryAddressEntity = laboratoryAddressRepository.findByLaboratory(laboratoryMapper.buildLaboratory(laboratoryId));
			addressRepository.save(addressMapper.buildAddressEntity(address,laboratoryAddressEntity.getAddress().getId()));

		} catch (Exception e) {
			throw new InternalServerException(e);
		}
	}

	@Override
	public void deleteByLaboratory(Long laboratoryId) {
		try {
			LaboratoryAddressEntity laboratoryAddressEntity = laboratoryAddressRepository.findByLaboratory(laboratoryMapper.buildLaboratory(laboratoryId));
			laboratoryAddressRepository.deleteById(laboratoryAddressEntity.getId());
			addressRepository.deleteById(laboratoryAddressEntity.getAddress().getId());
			
		} catch (Exception e) {
			throw new InternalServerException(e);
		}
	}
}