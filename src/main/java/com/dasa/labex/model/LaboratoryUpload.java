package com.dasa.labex.model;

import com.opencsv.bean.CsvBindByName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
public class LaboratoryUpload {
	
	@CsvBindByName
	private String name;
	
	@CsvBindByName
	private String status;
	
	@CsvBindByName
	private String street;
	
	@CsvBindByName
	private String number;
	
	@CsvBindByName
	private String neighborhood;
	
	@CsvBindByName
	private String additionalInfo;
	
	@CsvBindByName
	private String zipCode;
	
	@CsvBindByName
	private String city;
	
	@CsvBindByName
	private String state;

}