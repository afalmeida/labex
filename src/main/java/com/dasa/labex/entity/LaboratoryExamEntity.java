package com.dasa.labex.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
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
@Table(name="laboratorioexame")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class LaboratoryExamEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8129804423946552280L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_laboratorio", referencedColumnName = "id")
	private LaboratoryEntity laboratory;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_exame", referencedColumnName = "id")
	private ExamEntity exam;
}