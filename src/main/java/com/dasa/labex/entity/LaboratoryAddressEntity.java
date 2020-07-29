package com.dasa.labex.entity;

import java.io.Serializable;

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
@Table(name="laboratorioendereco")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class LaboratoryAddressEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5510963533293944156L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_laboratorio", referencedColumnName = "id")
	private LaboratoryEntity laboratory;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_endereco", referencedColumnName = "id")
	private AddressEntity address;
}