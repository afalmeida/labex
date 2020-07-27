package com.dasa.labex.model;

public enum TypeEnum {
	
	ANC("ANALISE CLINICA"),
	IMG("IMAGEM");
	
	private String description;
	
	private TypeEnum(String description) {
		this.description = description;
	}

	public String getDescription () {
		return description;
	}
}