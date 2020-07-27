package com.dasa.labex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dasa.labex.model.Exam;
import com.dasa.labex.service.ExamService;

@RestController
@RequestMapping(value = "/labex/exams", produces = MediaType.APPLICATION_JSON_VALUE)
public class ExamController {
	
	@Autowired
	private ExamService examService;
	
    @GetMapping()
    @ResponseBody
    public ResponseEntity<List<Exam>> exams(
    		@RequestParam(value = "name", required = false) String name) {
    	
        return new ResponseEntity<List<Exam>>(examService.exams(), HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Exam> exam(
    		@PathVariable("id") Long id) {
    	
        return new ResponseEntity<Exam>(examService.exam(id), HttpStatus.OK);
    }

}
