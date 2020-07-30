package com.dasa.labex.model;



import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.opencsv.bean.CsvBindByName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper=false)
@Valid
@JsonInclude(Include.NON_EMPTY)
public class Exam extends RepresentationModel<Exam> {
	
	private Long id;
	
	@NotEmpty
	@CsvBindByName()
	private String name;
	
	@NotNull
	@CsvBindByName
	private TypeEnum type;
	
	@NotNull
	@CsvBindByName
	private StatusEnum status;

}