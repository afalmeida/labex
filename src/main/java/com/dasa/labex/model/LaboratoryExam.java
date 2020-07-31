package com.dasa.labex.model;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

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
public class LaboratoryExam {
	
	private Long laboratoryId;
	
	@JsonProperty("id")
	@NotNull
	private Long examId;
	
	@JsonProperty("name")
	private String examName;

}