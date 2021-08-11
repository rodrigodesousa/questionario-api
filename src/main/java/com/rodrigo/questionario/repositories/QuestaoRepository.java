package com.rodrigo.questionario.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rodrigo.questionario.entities.Questao;

@Repository
public interface QuestaoRepository extends JpaRepository<Questao, Long> {

	@Transactional(readOnly=true)
	@Query("SELECT obj FROM Questao obj WHERE obj.questionario.id = :questionarioId")
	public List<Questao> findByQuestionario(@Param("questionarioId") Long questionario_id);
}
