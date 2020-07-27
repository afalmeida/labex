package com.dasa.labex.model;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.hateoas.RepresentationModel;

import com.sun.istack.NotNull;

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
public class Exam extends RepresentationModel<Exam> {
	
	private Long id;
	
	@NotEmpty
	private String name;
	
	@NotNull
	private TypeEnum type;
	
	private StatusEnum status;

}