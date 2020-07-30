package com.dasa.labex.model;



import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper=false)
@Valid
@JsonInclude(Include.NON_NULL)
public class Exam extends RepresentationModel<Exam> {
	
	private Long id;
	
	@NotEmpty
	private String name;
	
	@NotNull
	private TypeEnum type;
	
	@NotNull
	private StatusEnum status;

}