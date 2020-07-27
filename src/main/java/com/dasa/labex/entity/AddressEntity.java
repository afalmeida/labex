package com.dasa.labex.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="endereco")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class AddressEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4878288873987119579L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name= "rua")
	private String street;
	
	@Column(name= "numero")
	private String number;
	
	@Column(name= "bairro")
	private String neighborhood;
	
	@Column(name= "complemento")
	private String additionalInfo;
	
	@Column(name= "cep")
	private String zipCode;
	
	@Column(name= "cidade")
	private String city;
	
	@Column(name= "estado")
	private String state;

}