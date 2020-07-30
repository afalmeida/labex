package com.dasa.labex.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.dasa.labex.model.Laboratory;

public interface LaboratoryService {
	
	public Laboratory laboratory(Long id);
	public List<Laboratory> laboratories(String name, String status, String examName);
	public Laboratory save(Laboratory laboratory);
	public void update(Laboratory laboratory);
	public void delete(Long id);
	public void uploadLaboratories(MultipartFile file);

}