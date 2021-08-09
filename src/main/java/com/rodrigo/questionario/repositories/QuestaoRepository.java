package com.rodrigo.questionario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rodrigo.questionario.entities.Questao;

@Repository
public interface QuestaoRepository extends JpaRepository<Questao, Long> {

}
