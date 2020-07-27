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
@Table(name="exame")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class ExamEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7373501474134270037L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name= "nome")
	private String name;
	
	@Column(name= "tipo")
	private String type;
	
	@Column(name= "status")
	private char status;

}