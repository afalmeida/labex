package com.dasa.labex.service;

import java.util.List;

import com.dasa.labex.model.Laboratory;

public interface LaboratoryService {
	
	public Laboratory laboratory(Long id);
	public List<Laboratory> laboratories(String name, String status);
	public Laboratory save(Laboratory laboratory);
	public void update(Laboratory laboratory);
	public void delete(Long id);

}