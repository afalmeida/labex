package com.dasa.labex.model;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

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
@JsonInclude(Include.NON_EMPTY)
public class Address extends RepresentationModel<Address> {
	
	private Long id;
	
	@NotEmpty
	private String street;
	
	@NotEmpty
	private String number;
	
	@NotEmpty
	private String neighborhood;
	
	private String additionalInfo;
	
	@NotEmpty
	private String zipCode;
	
	@NotEmpty
	private String city;
	
	@NotEmpty
	private String state;

}