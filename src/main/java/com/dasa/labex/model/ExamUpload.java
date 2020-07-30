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
public class ExamUpload {
	
	@CsvBindByName
	private String name;
	

	@CsvBindByName
	private String type;

	@CsvBindByName
	private String status;

}