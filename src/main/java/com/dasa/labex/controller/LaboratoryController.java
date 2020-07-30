package com.dasa.labex.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dasa.labex.exception.BadRequestException;
import com.dasa.labex.exception.FieldError;
import com.dasa.labex.model.Laboratory;
import com.dasa.labex.service.LaboratoryService;

@RestController
@RequestMapping(value = "/labex/laboratories", produces = MediaType.APPLICATION_JSON_VALUE)
public class LaboratoryController {
	
	@Autowired
	private LaboratoryService laboratoryService;
	
	private final static String DEFAULT_STATUS = "A";
	
    @GetMapping()
    @ResponseBody
    public ResponseEntity<List<Laboratory>> laboratories(
    		@RequestParam(value = "name", required = false) String name,
    		@RequestParam(defaultValue = DEFAULT_STATUS, value = "status", required = false) String status,
    		@RequestParam(value = "examName", required = false) String examName) {
    	
        return new ResponseEntity<List<Laboratory>>(laboratoryService.laboratories(name,status, examName), HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Laboratory> laboratory(
    		@PathVariable("id") Long id) {
    	
        return new ResponseEntity<Laboratory>(laboratoryService.laboratory(id), HttpStatus.OK);
    }
    
    @PostMapping()
    public ResponseEntity<Laboratory> save(
    		@Valid @RequestBody Laboratory laboratory, BindingResult result) {
    	
    	if (result.hasErrors()) {
    		 handleErrorBadRequestException(result);
    	}
    	
    	Laboratory newLaboratory = laboratoryService.save(laboratory);
    	
    	return new ResponseEntity<Laboratory>(newLaboratory,HttpStatus.CREATED);
    }
    
	@PostMapping(consumes = "multipart/form-data")
	public ResponseEntity<String> uploadLaboratories(@RequestParam("file") MultipartFile file) {

		laboratoryService.uploadLaboratories(file);
		return new ResponseEntity<String>(HttpStatus.CREATED); 
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<String> update(
    		@PathVariable("id") Long id,
    		@Valid @RequestBody Laboratory laboratory, BindingResult result) {
    	
    	laboratory.setId(id);
    	
    	if (result.hasErrors()) {
    		 handleErrorBadRequestException(result);
    	}
    	
    	laboratoryService.update(laboratory);
    	
    	return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(
    		@PathVariable("id") Long id) {
    	
    	laboratoryService.delete(id);
    	
    	return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
    }
    
    
    public void handleErrorBadRequestException(BindingResult bindingResult) throws BadRequestException {
		List<FieldError> validationErrors = new ArrayList<FieldError>();
		if (bindingResult.hasErrors()){

	        for (org.springframework.validation.FieldError error :bindingResult.getFieldErrors()){
	        	FieldError build = FieldError.builder().error(error.getDefaultMessage()).name(error.getField()).build();
	        	validationErrors.add(build);
	        }

            throw new BadRequestException(validationErrors);
        }
	}
}