package com.dasa.labex.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dasa.labex.entity.LaboratoryEntity;

@Repository
public interface LaboratoryRepository extends JpaRepository<LaboratoryEntity, Long> {
	
	List<LaboratoryEntity> findByNameContains(String name);
	List<LaboratoryEntity> findByNameContainsAndStatus(String name, char status);
	List<LaboratoryEntity> findByStatus(char status);
	
	@Query(value = "SELECT l.* FROM "
			+ "laboratorioexame le, "
			+ "exame e, laboratorio l "
			+ "WHERE  le.id_exame = e.id AND "
			+ "le.id_laboratorio = l.id AND "
			+ "e.status= 'A' AND "
			+ "l.status= 'A' AND "
			+ "e.nome like %:examName ", nativeQuery = true)
	List<LaboratoryEntity>  findByExamName(@Param("examName") String examName);
}