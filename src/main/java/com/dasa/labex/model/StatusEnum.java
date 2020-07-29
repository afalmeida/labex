package com.dasa.labex.model;

public enum StatusEnum {
	
	ATIVO("A"),
	INATIVO("I"),
	ALL("");
	
	private String status;
	
	private StatusEnum(String status) {
		this.status=status;
	}

	public String getStatus() {
		return status;
	}
	
	public static StatusEnum status(char status) {		
		for (StatusEnum statusEnum : StatusEnum.values()) {
			if(statusEnum.status.equalsIgnoreCase(String.valueOf(status))) {
				return statusEnum;
			}
		}
		
		return StatusEnum.ATIVO;
	}
	
	public static StatusEnum status(String status) {		
		for (StatusEnum statusEnum : StatusEnum.values()) {
			if(statusEnum.status.equalsIgnoreCase(String.valueOf(status))) {
				return statusEnum;
			}
		}
		
		return StatusEnum.ALL;
	}
}