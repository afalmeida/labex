package com.dasa.labex.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/resource-status", produces = MediaType.APPLICATION_JSON_VALUE)
public class ResourceStatusController {
	
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String,String>> getResourceStatus() {
    	Map<String,String> value = new HashMap<String,String>();
    	
    	value.put("resource-status", "OK");
    	
        return new ResponseEntity<Map<String,String>>(value, HttpStatus.OK);
    }
}