package com.rodrigo.questionario.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rodrigo.questionario.entities.Resposta;

@Repository
public interface RespostaRepository extends JpaRepository<Resposta, Long> {
	
	// @Query("SELECT obj FROM Resposta obj INNER JOIN obj.questao q WHERE q.questionario.id = :questinarioId")
	// List<Resposta> findRespostaByQuestionario(@Param("questionarioId") Long questionario_id);
}
