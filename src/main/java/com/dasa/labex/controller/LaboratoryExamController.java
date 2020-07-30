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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "LABORATORIES", description = "recursos de laboratorios")
@RestController
@RequestMapping(value = "/labex/laboratories", produces = MediaType.APPLICATION_JSON_VALUE)
public class LaboratoryExamController {
	
	@Autowired
	private LaboratoryExamService laboratoryExamService;
    
	@ApiOperation(value = "Consulta informações de uma lista exames por ID laboratorio") 
    @GetMapping("/{id}/exams")
    @ResponseBody
    public ResponseEntity <List<Exam>> exams(
    		@ApiParam(value="Id do laboratorio")
    		@PathVariable("id") Long id) {
    	
        return new ResponseEntity<List<Exam>>(laboratoryExamService.examsByLaboratory(id), HttpStatus.OK);
    }
    
	@ApiOperation(value = "Salvar informações de associação de um exame ao laboratorio") 
    @PostMapping("/{id}/exams")
    public ResponseEntity<String> save(
    		@ApiParam(value="Id do laboratorio")
    		@PathVariable("id") Long id,
    		
    		@ApiParam(value="Informacoes do exame")
    		@Valid @RequestBody LaboratoryExam laboratoryExam, BindingResult result) {
    	
    	laboratoryExam.setLaboratoryId(id);
    	
    	if (result.hasErrors()) {
    		 handleErrorBadRequestException(result);
    	}
    	
    	laboratoryExamService.save(laboratoryExam);
    	
    	return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
    }
    
	@ApiOperation(value = "Deletar informações de associação de um exame ao laboratorio") 
    @DeleteMapping("/{id}/exams/{examId}")
    public ResponseEntity<String> delete(
    		@ApiParam(value="Id do laboratorio")
    		@PathVariable("id") Long id,
    		
    		@ApiParam(value="Id do exame")
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