package com.dasa.labex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dasa.labex.model.Address;
import com.dasa.labex.service.AddressService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "LABORATORIES",  description = "recursos de laboratorios")
@RestController
@RequestMapping(value = "/labex/laboratories", produces = MediaType.APPLICATION_JSON_VALUE)
public class LaboratoryAddressController {
	
	@Autowired
	private AddressService addressService;
    
	@ApiOperation(value = "Consulta informações de endereço por ID laboratorio") 
    @GetMapping("/{id}/address")
    @ResponseBody
    public ResponseEntity<Address> address(
    		@ApiParam(value="Id do laboratorio")
    		@PathVariable("id") Long id) {
    	
        return new ResponseEntity<Address>(addressService.address(id), HttpStatus.OK);
    }
}