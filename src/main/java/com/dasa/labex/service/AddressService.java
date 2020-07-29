package com.dasa.labex.service;

import com.dasa.labex.model.Address;

public interface AddressService {
	
	public Address address(Long id);
	public void saveByLaboratory(Long laboratoryId,Address address);
	public void updateByLaboratory(Long laboratoryId,Address address);
	public void deleteByLaboratory(Long laboratoryId);

}