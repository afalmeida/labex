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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dasa.labex.exception.BadRequestException;
import com.dasa.labex.exception.FieldError;
import com.dasa.labex.model.Exam;
import com.dasa.labex.model.LaboratoryExam;
import com.dasa.labex.service.LaboratoryExamService;

@RestController
@RequestMapping(value = "/labex/laboratories", produces = MediaType.APPLICATION_JSON_VALUE)
public class LaboratoryExamController {
	
	@Autowired
	private LaboratoryExamService laboratoryExamService;
    
    @GetMapping("/{id}/exams")
    @ResponseBody
    public ResponseEntity <List<Exam>> exams(
    		@PathVariable("id") Long id) {
    	
        return new ResponseEntity<List<Exam>>(laboratoryExamService.examsByLaboratory(id), HttpStatus.OK);
    }
    
    @PostMapping("/{id}/exams")
    public ResponseEntity<String> save(
    		@PathVariable("id") Long id,
    		@Valid @RequestBody LaboratoryExam laboratoryExam, BindingResult result) {
    	
    	laboratoryExam.setLaboratoryId(id);
    	
    	if (result.hasErrors()) {
    		 handleErrorBadRequestException(result);
    	}
    	
    	laboratoryExamService.save(laboratoryExam);
    	
    	return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
    }
    
    @DeleteMapping("/{id}/exams/{examId}")
    public ResponseEntity<String> delete(
    		@PathVariable("id") Long id,
    		@PathVariable("examId") Long examId) {
    	
    	laboratoryExamService.delete(id,examId);
    	
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