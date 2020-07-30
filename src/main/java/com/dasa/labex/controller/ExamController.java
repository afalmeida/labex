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
import com.dasa.labex.model.Exam;
import com.dasa.labex.service.ExamService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


@Api(tags = "EXAMS",  description = "recursos de exames")
@RestController
@RequestMapping(value = "/labex/exams", produces = MediaType.APPLICATION_JSON_VALUE)
public class ExamController {

	@Autowired
	private ExamService examService;

	private final static String DEFAULT_STATUS = "A";

	@ApiOperation(value = "Consulta informações de uma lista exames" ) 
	@GetMapping()
	@ResponseBody
	public ResponseEntity<List<Exam>> exams(
			@ApiParam(value="Nome do exame")
			@RequestParam(value = "name", required = false) String name,
			
			@ApiParam(value="Status do Exame, [A=ATIVO, I=INATIVO, ALL=TODOS]")
			@RequestParam(defaultValue = DEFAULT_STATUS, value = "status", required = false) String status) {

		return new ResponseEntity<List<Exam>>(examService.exams(name, status), HttpStatus.OK);
	}

	@ApiOperation(value = "Consulta informações por id do exame" ) 
	@GetMapping("/{id}")
	@ResponseBody
	public ResponseEntity<Exam> exam(
			@ApiParam(value="Id do exame")
			@PathVariable("id") Long id) {

		return new ResponseEntity<Exam>(examService.exam(id), HttpStatus.OK);
	}

	@ApiOperation(value = "Salvar informações de um exame") 
	@PostMapping()
	public ResponseEntity<Exam> save(
			@ApiParam(value="Body do exame")
			@Valid @RequestBody Exam exam, BindingResult result) {

		if (result.hasErrors()) {
			handleErrorBadRequestException(result);
		}

		Exam newExam = examService.save(exam);

		return new ResponseEntity<Exam>(newExam, HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Salvar informações de um exame em lote via CSV") 
	@PostMapping(consumes = "multipart/form-data")
	public ResponseEntity<String> uploadExams(
			@ApiParam(value="File dos exames, arquivo csv")
			@RequestParam("file") MultipartFile file) {

		examService.uploadExams(file);
		return new ResponseEntity<String>(HttpStatus.CREATED); 
    }


	@ApiOperation(value = "Atualizar informações de um exame") 
	@PutMapping("/{id}")
	public ResponseEntity<String> update(
			@ApiParam(value="Id do exame")
			@PathVariable("id") Long id, @Valid @RequestBody Exam exam,
			BindingResult result) {

		exam.setId(id);

		if (result.hasErrors()) {
			handleErrorBadRequestException(result);
		}

		examService.save(exam);

		return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "Deletar informações de um exame") 
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(
			@ApiParam(value="Id do exame")
			@PathVariable("id") Long id) {

		examService.delete(id);

		return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
	}

	public void handleErrorBadRequestException(BindingResult bindingResult) throws BadRequestException {
		List<FieldError> validationErrors = new ArrayList<FieldError>();
		if (bindingResult.hasErrors()) {

			for (org.springframework.validation.FieldError error : bindingResult.getFieldErrors()) {
				FieldError build = FieldError.builder().error(error.getDefaultMessage()).name(error.getField()).build();
				validationErrors.add(build);
			}

			throw new BadRequestException(validationErrors);
		}
	}
}