package com.dasa.labex.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="laboratorio")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class LaboratoryEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4895413062180851388L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name= "nome")
	private String name;
	
	@Column(name= "status")
	private char status;

}